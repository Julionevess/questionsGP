var gapContentOne = 1, gapContentTwo = 1, gapContentThree = 1;
var gapOne = 0, gapTwo = 0, gapThree = 0;
var idOrigin, idTarget;
var gapDisponivel = "S";

var questionAndAnswer1 = {q:"q1",r:"Projetos possuem objetivos definidos. O escopoo é elaborado progressivamente durante o ciclo de vida do projeto."};
var questionAndAnswer2 = {q:"q2",r:"Os programas possuem um escopo maior e fornecem benefícios mais significativos."};
var questionAndAnswer3 = {q:"q3",r:"Os portfólios possuem um escopo de negócios que muda com os objetivos estratégicos da organização."};
var questionAndAnswer4 = {q:"q4",r:"Os gerentes de projetos esperam mudanç e implementam processos para manter as mudanças gerenciadas e controladas."};
var questionAndAnswer5 = {q:"q5",r:"Os gerentes de programas devem esperar mudanças tanto de dentro como de fora do programa e estar preparados para gerenciá-las."};
var questionAndAnswer6 = {q:"q6",r:"Os gerentes de portfólios monitoram continuamente as mudanças ocorridas no ambiente mais amplo da organização."};
var questionAndAnswer7 = {q:"q7",r:"Os gerentes de projetos elaboram progressivamente planos detalhados no decorrer do ciclo de vida do projeto a partir de informações de alto nível."};
var questionAndAnswer8 = {q:"q8",r:"Os Gerentes de programas desenvolvem o plano geral do programa e criam planos de alto nível para orientar o planejamento detalhado no nível de componentes."};
var questionAndAnswer9 = {q:"q9",r:"Os gerentes de portfólios criam e mantém comunicação e processos necessários ao portfólio global."};
var questionAndAnswer10 = {q:"q10",r:"Os gerentes de projetos gerenciam a equipe do projeto para atender aos objetivos do projeto."};
var questionAndAnswer11 = {q:"q11",r:"Os gerentes de programas gerenciam a equipe do programa e os gerentes de projetos; eles proveem visão e liderança global."};
var questionAndAnswer12 = {q:"q12",r:"Os gerentes de portfólios podem gerenciar ou coordenar a equipe de gerenciamento de portfólios."};
var questionAndAnswer13 = {q:"q13",r:"O sucesso é medido pela qualidade do produto e do projeto, pontualidade, conformidade orçamentária e grau de satisfação do cliente."};
var questionAndAnswer14 = {q:"q14",r:"O sucesso é medido pelo grau em que o programa atende às necessidades e aos benefícios para os quais foi executado."};
var questionAndAnswer15 = {q:"q15",r:"O sucesso é medido em termos do desempenho agregado dos componentes do portfólio."};
var questionAndAnswer16 = {q:"q16",r:"Os gerentes de projetos monitoram e controlam o trabalho de elaboração dos produtos, serviços ou resultados para os quais o projeto foi realizado."};
var questionAndAnswer17 = {q:"q17",r:"Os gerentes de programas monitoram o progresso dos componentes do programa para garantir que os objetivos, cronogramas, orçamento e benefícios globais do mesmo sejam atendidos."};
var questionAndAnswer18 = {q:"q18",r:"Os gerentes de portfólios monitoram o desempenho e os indicadores de valor agregado da carteira."};


var questionsAndAnswers  = new Array();
questionsAndAnswers["0"] = questionAndAnswer1;
questionsAndAnswers["1"] = questionAndAnswer2;
questionsAndAnswers["2"] = questionAndAnswer3;
questionsAndAnswers["3"] = questionAndAnswer4;
questionsAndAnswers["4"] = questionAndAnswer5;
questionsAndAnswers["5"] = questionAndAnswer6;
questionsAndAnswers["6"] = questionAndAnswer7;
questionsAndAnswers["7"] = questionAndAnswer8;
questionsAndAnswers["8"] = questionAndAnswer9;
questionsAndAnswers["9"] = questionAndAnswer10;
questionsAndAnswers["10"] = questionAndAnswer11;
questionsAndAnswers["11"] = questionAndAnswer12;
questionsAndAnswers["12"] = questionAndAnswer13;
questionsAndAnswers["13"] = questionAndAnswer14;
questionsAndAnswers["14"] = questionAndAnswer15;
questionsAndAnswers["15"] = questionAndAnswer16;
questionsAndAnswers["16"] = questionAndAnswer17;
questionsAndAnswers["17"] = questionAndAnswer18;


var answers  = new Array();
var gaps  = new Array();

var linhas = new Array(3);
var colunas = new Array(new Array(3),new Array(3),new Array(3));

function load(){
	document.getElementById("contentOne").innerText = questionAndAnswer1.r;
	document.getElementById("contentTwo").innerText = questionAndAnswer2.r;
	document.getElementById("contentThree").innerText = questionAndAnswer3.r;
	document.getElementById("contentFour").innerText = questionAndAnswer4.r;
	document.getElementById("contentFive").innerText = questionAndAnswer5.r;
	document.getElementById("contentSix").innerText = questionAndAnswer6.r;
	document.getElementById("contentSeven").innerText = questionAndAnswer7.r;
	document.getElementById("contentEight").innerText = questionAndAnswer8.r;
	document.getElementById("contentNine").innerText = questionAndAnswer9.r;
	document.getElementById("contentTen").innerText = questionAndAnswer10.r;
	document.getElementById("contentEleven").innerText = questionAndAnswer11.r;
	document.getElementById("contentTwelve").innerText = questionAndAnswer12.r;
	document.getElementById("contentThirteen").innerText = questionAndAnswer13.r;
	document.getElementById("contentFourteen").innerText = questionAndAnswer14.r;
	document.getElementById("contentFifteen").innerText = questionAndAnswer15.r;
	document.getElementById("contentSixteen").innerText = questionAndAnswer16.r;
	document.getElementById("contentSeventeen").innerText = questionAndAnswer17.r;
	document.getElementById("contentEighteen").innerText = questionAndAnswer18.r;
}

function correct(){

answers[0] = document.getElementById("gapOne").innerText.trim();
gaps[0] = document.getElementById("gapOne").id;
answers[1] = document.getElementById("gapTwo").innerText.trim();
gaps[1] = document.getElementById("gapTwo").id;
answers[2] = document.getElementById("gapThree").innerText.trim();
gaps[2] = document.getElementById("gapThree").id;
answers[3] = document.getElementById("gapFour").innerText.trim();
gaps[3] = document.getElementById("gapFour").id;
answers[4] = document.getElementById("gapFive").innerText.trim();
gaps[4] = document.getElementById("gapFive").id;
answers[5] = document.getElementById("gapSix").innerText.trim();
gaps[5] = document.getElementById("gapSix").id;
answers[6] = document.getElementById("gapSeven").innerText.trim();
gaps[6] = document.getElementById("gapSeven").id;
answers[7] = document.getElementById("gapEight").innerText.trim();
gaps[7] = document.getElementById("gapEight").id;
answers[8] = document.getElementById("gapNine").innerText.trim();

gaps[8] = document.getElementById("gapNine").id;
gaps[9] = document.getElementById("gapTen").id;
gaps[10] = document.getElementById("gapEleven").id;
gaps[11] = document.getElementById("gapTwelve").id;
gaps[12] = document.getElementById("gapThirteen").id;
gaps[13] = document.getElementById("gapFourteen").id;
gaps[14] = document.getElementById("gapFifteen").id;
gaps[15] = document.getElementById("gapSixteen").id;
gaps[16] = document.getElementById("gapSeventeen").id;
gaps[17] = document.getElementById("gapEighteen").id;

var result = "";
var answer = "";
var countError = 0;
var countHit = 0;
var msgError;
var msgHit;

	for (var i = 0 ; i < questionsAndAnswers.length ; i++){
		result = result + answers[i] + " = " + questionsAndAnswers[i].r ;
		answer = document.getElementById(gaps[i]).innerText.trim();		
		if (answer == questionsAndAnswers[i].r){
			document.getElementById(gaps[i]).className = "col-sm  lacunaOk";			
			countHit = countHit + 1;			
		}else{
			document.getElementById(gaps[i]).className = "col-sm  lacunaErro";
			countError = countError + 1;
		}
	}
	
	msgError = "Quantidade de erros: "  + countError;
	msgHit = "Quantidade de acertos: " + countHit;
	 
	 document.getElementById("msgError").className = "alert alert-danger visible";
	 document.getElementById("msgHit").className = "alert alert-success visible";
	  
	document.getElementById("msgError").innerText = msgError;
	document.getElementById("msgHit").innerText = msgHit;
	
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("data", ev.target.id);
	idOrigin = ev.target.parentElement.id;
	
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("data");
	idTarget = ev.target.id;
	var content = ev.target.innerHTML.trim().length == 0;
		
	if (content){
		ev.target.appendChild(document.getElementById(data));
		idTarget = ev.target.id;
	}
	
}