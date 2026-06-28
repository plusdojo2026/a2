<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- グラフ作成用Chart.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"></script>
<script  src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
<title>マメッスル|成長記録</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet" href="/a2/css/mypage.css">
<link rel="stylesheet" href="/a2/css/graph.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
<link rel="icon" href="/a2/img/mame.png" type="image/png">
 <!-- <style>
            *{
                outline: 1px solid #000000;
            }
        </style>    --> 
</head>
<body>
<div class="app-wrapper">
<!--ヘッダーここから-->
<div class="solid"></div>
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="/a2/HomeServlet" class="logo">
		<img class="logo" src='img/logo.png'>
	</a>
    <a href="/a2/InfoServlet" class="bean-info">
    	<img class="info" src='img/info.png'>
    </a>
</header>
<!--ヘッダーここまで-->
<!--　メインここから　-->
<main>
<div class ="graph">

<nav id = "todaycomment">
豆のひとこと
</nav>
<div class="comment-box">
<img class="mamegraph" src='img/graph.png'>
<nav class="daycomment" id= "daycomment">
<c:forEach var="Sw" items="${SelectWord}">
${Sw.word_of_day}
</c:forEach>
</nav>
</div>

<ul id = "select">
<li>
<div id ="selectitem">
	<label for="itemSelect">トレーニング項目</label><select id="itemSelect"  onchange="changeItem()">
		 <c:forEach var="gi" items="${WeekGraph}">
		<option value="${gi.key}"><c:out value="${gi.key}"/>
		 </option>
	 	</c:forEach> 
	</select><br>
</div> 
</li>
<li>
<div id = "selectweight">
<label for="weightSelect">重量</label><select id="weightSelect" onchange="changeWeight()">
</select><br>
</div>
</li>
</ul>


<!-- グラフを表示する場所 -->
<div class = "canvas-container">
<canvas id="lineChart" width="400" height="300"></canvas>
</div>


<!-- 表示変更 -->
<ul id="dayschange">
<li>
<button id ="wcbutton" class ="cbutton" onclick="updateChart(weekData)" >直近7回の記録</button>
</li>
<li>
<button id ="mcbutton" class ="cbutton" onclick="updateChart(monthData)" >直近30回の記録</button>
</li>
</ul>
</div>
</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer><!--nowpageはそのページに着けてほしいです。-->
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up nowpage"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user"></i></a>
</nav>
</footer>
<!--　フッターここまで　-->
<script>

'use strict'

//ヘッダー日付表示用
 window.onload = function(){
const now =new Date();
const year = now.getFullYear();
const month= now.getMonth()+1;
const date = now.getDate();
const text = year+"年"+month+"月"+date+"日";
if( month === 6 && date === 30 ){
    document.getElementById('anniversary').textContent='発表の日';
}else if( month === 1 && date === 10 ){
    document.getElementById('anniversary').textContent='糸引き納豆の日';
}else if( month === 2 && date === 3 ){
    document.getElementById('anniversary').textContent='節分・大豆の日';
}else if( month === 2 && date === 10 ){
    document.getElementById('anniversary').textContent='世界マメの日';
}else if( month === 4 && date === 3 ){
    document.getElementById('anniversary').textContent='いんげん豆の日';
}else if( month === 7 && date === 10 ){
    document.getElementById('anniversary').textContent='納豆の日';
}else if( month === 10 && date === 2 ){
    document.getElementById('anniversary').textContent='豆腐の日';
}else if( month === 10 && date === 12 ){
    document.getElementById('anniversary').textContent='豆乳の日';
}else if( month === 10 && date === 13 ){
    document.getElementById('anniversary').textContent='豆の日';
}
document.getElementById('today').textContent=text;
}
//スクロールに合わせたアイコンバーの変更
 /*id=barを定数barに代入*/
 const bar = document.getElementById("bar");
 let lastScroll = 0;
 /*スクロールすると以下の処理を実行する*/
 window.addEventListener("scroll", () => {
   /*スクロール幅の取得*/
   const current = window.scrollY;
   /*下スクロールでsmallクラスを付与する*/
   if(current > lastScroll && current > 40){
     bar.classList.add("small");
     }
   /*上スクロールでsmallクラスを削除*/
   else{bar.classList.remove("small");
   }
   lastScroll = current;
 });
 
 //-----------グラフ作成 --------------
 	
//-------------データ取得-------------

//直近7回の記録
 //Y軸label。サーブレットから拾ってくる
 //X軸data。サーブレットから拾ってくる
 //originalcounts,originalsetsカーソルを合わせたときに出るデータ
let weekData = {
			<c:forEach var="gi" items="${WeekGraph}" varStatus="st">
			"${gi.key}":{
					  <c:forEach var="gw" items="${gi.value}" varStatus="st2">
					    "${gw.key}":{
					        labels: [
					        <c:forEach var="g" items="${gw.value}" varStatus="st3">
					            "${g.td_date}"<c:if test="${!st3.last}">,</c:if>
					        </c:forEach>
					        ],
					        data: [
					        <c:forEach var="g" items="${gw.value}" varStatus="st4">
					            ${g.counts * g.sets}<c:if test="${!st4.last}">,</c:if>
					        </c:forEach>
					        ],
					        originalcounts:[
						        <c:forEach var="g" items="${gw.value}" varStatus="st5">
						            ${g.counts}<c:if test="${!st5.last}">,</c:if>
						        </c:forEach>
						        ],
						        originalsets:[
							    <c:forEach var="g" items="${gw.value}" varStatus="st6">
						            ${g.sets}<c:if test="${!st6.last}">,</c:if>
						        </c:forEach>
						        ]
					    }<c:if test="${!st2.last}">,</c:if>
					 </c:forEach>
				}<c:if test="${!st.last}">,</c:if>
			</c:forEach>
			};
 
 //直近30回の記録
 //Y軸label。サーブレットから拾ってくる
 //X軸data。サーブレットから拾ってくる
 //originalcounts,originalsetsカーソルを合わせたときに出るデータ
 	let monthData = {
			<c:forEach var="gi" items="${MonthGraph}" varStatus="st">
			    "${gi.key}":{
			  <c:forEach var="gm" items="${gi.value}" varStatus="st2">
			    "${gm.key}": {
			        labels: [
			        <c:forEach var="g" items="${gm.value}" varStatus="st3">
			            "${g.td_date}"<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [
			        <c:forEach var="g" items="${gm.value}" varStatus="st4">
			            ${g.counts * g.sets}<c:if test="${!st4.last}">,</c:if>
			        </c:forEach>
			        ],
			        originalcounts:[
			        <c:forEach var="g" items="${gm.value}" varStatus="st5">
			            ${g.counts}
			            <c:if test="${!st5.last}">,</c:if>
			        </c:forEach>
			        ],
			        originalsets:[
				    <c:forEach var="g" items="${gm.value}" varStatus="st6">
			            ${g.sets}
			            <c:if test="${!st6.last}">,</c:if>
			        </c:forEach>
			        ]
			      }<c:if test="${!st2.last}">,</c:if>
			 	</c:forEach>
			  }<c:if test="${!st.last}">,</c:if>
			</c:forEach>
			};

 
//------グラフ表示-------
let context3 = document.querySelector("#lineChart").getContext('2d')
let chart = new Chart(context3, {
  type: 'bar', //棒グラフ
  data: {
    labels: [],  // X軸のラベル（日付など）
    datasets: [{
      label: '',
      data: [],
      borderColor: '#4169e1',
      backgroundColor: 'rgba(65, 105, 225, 0.2)',
 //     tension: 0,  // 線を少し曲線にする（0にすると直線）
    }]
  },
  //オプションここから
  options: {
      responsive: true,
      maintainAspectRatio: false,	
      
      plugins: {
          title:  { display: false, }, // グラフタイトルの表示/非表示
          legend: { display: false, }, // 判例の表示/非表示
          //マウスカーソルをホバーしたときに出るデータ
          tooltip: {
        	  callbacks: {
        	    label: function(context) {
        	      const index = context.dataIndex;
        	      const dataset = context.dataset;

        	      const date  = context.chart.data.labels[index];
        	      const count = dataset.originalcounts[index];
        	      const sets  = dataset.originalsets[index];
        	      const total = context.raw;

        	      return [
        	        `日付: ${date}`,
        	        `回数: ${count}`,
        	        `セット: ${sets}`,
        	        `合計: ${total}`
        	      ];
        	    }
        	  }
        	}
        },
       //オプションここまで
      //軸設定
      scales: {
    	  //X軸ここから
			x:{
				display:true,
				//X軸のラベル変更
				ticks:{
					color:"blue"
				},
				grid:{
					display:false
				}
				
        
			},
    	  //X軸ここまで	
    	  //Y軸ここから
	        y: {
	            ticks: {
	                stepSize: 5,
	                color: "blue"
	            },
	            grid: {
	                color: "rgba(0, 0, 255, 0.2)"
	            },
	            title: {
	                display: true,
	                text: "回数×セット",
	                color: "blue",
	                font: {
	                    size: 14
	                }
	            }
	        }//Y軸ここまで
	    }//軸設定ここまで
	}  //オプションここまで
});


//------ 初期表示 -------
function convertToChartJs(dataObj) {
    return dataObj; 
}
let currentData = weekData;//最初に表示する週のグラフ

//最初に表示する項目を取得
let firstKey = Object.keys(currentData)[0];
//項目をセッションに保存
window.sessionStorage.setItem('gitem', firstKey);
//最初に表示される項目の重量一覧を取得
let weights = Object.keys(currentData[firstKey]);

//一覧にある最初の重量を取得
let firstWeight = weights[0];

//期間変更ボタンの色
let weekcolor = "#ff0000";
let moncolor = "#ffffff";
document.getElementById("wcbutton").style.color = weekcolor;
document.getElementById("mcbutton").style.color = moncolor;


//項目をセッションに保存
window.sessionStorage.setItem('gweight', firstWeight);

//プルダウンに表示
    var weightSelect = document.getElementById("weightSelect");
    weightSelect.innerHTML = "";

    for (var i = 0; i < weights.length; i++) {
        var opt = document.createElement("option");
        opt.value = weights[i];
        opt.text = weights[i] + " kg";
        weightSelect.appendChild(opt);
    }

    weightSelect.value = firstWeight;

    //グラフ初期表示
    var chartData = weekData[firstKey][firstWeight];

    chart.data.labels = chartData.labels;
    chart.data.datasets[0].data = chartData.data;
    chart.data.datasets[0].label = firstKey + "（" + firstWeight + "kg）";
	 //マウスホバー表示
	chart.data.datasets[0].originalcounts = chartData.originalcounts;
	chart.data.datasets[0].originalsets = chartData.originalsets;

    
    chart.update();



 
//-------項目変更処理-------
function changeItem() {
    let key = document.getElementById("itemSelect").value;
    window.sessionStorage.setItem('gitem', key);
    
    let weightSelect = document.getElementById("weightSelect");

    // 重量リストを削除
    while (weightSelect.firstChild) {
        weightSelect.removeChild(weightSelect.firstChild);
    }

    // 選んだ項目の重量一覧を取得
    let weights = Object.keys(currentData[key]);

    // 重量プルダウンに追加
    for (let i = 0; i < weights.length; i++) {
        let opt = document.createElement("option");
        opt.value = weights[i];
        opt.text = weights[i] + " kg";
        weightSelect.appendChild(opt);
    }
    // 重量プルダウンの最初の項目を選択
    let firstWeight = weights[0];
    weightSelect.value = firstWeight;
    sessionStorage.setItem('gweight', firstWeight);

    changeWeight();
} 

//重量変更処理
function changeWeight(){
    let key = document.getElementById("itemSelect").value;    
    window.sessionStorage.setItem('gitem', key);
    let weight = document.getElementById("weightSelect").value;
    window.sessionStorage.setItem('gweight', weight);

    if (!key || !weight) return;

    let selected = currentData[key][weight];

    chart.data.labels = selected.labels;	//X軸
    chart.data.datasets[0].data = selected.data;//Y軸
    chart.data.datasets[0].label = key + "(" + weight + "kg)";	
    //マウスホバー表示
	chart.data.datasets[0].originalcounts = selected.originalcounts;
	chart.data.datasets[0].originalsets = selected.originalsets;


    chart.update();
}

//グラフ期間変更ボタンクリック時の処理
function updateChart(newData) {
	
    currentData = newData;
	//前に設定していた項目
    let key = window.sessionStorage.getItem('gitem');
    let weight  = window.sessionStorage.getItem('gweight');

    // 切替後のデータに重量が存在しない場合は初期化
    if (!currentData[key][weight]) {
    	weight  = Object.keys(currentData[key])[0];
        sessionStorage.setItem('gweight', weight );
    }

    //ボタン色変更
    if (currentData == monthData){
    	weekcolor = "#ffffff";
    	moncolor = "#ff0000";
    	document.getElementById("wcbutton").style.color = weekcolor;
    	document.getElementById("mcbutton").style.color = moncolor;
    }else if(currentData == weekData){
    	weekcolor = "#ff0000";
    	moncolor = "#ffffff";
    	document.getElementById("wcbutton").style.color = weekcolor;
    	document.getElementById("mcbutton").style.color = moncolor;
    }
    
    // 重量をリセット
    while (weightSelect.firstChild) {
        weightSelect.removeChild(weightSelect.firstChild);
    }

    let weights = Object.keys(currentData[key]);
    for (let i = 0; i < weights.length; i++) {
        let opt = document.createElement("option");
        opt.value = weights[i];
        opt.text = weights[i] + " kg";
        weightSelect.appendChild(opt);
    }
    weightSelect.value = weight ;
    
 // let firstKey = Object.keys(currentData)[0];
	    let changed = currentData[key][weight];

    chart.data.labels = changed.labels;			//X軸
    chart.data.datasets[0].data = changed.data;	//Y軸
    chart.data.datasets[0].label = key + "(" + weight + "kg)";
    //マウスホバー表示
	chart.data.datasets[0].originalcounts = changed.originalcounts;
	chart.data.datasets[0].originalsets = changed.originalsets;
    

    
    chart.update();
}



</script>
</div>
</body>
</html>