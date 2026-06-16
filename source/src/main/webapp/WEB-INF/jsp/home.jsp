<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホームページ</title>


<style>
    /* モーダルの背景（暗い部分） */
    .modal-background {
      display: none; /* 最初は非表示 */
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
      z-index: 10;
    }

    /* モーダルの本体 */
    .modal-content {
      background-color: white;
      width: 300px;
      margin: 100px auto;
      padding: 20px;
      border-radius: 10px;
      text-align: center;
      z-index: 11;
    }

    /* 閉じるボタン */
    .close-btn {
      margin-top: 10px;
    }
  </style>


</head>
<body>

<!--　ヘッダーここから　-->
<header>



</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>



<h1>入力欄</h1>





<h2>基本データ</h2>
体重(kg)　<input type="text" name="weight"><br>
体脂肪率(％)<input type="text" name="fat"><br>

<br>
<h2>カスタムデータ</h2><br>


<input type="submit" name="saveb" value="保存">
<button onclick="openModal()">＋項目を追加</button>
<input type="submit" name="deli" value="ー項目を削除">

<!-- <form method="POST" action="/a2/HomeServlet"> -->
  
   <div id="modal" class="modal-background">
    <div class="modal-content">
      <p>項目を追加</p>
      
      項目：
      <input type="text" name="text" value="aaaa"><br>
      メモ：<input type="radio" name="memo" value="1">有
      		<input type="radio" name="memo" value="2" checked>無<br>
      <div style="display: none" id="tx">
     	 <textarea name="memo" ></textarea>
      </div>
<!-- </form> -->
      <button class="close-btn" onclick="closeModal()">閉じる</button>
      <button class="addi-btn" onclick="closeModal()">追加する</button>
    </div>
  </div>



  




</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->

<script>



    
    
    
    /* document.getElementById("tx").style.display = "none"; */
    
   // モーダル表示
    function openModal() {
      document.getElementById("modal").style.display = "block";
    }

    // モーダル非表示
    function closeModal() {
      document.getElementById("modal").style.display = "none";
    }
    
    
  //メモの有無でメモ欄を表示
    function closeModal(){
    	//JSPの内容を取ってきている　memoという変数を作っている
    	//input[name="memo"]:checkedの意味　name="memo"が使われている、inputを取ってくる。checkedは選ばれている方
    	//valueは取得する物自体。今回の場合は（1,2のどちらか）
    	
    	const memo = document.querySelector('input[name="memo"]:checked').value;
    	
    	
        
    	
    	if(memo == "1"){
    		//有を選択（１を選択されたとき）　blockは　displayを使用したときに使える「要素を表示する」単語
    		document.getElementById("tx").style.display = "block";
    	}else{
    		//無を選択（２を選択されたとき）noneは非表示
    		 document.getElementById("tx").style.display = "none";
    	}
    	
    }
    
    
   /*  function closeModal() {
        alert("実行された");
    } */

  </script>

</body>
</html>