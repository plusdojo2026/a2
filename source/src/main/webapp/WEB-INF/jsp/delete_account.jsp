<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<div class="app-wrapper">
<!--　ヘッダーここから　-->
<header>
</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<form method=POST action="/a2/DeleteAccountServlet">
<div class="my_page_user_info">
    <div class="circle">
    </div>
    <div class="user_id_name">
        ID:<c:out value="${userInfo.userId}"/><br>
        name：<c:out value="${userInfo.userName}"/>
    </div>
</div>

<p>上記アカウントの退会手続きを行います。<br>
退会後は、アカウントの利用・ログインが出来なくなります。
</p>
<input type="submit" value="退会する" id="delete">
</form>

<a href="/a2/MyPageServlet">戻る</a>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
<script>
'use strict'
//退会アラート
document.getElementById('delete').onclick = function(event){
    let logout = window.confirm('退会してよろしいですか？');
    if( logout === false){
        event.preventDefault();
    }
}

</script>
</div>
</body>
</html>