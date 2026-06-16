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
<!-- <style>
            *{
                outline: 1px solid #000000;
            }
        </style>  -->
</head>
<body>
<!--ヘッダーここから-->
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="home.html" class="logo">rogo</a>
    <a href="home.html" class="bean-info"><i class="fa-solid fa-circle-info"></i>豆情報</a>


</header>
<!--ヘッダーここまで-->
<!--メインここから-->
<main>

<c:out value="${userInfo.point}"/>
<div class="my_page_user_info">
    <div class="circle">
    </div>
    <div class="user_id_name">
		<p>　ID：</p><c:out value="${userInfo.userId}"/>
            <br>
        <p>名前：</p><c:out value="${userInfo.userName}"/>
    </div>
</div>

<div>
<a href="/a2/DesignServlet.java" class="design">
	背景・アイコンの変更
</a>
</div>
<br>
<div>
<a href="/a2/EditProfileServlet.java" class="profile">
	基本情報の変更
</a>
<a href="/a2/PasswordServlet.java" class="password">
	パスワードの変更
</a>
</div>
<br>
<div>
<a href="/a2/LogoutServlet.java" class="logout">
	ログアウト
</a>
</div>
<br>

<a href="/a2/DeleteAccountServlet.java" class="delete_account">
	退会手続き
</a>

</main>
<!--メインここまで-->
<!--フッターここから-->
<footer><!--nowpageはそのページに着けてほしいです。-->
<nav class="bottom-bar" id="bar">
  <a href="home.html"><i class="fa-solid fa-arrow-trend-up nowpage"></i></a>
  <a href="home.html"><i class="fa-solid fa-user-group"></i></a>
  <a href="home.html"><i class="fa-regular fa-square-plus"></i></a>
  <a href="home.html"><i class="fa-regular fa-calendar"></i></a>
  <a href="home.html"><i class="fa-solid fa-circle-user"></i></a>
</nav>
</footer>
<!--フッターここまで-->
<!--スクリプトここから-->
<script>
    'use strict';
//ヘッダー日付表示用
const now =new Date();
const year = now.getFullYear();
const month= now.getMonth()+1;
const date = now.getDate();
const text = `${year}年${month}月${date}日`;
document.getElementById('today').textContent=text;
if(text==="2026年6月13日"){
    document.getElementById('anniversary').textContent='テスト用';
}else if(text==="2026年7月10日"){
    document.getElementById('anniversary').textContent='納豆の日';
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
</body>
</html>