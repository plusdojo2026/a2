<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>アイコン着せ替え変更</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
</head>
<body>
<div class="app-wrapper">
<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<c:out value="${message}"/>
<h3>アイコンの変更</h3>
<form method=POST action="/a2/DesignServlet">
<input type="hidden" name="userId" value="${userInfo.userId}">
<div>
<input type="submit" value="0" name="number">
<input type="submit" value="1" name="number">
<input type="submit" value="2" name="number">
<input type="submit" value="3" name="number"><br>
<input type="submit" value="4" name="number">
<input type="submit" value="5" name="number">
<input type="submit" value="6" name="number">
<input type="submit" value="7" name="number">
</div>


<h3>背景色の変更</h3>
<div>
<input type="submit" value="8" name="number">
<input type="submit" value="9" name="number">
<input type="submit" value="10" name="number">
<input type="submit" value="11" name="number"><br>
<input type="submit" value="12" name="number">
<input type="submit" value="13" name="number">
<input type="submit" value="14" name="number">
<input type="submit" value="15" name="number">
</div>
</form>
<a href="/a2/MyPageServlet">戻る</a>
</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user nowpage"></i></a>
</nav>
</footer>
<!--　フッターここまで　-->
</div>
</body>
</html>