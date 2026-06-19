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


<form method="POST" action="/a2/HomeServlet">
<h1>今日の記録</h1>
<input type="submit" name="savetime" value="一時保存">





<h2>基本データ</h2>

体重(kg)　<input type="text" name="weight"><br>
体脂肪率(％)<input type="text" name="fat"><br>
<br>
<br>
<input type ="text" id ="c" name = "coun"><br>
<!-- <input type ="hidden" id ="c" name = "count"><br> -->
<br>

一日メモ<br>
<textarea name="comments"></textarea>
<br>
スタンプ：
	<select id="stamp" onchange="changestamp()" name="stamp">
    	<option value="1">なし</option>
	    <option value="2">足トレ</option>
	    <option value="3">背中トレ</option>
	    <option value="4">腕トレ</option>
	    <option value="5">尻トレ</option>
	    <option value="6">腹トレ</option>
	    <option value="7">豆トレ</option>
	    <option value="8">酒</option>
	</select>

	<br>
	<br>

	<img id="stampImage" src="" width="200">
	
	
<br>
<h2>カスタムデータ</h2>
<div id="itemArea"></div>
<div id="memoArea"></div><br>




<input type="submit" name="saveb" value="保存">
</form>
<button onclick="openModal()">＋項目を追加</button>




<div id="memoArea"></div>
  
   <div id="modal" class="modal-background">
   
    <div class="modal-content">
      <p>項目を追加</p>
      <!--  下の文でここに警告文が出るようになる　使用するために id="msg”をつけた-->
      <div style="color:red" id="msg"></div>
      項目：
     <select name="trainingItem" id="item">
 		<c:forEach var="item" items="${itemList}">
        	<option value="${item}" >
            	${item}
        	</option>
		</c:forEach>    	
    </select>
	
      メモ：<input type="radio" name="memo" value="1">有
      		<input type="radio" name="memo" value="2" checked>無<br>
      <div style="display: none" id="tx">
     	 <textarea name="memo" ></textarea>
      </div>
<!-- </form> -->
      <button class="close-btn" onclick="closeModal()">閉じる</button>
      <button class="addi-btn" onclick="addItem()" >追加する</button>
    </div>
  </div>



  
  




</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<footer>


</footer>
<!--　フッターここまで　-->

<script>
	function addButton(){	
		
	}
	

    //画像表示する
    
    
    function changestamp(){
    	
    	/* alert("動いた"); */
    	
    	const stamp = document.getElementById("stamp").value;

        const image = document.getElementById("stampImage");
        
  
        
        if (stamp == "2") {
        	image.src = "img/stamp1.png";
        }else if (stamp == "3") {
        	image.src = "img/stamp2.png";
        }else if (stamp == "4") {
        	image.src = "img/stamp3.png";
        }else if (stamp == "5") {
        	image.src = "img/stamp4.png";
        }else if (stamp == "6") {
        	image.src = "img/stamp5.png";
        }else if (stamp == "7") {
        	image.src = "img/stamp6.png";
        }else if (stamp == "8") {
        	image.src = "img/stamp7.png";
        }else{
        	image.src = "";
        }
    	
    	
    }
	

    


	 function closeModal() {
 	    document.getElementById("modal").style.display = "none";

 	    // エラーメッセージを消す
 	    document.getElementById("msg").textContent = "";
 	}
 	
 	function openModal() {
 	    document.getElementById("modal").style.display = "block";

 	    // エラーメッセージを消す
 	    document.getElementById("msg").textContent = "";
 	}  
 	
    
    
    let count=0;
    function addItem(){
    	//とりあえずansをfalseとして宣言
    	let ans = false;
    	//すべての追加されたものにcountがつくので、追加されている分だけループする
    	for(let i = 0;i<count;i++){
    		//追加された項目にはit+1されるので、ひとつひとつの項目をチェック
    		//mの中にダンベルとかの種目が入る
    		
    		
    		/* let m = document.getElementById("it"+i).value; */
    		
    		let mm = document.getElementById("it"+i);
    		
    		if(mm !=null){
    			
    			let m = mm.value;
    			
    			//今ある種目と、直近でプルダウンから選んだものが一緒なら
        		if(m ==document.getElementById("item").value ){
        			//上のans変数の値をtrueに変更する
        			ans = true;    			
        		}
    			
    		}
    		
    		
    }
    	
    	

    	
    	//ひとつでも過去の種目と被っていたら
    	if(ans==true){
    		//エラーメッセージを出して
    		document.getElementById("msg").textContent="同じ項目は選べません"
    		//処理を終了する（ここより下の処理はさせない）
    		return;
    	}
    	//追加する場所のデータを取得してくる
		let itemArea=document.getElementById("itemArea");
    	const  div = document.createElement("div");
    	div.id = "item"+count;
    	//改行のことをbrという変数に入れている
    	const br = document.createElement("br");
    	
    	
    	
    	
    	
    	//テキストボックスに追加アイテムの作成（nameはit1~itn）
    	//ここではjspでは一行で書くものをJavaScriptから入れるので細分化している
    	
    	
    	//<input　inputをjspに入れるよ
    	const input = document.createElement("input");
    	//<input type="text"　input typeはtextですよ
    	input.type="text";
    	 //<input type="text" name="it?"　名前はitにします。+countを付けているのでit1,it2のように順番につきます
    	input.name="it"+count;
    	input.id = "it"+count; //id=it1,it2
    	//<input type="text" name="it?" readOnry　textの中身は変えられません。項目の部分なので書き換えられないようにする
    	input.readOnly="true";
    	//<input type="text" name="it?" readOnry value="ダンベル">　
		//上でユーザーが選択したでーたをvalue=に入れる文
    	input.value=document.getElementById("item").value; 
		
		const deleteButton = document.createElement("input");
		deleteButton.type="button";
		deleteButton.value="削除";

		
		deleteButton.onclick = function(){
			if(confirm("削除しますか？")){
				itemArea.removeChild(div);
				alert("削除しました");
				}
			}
    	
		
		
    	//テキストエリアのデータを作成
   		const textarea = document.createElement("textarea");
    	//ここもカウントを入れることで項目に着けた名前と連動する　it１が追加されたらmemo１
   		textarea.name = "memo"+count;
   		
   		//上記で追加したアイテムをitemAreaに入れる
   		//divというタグをつくり、変数piに入れた
   		//let pi = document.createElement("div");
   		//<div>項目</div>という形になる　タグの中に入れている
   		
   		//pi を itemArea (jspのitemAreaの位置）の中に追加
   		//itemArea.appendChild(pi);
   		//inputもitemArea (jspのitemAreaの位置）の中に追加
   		div.textContent = "項目";
   		div.appendChild(input);
   		div.appendChild(deleteButton);
		/* deleteButton.onclick = function(){
			itemArea.removeChild(div);
		} */
   		
   		 let input1 = document.createElement("input");
     	input1.type = "text";
     	/* input1.name = "text"; */
     	
    
   		
   		
   		//JSPの内容を取ってきている　memoという変数を作っている
    	//input[name="memo"]:checkedの意味　name="memo"が使われている、inputを取ってくる。checkedは選ばれている方
    	//valueは取得する物自体。今回の場合は（1,2のどちらか）
    	const memo = document.querySelector('input[name="memo"]:checked').value;
    	
    	
   		if(memo == "1"){
   			
   			
   			//重さ（距離）を追加する文
   			div.appendChild(document.createElement("br"));
   			let w = document.createElement("div");
   			w.textContent = "重さ（距離）";
   			
   			div.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "text";
   			weightinput.name ="tr_weight" +count;
   			div.appendChild(weightinput);
			
			
			
			
			//回数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let k = document.createElement("div");
   			k.textContent = "回数";
   			
   			div.appendChild(k); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "text";
			countinput.name ="counts" +count;
			div.appendChild(countinput);
   			
			
			
   			
   			//セット数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let s = document.createElement("div");
   			s.textContent = "セット";
   			
   			div.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "text";
			setinput.name ="sets" + count;
			div.appendChild(setinput);
   			
			
			
			
   			//メモを追加するための文
   			
   			//改行を作っている文章があり（document.createElement("br")のこと）、それをjspのitemAreaに追加している。（itemArea.appendChild(...)の文章の部分）
   			div.appendChild(document.createElement("br"));
   			//メモという文字を入れる為のdiv作っている
   			let p = document.createElement("div");
   			p.textContent = "メモ";
   			
   			//上のpとtextを入れている
   			div.appendChild(p); 
   			//文字が書けるテキストボックスを入れている。
   			div.appendChild(textarea); 
   			
   			itemArea.appendChild(div);
   			
   			textarea.name  = "memo" + count;
   			
    	}else{
    		
    		
    		//重さ（距離）を追加する文
   			div.appendChild(document.createElement("br"));
   			let w = document.createElement("div");
   			w.textContent = "重さ（距離）";
   			
   			div.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "text";
   			weightinput.name ="tr_weight"+ count;
   			div.appendChild(weightinput);
			
			
			
			
			
			//回数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let k = document.createElement("div");
   			k.textContent = "回数";
   			
   			div.appendChild(k);
   			
   			
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "text";
			countinput.name ="counts" + count;
			div.appendChild(countinput);
   			
   			
			
			
   			
   			//セット数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let s = document.createElement("div");
   			s.textContent = "セット";
   			
   			div.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "text";
			setinput.name ="sets" + count;
			div.appendChild(setinput);
			
			itemArea.appendChild(div);
		
			
    		
    	}
    	
    	

    	
    	
    	
    	document.getElementById("modal").style.display = "none";
    	
    	//countを増やしている
   
    	
    	count++;

    	document.getElementById("c").value =count;
    	document.getElementById("modal").style.display = "none";
    	document.getElementById("msg").textContent = "";
    }
    
    
 /*    function openModal() {
        document.getElementById("modal").style.display = "block";
    }
     */
    
    function closeModal() {
        document.getElementById("modal").style.display = "none";
    }
    
    
    

  </script>

</body>
</html>