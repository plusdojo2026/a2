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
<!-- データ上ではid、ユーザー側では項目名が表示される -->
		 <option value="${gi.key}"><c:out value="${gi.key}" />
		 </option>
	 	</c:forEach> 
	</select><br>
</div> 


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
			    "${gi.key}": {
			        labels: [//X軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st2">
			            "${g.td_date}"<c:if test="${!st2.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [//Y軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st3">
			            ${g.counts}*${g.sets}<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ]
			    }<c:if test="${!st.last}">,</c:if>//Listの中身があるときは,を入れる
			</c:forEach>
			};
 
 //直近30回の記録
 	let monthData = {
			<c:forEach var="gi" items="${MonthGraph}" varStatus="st">
			    "${gi.key}": {
			        labels: [//X軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st2">
			            "${g.td_date}"<c:if test="${!st2.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [//Y軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st3">
			            ${g.counts}*${g.sets}<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ]
			    }<c:if test="${!st.last}">,</c:if>//Listの中身があるときは,を入れる
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

//------selectElementを取得して変数に代入-------

const selectElement = document.getElementById('itemSelect');

selectElement.addEventListener('change', function() {

    // 選択された option の値とテキストを取得
    const selectedText = selectElement.options[selectElement.selectedIndex].text;

//    console.log("選択されたテキスト:", selectedText);

    retrieveItemPrice(selectedText);
})



//------ 初期表示 -------
//期間を変更するボタンを押したらデータ置き換え
function convertToChartJs(dataObj) {
    return dataObj; 
}
let currentData = sessionStorage.getItem('gdata') ?? weekData;//最初に表示する週のグラフ

//最初に表示する項目
let firstKey = Object.keys(currentData)[0];
//項目をセッションに保存
window.sessionStorage.setItem('gitem', firstKey);

chart.data.labels = currentData[firstKey].labels;
chart.data.datasets[0].label = firstKey;
chart.data.datasets[0].data = currentData[firstKey].data;
chart.update();

selectElement.value = firstKey;



//項目変更処理
function retrieveItemPrice(selectedText){
    let key = selectedText;
    window.sessionStorage.setItem('gitem', key);
    if (!key) return;

    let selected = currentData[key];

    chart.data.labels = selected.labels;
    chart.data.datasets[0].label = key;
    chart.data.datasets[0].data = selected.data;

    chart.update();
}


//グラフ期間変更ボタンクリック時の処理
function updateChart(newData) {
	
    currentData = newData;
    window.sessionStorage.setItem('gdata', currentData);
	//前に設定していた項目
    let getKey = window.sessionStorage.getItem('gitem');

 // let firstKey = Object.keys(currentData)[0];

    chart.data.labels = currentData[getKey].labels;
    chart.data.datasets[0].label = getKey;
    chart.data.datasets[0].data = currentData[getKey].data;

    chart.update();

    selectElement.value = getKey;
}

   /*     try {
            myChart.destroy();//キャンバスをリセット
        }
        catch {
          //万が一イベント実行時にグラフが存在しなかった場合
        }
		    	let graphData = {
		    			<c:forEach var="gi" items="${WeekGraph}" varStatus="st">
		    			    "${gi.key}": {
		    			        labels: [//X軸。サーブレットから拾ってくる
		    			        <c:forEach var="g" items="${gi.value}" varStatus="st2">
		    			            "${g.td_date}"<c:if test="${!st2.last}">,</c:if>
		    			        </c:forEach>
		    			        ],
		    			        data: [//Y軸。サーブレットから拾ってくる
		    			        <c:forEach var="g" items="${gi.value}" varStatus="st3">
		    			            ${g.counts}*${g.sets}<c:if test="${!st3.last}">,</c:if>
		    			        </c:forEach>
		    			        ]
		    			    }<c:if test="${!st.last}">,</c:if>//Listの中身があるときは,を入れる
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
		        
		    	yAxes: [{
		            ticks: {           // Ｙ軸目盛り        
		                min: 0,            // 最小値
		                stepSize: 5,       // 間隔
		                fontColor: "blue"  // 色
		            	},
		            gridLines: {        // 水平補助線の定義
		                color: "rgba(0, 0, 255, 0.2)"
		            	}
		            }],
		      }
		    });
		    let firstKey = Object.keys(graphData)[0];
		    chart.data.labels = graphData[firstKey].labels;
		    chart.data.datasets[0].label = firstKey;
		    chart.data.datasets[0].data = graphData[firstKey].data;
		    chart.update();
		    selectElement.value = firstKey;
 				};

*/


</script>
</body>
</html>