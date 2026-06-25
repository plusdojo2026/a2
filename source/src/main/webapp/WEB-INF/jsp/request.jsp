<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>リクエスト一覧</title>
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
<nav class="tab-menu">
		<ul>
			<li><a href="/a2/FriendListServlet" class="active">フレンド</a></li>
            <li><a href="/a2/FriendrequestsServlet">リクエスト</a></li>

		</ul>
	</nav>
	
<main style="max-width: 500px; margin: 50px auto; padding: 20px;  border-radius: 10px;">
<p><c:out value="${message}"/></p>

	    <h2>フレンド申請</h2>
    <ul style="list-style: none; padding: 0;">
        <c:forEach var="req" items="${rqSearch}">
            <li style="padding: 15px; border-bottom: 1px solid #eee; display: flex; align-items: center; justify-content: space-between;">
                
                <div style="display: flex; align-items: center; gap: 10px;">
                    <span style="font-size: 30px;">${req.iconId}</span>
                    <span style="font-size: 12px;">${req.userId}</span>
                    <span style="font-size: 18px; font-weight: bold;">${req.userName}</span>
                    <span style="font-size: 14px; color: #666;">さんから</span>
                </div>
                
                <form action="/a2/FriendrequestsServlet" method="POST" style="margin: 0;">
                    <input type="hidden" name="targetUserId" value="${req.userId}"> 
      
                    
                    <div style="display: flex; gap: 10px;">
                    
                        <button type="submit" name="action"  value="approve" style="background-color: #28a745; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-weight: bold;">承認</button>
                        <button type="submit" name="action"  value="reject" class="reject" style="background-color: #dc3545; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-weight: bold;">拒否</button>
                   
                    </div>
                </form>
            </li>
        </c:forEach>
    </ul>
    
	    <h2>申請中</h2>
    <ul style="list-style: none; padding: 0;">
        <c:forEach var="req" items="${fmSearch}">
            <li style="padding: 15px; border-bottom: 1px solid #eee; display: flex; align-items: center; justify-content: space-between;">
                
                <div style="display: flex; align-items: center; gap: 10px;">
                    <span style="font-size: 30px;">${req.iconId}</span>
                    <span style="font-size: 12px;">${req.userId}</span>
                    <span style="font-size: 18px; font-weight: bold;">${req.userName}</span>
                    <span style="font-size: 14px; color: #666;">さんへ</span>
                </div>

            </li>
        </c:forEach>
    </ul>
    <div style="margin-top: 30px; text-align: center;">
        <a href="/a2/FriendListServlet" style="color: #007bff; text-decoration: none; font-weight: bold;">フレンド一覧に戻る</a>
    </div>
</main>
<footer>
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group nowpage"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user"></i></a>
</nav>
</footer>
<!--スクリプトここから-->
<script>
    'use strict';
	
    const buttons = document.querySelectorAll('.reject');
    buttons.forEach((button) => {
    	button.addEventListener('click', (e) => {
    		const ok = confirm('拒否しますか？');
    		if (!ok) {
    		      e.preventDefault();
    		    }
    	});
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