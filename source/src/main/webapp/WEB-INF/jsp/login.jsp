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
<input type="password" name="password">
</div>    

<div class="button-area">
<input type="submit" value="ログイン">
	<div style="color:green; margin-top: 10px;">
		${result}
	</div>
</div>


<div class="register-link">
<p>新規会員登録は</p><a href="/a2/NewRegistServlet">こちら</a>
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
</body>
</html>