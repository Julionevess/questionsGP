var question;
var correctAnswer;
var incorrectOptionOne;
var incorrectOptionTwo;
var incorrectOptionThree;
var incorrectOptionFour;

var jsonObjectSelected;

$(document).ready(function(){
	$("#search").on("keyup", function() {
	  var value = $(this).val().toLowerCase();
	  $("#questionTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  });
	});
  });

var json = "[{\"id\":\"1\",\"question\":\"O que é Escopo?\",\"answer\":\"Jose\",\"optionOne\":\"Maria\",\"optionTwo\":\"Fernando\",\"optionThree\":\"Joaquim\",\"optionFour\":\"Paulo\",\"category\":\"Integração\"},{\"id\":\"2\",\"question\":\"Qual a sua idade?\",\"answer\":\"18\",\"optionOne\":\"17\",\"optionTwo\":\"22\",\"optionThree\":\"25\",\"optionFour\":\"33\",\"category\":\"Escopo\"},{\"id\":\"3\",\"question\":\"Qual o seu telefone?\",\"answer\":\"81 9.9898-9898\",\"optionOne\":\"11 9.9898-9898\",\"optionTwo\":\"88 9.9898-9898\",\"optionThree\":\"81 9.9898-7898\",\"optionFour\":\"81 9.9898-9897\",\"category\":\"Categoria 1\"}]"

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

	$('#selectCategory').change( function(e) {
		var selSelection = $("#selectCategory").val();
		  alert(selSelection);
		if(selSelection == "Todas as categorias"){
			$("#questionTable tr").show();
		} else {
			$("#questionTable tr").show().filter(function(index){
				return $("td:eq(1)", this).html().indexOf(selSelection) == -1;
			}).hide();
		}
	});

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

function loadQuestion(){
	alert("chamou");
	
	for (var i = 0 ; i < jsonObject.length ; i++){
		if (jsonObject[i].id == idSelected){
			jsonObjectSelected = jsonObject[i];
		}
	}
	


	

}



function loadTable(){

	jQuery.each(jsonObject, function (index, value) {
        if (typeof value == 'object') {
			var row = $('<tr></tr>');
			$('#questionTable').append(row);
			$('<td></td>').attr({ class: ["col-0 invisible"]}).text(value.id).appendTo(row).hide();
			$('<td></td>').attr({ class: ["col-20"]}).text(value.category).appendTo(row);
			$('<td></td>').attr({ class: ["col-79"]}).text(value.question).appendTo(row);
            
        }
        else {
             alert(index + "   :   " + value);
        }
	});
	
	
}

function init(){
	loadTable();
	
	$('#questionTable').find('tr').click( function(){
		var idSelected = $(this).find('td:first').text();
		for (var i = 0 ; i < jsonObject.length ; i++){
			if (jsonObject[i].id == idSelected){
				jsonObjectSelected = jsonObject[i];				
				$('#myModal').modal('toggle');
				break;

			}
		}

		$("#questionModal").val(jsonObjectSelected.question);
		$("#correctAnswerModal").val(jsonObjectSelected.answer);
		$("#incorrectOptionOneModal").val(jsonObjectSelected.optionOne);
		$("#incorrectOptionTwoModal").val(jsonObjectSelected.optionTwo);
		$("#incorrectOptionThreeModal").val(jsonObjectSelected.optionThree);
		$("#incorrectOptionFourModal").val(jsonObjectSelected.optionFour);

	});
}






