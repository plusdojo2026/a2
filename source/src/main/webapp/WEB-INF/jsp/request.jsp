<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>リクエスト一覧</title>
</head>
<body>

<nav class="tab-menu">
		<ul>
			<li><a href="/a2/FriendListServlet" class="active">フレンド</a></li>
            <li><a href="/a2/FriendrequestsServlet">リクエスト</a></li>

		</ul>
	</nav>
	
<main style="max-width: 500px; margin: 50px auto; padding: 20px;  border-radius: 10px;">


	    <h2>フレンド申請</h2>
    <ul style="list-style: none; padding: 0;">
        <c:forEach var="req" items="${rqSearch}">
            <li style="padding: 15px; border-bottom: 1px solid #eee; display: flex; align-items: center; justify-content: space-between;">
                
                <div style="display: flex; align-items: center; gap: 10px;">
                    <span style="font-size: 30px;">${req.iconId}</span>
                    <span style="font-size: 12px;">${req.userId}</span>
                    <span style="font-size: 18px; font-weight: bold;">${req.userName}</span>
                    <span style="font-size: 14px; color: #666;">さんから</span>
                </div>
                
                <form action="/a2/FriendrequestsServlet" method="POST" style="margin: 0;">
                    <input type="hidden" name="targetUserId" value="${req.userId}"> 
                    
                    <div style="display: flex; gap: 10px;">
                    
                        <button type="submit" name="action"  value="approve" style="background-color: #28a745; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-weight: bold;">承認</button>
                        <button type="submit" name="action"  value="reject" style="background-color: #dc3545; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-weight: bold;">拒否</button>
                   
                    </div>
                </form>
            </li>
        </c:forEach>
    </ul>
    
	    <h2>申請中</h2>
    <ul style="list-style: none; padding: 0;">
        <c:forEach var="req" items="${fmSearch}">
            <li style="padding: 15px; border-bottom: 1px solid #eee; display: flex; align-items: center; justify-content: space-between;">
                
                <div style="display: flex; align-items: center; gap: 10px;">
                    <span style="font-size: 30px;">${req.iconId}</span>
                    <span style="font-size: 12px;">${req.userId}</span>
                    <span style="font-size: 18px; font-weight: bold;">${req.userName}</span>
                    <span style="font-size: 14px; color: #666;">さんへ</span>
                </div>

            </li>
        </c:forEach>
    </ul>
    <div style="margin-top: 30px; text-align: center;">
        <a href="/a2/FriendListServlet" style="color: #007bff; text-decoration: none; font-weight: bold;">フレンド一覧に戻る</a>
    </div>
</main>
<script>
	
</script>
</body>
</html>