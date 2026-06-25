<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="css/header_footer.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<div class="app-wrapper">
<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>


<div class="logo">
<h1>マメッスル</h1>

</div>

<div class="form-container">
<section>
<!-- /プロジェクト名/サーブレットの名前 -->
<form action="/a2/LoginServlet" id="form" method="post">

<div class="form-group">
<label for="user-id">ユーザーID</label><br>
<input type="text" name="user_id" value="${param.user_id}"><br>
</div>
<div class="form-group">
    <label for="password">パスワード</label><br>
    <div class="password-wrapper">
        <input type="password" name="password" id="loginPw">
        <button type="button" id="show" class="pw-toggle">
            <i id="icon" class="fa-regular fa-eye-slash"></i>
        </button>
    </div>
</div>   

<div class="button-area">
<input type="submit" value="ログイン">
	<div style="color:green; margin-top: 10px;">
		${result}
	</div>
</div>


<div class="register-link">
新規会員登録は<a href="/a2/RegistServlet">こちら</a>
</div>


</form>
</section>
</div>


</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
</div>
<script>
	// パスワード表示切替
	const pw = document.getElementById('loginPw');
	const icon = document.getElementById('icon');
	
	document.getElementById('show').onclick = function() {
	    if (pw.type === "password") {
	        pw.type = "text";
	        icon.className = "fa-regular fa-eye";
	    } else {
	        pw.type = "password";
	        icon.className = "fa-regular fa-eye-slash";
	    }
	}
</script>
</body>
</html>