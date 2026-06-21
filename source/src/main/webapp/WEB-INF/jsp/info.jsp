<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マメッスル　豆情報</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
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
<p>${todayTrivia}</p>
<img src="${recipe.imagePath}" alt="${recipe.recipeName}">

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user"></i></a>
</nav>

</footer>
<!--　フッターここまで　-->
</body>
</html>