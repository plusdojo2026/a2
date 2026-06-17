<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	トレーニング項目<select name="tr_items">
		<c:forEach var="e" items="${gItem}">
<!-- データ上ではid、ユーザー側では項目名が表示される -->
		 <option value="${e.tr_id}"><c:out value="${e.tr_item}" />
		 </option>
	 	</c:forEach> 
	</select><br>
</div> 

<!-- グラフデータ -->
<div>
 <c:forEach ver="gi" items="${GraphList}">
 	<c:out value="${gi.tr_weight}">
 	</c:out><br>
 	 <c:out value="${gi.counts}">
 	</c:out><br> 	
 	<c:out value="${gi.sets}">
 	</c:out><br> 	
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

//

</script>
</body>
</html>