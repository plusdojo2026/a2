<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マメッスル</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
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
<p>
ID<input type="text" name="user_id" value="${param.user_id}">
</p>
<p>
PW<input type="password" name="password">      
</p>
<p>
<input type="submit" value="ログイン">
	<div style="color:red">
		${result}
	</div>
</p>
<p>新規会員登録は</p><a href="/a2/NewRegistServlet">こちら</a>
</form>
</section>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
</body>
</html>