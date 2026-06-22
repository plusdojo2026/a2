<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- グラフ作成用Chart.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"></script>
<script  src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
<title>マメッスル|成長記録</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet" href="/a2/css/mypage.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
<!-- <style>
            *{
                outline: 1px solid #000000;
            }
        </style>  -->
</head>
<body>
<!--　ヘッダーここから　-->
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="/a2/HomeServlet" class="logo">rogo</a>
    <a href="/a2/InfoServlet" class="bean-info"><i class="fa-solid fa-circle-info"></i>豆情報</a>


</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>

<div>
	トレーニング項目<select id="itemSelect">
		<c:forEach var="gi" items="${WeekGraph}">
		 <option value="${gi.key}"><c:out value="${gi.key}" />
		 </option>
	 	</c:forEach> 
	</select>
</div> 
<select id="weightSelect" onchange="changeWeight()">
</select><br>


<!-- グラフを表示する場所 -->
<canvas id="lineChart" width="600" height="300"></canvas>



<!-- 表示変更 -->
<ul>
<li>
<button onclick="updateChart(weekData)" >直近7回の記録</button>
</li>
<li>
<button onclick="updateChart(monthData)" >直近30回の記録</button>
</li>
</ul>

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
if( month === 6 && date === 17 ){
    document.getElementById('anniversary').textContent='テスト用';
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
 
 //-----------折れ線グラフ作成 --------------
 	
//-------------データ取得-------------

//直近7回の記録
let weekData = {
			<c:forEach var="gi" items="${WeekGraph}" varStatus="st">
			"${gi.key}":{
					  <c:forEach var="gw" items="${gi.value}" varStatus="st2">
					    "${gw.key}":{
					        labels: [//X軸。サーブレットから拾ってくる
					        <c:forEach var="g" items="${gw.value}" varStatus="st3">
					            "${g.td_date}"<c:if test="${!st3.last}">,</c:if>
					        </c:forEach>
					        ],
					        data: [//Y軸。サーブレットから拾ってくる
					        <c:forEach var="g" items="${gw.value}" varStatus="st4">
					            ${g.counts}*${g.sets}<c:if test="${!st4.last}">,</c:if>
					        </c:forEach>
					        ]
					    }<c:if test="${!st2.last}">,</c:if>//Listの中身があるときは,を入れる
					 </c:forEach>
				}<c:if test="${!st.last}">,</c:if>
			</c:forEach>
			};
 
 //直近30回の記録
 	let monthData = {
			<c:forEach var="gi" items="${MonthGraph}" varStatus="st">
			    "${gi.key}":{
			  <c:forEach var="gm" items="${gi.value}" varStatus="st2">
			    "${gm.key}": {
			        labels: [//X軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gm.value}" varStatus="st3">
			            "${g.td_date}"<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [//Y軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gm.value}" varStatus="st4">
			            ${g.counts}*${g.sets}<c:if test="${!st4.last}">,</c:if>
			        </c:forEach>
			        ]
			      }<c:if test="${!st2.last}">,</c:if>//Listの中身があるときは,を入れる
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
  options: {
	    responsive: false,
	    scales: {
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
	                    size: 16
	                }
	            }
	        }
	    }
	}
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

    chart.update();



 
//-------項目変更処理-------
function changeItem() {
    let key = document.getElementById("itemSelect").value;
    window.sessionStorage.setItem('gitem', key);
    
    let weightSelect = document.getElementById("weightSelect");

    //  onchange を一時停止
    weightSelect.onchange = null;

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
    // 最初の重量を選択
    let firstWeight = weights[0];
    weightSelect.value = firstWeight;
    sessionStorage.setItem('gweight', firstWeight);
    
    //onchange を復活
    weightSelect.onchange = changeWeight;
    
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

    //  onchange を一時停止
    weightSelect.onchange = null;

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
    
    // onchange を復活
    weightSelect.onchange = changeWeight;
    
    chart.update();
}



</script>
</body>
</html>