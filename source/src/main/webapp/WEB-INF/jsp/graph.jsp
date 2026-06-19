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
</head>
<body>
<!--　ヘッダーここから　-->
<header>
	<span class ="date" id="date"></span>
	<a href="/webapp/HomeServlet">ロゴ</a>
	今日は○の日
	<a href="/webapp/InfoServlet">今日の豆情報</a>

</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>

<div>
	トレーニング項目<select id="itemSelect">
		<c:forEach var="gi" items="${grouped}">
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
<input type="button" name="week" value="週">
</li>
<li>
<input type="button" name="month" value="月">
</li>
</ul>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>
<ul>
  <li><a href="/webapp/GraphServlet">成長記録</a></li>
  <li><a href="/webapp/FriendListServlet">共有</a></li>
  <li><a href="/webapp/HomeServlet">ホーム</a></li>
  <li><a href="/webapp/CalendarServlet">カレンダー</a></li>
  <li><a href="/webapp/MyPageServlet">マイページ</a></li>
  
</ul>

</footer>
<!--　フッターここまで　-->
<script>

'use strict'

//現在時刻の表示innerHTMLで中身を書き換える
const days=["日","月","火","水","木","金","土"];
document.getElementById("date").innerHTML = showDay();
function showDay(){
	var now = new Date();
	var year =now.getFullYear();
	var month = now.getMonth();
	var day = now.getDate();
	var youbi = now.getDay();
	
	return year+"/"+(month+1)+"/"+day+"("+ days[youbi] +")";
}

//今週のデータを取得する
function get7Days(){
	const days=["日","月","火","水","木","金","土"];
	const week=[];
	const now = new Date();
	let i =0;
	
		while(i< days.length){
		const year =now.getFullYear();
		const month = now.getMonth();
		const day = now.getDate();
		const youbi = now.getDay();
		
		i = i+1;
		
		
		}
	}
	return week;
}


 //-----------折れ線グラフ作成 --------------
 	
//-------------データ取得-------------
	let graphData = {
			<c:forEach var="gi" items="${grouped}" varStatus="st">
			    "${gi.key}": {
			        labels: [//X軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st2">
			            "${g.td_date}"<c:if test="${!st2.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [//Y軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st3">
			            ${g.counts}<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ]
			    }<c:if test="${!st.last}">,</c:if>//Listの中身があるときは動き,を入れる
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




//------selectElementを取得して変数に代入-------

const selectElement = document.getElementById('itemSelect');

selectElement.addEventListener('change', function() {

    // 選択された option の値とテキストを取得
    const selectedText = selectElement.options[selectElement.selectedIndex].text;

    console.log("選択されたテキスト:", selectedText);

    retrieveItemPrice(selectedText);
})

//------ 初期表示 -------
let firstKey = Object.keys(graphData)[0];
chart.data.labels = graphData[firstKey].labels;
chart.data.datasets[0].label = firstKey;
chart.data.datasets[0].data = graphData[firstKey].data;
chart.update();
selectElement.value = firstKey;


function retrieveItemPrice(selectedText){
    let key = selectedText;

    if (!key) return;

    let selected = graphData[key];

    chart.data.labels = selected.labels;
    chart.data.datasets[0].label = key;
    chart.data.datasets[0].data = selected.data;

    chart.update();
}


</script>
</body>
</html>