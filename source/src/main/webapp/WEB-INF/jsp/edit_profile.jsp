<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>基本情報の変更</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet" href="/a2/css/edit_profile.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
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
<c:out value="${message}"/>
<form method=POST action="/a2/EditProfileServlet" id="form">
<h2>基本情報の変更</h2>
<div class="form-container">
<input type="hidden" name="userId" value="${userInfo.userId}">

	<table>
		<tr>
			<th> ID：</th>
			<td><c:out value="${userInfo.userId}"/></td>
		<tr>
		<tr>
			<th>ユーザー名：</th>
			<td><input type="text" id="must1" name="userName" value="${userInfo.userName}"></td>
		<tr>
		<tr>
			<th>身長：</th>
			<td><input type="text" id="must2" name="height" id="height" class="hw" value="${userInfo.height}">cm</td>
		<tr>
		<tr>
			<th>性別：</th>
			<td>
				男:<input type="radio" name="gender" value="male" 
				${userInfo.gender == 'male' ? 'checked' : ''} >
				女:<input type="radio" name="gender" value="female" 
				${userInfo.gender == 'female' ? 'checked' : ''}>
				その他:<input type="radio" name="gender" value="other" 
				${userInfo.gender == 'other' ? 'checked' : ''}>
			</td>
		<tr>
		<tr>
			<th>目標体重：</th>
			<td><input type="text" id="must3" class="hw" name="targetWeight" id="targetWeight" value="${userInfo.targetWeight}">kg</td>
		<tr>
	</table>

	<div class="button">
		<input type="button"  class="back-button" onclick="location.href='/a2/MyPageServlet'" value="戻る">
		<input type="submit"  class="regist-button" value="登録">
	</div>
</div>
</form>
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
<script>
'use strict'

document.getElementById('form').onsubmit = function(event){

        let m1 = document.getElementById('form').must1.value;
        let m2 = document.getElementById('form').must2.value;
        let m3 = document.getElementById('form').must3.value;
//もし、名前が未入力なら進めない
         if( m1 === '' || m2 === '' || m3 ===''){
        	alert('必須項目が未入力です。');
            event.preventDefault();
        }
};

document.querySelector("form").addEventListener("submit", function(event) {

	const height = document.querySelector("input[name='height']").value.trim();
	const targetWeight = document.querySelector("input[name='targetWeight']").value.trim();
	
	// 数値チェックのコード
	const numberCheck = /^[0-9]+(\.[0-9]+)?$/;
	
	// 身長チェック
	if (!numberCheck.test(height) || Number(height) <= 0) {
		alert("身長は0より大きい数値を入力してください");
		event.preventDefault();
		return;
	}
	
	// 目標体重チェック
	if (!numberCheck.test(targetWeight) || Number(targetWeight) <= 0) {
		alert("目標体重は0より大きい数値を入力してください");
		event.preventDefault();
		return;
	}
});

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