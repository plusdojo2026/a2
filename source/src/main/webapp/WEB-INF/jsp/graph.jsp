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
 <select name ="tr_items">
 <%for(int i = 0; i < List.size(); i++) %>
 
 </select>
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


</script>
</body>
</html>