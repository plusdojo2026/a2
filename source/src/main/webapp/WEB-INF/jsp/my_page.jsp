<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>マイページ</title>

<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet" href="/a2/css/mypage.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
<link rel="icon" href="/a2/img/mamekiti.png" type="image/png">
<!-- <style>
            *{
                outline: 1px solid #000000;
            }
        </style>  -->
</head>
<body>
<div class="app-wrapper">
<!--ヘッダーここから-->
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="/a2/HomeServlet" class="logo">rogo</a>
    <a href="/a2/InfoServlet" class="bean-info"><i class="fa-solid fa-circle-info"></i>豆情報</a>


</header>
<!--ヘッダーここまで-->
<!--メインここから-->
<main>
<p><c:out value="${message}"/></p>
<c:out value="${userInfo.point}"/>:豆pt
<div class="my_page_user_info">
    <div class="circle">
    </div>
    <div class="user_id_name">
		ID：<c:out value="${userInfo.userId}"/><br>
        名前：<c:out value="${userInfo.userName}"/>
    </div>
</div>
<br>
<div class="kihon">
<a href="/a2/EditProfileServlet" class="profile">
	基本情報の変更
</a>
</div>

<div class="pass">
<a href="/a2/PasswordServlet" class="password">
	パスワードの変更
</a>
</div>
<br>
<div>
<div class="logout">
<a href="/a2/LogoutServlet" class="logout" id="logout">
	ログアウト
</a>
</div>
<br>
<div class="taikai">
<a href="/a2/DeleteAccountServlet" class="delete_account">
	退会手続き
</a>
</div>
</main>
<!--メインここまで-->
<!--フッターここから-->
<footer><!--nowpageはそのページに着けてほしいです。-->
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user nowpage"></i></a>
</nav>
</footer>
<!--フッターここまで-->
<!--スクリプトここから-->
<script>
    'use strict';
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
//ログアウトアラート
document.getElementById('logout').onclick = function(event){
    let logout = window.confirm('ログアウトしてよろしいですか？');
    if( logout === false){
        event.preventDefault();
    }
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
</script>
</div>
</body>
</html>