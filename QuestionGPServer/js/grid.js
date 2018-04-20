

var question;
var correctAnswer;
var incorrectOptionOne;
var incorrectOptionTwo;
var incorrectOptionThree;
var incorrectOptionFour;

function sendForm(){
	
	question = $('#question').val();
	correctAnswer = $('#correctAnswer').val();
	incorrectOptionOne = $('#incorrectOptionOne').val();
	incorrectOptionTwo = $('#incorrectOptionTwo').val();
	incorrectOptionThree = $('#incorrectOptionThree').val();
	incorrectOptionFour = $('#incorrectOptionFour').val();
	
	if (question.trim() == "" ||
	correctAnswer.trim() == "" ||
	incorrectOptionOne.trim() == "" ||
	incorrectOptionTwo.trim() == "" ||
	incorrectOptionThree.trim() == "" ||
	incorrectOptionFour.trim() == ""){		
		$('#alert').attr('class', ('alert alert-danger alert-dismissible fade show visible center'));
		
	}else{		
		$('#formQuestion').submit();		
		var object = setObject();
		//updateJson();
	}	
	
}

function setObject(){

	var questionAndAnswer = {
		question:question,
		correctAnswer:correctAnswer,
		incorrectOptionOne:incorrectOptionOne,
		incorrectOptionThree:incorrectOptionThree,
		incorrectOptionFour:incorrectOptionFour
	};

	var jsonQA = JSON.stringify(questionAndAnswer);
	return jsonQA;

}

function updateJson(){
	
		try {
			// Create an instance of StreamReader to read from a file.
			sr = new StreamReader("TestFile.txt");
			// Read and display lines from the file until the end of the file is reached.
			line = sr.ReadLine();
			while (line != null) {
				print(line);
				line = sr.ReadLine();
			}
			sr.Close();
		}
		catch (e) {
			// Let the user know what went wrong.
			//print("The file could not be read:");
			//print(e.Message);
		}
	
}

$(function() {
	$( '#formQuestion' ).find( 'input' ).click(function() {
		
		$('#alert').attr('class', ('invisible'));
		
	});
});

function init(){

	var txt = '';
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
	  if(xmlhttp.status == 200 && xmlhttp.readyState == 4){
		txt = xmlhttp.responseText;
	  }
	};
	xmlhttp.open("GET","abc.txt",true);
	alert(xmlhttp.val);
	xmlhttp.send();

/*
	var txtFile = "c:/test.txt";
	var file = File.ReadLine(txtFile,);
	var str = "My string of text";
	
	file.open("w"); // open file with write access
	file.writeln("First line of text");
	file.writeln("Second line of text " + str);
	file.write(str);
	file.close();*/
}






