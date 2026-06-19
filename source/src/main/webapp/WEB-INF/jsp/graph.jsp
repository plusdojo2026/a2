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
		<c:forEach var="e" items="${gItem}">
<!-- データ上ではid、ユーザー側では項目名が表示される -->
		 <option value="${e.tr_item}"><c:out value="${e.tr_item}" />
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

 //-----------折れ線グラフ作成 --------------
 
/*let inputLabel = 
	labels:[
	<c:forEach var="gi" items="${grouped.value}" varStatus="st">
    "${gi.td_date}"<c:if test="${!st.last}">,</c:if>
	</c:forEach>,
] ;

let arr =
	data : [
	<c:forEach var="gi" items="${grouped.value}" varStatus="st">
	    ${gi.counts}<c:if test="${!st.last}">,</c:if>
	</c:forEach>
	] ;
*/	
//-------------データ取得-------------
	let graphData = {
			<c:forEach var="gi" items="${grouped.value}" varStatus="st">
			    "${gi.key}": {
			        labels: [//X軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st2">
			            "${g.td_date}"<c:if test="${!st2.last}">,</c:if>
			        </c:forEach>
			        ],
			        data: [//Y軸。サーブレットから拾ってくる
			        <c:forEach var="g" items="${gi.value}" varStatus="st3">
			            ${g.price}<c:if test="${!st3.last}">,</c:if>
			        </c:forEach>
			        ]
			    }<c:if test="${!st.last}">,</c:if>//Listの中身があるときは動き,を入れる
			</c:forEach>
			};
			
//------グラフ表示-------
let context3 = document.querySelector("#lineChart").getContext('2d')
new Chart(context3, {
  type: 'line', //折れ線
  data: {
    labels: [],  // X軸のラベル（日付など）
    datasets: [{
      label: '',
      data: [],
      borderColor: '#4169e1',
      backgroundColor: 'rgba(65, 105, 225, 0.2)',
      tension: 0,  // 線を少し曲線にする（0にすると直線）
    }]
  },
  options: {
    responsive: false,
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

function retrieveItemPrice(selectedText){
    let key = selectedText;

    if (!key) return;

    let selected = graphData[key];

    context3.data.labels = selected.labels;
    context3.data.datasets[0].label = key;
    context3.data.datasets[0].data = selected.data;

    chart.update();
}


</script>
</body>
</html>