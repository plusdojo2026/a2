<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー追加</title>
</head>
<body>

<main style="max-width: 400px; margin: 50px auto; padding: 20px; text-align: center;">
    <h2>メンバーを追加する</h2>
    <p style="color: #666; font-size: 14px;">グループに追加したいフレンドを選んでください。</p>
    
    <form action="GroupAddServlet" method="post">
        
        <input type="hidden" name="groupId" value="${groupId}">
        
        <ul style="list-style: none; padding: 0; text-align: left; margin-bottom: 20px;">
            
            <c:forEach var="friend" items="${friendList}">
                <li style="padding: 10px; border-bottom: 1px solid #eee;">
                    <label style="cursor: pointer; display: flex; align-items: center; gap: 10px;">
                        
                        <input type="checkbox" name="addMemberIds" value="${friend.userId}">
                        
                        <span style="font-size: 24px;">${friend.icon}</span>
                        <span style="font-size: 18px;">ID:${friend.userId}</span>
                        <span style="font-size: 18px;">ユーザー名:${friend.name}</span>
                        
                    </label>
                </li>
            </c:forEach>
            
        </ul>
        
        <div style="display: flex; justify-content: center; gap: 10px;">
            <a href="GroupListServlet" style="padding: 10px 20px; background-color: #ccc; color: black; text-decoration: none; border-radius: 5px;">キャンセル</a>
            
            <button type="submit" style="padding: 10px 20px; background-color: #28a745; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;">
                チェックした人を追加
            </button>
        </div>
        
    </form>
</main>

</body>
</html>