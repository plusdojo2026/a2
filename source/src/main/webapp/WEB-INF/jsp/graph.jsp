<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- グラフ作成用Chart.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
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
	トレーニング項目<select name="tr_item">
		<c:forEach var="e" items="${gItem}">
<!-- データ上ではid、ユーザー側では項目名が表示される -->
		 <option><c:out value="${e.tr_item}" />
		 </option>
	 	</c:forEach> 
	</select><br>
</div> 
<!-- グラフを表示する場所 -->
<canvas id = "lineChart"></canvas>

<!-- グラフデータ -->
<div>
 <c:forEach var="gi" items="${GraphList}">
 	<c:out value="${gi.tr_item }">
 	</c:out>
 	<c:out value="${gi.tr_weight}">
 	</c:out>kg
 	 <c:out value="${gi.counts}">回
 	</c:out> 回	
 	<c:out value="${gi.sets}">セット
 	</c:out> セット	
 	<c:out value="${gi.TD_date}">
 	</c:out><br>
 </c:forEach>
</div>

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

//グラフ作成



</script>
</body>
</html>