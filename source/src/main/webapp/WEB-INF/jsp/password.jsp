<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
</head>
<body>
<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<form method=POST action="/a2/PasswordServlet" id="form">
<input type="hidden" value="${userInfo.userId}">
<table>
    <tr>
        <td>現在のパスワード</td>
        <td>
        	<input type="password" id="password1">
        </td>

    </tr>
    <tr>
        <td>新しいパスワード</td>
        <td>
        	<input type="password" name="password" id="password2">
        </td>

    </tr>
    <tr>
        <td>新しいパスワード</td>
        <td>
        	<input type="password" id="password3">
        </td>
 
    </tr>
</table>  
    <button type="button" id="show">
    	<i class="fa-regular fa-eye-slash" id="icon"></i>
    </button>
    
    <input type="submit" value="変更">
</form>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
<script>
	'use strict';
	//パスワード表示切替
	const pas1=document.getElementById('password1');
	const pas2=document.getElementById('password2');
	const pas3=document.getElementById('password3');
	const ele=document.getElementById('icon');
	document.getElementById('show').onclick = function(){
		if(pas1.type==="password"){
			pas1.type="text";
			pas2.type="text";
			pas3.type="text";
			ele.className="fa-regular fa-eye";
		}else{
			pas1.type="password";
			pas2.type="password";
			pas3.type="password";
			ele.className="fa-regular fa-eye-slash";
		}
	}
	//現在のパスワードと照らし合わせ
	const nowPw = '${userInfo.password}';
    document.getElementById('form').onsubmit = function(event){

        const pasCheck1 = document.getElementById('password1').value;
        const pasCheck2 = document.getElementById('password2').value;
        const pasCheck3 = document.getElementById('password3').value;
		//空白をはじく
         if( pasCheck1 === '' || pasCheck2 === '' || pasCheck3 ===''){
            document.getElementById('msg').textContent = '※すべてのパスワードを入力してください。';
            event.preventDefault();
        //現在のパスワード不一致をはじく
        }else if(nowPw !== pasCheck1){
        	document.getElementById('msg').textContent = '※現在のパスワードが間違っています。';
            event.preventDefault();
        //新しいパスワード不一致をはじく
        }else if(pasCheck2 !== pasCheck3){
        	document.getElementById('msg').textContent = '※新しいパスワードが一致しませんでした。';
            event.preventDefault();
        }
	}		
</script>
</body>
</html>