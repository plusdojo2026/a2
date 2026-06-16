<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループ一覧</title>
</head>
<body>
<header>

</header>
<main>
    <nav class="tab-menu">
        <ul>
            <li><a href="FriendListServlet">フレンド</a></li>
            <li><a href="GroupListServlet" class="active">グループ</a></li>
            <li><a href="RequestServlet">リクエスト</a></li>
            <li><a href="RankingServlet">ランキング</a></li>
        </ul>
    </nav>
    
    <div class="action-area">
        <button type="button" onclick="openCreateModal()"> 新規グループ作成</button>
    </div>
    
    <section class="group-list-section">
        <h2>グループ一覧 <button type="button" id="toggle-leave-btn">🏃</button></h2>
        
        <c:if test="${not empty groupList}">
            <form action="GroupLeaveServlet" method="post">
                <ul class="group-list">
                    
    <c:forEach var="group" items="${groupList}">
     <li class="group-item">
        
        <div class="group-info" style="cursor: pointer;" 
             onclick="openGroupModal('${group.groupId}', '${group.GroupName}')">
            
            <h3 class="group-name">${group.GroupName}</h3>
            
            <div class="member-icons">
                <c:forEach var="icon" items="${group.iconList}">
                    
                    <span class="Group-icon">${icon}</span>
                    
                </c:forEach>
                </div>
            
        </div>
        
        <div class="group-action leave-target" style="display: none;">
            <label>
                <input type="checkbox" name="leaveIds" value="${group.groupId}">
            </label>
        </div>
        
    </li>
</c:forEach>
                    
                </ul>
                
                <div class="leave-button-area leave-target" style="display: none;">
                    <button type="submit" class="leave-btn">グループから脱退する</button>
                </div>
            </form>
        </c:if>
    </section>
</main>
<footer>

</footer>

<div id="create-modal" style="display: none;">
    <div class="modal-content">
        <h3>グループ作成</h3>
        <p>※ここに後で作成フォームを作ります！</p>
        <button type="button" onclick="closeCreateModal()">閉じる</button>
    </div>
</div>

<script>
    // 脱退モードの切り替え
    document.addEventListener("DOMContentLoaded", function() {
        const toggleBtn = document.getElementById("toggle-leave-btn");
        const leaveTargets = document.querySelectorAll(".leave-target");

        if (toggleBtn) {
            toggleBtn.addEventListener("click", function() {
                leaveTargets.forEach(function(target) {
                    if (target.style.display === "none") {
                        target.style.display = "block";
                    } else {
                        target.style.display = "none";
                    }
                });
            });
        }
    }); 

    // 新規作成モーダルを開く・閉じる
    function openCreateModal() {
        document.getElementById("create-modal").style.display = "block";
    }

    function closeCreateModal() {
        document.getElementById("create-modal").style.display = "none";
    }
</script>
</body>
</html>