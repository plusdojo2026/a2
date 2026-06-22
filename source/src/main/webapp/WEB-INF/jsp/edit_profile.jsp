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
<c:out value="${message}"/>
<form method=POST action="/a2/EditProfileServlet">
<p>　ID：<c:out value="${userInfo.userId}"/></p>
<input type="hidden" name="userId" value="${userInfo.userId}">
<div>
	<table>
		<tr>
			<th>ユーザー名</th>
			<td><input type="text" name="userName" value="${userInfo.userName}"></td>
		<tr>
		<tr>
			<th>身長</th>
			<td><input type="text" name="height" id="height" value="${userInfo.height}"></td>
		<tr>
		<tr>
			<th>性別</th>
			<td>
				男:<input type="radio" name="gender" value="man" 
				${userInfo.gender == 'man' ? 'checked' : ''}>
				女:<input type="radio" name="gender" value="woman" 
				${userInfo.gender == 'woman' ? 'checked' : ''}>
				その他:<input type="radio" name="gender" value="other" 
				${userInfo.gender == 'other' ? 'checked' : ''}>
			</td>
		<tr>
		<tr>
			<th>目標体重</th>
			<td><input type="text" name="targetWeight" id="targetWeight" value="${userInfo.targetWeight}"></td>
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
<script>
'use strict'
document.querySelector("form").addEventListener("submit", function(event) {

	const height = document.querySelector("input[name='height']").value.trim();
	const targetWeight = document.querySelector("input[name='targetWeight']").value.trim();
	
	// 数値チェックのコード
	const numberCheck = /^[0-9]+(\.[0-9]+)?$/;
	
	// 身長チェック
	if (!numberCheck.test(height) || Number(height) <= 0) {
		alert("身長は0より大きい数値を入力してください");
		event.preventDefault();
		return;
	}
	
	// 目標体重チェック
	if (!numberCheck.test(targetWeight) || Number(targetWeight) <= 0) {
		alert("目標体重は0より大きい数値を入力してください");
		event.preventDefault();
		return;
	}
});


</script>

</body>
</html>