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
             onclick="openGroupModal(this, '${group.groupId}', '${group.GroupName}')">
            
            <h3 class="group-name">${group.GroupName}</h3>
            
            <div class="member-icons" style="display: flex; gap: 5px;">
                <c:forEach var="member" items="${group.memberList}">
                    <span class="Group-icon">${member.icon}</span>
                </c:forEach>
            </div>
            
            <div class="modal-members-data" style="display: none;">
                <c:forEach var="member" items="${group.memberList}">
                    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 8px; font-size: 16px;">
                        <span>${member.icon}</span>
                        <span>${member.name}</span>
                    </div>
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

<div id="group-modal" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5);">
    <div class="modal-content" style="background: white; width: 300px; margin: 100px auto; padding: 20px; border-radius: 10px; text-align: center;">
        
        <h3 id="modal-group-name">グループ名</h3>
        
        <p style="font-size: 14px; color: #666; margin-bottom: 5px;">現在のメンバー</p>
        
        <div id="modal-members-area" style="max-height: 120px; overflow-y: auto; border: 1px solid #ddd; padding: 10px; margin-bottom: 20px; background: #f9f9f9; border-radius: 5px;">
            </div>
        
        <div style="margin: 20px 0;">
            <a id="modal-add-member-btn" href="#" style="display: block;  padding: 10px; text-decoration: none; border-radius: 5px; font-weight: bold; margin-bottom: 10px;">
                ➕ メンバーを追加する
            </a>
        </div>
        
        <button type="button" onclick="closeGroupModal()" style="padding: 5px 20px; cursor: pointer;">閉じる</button>
    </div>
</div>

<script>
    
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

   
    function openGroupModal(element, groupId, groupName) {
        document.getElementById("modal-group-name").innerText = groupName;
        
       
        const membersHtml = element.querySelector(".modal-members-data").innerHTML;
        document.getElementById("modal-members-area").innerHTML = membersHtml;
        
        document.getElementById("modal-add-member-btn").href = "GroupAddServlet?groupId=" + groupId;
        document.getElementById("group-modal").style.display = "block";
    }

    function closeGroupModal() {
        document.getElementById("group-modal").style.display = "none";
    }
</script>
</body>
</html>