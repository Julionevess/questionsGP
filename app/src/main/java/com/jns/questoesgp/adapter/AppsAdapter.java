package com.jns.questoesgp.adapter;


import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jns.questoesgp.questoesgp.R;

import java.util.List;

public class AppsAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private List<ResolveInfo> mApps;

	public AppsAdapter(Context context, List<ResolveInfo> mApps) {

		this.inflater = LayoutInflater.from(context);
		this.context = context;
		this.mApps = mApps;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHandler handler;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_app_view, null);
			handler = new ViewHandler();
			handler.lable = (TextView) convertView.findViewById(R.id.textViewLable);
			handler.iconImage = (ImageView) convertView.findViewById(R.id.imageViewIcon);
			convertView.setTag(handler);
		} else {
			handler = (ViewHandler) convertView.getTag();
		}
		ResolveInfo info = this.mApps.get(position);
		handler.iconImage.setImageDrawable(info.loadIcon(context.getPackageManager()));
		handler.lable.setText(info.loadLabel(context.getPackageManager()));

		return convertView;

	}

	public final int getCount() {
		return mApps.size();
	}

	public final Object getItem(int position) {
		return mApps.get(position);
	}

	public final long getItemId(int position) {
		return position;
	}

	class ViewHandler {
		TextView lable;
		ImageView iconImage;
	}

}