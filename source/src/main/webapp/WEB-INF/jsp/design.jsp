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