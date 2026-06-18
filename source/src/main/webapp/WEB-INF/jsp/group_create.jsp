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
			<li><a href="FriendListServlet">フレンド</a></li>
			<li><a href="GroupListServlet" class="active">グループ</a></li>
            <li><a href="RequestServlet">リクエスト</a></li>
            <li><a href="RankingServlet">ランキング</a></li>
		</ul>
	</nav>
	
	
	
	<section class="Group-Create-section">
        <h2>グループ作成 </h2>
        
        <div class="create-area">
		<form action="GroupCreateServlet" method="get">
			<div style="text-align: center;">
			<label for="create" >グループ名</label><br>
			<input type="text" id="createId" name="createId" placeholder="グループ名を入力"><br>
			</div>
			<div style="text-align: center;">
			<label for="create-menu" >グループ内で共有したい種目を選択してください</label><br>
			<input type="text" id="create-menu-Id" name="create-menu-Id" >	
			</div>
			<div class="group-create-menu" onclick="openModal('あ','あ')">
			<button type="button"  style="padding: 5px 20px; cursor: pointer;">作成</button>
			</div>
		</form>
		</div>
	
			
                
                
      
        </section>
</main>
<footer>

</footer>
<div id="group-create-modal" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5);">
    <div style="background: white; width: 300px; margin: 100px auto; padding: 20px; border-radius: 10px; text-align: center;">
        <h3>グループを作成しますか？</h3>
        <p>グループ名<span id="modal-group-name"></span></p>
        <p>種目<span id="modal-training-menu"></span></p>
        <div id="modal-create-area" style="max-height: 120px; overflow-y: auto; border: 1px solid #ddd; padding: 10px; margin-bottom: 20px; background: #f9f9f9; border-radius: 5px;">
        </div>
        <button type="button" onclick="closeModal()">いいえ</button>
        <a id="modal-group-create-btn">
        <button type="submit" >はい</button>
        </a>
    </div>
</div>
<script> 
    function openModal(namu,menu) {
    	document.getElementById("modal-group-name").innerText = namu;
    	document.getElementById("modal-training-menu").innerText = menu;
        document.getElementById("group-create-modal").style.display = "block";
    
    	const membersHtml = element.querySelector(".modal-create-data").innerHTML;
    	document.getElementById("modal-create-area").innerHTML = membersHtml;
    
    	
    }
    function closeModal() {
        document.getElementById("group-create-modal").style.display = "none";
    }
    
    	document.getElementById("modal-group-create-btn").href = "GroupAddServlet" 
    
</script>
</body>
</html>