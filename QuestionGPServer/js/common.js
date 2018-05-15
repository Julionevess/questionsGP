var question;
var correctAnswer;
var incorrectOptionOne;
var incorrectOptionTwo;
var incorrectOptionThree;
var incorrectOptionFour;

var jsonObjectSelected;

var TIPO_ERROR = "Erro";
var TIPO_SUCCESS = "Sucesso";

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

function showAlert(tipo, msg){
	if (tipo == TIPO_ERROR){
		$('#alert').attr('class', ('alert alert-danger alert-dismissible fade show visible center')).text(msg);
	}else if (tipo == TIPO_SUCCESS){
		alert($('#alert'));
		$('#alert').attr('class', ('alert alert-success alert-dismissible fade show visible center')).text(msg);

		//TODO precisa submeter a página
	}
}

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
			showAlert(TIPO_ERROR, "ATENÇÃO! Todos os campos do formulário são obrigatórios.");		
	}else{		
		$('#formQuestion').submit();		
		var object = setObject();
		//updateJson();
	}		
}

	$('#selectCategory').change( function(e) {
		var selSelection = $("#selectCategory").val();
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
			$('<td></td>').attr({ class: ["col-0"]}).text(value.id).appendTo(row).hide();
			$('<td></td>').attr({ class: ["col-20"]}).text(value.category).appendTo(row);
			$('<td></td>').attr({ class: ["col-70"]}).text(value.question).appendTo(row);
						
			var tdAction = $('<td></td>').attr({ class: ["col-10"]});
			var img = '<img id="imgView" src="images/view.png" alt="Visualizar informações detalhadas"> <img id="imgEdit" src="images/edit.png" alt="Editar informações">';
			
			tdAction.html(img).appendTo(row);			
        }
        else {
             alert(index + "   :   " + value);
        }
	});		
}

function actionModal(){

	if ($("#buttonAction").text() == "Salvar"){
		$('#myModal').modal('toggle');
		showAlert(TIPO_SUCCESS, "As informações foram salvas com sucesso.");
	}else{
		var questionModal = $("#questionModal");
		var correctAnswerModal = $("#correctAnswerModal");
		var incorrectOptionOneModal = $("#incorrectOptionOneModal");
		var incorrectOptionTwoModal = $("#incorrectOptionTwoModal");
		var incorrectOptionThreeModal = $("#incorrectOptionThreeModal");
		var incorrectOptionFourModal = $("#incorrectOptionFourModal");
		
		questionModal.prop( "disabled", false );
		correctAnswerModal.prop( "disabled", false );
		incorrectOptionOneModal.prop( "disabled", false );
		incorrectOptionTwoModal.prop( "disabled", false );
		incorrectOptionThreeModal.prop( "disabled", false );
		incorrectOptionFourModal.prop( "disabled", false );

		$("#buttonAction").text("Salvar")
	}
	

}
function openModal(that){
	var idSelected = that.parent().parent().find('td:first').text();	
	for (var i = 0 ; i < jsonObject.length ; i++){
		if (jsonObject[i].id == idSelected){
			jsonObjectSelected = jsonObject[i];				
			$('#myModal').modal('toggle');
			break;

		}
	}

	var questionModal = $("#questionModal");
	var correctAnswerModal = $("#correctAnswerModal");
	var incorrectOptionOneModal = $("#incorrectOptionOneModal");
	var incorrectOptionTwoModal = $("#incorrectOptionTwoModal");
	var incorrectOptionThreeModal = $("#incorrectOptionThreeModal");
	var incorrectOptionFourModal = $("#incorrectOptionFourModal");
	
	questionModal.val(jsonObjectSelected.question);
	correctAnswerModal.val(jsonObjectSelected.answer);
	incorrectOptionOneModal.val(jsonObjectSelected.optionOne);
	incorrectOptionTwoModal.val(jsonObjectSelected.optionTwo);
	incorrectOptionThreeModal.val(jsonObjectSelected.optionThree);
	incorrectOptionFourModal.val(jsonObjectSelected.optionFour);

	if (that.attr("id") == "imgEdit"){
		questionModal.prop( "disabled", false );
		correctAnswerModal.prop( "disabled", false );
		incorrectOptionOneModal.prop( "disabled", false );
		incorrectOptionTwoModal.prop( "disabled", false );
		incorrectOptionThreeModal.prop( "disabled", false );
		incorrectOptionFourModal.prop( "disabled", false );

		$("#buttonAction").text("Salvar")
	}else{
		questionModal.prop( "disabled", true );
		correctAnswerModal.prop( "disabled", true );
		incorrectOptionOneModal.prop( "disabled", true );
		incorrectOptionTwoModal.prop( "disabled", true );
		incorrectOptionThreeModal.prop( "disabled", true );
		incorrectOptionFourModal.prop( "disabled", true );
		$("#buttonAction").text("Alterar")
	}
}

function init(){
	loadTable();
	
	$('#imgView').click( function(){
		openModal($(this));
	});	

	$('#imgEdit').click( function(){
		openModal($(this));
	});
}






