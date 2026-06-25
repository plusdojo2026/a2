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

<div class="my_page_user_info">
    <div class="circle">
    </div>
    <div class="user_id_name">
    ID:<c:out value="${userInfo.userId}"/><br>
        name：<c:out value="${userInfo.userName}"/>
    </div>
</div>

<p>上記アカウントの退会手続きを行います。<br>
退会後は、アカウントの利用・ログインが出来なくなります。
</p>
<form method=POST action="/a2/DeleteAccountServlet">
<input type="hidden" value="${userInfo.userId}" name="userId">
<input type="submit" value="退会する">
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