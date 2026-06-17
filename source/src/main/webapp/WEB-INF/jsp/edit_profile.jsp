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
<form method=POST action="/a2/EditProfileServlet.java">
<p>　ID：<c:out value="${userInfo.userId}"/></p>
<div>
	<table>
		<tr>
			<th>ユーザー名</th>
			<td><input type="text" value="${userInfo.userName}"></td>
		<tr>
		<tr>
			<th>身長</th>
			<td><input type="text" value="${userInfo.height}"></td>
		<tr>
		<tr>
			<th>性別</th>
			<td>
				<input type="radio" name="gender" value="woman">
				<input type="radio" name="gender" value="man">
				<input type="radio" name="gender" value="other">
			</td>
		<tr>
		<tr>
			<th>目標体重</th>
			<td><input type="text" value="${userInfo.targetWeight}"></td>
		<tr>
	</table>
</div>
<a href="/a2/MyPageServlet">戻る</a>
<input type="submit" value="更新">
</form>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->


</body>
</html>