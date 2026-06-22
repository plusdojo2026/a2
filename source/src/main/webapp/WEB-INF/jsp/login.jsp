<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<h1>マメッスル</h1>
<section>
<!-- /プロジェクト名/サーブレットの名前 -->
<form action="/a2/LoginServlet" id="form" method="post">
<div class="form-container">
<div class="form-group">
<label for="user-id">ユーザーID</label><br>
<input type="text" name="user_id" value="${param.user_id}"><br>
</div>
<div class="form-group">
<label for="password">パスワード</label><br>
<input type="password" name="password">
</div>    
</p>
<p>
<input type="submit" value="ログイン">
	<div style="color:red">
		${result}
	</div>
</p>
<p>新規会員登録は</p><a href="/a2/NewRegistServlet">こちら</a>
</div>
</form>
</section>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
</div>
</body>
</html>