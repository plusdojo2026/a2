<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フレンド一覧</title>
<style>
.modal-bg{
    display:none;
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background: rgb(135, 247, 163);
}
.modal{
    background:white;
    width:300px;
    margin:100px auto;
    padding:20px;
}
</style>

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
		<form action="FriendListServlet" method="POST">
			<label for="searchId">ID検索：</label>
			<input type="text" id="searchId" name="searchId" placeholder="ユーザーIDを入力">
			<button type="submit">🔍</button>
		</form>
	</div>
	
	<section class="friend-list-section">
        <h2>フレンド一覧 <button type="button" id="toggle-delete-btn">🗑️</button></h2>
             <!-- <ul class="friend-list"> -->
             <!-- フレンド表示のループ -->
                 <c:forEach var="f" items="${friendFullList}">
                         <!-- <div class="friend-info"> -->
                         
                             <div class="friend-icon"style="cursor: pointer;" 
   							 onclick="openModal('${f.friend.friendUserId}')">
   							 ${f.friend.friendUserId}
	                             <span class="friend-id">${f.friend.userId}</span><br>
	                             <span class="friend-name">${f.friendInfo.userName}</span><br>
	                             <span class="friend-point">${f.friendInfo.point}</span><br>
                             </div>
                             
                         <!-- </div> -->
                         <div class="friend-action delete-target" style="display: none;">
                             <label>
                                 <input type="checkbox" name="deleteIds" value="${f.friend.friendUserId}"> 削除する
                             </label>
                         </div>
        			<br>
                 </c:forEach>
<!--              </ul>
                
                <div class="delete-button-area delete-target" style="margin-top: 15px; display: none;">
                    <button type="submit" class="delete-btn">選択したフレンドを削除</button>
                </div> -->

        </section>
</main>
<footer>

</footer>

<!-- モーダル -->
	<div id="friend-modal" class="friend-modal">
	    <div class="modal">
	    	<!-- 名前とIDの表示 -->
	    	<h4 id="icon-id">${f.friend.iconId}</h4>
	        <h4 id="friend-id">${f.friend.userId}</h4>
	        <h4 id="friend-name">${f.friendInfo.userName}</h4>
	        <h4 id="friend-point">${f.friendInfo.point}</h4>
	        
	        <h4>トレーニング内容</h4>
	            <div id="modal-training-area" class="training-area"></div>
	        
	        <div class="close-btn" onclick="closeModal()">閉じる</div>
	    
	    </div>
	</div>
<script>
'use strict'


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
    //JSON
	const friendDataJs = JSON.parse('${friendDataJson}');
function openModal(friendUserId) {
	// 対象データを探す
	const data = friendDataJs.find(f => f.friend.friendUserId === friendUserId);
	if (!data) return;
	// 表示データセット
	document.getElementById("icon-id").innerText = data.friendInfo.iconId;
	document.getElementById("friend-id").innerText = data.friend.friendUserId;
	document.getElementById("friend-name").innerText = data.friendInfo.userName;
	document.getElementById("friend-point").innerText = data.friendInfo.point;
	// トレーニング表示
	let html = "";
	for (let tr of data.latestTraining) {
		html +=
		    "<div>" +
		    (tr.trId || "不明") + "<br>" +
		    (tr.trWeight || 0) + "kg " +
		    (tr.count || 0) + "rep " +
		    (tr.sets || 0) + "set" +"<br>" +
		    (tr.memo || "") + 
		    "</div>";
	}
	document.getElementById("modal-training-area").innerHTML = html;
	// 表示
	document.getElementById("friend-modal").style.display = "block";
}
function closeModal() {
    document.getElementById("friend-modal").style.display = "none";
}



</script>
</body>
</html>