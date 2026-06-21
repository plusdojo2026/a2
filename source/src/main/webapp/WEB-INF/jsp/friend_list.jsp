<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>フレンド一覧</title>
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>

<style>
.modal-bg{
    display:none;
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.5);
    z-index:999;
}

/* モーダル本体 */
.friend-modal {
    display:none;
    position:fixed;
    top:50%;
    left:50%;
    transform:translate(-50%, -50%);
    
    background:white;
    width:350px;
    max-width:90%;
    
    padding:20px;
    border-radius:10px;
    
    box-shadow:0 4px 10px rgba(0,0,0,0.3);
    
    z-index:1000;

    display:flex;   /* ←これ追加 */
    flex-direction:column;
    gap:10px;
}

/* 中身 */
.friend-modal .modal{
    display:flex;
    flex-direction:column;
    gap:10px;
}

/* 閉じるボタン */
.close-btn,.btn-secondary{
	position:fixed;
	top:0px;
	right:0px;
	
    margin:5px;
    padding:10px;
    
    background:#ff6666;
    color:white;
    
    text-align:center;
    border-radius:5px;
    
    cursor:pointer;
}

.close-btn:hover{
    background:#ff4444;
}

/* トレーニング表示 */
.training-area{
    max-height:200px;
    overflow-y:auto;
    
    padding:10px;
    background:#f5f5f5;
    border-radius:5px;
}
</style>

</head>
<body>
<!--*=====ヘッダーここから=====*-->
<header>

</header>
<!--*=====ヘッダーここまで=====*-->
<!--*=====メインここから=====*-->
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
		<form action="/a2/FriendListServlet" method="POST" id="form">
			<label for="searchId">ID検索：</label>
			<input type="text" id="searchId" name="searchId" placeholder="ユーザーIDを入力">
			<button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
		</form>
	</div>
	
	
	
	<form action="FriendRequestServlet" method="POST">
		
	</form>
	
	
	<section class="friend-list-section">
		<h2>フレンド一覧 <button type="button" id="toggle-delete-btn"><i class="fa-solid fa-trash"></i></button></h2>

			<form action="FriendDeleteServlet" method="POST">
			
			
			<!-- フレンド表示のループ -->
				<c:forEach var="f" items="${friendFullList}">
					<!-- <div class="friend-info"> -->
					<div class="friend-icon"style="cursor: pointer;" 
					onclick="openModal('${f.friend.friendUserId}')">
						ID:<span class="friend-id">${f.friend.friendUserId}</span><br>
						<span class="friend-name">${f.friendInfo.userName}</span><br>
						<span class="friend-point">${f.friendInfo.point}</span>:pt<br>
					</div>
						
					<!-- </div> -->
					<div class="friend-action delete-target" style="display: none;">
						<label>
							<input type="checkbox" name="deleteIds" value="${f.friend.friendUserId}"> 削除する
						</label>
					</div>
				</c:forEach>
                
                <div class="delete-button-area delete-target" style=" display: none;">
                    <button type="submit" class="delete-btn">選択したフレンドを削除</button>
                </div>
                </form>

        </section>
</main>
<!--*=====メインここまで=====*-->
<!--*=====フッターここから=====*-->
<footer>

</footer>
<!--*=====フッターここまで=====*-->
<!--*=====フレンドモーダル=====*-->
<div id="friend-modal" class="friend-modal" style="display:none;">
	<div class="modal">
	<!-- 名前とIDの表示 -->
		<span id="icon-id"></span>
		<span id="friend-id"></span>
		<span id="friend-name"></span>
		<span id="friend-point"></span>
		
		<h4>トレーニング内容</h4><span id="training-date"></span>
		<div id="modal-training-area" class="training-area"></div>
		   
		<div class="close-btn" onclick="closeModal()">閉じる</div>
		
	</div>
</div>
<!--*=====検索結果モーダル=====*-->
<c:if test="${not empty searchAns}">
	<div id="search-modal" class="friend-modal" style="display:none;">
		<div class="modal">
			<h5 class="modal-title">検索結果</h5>
				<div>
					ユーザーID: ${searchAns.userId}
				</div>
				<div>
					名前: ${searchAns.userName}
				</div>
				<!-- フレンド申請 -->
				<form action="FriendRequestServlet" method="post">
					<input type="hidden" name="targetUserId" value="${searchAns.userId}" id="sCheck">
					<button type="submit">
						フレンド申請
					</button>
				</form>
			<div class="btn-secondary" onclick="closeSearchModal()">閉じる</div>
		</div>
	</div>
</c:if>
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
	const date = data.latestTraining[0]?.date || "";
	if (!data) return;
	// 表示データセット
	document.getElementById("icon-id").innerText = data.friendInfo.iconId;
	document.getElementById("friend-id").innerText = "ID:"+ data.friend.friendUserId;
	document.getElementById("friend-name").innerText = "名前:"+data.friendInfo.userName;
	document.getElementById("friend-point").innerText = data.friendInfo.point + " pt";
	document.getElementById("training-date").innerText = date;
	
	// トレーニング表示
	let html = "";
	for (let tr of data.latestTraining) {
		html +=
			"<div>" +
			(tr.trItem || "不明") + "<br>" +
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
//既に登録済みのフレンドと自分へのフレンド登録を防ぐ
const alreadyFriendJs = JSON.parse('${alreadyFriendJson}');
document.getElementById('form').onsubmit = function(event){
	const userId = "${sessionScope.user.userId}";
	const searchId = document.getElementById('searchId').value;
	// friendDataJsonからフレンドID一覧を取得
	const friendIds = friendDataJs.map(
		f => f.friend.friendUserId
	);
	const alreadyFriendIds = alreadyFriendJs.map(
			f => f.friendUserId
		);
	// 自分を検索禁止
	if(searchId === userId){
		alert('自分は検索できません');
		event.preventDefault();
		return;
	}
	// 既にフレンド
	if(friendIds.includes(searchId)){
		alert('すでにフレンドです');
		event.preventDefault();
	}
	// 申請中
	if(alreadyFriendIds.includes(searchId)){
		alert('リクエスト中または、リクエスト承認待ちです。');
		event.preventDefault();
	}
}

//===検索モーダルscript===
document.addEventListener("DOMContentLoaded", function () {

	const hasResult = ${not empty searchAns};

    if (hasResult) {
        document.getElementById("search-modal").style.display = "block";
    }
});
function closeSearchModal() {
    document.getElementById("search-modal").style.display = "none";
}
</script>
</body>
</html>