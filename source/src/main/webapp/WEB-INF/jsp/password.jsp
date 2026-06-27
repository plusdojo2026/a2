<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>パスワード変更画面</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet" href="/a2/css/password.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
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
<c:out value="${message}"/>
<p id="msg"></p>
<form method="POST" action="/a2/PasswordServlet" id="form">
	<div class="form-container">
		<input type="hidden" name="userId" value="${userInfo.userId}">
		<table>
    		<tr>
        		<td>現在のパスワード</td>
        		<td>
        			<input type="password" name="inputPassword" id="password1">
        		</td>

    		</tr>
    		<tr>
        		<td>新しいパスワード</td>
        		<td>
        			<input type="password" name="newPassword" id="password2">
        		</td>

    		</tr>
    		<tr>
        		<td><nobr>新しいパスワード</nobr></td>
       			<td style="text-align: right;">
       				<div class="eye-slash">
        				<input type="password" id="password3">
        			</div>
        		</td>
 
    		</tr>
    		
		</table> 
		<button type="button" id="show" class="right">
        				<i class="fa-regular fa-eye-slash" id="icon"></i>
        </button>
    	
   	
   		
    
   		<div class="button">
    		<input type="submit" class="regist-button" value="変更">
			<input type="button" class="back-button" onclick="location.href='/a2/MyPageServlet'" value="戻る">
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
</div>
<script>
	'use strict';
	//パスワード表示切替
	const pas1=document.getElementById('password1');
	const pas2=document.getElementById('password2');
	const pas3=document.getElementById('password3');
	const ele=document.getElementById('icon');
	document.getElementById('show').onclick = function(){
		if(pas1.type==="password"){
			pas1.type="text";
			pas2.type="text";
			pas3.type="text";
			ele.className="fa-regular fa-eye";
		}else{
			pas1.type="password";
			pas2.type="password";
			pas3.type="password";
			ele.className="fa-regular fa-eye-slash";
		}
	}
	//現在のパスワードと照らし合わせ
    document.getElementById('form').onsubmit = function(event){

        const pasCheck1 = document.getElementById('password1').value;
        const pasCheck2 = document.getElementById('password2').value;
        const pasCheck3 = document.getElementById('password3').value;
		//空白をはじく
         if( pasCheck1 === '' || pasCheck2 === '' || pasCheck3 ===''){
            document.getElementById('msg').textContent = '※すべてのパスワードを入力してください。';
            event.preventDefault();
        //新しいパスワード不一致をはじく
        }else if(pasCheck2 !== pasCheck3){
        	document.getElementById('msg').textContent = '※新しいパスワードが一致しませんでした。';
            event.preventDefault();
        }
	}	
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

</body>
</html>