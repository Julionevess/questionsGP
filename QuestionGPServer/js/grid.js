

var question;
var correctAnswer;
var incorrectOptionOne;
var incorrectOptionTwo;
var incorrectOptionThree;
var incorrectOptionFour;

$(document).ready(function(){
	$("#search").on("keyup", function() {
	  var value = $(this).val().toLowerCase();
	  $("#questionTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  });
	});
  });

var json = "[{\"id\":\"1\",\"question\":\"Qual o seu nome?\",\"answer\":\"Jose\",\"optionOne\":\"Maria\",\"optionTwo\":\"Fernando\",\"optionThree\":\"Joaquim\",\"optionFour\":\"Paulo\",\"category\":\"Categoria 1\"},{\"id\":\"2\",\"question\":\"Qual a sua idade?\",\"answer\":\"18\",\"optionOne\":\"17\",\"optionTwo\":\"22\",\"optionThree\":\"25\",\"optionFour\":\"33\",\"category\":\"Categoria 1\"},{\"id\":\"3\",\"question\":\"Qual o seu telefone?\",\"answer\":\"81 9.9898-9898\",\"optionOne\":\"11 9.9898-9898\",\"optionTwo\":\"88 9.9898-9898\",\"optionThree\":\"81 9.9898-7898\",\"optionFour\":\"81 9.9898-9897\",\"category\":\"Categoria 1\"}]"

var jsonObject = JSON.parse(json);

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

function loadTable(){

	jQuery.each(jsonObject, function (index, value) {
        if (typeof value == 'object') {
			var row = $('<tr></tr>');
			$('#questionTable').append(row);
			$('<td></td>').attr({ class: ["col-20"]}).text(value.category).appendTo(row);
			$('<td></td>').attr({ class: ["col-80"]}).text(value.question).appendTo(row);
            
        }
        else {
             alert(index + "   :   " + value);
        }
	});
	/*
	for (var prop in jsonObject) {
		alert("id:" + prop.id);
		alert("Question:" + prop.question);
	}

/*
	for(i=0; i<3; i++){
		var row = $('<tr></tr>');
		$('#questionTable').append(row);
		$('<td></td>').attr({ class: ["col-20"]}).text("Cat" + i).appendTo(row);
		$('<td></td>').attr({ class: ["col-80"]}).text("Pergunta" + i).appendTo(row);
		
	}*/
}

function init(){
	loadTable();
/*
	var txt = '';
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
	  if(xmlhttp.status == 200 && xmlhttp.readyState == 4){
		txt = xmlhttp.responseText;
	  }
	};
	xmlhttp.open("GET","abc.txt",true);
	//alert(xmlhttp.val);
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






