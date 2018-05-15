package com.jns.questoesgp.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.jns.questoesgp.adapter.AnswerAdapter;
import com.jns.questoesgp.adapter.AppsAdapter;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {

	private static final String IMAGE_CONTENT_TYPE = "image/png";
	private static int REQUEST_PERMISSIONS = 1;

	public static final String CURRENT_PAGE = "currentPage";
	private static final String CORRECT = "Certo";
	private static final String WRONG = "Errado";

	private TextView tvAnswerCorrect;
	private TextView tvAnswerWrong;

	public static List<Answer> answers;
	public static List<Question> questions;
	public static Context context;
	public static int currentPage;
	public Answer answer;

	private int countCorrect;
	private int countWrong;

	private boolean hasPermissionAllowed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		init();

		AnswerAdapter adapter = new AnswerAdapter(this);
		adapter.setItems(answers);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.swapAdapter(adapter, true);

		Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
		btnSendEmail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (hasPermissionAllowed) {
					final File file = createPdf(answers);
					composeEmail(file);
				} else {
					checkPermission();
				}
			}
		});

		TextView tvStart = (TextView) findViewById(R.id.tvStart);
		tvStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		tvAnswerCorrect = (TextView) findViewById(R.id.tvAnswerCorrect);
		tvAnswerWrong = (TextView) findViewById(R.id.tvAnswerWrong);

		checkAnswer();

	}

	private void checkAnswer() {
		countCorrect = 0;
		countWrong = 0;

		for (Answer answer : answers) {
			if (answer.getAnswer().equals(answer.getCorrectAnswer())) {
				countCorrect++;
			} else {
				countWrong++;
			}
		}

		if (countCorrect == 0) {
			tvAnswerCorrect.setText("Você não acertou nenhuma questão");
		} else if (countCorrect == 1) {
			tvAnswerCorrect.setText("Você acertou " + countCorrect + " questão");
		} else {
			tvAnswerCorrect.setText("Você acertou " + countCorrect + " questões");
		}
		if (countWrong == 0) {
			tvAnswerWrong.setText("Você não errou nenhuma questão");
		} else if (countWrong == 1) {
			tvAnswerWrong.setText("Você errou " + countWrong + " questão");
		} else {
			tvAnswerWrong.setText("Você errou " + countWrong + " questões");
		}
	}

	private void init() {

		context = this;
		Bundle b = getIntent().getExtras();
		currentPage = -1;
		if (b != null) {
			currentPage = b.getInt(CURRENT_PAGE);
			currentPage++;
		}
		answers = SharedPreferenceUtil.getListAnswers(context);

	}

	private void composeEmail(final File file) {

		final Intent queryIntent = new Intent(Intent.ACTION_SENDTO);
		queryIntent.setData(Uri.parse("mailto:"));

		final PackageManager pm = getPackageManager();
		final List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(queryIntent, 0);

		AppsAdapter adapter = new AppsAdapter(this, resolveInfoList);

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				final ResolveInfo resolveInfo = resolveInfoList.get(which);

				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType(IMAGE_CONTENT_TYPE);
				shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
				shareIntent.setPackage(resolveInfo.activityInfo.packageName);
				startActivity(shareIntent);//Aqui se quiser fazer o handle se o usuario cancelou, utilizar startActivityForResult
			}
		});

		final AlertDialog dialog = builder.create();
		dialog.setTitle("Compartilhamento...");
		dialog.show();
	}

	private File createPdf(List<Answer> answers) {

		DisplayMetrics displaymetrics = new DisplayMetrics();
		AnswerActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;

		String appFolder = Environment.getExternalStorageDirectory().toString() + "/" + getString(R.string.app_name);
		new File(appFolder).mkdirs();

		File file = null;
		try {
			file = new File(appFolder, "resultado.pdf");
			file.createNewFile();
			FileOutputStream fOut = new FileOutputStream(file);

			PdfDocument document = new PdfDocument();
			PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(width, height, 1).create();
			PdfDocument.Page page = document.startPage(pageInfo);

			Canvas canvas = page.getCanvas();

			writeOnDocument(answers, canvas);

			document.finishPage(page);
			document.writeTo(fOut);
			document.close();

		} catch (IOException e) {
			Log.i("error", e.getLocalizedMessage());
		}

		return file;
	}

	private void writeOnDocument(List<Answer> answers, Canvas canvas) {
		Paint pQuestion = new Paint();
		pQuestion.setTextSize(52f);
		pQuestion.setTypeface(Typeface.DEFAULT_BOLD);

		Paint pAnswer = new Paint();
		pAnswer.setTextSize(48f);

		//Adding space and title for the page
		Paint pTitle = new Paint();
		pTitle.setTypeface(Typeface.DEFAULT_BOLD);
		pTitle.setTextSize(65);
		canvas.drawText("Resultado", 500, 100, pTitle);

		int startMarginPage = 50;
		int lineHeight = 200;
		for (int i = 0; i < answers.size(); i++) {
			final Answer answer = answers.get(i);

			//Question //TODO add logic here to breaklines for Long Titles.
			canvas.drawText(answer.getQuestion(), startMarginPage, lineHeight, pQuestion);

			//Correct answer
			lineHeight += 60;
			pAnswer.setColor(Color.GREEN);
			canvas.drawText(answer.getCorrectAnswer(), startMarginPage, lineHeight, pAnswer);

			//Wrong answer
			if (!answer.getAnswer().equals(answer.getCorrectAnswer())) {
				lineHeight += startMarginPage;
				pAnswer.setColor(Color.RED);
				canvas.drawText(answer.getAnswer(), startMarginPage, lineHeight, pAnswer);
			}

			//Separator
			canvas.drawText("", startMarginPage, lineHeight, pQuestion);
			lineHeight += 100;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//        getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void checkPermission() {
		if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
				(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

			if ((ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
			} else {
				ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.READ_EXTERNAL_STORAGE },
						REQUEST_PERMISSIONS);

			}

			if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
			} else {
				ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
						REQUEST_PERMISSIONS);

			}
		} else {
			hasPermissionAllowed = true;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == REQUEST_PERMISSIONS) {

			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

				hasPermissionAllowed = true;

			} else {
				Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

			}
		}
	}
}
