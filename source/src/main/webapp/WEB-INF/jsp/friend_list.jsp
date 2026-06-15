<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フレンド一覧</title>
</head>
<body>
<header>

</header>
<main>
	<nav class="tab-menu">
		<ul>
			<li><a href="FriendListServlet" class="active">フレンド</a></li>
			<li><a href="GroupListServlet">グループ</a></li>
            <li><a href="RequestServlet">リクエスト</a></li>
            <li><a href="RankingServlet">ランキング</a></li>
		</ul>
	</nav>
	
	<div class="search-area">
		<form action="FriendAddServlet" method="get">
			<label for="searchId">ID検索：</label>
			<input type="text" id="searchId" name="searchId" placeholder="ユーザーIDを入力">
			<button type="submit">🔍</button>
		</form>
	</div>
	
	<section class="friend-list-section">
        <h2>フレンド一覧 <button type="button" id="toggle-delete-btn">🗑️</button></h2>
        
        <c:if test="${not empty friendList}">
            <form action="FriendDeleteServlet" method="post">
                <ul class="friend-list">
                    <c:forEach var="friend" items="${friendList}">
                        <li class="friend-item">
                            <div class="friend-info">
                                <span class="friend-icon"style="cursor: pointer;" 
      							onclick="openModal('${friend.userName}', '${friend.beanPoints}')">🟢</span>
                                <span class="friend-id">${friend.userId}</span><br>
                                <span class="friend-name">${friend.Name}</span><br>
                                <span class="friend-point">${friend.Point}</span><br>
                            </div>
                            
                            <div class="friend-action delete-target" style="display: none;">
                                <label>
                                    <input type="checkbox" name="deleteIds" value="${friend.id}"> 削除する
                                </label>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                
                <div class="delete-button-area delete-target" style="margin-top: 15px; display: none;">
                    <button type="submit" class="delete-btn">選択したフレンドを削除</button>
                </div>
            </form>
        </c:if>
        </section>
</main>
<footer>

</footer>
<div id="friend-modal" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5);">
    <div style="background: white; width: 300px; margin: 100px auto; padding: 20px; border-radius: 10px; text-align: center;">
        <h3>フレンド詳細</h3>
        <p>名前: <span id="modal-name"></span></p>
        <p>豆ぽ: <span id="modal-point"></span></p>
        <button type="button" onclick="closeModal()">閉じる</button>
    </div>
</div>

<script>
	//ゴミ箱ボタンチェックボックス
    document.addEventListener("DOMContentLoaded", function() {
        const toggleBtn = document.getElementById("toggle-delete-btn");
        const deleteTargets = document.querySelectorAll(".delete-target");

        if (toggleBtn) {
            toggleBtn.addEventListener("click", function() {
                deleteTargets.forEach(function(target) {
                    if (target.style.display === "none") {
                        target.style.display = "block";
                    } else {
                        target.style.display = "none";
                    }
                });
            });
        }
    }); 

    // モーダル
    function openModal(name, point) {
        document.getElementById("modal-name").innerText = name;
        document.getElementById("modal-point").innerText = point;
        document.getElementById("friend-modal").style.display = "block";
    }

    function closeModal() {
        document.getElementById("friend-modal").style.display = "none";
    }
</script>
</body>
</html>