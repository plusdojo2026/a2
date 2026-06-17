<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<c:out value="${message.message}"/>
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


</footer>
<!--　フッターここまで　-->
</body>
</html>