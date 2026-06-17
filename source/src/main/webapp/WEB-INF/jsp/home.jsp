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
<h2>カスタムデータ</h2>
<div id="itemArea"></div>
<div id="memoArea"></div><br>

<input type="submit" name="saveb" value="保存">
<button onclick="openModal()">＋項目を追加</button>
<input type="submit" name="deli" value="ー項目を削除">

<!-- <form method="POST" action="/a2/HomeServlet"> -->

<div id="memoArea"></div>
  
   <div id="modal" class="modal-background">
    <div class="modal-content">
      <p>項目を追加</p>
      
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
	

    
    
    let count=0;
    function addItem(){
    	
    	//追加する場所のデータを取得してくる
		let itemArea=document.getElementById("itemArea");
    	//改行のことをbrという変数に入れている
    	const br = document.createElement("br");
    	
    	
    	
    	//テキストボックスに追加アイテムの作成（nameはit1~itn）
    	//ここではjspでは一行で書くものをJavaScriptから入れるので細分化している
    	const input = document.createElement("input");//<input　inputをjspに入れるよ
    	input.type="text";//<input type="text"　input typeはtextですよ
    	input.name="it"+count; //<input type="text" name="it?"　名前はitにします。+countを付けているのでit1,it2のように順番につきます
    	input.readOnly="true";//<input type="text" name="it?" readOnry　textの中身は変えられません。項目の部分なので書き換えられないようにする
    	input.value=document.getElementById("item").value; //<input type="text" name="it?" readOnry value="ダンベル">　
    														//上でユーザーが選択したでーたをvalue=に入れる文
    	
    														
    														
    	//テキストエリアのデータを作成
   		const textarea = document.createElement("textarea");
    	//ここもカウントを入れることで項目に着けた名前と連動する　it１が追加されたらmemo１
   		textarea.name = "memo"+count;
   		
   		//上記で追加したアイテムをitemAreaに入れる
   		//divというタグをつくり、変数piに入れた
   		let pi = document.createElement("div");
   		//<div>項目</div>という形になる　タグの中に入れている
   		pi.textContent = "項目";
   		//pi を itemArea (jspのitemAreaの位置）の中に追加
   		itemArea.appendChild(pi);
   		//inputもitemArea (jspのitemAreaの位置）の中に追加
   		itemArea.appendChild(input);
   		
   		
   		//JSPの内容を取ってきている　memoという変数を作っている
    	//input[name="memo"]:checkedの意味　name="memo"が使われている、inputを取ってくる。checkedは選ばれている方
    	//valueは取得する物自体。今回の場合は（1,2のどちらか）
    	const memo = document.querySelector('input[name="memo"]:checked').value;
    	
    	
   		if(memo == "1"){
   			
   			
   			//重さ（距離）を追加する文
   			itemArea.appendChild(document.createElement("br"));
   			let w = document.createElement("div");
   			w.textContent = "重さ（距離）";
   			
   			itemArea.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "text";

			itemArea.appendChild(weightinput);
			
			
			
			
			//回数を追加する文
   			
   			itemArea.appendChild(document.createElement("br"));
   			let k = document.createElement("div");
   			k.textContent = "回数";
   			
   			itemArea.appendChild(k); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "text";

			itemArea.appendChild(countinput);
   			
   			
			
			
   			
   			//セット数を追加する文
   			
   			itemArea.appendChild(document.createElement("br"));
   			let s = document.createElement("div");
   			s.textContent = "セット";
   			
   			itemArea.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "text";

			itemArea.appendChild(setinput);
   			
   			
			
			
   			//メモを追加するための文
   			
   			//改行を作っている文章があり（document.createElement("br")のこと）、それをjspのitemAreaに追加している。（itemArea.appendChild(...)の文章の部分）
   			itemArea.appendChild(document.createElement("br"));
   			//メモという文字を入れる為のdiv作っている
   			let p = document.createElement("div");
   			p.textContent = "メモ";
   			
   			//上のpとtextを入れている
   			itemArea.appendChild(p); 
   			//文字が書けるテキストボックスを入れている。
   			itemArea.appendChild(textarea); 
   			
   			
   			
    	}else{
    		
    		
    		//重さ（距離）を追加する文
   			itemArea.appendChild(document.createElement("br"));
   			let w = document.createElement("div");
   			w.textContent = "重さ（距離）";
   			
   			itemArea.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "text";

			itemArea.appendChild(weightinput);
			
			
			
			
			//回数を追加する文
   			
   			itemArea.appendChild(document.createElement("br"));
   			let k = document.createElement("div");
   			k.textContent = "回数";
   			
   			itemArea.appendChild(k); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "text";

			itemArea.appendChild(countinput);
   			
   			
			
			
   			
   			//セット数を追加する文
   			
   			itemArea.appendChild(document.createElement("br"));
   			let s = document.createElement("div");
   			s.textContent = "セット";
   			
   			itemArea.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "text";

			itemArea.appendChild(setinput);
    		
    	}
    	
    	
    	/* 
    	//セレクトboxのデーターを取得（ダンベルとか）
		let it = document.getElementById("item").value;
    	
		
		
		
		//pタグを作り出して、pという名前をつける
		let p = document.createElement("p");
		
		
		//上のpタグの中にダンベルとかの情報を入れる<p>ダンベル</p>みたいになる
		p.textContent = it;
		
		
		//上の<p>ダンベル</p>みたいなのを規定の場所に追加する
		itemArea.appendChild(p);
    	
		
    	//JSPの内容を取ってきている　memoという変数を作っている
    	//input[name="memo"]:checkedの意味　name="memo"が使われている、inputを取ってくる。checkedは選ばれている方
    	//valueは取得する物自体。今回の場合は（1,2のどちらか）
    	const memo = document.querySelector('input[name="memo"]:checked').value;
    	
    	
    	
    	
    	
    	//memoAreaというものはjspの<div id="memoArea"></div>をさす
    	const memoArea = document.getElementById("memoArea");
    	
    	
    	
    	//div.innerHTML = item + "<br>";　を使用するための文　<div>アイテムの名前</div>の<div>を新しく作った
    	const div = document.createElement("div");
    	
    	
    	
    	//select name="trainingItem"の部分を指している　valueは帰ってきた単語なので項目名になる
    	const item =document.querySelector('select[name="trainingItem"]').value;
    	
    	
    	
    	
    	//ここでdivタグの中に　アイテムと改行を作っている
    	div.innerHTML = item + "<br>";
    	
    	
    	
    	
    	
    	if(memo == "1"){
    		// memoArea.innerHTMLはここでいうと<div id="memoArea">○○○○</div>の○○○○の部分
    		//ここでは○○○○が　=の後の　'<textarea name="memo"></textarea>'　に書き換わる　つまりtextbox追加！
    		/* memoArea.innerHTML ='<textarea name="memo"></textarea>'; */
    		 
    		 
    		//const textarea = document.createElement("textarea"); 
    		
    		//appendChildはtext追加の意味！
    		// memoArea.appendChild(textarea);
    	//}
    	
    	//memoArea.appendChild(div); 
    	
    	document.getElementById("modal").style.display = "none";
    }
    
    
    function openModal() {
        document.getElementById("modal").style.display = "block";
    }
    
    
    function closeModal() {
        document.getElementById("modal").style.display = "none";
    }
    
    
    
   /*  const textarea = document.createElement("textarea");
    memoArea.appendChild(textarea); */
    
    
    
    
   /* // モーダル表示
    function openModal() {
      document.getElementById("modal").style.display = "block";
    }

    // モーダル非表示
    function closeModal() {
      document.getElementById("modal").style.display = "none";
    }
     */
    
/*   //メモの有無でメモ欄を表示
    function closeModal(){
    	
    	
    	const memo = document.querySelector('input[name="memo"]:checked').value;
    	
    	
        
    	
    	if(memo == "1"){
    		//有を選択（１を選択されたとき）　blockは　displayを使用したときに使える「要素を表示する」単語
    		document.getElementById("tx").style.display = "block";
    	}else{
    		//無を選択（２を選択されたとき）noneは非表示
    		 document.getElementById("tx").style.display = "none";
    	}
    	
    } */
    
    
   /*  function closeModal() {
        alert("実行された");
    } */

  </script>

</body>
</html>