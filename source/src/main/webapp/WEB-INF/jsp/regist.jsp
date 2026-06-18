<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>


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
<form method="POST" action="/a2/RegistServlet" id="fr">
	ユーザーID*<input type="text" name="user_id"><br>
	パスワード*<input type="text" name="password"><br>
	ユーザー名*<input type="text" name="user"><br>
	性別<input type="radio" name="gender" value="male"> 男性
	　　　<input type="radio" name="gender" value="female"> 女性
	　　　<input type="radio" name="gender" value="other" checked> その他<br>
	身長*<input type="number" step="0.1" name="height"><br>
	目標体重*<input type="number" step="0.1" name="target_weight"><br>
	
	<p id="msg" style="color:red">*必須項目</p>
	<p style="color:red">${result}</p> <!-- home.jspにもこれを書いたら登録完了の表示ができるよ -->
	


<input type="button"  onclick="re()" value="リセット">
<input type="submit"  value="登録">
</form>
</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>
<script>
'use strict';

document.getElementById('fr').onsubmit = function(event){
	let id = document.getElementById('fr').user_id.value;
	let pw = document.getElementById('fr').password.value;
	let name = document.getElementById('fr').user.value;
	let height = document.getElementById('fr').height.value;
	let weight = document.getElementById('fr').target_weight.value;
	if (id === '' || pw === '' || name === '' || height === '' || weight === ''){
		document.getElementById('msg').textContent = '必須項目を入力してください';
		event.preventDefault();
 	}
}
//リセットボタンを押したときの処理
function re(){	
	//document.getElementById('fr').user_id.value="";
	//document.getElementById('fr').password.value="";
	//document.getElementById('fr').user.value="";
	document.getElementById('fr').reset();
	//document.getElementById('fr').height.value="";
	//document.getElementById('fr').target_weight.value="";
	
	
}

</script>


</footer>
<!--　フッターここまで　-->
</body>
</html>