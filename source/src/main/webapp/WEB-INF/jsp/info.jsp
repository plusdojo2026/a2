<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>マメッスル　豆情報</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
<link rel="stylesheet" href="/a2/css/info.css">
<link rel="icon" href="/a2/img/mame.png" type="image/png">
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
	<div class="trivia-card">
	    <i class="fa-solid fa-lightbulb trivia-icon"></i>
	    <div class="trivia-text">${todayTrivia}</div>
	</div>
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

<script>
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
</script>
</div>
</body>
</html>