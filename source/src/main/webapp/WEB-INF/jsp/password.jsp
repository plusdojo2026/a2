<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<table>
    <tr>
        <td>古いパスワード</td>
        <td>
        	<input type="password" id="password1">
        	<span id="show">
        		<i class="far fa-eye-slash"></i>
        	</span>
        </td>
    </tr>
    <tr>
        <td>新しいパスワード</td>
        <td><input type="password" id="password2"></td>
    </tr>
    <tr>
        <td>新しいパスワード</td>
        <td><input type="password" id="password3"></td>
    </tr>
</table>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
<script>
	'USE STRICT';
	let show=document.getElementById('show');
	let noshow=document.getElementById('password');
	$('#show').on('click',function(){
		if(noshow.type==='password'){
			noshow.type ='text';
			show.innerHTML='<i class="far fa-eye"></i>';	
		}else{
			noshow.type ='password';
			show.innerHTML='<i class="far fa-eye-slash"></i>';
		}
	});
</script>
</body>
</html>