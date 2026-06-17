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
<form action="/webapp/LoginServlet" id="form" method="post">
<p>
ID<input type="text" name="ID">
</p>
<p>
PW<input type="password" name="PW">      
</p>
<p>
<input type="submit" value="ログイン">
</p>
<p>新規会員登録は</p><a href="/webapp/jsp/resist.jsp">こちら</a>
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