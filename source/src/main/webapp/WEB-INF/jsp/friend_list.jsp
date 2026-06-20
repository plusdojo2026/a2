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
		<form action="FriendAddServlet" method="POST">
			<label for="searchId">ID検索：</label>
			<input type="text" id="searchId" name="searchId" placeholder="ユーザーIDを入力">
			<button type="submit">🔍</button>
		</form>
	</div>
	
	<section class="friend-list-section">
        <h2>フレンド一覧 <button type="button" id="toggle-delete-btn">🗑️</button></h2>
        
        <!-- フレンドリストに何かが入っていたら表示する -->
                <ul class="friend-list">
                
                <!-- フレンド表示のループ -->
                    <c:forEach var="f" items="${friendList}">
                        <li class="friend-item">
                            <div class="friend-info">
                                <div class="friend-icon"style="cursor: pointer;" 
      							 onclick="openModal('${friend.icon}','${f.userId}','${f.userName}','${f.point}','${f.training}')">${f.icon}
                                <span class="friend-id">${f.userId}</span><br>
                                <span class="friend-name">${f.name}</span><br>
                                <span class="friend-point">${f.point}</span><br>
                                </div>
                            </div>
                            
                            <div class="friend-action delete-target" style="display: none;">
                                <label>
                                    <input type="checkbox" name="deleteIds" value="${f.id}"> 削除する
                                </label>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                
                <div class="delete-button-area delete-target" style="margin-top: 15px; display: none;">
                    <button type="submit" class="delete-btn">選択したフレンドを削除</button>
                </div>

        </section>
</main>
<footer>

</footer>
<div id="friend-modal">
    <div>
        <h3>フレンド詳細</h3>
        <p><span id="modal-icon"></span></p>
        <p>ID: <span id="modal-Id"></span></p>
        <p>名前: <span id="modal-name"></span></p>
        <p>豆ぽ: <span id="modal-point"></span></p>
        <p>トレーニングメニュー: <span id="modal-training"></span></p>
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
    function openModal(icon,Id,name, point, training) {
    	document.getElementById("modal-icon").innerText = icon;
    	document.getElementById("modal-Id").innerText = Id;
        document.getElementById("modal-name").innerText = name;
        document.getElementById("modal-point").innerText = point;
        document.getElementById("modal-training").innerText = training;
        document.getElementById("friend-modal").style.display = "block";
    }
    function closeModal() {
        document.getElementById("friend-modal").style.display = "none";
    }
</script>
</body>
</html>