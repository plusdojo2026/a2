<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ホームページ</title> 
  <link rel="stylesheet" href="/a2/css/header_footer.css">
  <link rel="stylesheet" href="/a2/css/home.css">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
</head>
<body>
<div class="app-wrapper">
<!--　ヘッダーここから　-->
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="/a2/HomeServlet" class="logo">rogo</a>
    <a href="/a2/InfoServlet" class="bean-info"><i class="fa-solid fa-circle-info"></i>豆情報</a>
</header>
<!--　ヘッダーここまで　-->
<!--　メインここから　-->
<main>
<!--ホームサーブレットにある、messageを表示させている　if文でもしメッセージが存在したらと書く-->
<c:if test="${not empty message}">
    <div style="color: blue;">
        ${message}
    </div>
</c:if>
<c:if test="${not empty message2}">
    <div style="color: blue;">
        <a href="FriendrequestsServlet">フレンド申請</a>が来てます！
    </div>
</c:if>
<h1>今日の記録</h1>
<div class="input">
<form id="homeForm" method="POST" action="/a2/HomeServlet">


<input type="button" value="一時保存" onclick="submitTempSave()" class="save">

<h2>基本データ</h2>
<table>
	<tr>
		<td>
			体重(kg)
		</td>
		<td>
			<input type="number" name="weight" value="${weight}" step="0.1" required min=0>
		</td>
	</tr>
	<tr>
		<td>
			体脂肪率(％)
		</td>
		<td>
			<input type="number" name="fat" value="${fat}" step="0.1" min=0>
		</td>
</table>
<div id="weightEr" style="color:red;"></div>
<!-- <div id="fatEr" style="color:red;"></div>
 -->
<!--countをしているので何回繰り返したかがわかる　隠れているので表には見えない-->
<input type ="hidden" id ="c" name = "coun">
<br>

一日メモ<br>
<textarea name="comments">${comments}</textarea>
<br>
スタンプ：
	<select id="stamp" onchange="changestamp()" name="stamp">
    	<option value="1"<c:if test="${stamp == '1'}">selected</c:if>>
		なし
		</option>
		
		<option value="2"<c:if test="${stamp == '2'}">selected</c:if>>
		足トレ
		</option>
		
		<option value="3"<c:if test="${stamp == '3'}">selected</c:if>>
		背中トレ
		</option>
		
	    <option value="4"<c:if test="${stamp == '4'}">selected</c:if>>
		腕トレ
		</option>
	    
	    <option value="5"<c:if test="${stamp == '5'}">selected</c:if>>
		尻トレ
		</option>
	    
	    <option value="6"<c:if test="${stamp == '6'}">selected</c:if>>
		腹トレ
		</option>
		
	    <option value="7"<c:if test="${stamp == '7'}">selected</c:if>>
		豆トレ
		</option>
		
	    <option value="8"<c:if test="${stamp == '8'}">selected</c:if>>
		酒
		</option>    
	</select>
	<br>
	<br>
	<img id="stampImage" src="" width="200">
<br>
<h2>カスタムデータ</h2>
<div id="itemArea"></div>
<div id="memoArea"></div><br>
<button type="button" onclick="openModal()">＋項目を追加</button>
<!-- ✧✧✧✧✧✧✧保存確認のモーダル✧✧✧✧✧✧✧ -->
<button type="button" onclick="openSaveModal()">保存</button>
<!-- 隠してあるけどrequest.getParameter("saveb")　でモーダルから送信してもとってくれるようにする　 -->
<input type="hidden" name="saveb" id="saveb">
<div id="saveModal" class="modal-background">
	<div class="modal-content-two">
		<h3>確認</h3>
		<h4>以下の内容でしてよろしいでしょうか？​</h4>
		<br>
		<div id="confirmArea"></div>	
		<button type="button" onclick="submitSave()">はい</button>
	    <button type="button" onclick="closeSaveModal()">いいえ</button>
	</div>
</div>
</form>
</div>
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
	<br>
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
 <div id="completeModal" class="modal-background" style="display:none;">
    <div class="modal-content-two">
        <h2>本日の入力ありがとうございました。</h2>
        <br>
        <p>
            カレンダーページから入力内容の変更ができます。
        </p>   
        <br>
        <c:if test="${goalAchieved}">
	    <p style="color:red;">
	        🎉目標体重達成おめでとうございます！🎉<br>
	        目標体重の変更はマイページから更新できます！<br>
	        あなたは最強の豆ですね！！
	    </p>
		</c:if>
        <!-- <p>
        <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
        </p> -->
        <br>
    </div>
</div> 
</main>
<!--　メインここまで　-->
<!--　フッターここから　-->
<div class="footer">
<footer>
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus nowpage"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user"></i></a>
</nav>
</footer>
</div>
<!--　フッターここまで　-->
<script>
//スクロールに合わせたアイコンバーの変更
/*id=barを定数barに代入*/
const bar = document.getElementById("bar");
let lastScroll = 0;
/*スクロールすると以下の処理を実行する*/
window.addEventListener("scroll", () => {
  /*スクロール幅の取得*/
  const current = window.scrollY;
  /*下スクロールでsmallクラスを付与する*/
  if(current > lastScroll && current > 40){
    bar.classList.add("small");
    }
  /*上スクロールでsmallクラスを削除*/
  else{bar.classList.remove("small");
  }
  lastScroll = current;
});
	//ここでcount宣言
	let count=0;
	
	//マイナスが入ったときの処理 アラートを出す
	function checkMinus(){

		//document.querySelectorAllはCSSで使用したり属性指定をまとめて持ってこれる
		//'input[type="number"]'を今回は持ってきている
	    const numbers = document.querySelectorAll( 'input[type="number"]');

		//input[type="number"]をすべて繰り返して確認する処理
	    for(let i = 0; i < numbers.length; i++){

	    	//iにinput[type="number"] の中身を持ってくる
	        const value = numbers[i].value;

	    	//空欄ではないことと、マイナスの値であることが重なったらアラートを表示する
	        if(value !== "" && Number(value) < 0){
	            alert("マイナスの値は入力できません。");
	            //カーソルをマイナスを入れて止まった場所に持っていく
	            numbers[i].focus();
	            //マイナスが入っていたということを返す
	            return false;
	        }
	    }

	    return true;
	}
	
	
	// ⭕ 追加する関数：一時保存ボタンを押したときに安全にチェックして送信する
	function submitTempSave() {
		
		//マイナスチェックの関数を呼ぶ
		//checkMinus == false が !だったら
		 if(!checkMinus()){
		        return;
		    }
		
	    let weight = document.querySelector('input[name="weight"]').value;
	   
	    
	    document.getElementById("weightEr").textContent = "";
	
	    
	    let err = false;
	    if (weight.trim() === "") {
	        document.getElementById("weightEr").textContent = "体重を入力してください";
	        err = true;
	    }
	   
	    
	    if (err) return; // エラーがあれば送信しない
	    // 隠しボタンの代わりに、一時保存であることを示すパラメータを動的に作って送信
	    let form = document.getElementById("homeForm");
	    let hiddenInput = document.createElement("input");
	    hiddenInput.type = "hidden";
	    hiddenInput.name = "savetime";
	    hiddenInput.value = "一時保存";
	    form.appendChild(hiddenInput);
	    form.submit();
	} 
	//保存用の時のモーダル
	function openSaveModal() {
		
		//マイナスがあるときにfalseを返している (checkMinus() == false)だったらという意味
		//この関数自体がマイナスを渡すときという意味だから、ここではtrueが入ることで動く
		if(!checkMinus()){
	        return;
	    }
		
		//inputの中のname="weight"のvalueを取り出す（中身ということ）
		let weight = document.querySelector('input[name="weight"]').value;
		
		/* //inputの中のname="fat"のvalueを取り出す（中身ということ）
	    let fat = document.querySelector('input[name="fat"]').value;  */
	    let err = false;
	    document.getElementById("weightEr").textContent = "";
	    /* document.getElementById("fatEr").textContent = ""; */
		//体重が空欄だった場合 err = true;はエラーがあったということ
	    if (weight.trim() === "") {
	        document.getElementById("weightEr").textContent =
	            "体重を入力してください";
	        err = true;
	    }
		let fat = document.querySelector('input[name="fat"]').value; 
	  /* //体脂肪が空欄だった場合
	    if (fat.trim() === "") {
	        document.getElementById("fatEr").textContent =
	            "体脂肪を入力してください";
	        err = true;
	    }
		 */
		//ここでまとめて返すことでどっちも表示できる
	    if (err) {
	        return;
	    }
		
	  	//inputの中のname="comments"のvalueを取り出す（中身ということ）
	    let comments = document.querySelector('textarea[name="comments"]').value;
	    
	  	//スタンプは数字で表示されてしまうので、数字を取ってきて、セレクトの中のtextをとってきている
	    let select = document.getElementById("stamp");
	    let stampText = select.options[select.selectedIndex].text;
	  	
	  	
	  	//空箱づくり
	    let html = "";
	  	
	  	//HTMLという箱にに加えていく
	    html += "体重：" + weight + "kg<br>";
	    html += "体脂肪率：" + fat + "%<br>";
	    html += "スタンプ：" + stampText ;
	    html +="<br>"
	    html += "[メモ]" + "<br>"
	    html += comments + "<br><br>";
	  
	    
	    //追加項目繰り返し処理で回す
	    
	    // 追加項目
	     for(let i = 0; i < count; i++){
	
	        let item = document.getElementById("it" + i);
	        
	       
	
	        if(item != null){
	
	        	//オプショナルチェーン 「?.」のこと
	        	//0でいい理由としては前でとってきているデータが「"tr_weight" + i」でかわっているから
	            let tr_weight = document.getElementsByName("tr_weight" + i)[0]?.value || "";
	            let counts = document.getElementsByName("counts" + i)[0]?.value || "";
	            let sets = document.getElementsByName("sets" + i)[0]?.value || "";
	            let memo = document.getElementsByName("memo" + i)[0]?.value || "";
	            //表示パターン１
	        
	            html += "[" + item.value + "]"+"<br>";
	            html += tr_weight +" kg(km) ×";
	            html += counts + " 回 ×";
	            html += sets + " セット"+"<br>";
	            /* html +="<br>"; */
	            html += "[メモ]"+"<br>";
	            html += memo + "<br>";
	            html +="<br>";
	        } 
	    }
		document.getElementById("confirmArea").innerHTML = html;
	    document.getElementById("saveModal").style.display = "block";
	}
	function closeSaveModal() {
	    document.getElementById("saveModal").style.display = "none";
	}
	//保存押したときだけ送信される
	function submitSave() {
	    document.getElementById("saveb").value = "保存";
	    document.getElementById("homeForm").submit();
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
    //項目用のモーダル
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
    	
    	//項目追加した時に線を入れている
    	div.style.borderTop = "1px solid #ccc";
    	div.style.marginTop = "20px";
    	div.style.paddingTop = "20px";

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
   		let title = document.createElement("div");
		title.textContent = "項目";
		
		div.appendChild(title);
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
   			let w = document.createElement("span");
   			w.textContent = "重さ（距離）";
   			
   			div.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "number";
   			weightinput.name ="tr_weight" +count;
   			weightinput.min = 0;
   			div.appendChild(weightinput);
			
			
			
			
			//回数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let k = document.createElement("span");
   			k.textContent = "回数";
   			
   			div.appendChild(k); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "number";
			countinput.name ="counts" +count;
			countinput.min = 0;
			div.appendChild(countinput);
   			
			
			
   			
   			//セット数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let s = document.createElement("span");
   			s.textContent = "セット";
   			
   			div.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "number";
			setinput.name ="sets" + count;
			setinput.min = 0;
			div.appendChild(setinput);
   			
			
			
			
   			//メモを追加するための文
   			
   			//改行を作っている文章があり（document.createElement("br")のこと）、それをjspのitemAreaに追加している。（itemArea.appendChild(...)の文章の部分）
   			div.appendChild(document.createElement("br"));
   			//メモという文字を入れる為のdiv作っている
   			let p = document.createElement("span");
   			p.textContent = "メモ";
   			
   			//上のpとtextを入れている
   			div.appendChild(p); 
   			//文字が書けるテキストボックスを入れている。
   			div.appendChild(textarea);
   			
   			div.appendChild(document.createElement("br"));
   			
   			itemArea.appendChild(div);
   			
   			textarea.name  = "memo" + count;
   			
    	}else{
    		
    		
    		//重さ（距離）を追加する文
   			div.appendChild(document.createElement("br"));
   			let w = document.createElement("span");
   			w.textContent = "重さ（距離）";
   			
   			div.appendChild(w); 
   			
   			//文字が書けるテキストボックスを入れている。inputという名前だけだとかぶりまくるので名前を付けるときはweightinputなどにする
   			let weightinput = document.createElement("input");
   			weightinput.type = "number";
   			weightinput.name ="tr_weight"+ count;
   			weightinput.min = 0;
   			div.appendChild(weightinput);
			
			
			
			
			
			//回数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let k = document.createElement("span");
   			k.textContent = "回数";
   			
   			div.appendChild(k);
   			
   			
   			
   			//文字が書けるテキストボックスを入れている。
   			let countinput = document.createElement("input");
			countinput.type = "number";
			countinput.name ="counts" + count;
			countinput.min = 0;
			div.appendChild(countinput);
   			
   			
			
			
   			
   			//セット数を追加する文
   			
   			div.appendChild(document.createElement("br"));
   			let s = document.createElement("span");
   			s.textContent = "セット";
   			
   			div.appendChild(s); 
   			
   			//文字が書けるテキストボックスを入れている。
   			let setinput = document.createElement("input");
			setinput.type = "number";
			setinput.name ="sets" + count;
			setinput.min = 0;
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
    
    
    
    function closeModal() {
        document.getElementById("modal").style.display = "none";
    }
    
    
     
     
   //ここでsaveDetailListから一件ずつ取り出している
   //ページの読み込みが終わったら始めるという意味
 	window.onload = function(){
	   
		//ヘッダー日付表示用
		const now =new Date();
		const year = now.getFullYear();
		const month= now.getMonth()+1;
		const date = now.getDate();
		const text = year+"年"+month+"月"+date+"日";
		if( month === 6 && date === 17 ){
		    document.getElementById('anniversary').textContent='テスト用';
		}else if( month === 1 && date === 10 ){
		    document.getElementById('anniversary').textContent='糸引き納豆の日';
		}else if( month === 2 && date === 3 ){
		    document.getElementById('anniversary').textContent='節分・大豆の日';
		}else if( month === 2 && date === 10 ){
		    document.getElementById('anniversary').textContent='世界マメの日';
		}else if( month === 4 && date === 3 ){
		    document.getElementById('anniversary').textContent='いんげん豆の日';
		}else if( month === 7 && date === 10 ){
		    document.getElementById('anniversary').textContent='納豆の日';
		}else if( month === 10 && date === 2 ){
		    document.getElementById('anniversary').textContent='豆腐の日';
		}else if( month === 10 && date === 12 ){
		    document.getElementById('anniversary').textContent='豆乳の日';
		}else if( month === 10 && date === 13 ){
		    document.getElementById('anniversary').textContent='豆の日';
		}
		document.getElementById('today').textContent=text;
		

   
   
   

	 //サーブレットからわたされたsaveDetailListをループして呼び出している
 	<c:forEach var="item" items="${saveDetailList}">
 	    addSavedItem(
 	        '${item.trItem}',
 	        '${item.tr_weight}',
 	        '${item.counts}',
 	        '${item.sets}',
 	        '${item.memo}'
 	    );
 	</c:forEach>
 	
 	changestamp();
 	
 	
 	//日付またいだ時にモーダルだす
 	<c:if test="${todaySaved}">
 	document.getElementById(
 	    "completeModal"
 	).style.display = "block";
 	</c:if>
 	
 	
 	/* const trSaveJson =JSON.parse('${trSaveJson}');
	alert(trSaveJson[0].getTrItem());  */
 	}
 	
 	
 	
 	
 //一時保存をしたものを表示するためのもの
 //ここでうえで取得したデータを呼ぶ
 	
 	function addSavedItem(itemName, weight, counts, sets, memo){

    /* console.log("復元", itemName); */

    //<div id="itemArea"></div>のところに追加するので取得
    let itemArea = document.getElementById("itemArea");

    //divを作っている、項目は追加式なので+ countで　○○1,○○2のようにしている
    const div = document.createElement("div");
    div.id = "item" + count;

    
 	// 項目同士の間隔を作る
    div.style.marginBottom = "30px";
    div.style.paddingBottom = "15px";
    div.style.borderTop = "1px solid #ccc";
    
    // 項目
    const input = document.createElement("input");
    input.type = "text";
    input.name = "it" + count;
    input.id = "it" + count;
    //編集できないようにしている　項目名は勝手に名前を変えられない
    input.readOnly = true;
    //itemNameは項目名
    input.value = itemName;

    //"項目"と上で作ったinputが表示される
    /* div.appendChild(document.createTextNode("項目"));
    div.appendChild(input); */

    
    let title = document.createElement("span");
    title.textContent = "項目";

    div.appendChild(title);
    div.appendChild(input);
    
    
    // 重さ
    div.appendChild(document.createElement("br"));

    const weightinput = document.createElement("input");
    weightinput.type = "number";
    weightinput.name = "tr_weight" + count;
    weightinput.value = weight;

    /* div.appendChild(document.createTextNode("重さ（距離）")); */
    let w = document.createElement("span");
	w.textContent = "重さ（距離）";
	div.appendChild(w);
    
    
    div.appendChild(weightinput);

    
    
    // 回数
    div.appendChild(document.createElement("br"));

    const countinput = document.createElement("input");
    countinput.type = "number";
    countinput.name = "counts" + count;
    countinput.value = counts;

    /* div.appendChild(document.createTextNode("回数")); */
    
    let c = document.createElement("span");
	c.textContent = "回数";
	div.appendChild(c);
    
    div.appendChild(countinput);

    
    
    // セット
    div.appendChild(document.createElement("br"));

    const setinput = document.createElement("input");
    setinput.type = "number";
    setinput.name = "sets" + count;
    setinput.value = sets;

    /* div.appendChild(document.createTextNode("セット")); */
    
    let s = document.createElement("span");
	s.textContent = "セット";
	div.appendChild(s);
    div.appendChild(setinput);

    
    
    // メモある場合とない場合で分けている
    if(memo != null && memo.trim() !== ""){

        div.appendChild(document.createElement("br"));

        const memoTitle = document.createElement("span");
        memoTitle.textContent = "メモ";
        div.appendChild(memoTitle);

        const textarea = document.createElement("textarea");
        textarea.name = "memo" + count;
        textarea.value = memo;

        div.appendChild(textarea);
    }

    // 削除ボタン
    const deleteButton = document.createElement("input");
    deleteButton.type = "button";
    deleteButton.value = "削除";

    deleteButton.onclick = function(){
        if(confirm("削除しますか？")){
            itemArea.removeChild(div);
        }
    };

    div.appendChild(deleteButton);

    itemArea.appendChild(div);

    
    //countを増やしている
    count++;
    document.getElementById("c").value = count;

	} 
    
	
 	
  </script>
</div>
</body>
</html>