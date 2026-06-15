<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


</head>
<body>
<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<h1>新規会員登録</h1>
<hr>
<form method="POST" action="/a2 main/RegistServlet">
ユーザーID*<input type="text" name="userID"><br>
パスワード*<input type="text" name="password"><br>
ユーザー名*<input type="text" name="user"><br>
性別<input type="radio" name="gender" value="male"> 男性
　　　<input type="radio" name="gender" value="female"> 女性
　　　<input type="radio" name="gender" value="other"> その他<br>
身長*<input type="double" name="height"><br>
目標体重*<input type="double" name="target weight"><br>

<p>*必須項目</p>

<input type="submit" name="regist" value="リセット"><br>
<input type="submit" name="regist" value="登録"><br>


</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>
<script>
'use strict';

document.getElementById('form').onsubmit = function(event){
let id = document.getElementById('form').userID.value;
let pw = document.getElementById('form').password.value;
let name = document.getElementById('form').name.value;
let height = document.getElementById('form').height.value;
let weight = document.getElementById('form').weight.value;
if (id === '' || pw === '' || name === '' || height === '' || weight === ''){
document.getElementById('msg').textContent = '必須項目を入力してください';
event.preventDefault();
  }
}

</script>


</footer>
<!--　フッターここまで　-->
</body>
</html>