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
<form method=POST action="/a2/DeleteAccountServlet.java">
<div class="my_page_user_info">
    <div class="circle">
    </div>
    <div class="user_id_name">
        <p>ID:${user_id}</p>
            <br>
        <p>name：${user_name}</p>
    </div>
</div>

<p>上記アカウントの退会手続きを行います。<br>
退会後は、アカウントの利用・ログインが出来なくなります。
</p>
<input type="submit" value="退会する">
</form>

<a href="/a2/MyPageServlet.java">戻る</a>

</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->
</body>
</html>