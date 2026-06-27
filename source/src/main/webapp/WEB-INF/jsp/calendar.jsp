<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--デバイスの幅に合わせる-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>マメッスル　カレンダー</title>
<link rel="stylesheet" href="/a2/css/header_footer.css">
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
<link rel="stylesheet" href="/a2/css/calendar.css">
<link rel="icon" href="/a2/img/mame.png" type="image/png">
</head>
<body>
<!-----------　ヘッダーここから　----------->
<div class="app-wrapper">
<div class="solid"></div>
<header>
    <div class="header-left">
        <span id="today"></span>
        <span id="anniversary" class="anniversary"></span>
    </div>

    <a href="/a2/HomeServlet" class="logo">
		<img class="logo" src='img/logo.png'>
	</a>
    <a href="/a2/InfoServlet" class="bean-info">
    	<img class="info" src='img/info.png'>
    </a>
</header>
<!-------------　ヘッダーここまで　------------->
<!-------------　メインここから　------------->
<main>

	<form action="CalendarServlet" method="get">
	    <!-- 年のプルダウン -->
        <select name="year" onchange="this.form.submit()">
            <c:forEach var="y" begin="2020" end="${currentYear}">
                <option value="${y}" <c:if test="${y == year}">selected</c:if>>
                    ${y}年
                </option>
            </c:forEach>
        </select>

        <!-- 月のプルダウン -->
        <select name="month" onchange="this.form.submit()">
            <c:forEach var="m" begin="1" end="12">
                <option value="${m}" <c:if test="${m == month}">selected</c:if>>
                    ${m}月
                </option>
            </c:forEach>
        </select>
	</form>
	
	<!-- カレンダー作成 -->
	<div class="calendar-container">
	    <table>
	        <tr>
	            <th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
	        </tr>
	
	        <!-- dayList のインデックス -->
            <c:set var="index" value="0" />
			
			<!-- 6週分ループ -->
            <c:forEach var="week" begin="1" end="6">
			    <tr>
			    	<!-- 曜日0〜6 -->
			        <c:forEach var="dow" begin="0" end="6">
			
			            <c:choose>
			                <%-- 1週目：1日の曜日まで空白いれる --%>
			                <c:when test="${week == 1 && dow < startDay}">
			                    <td></td>
			                </c:when>
			
			                <%-- 日付がもう無い場合 --%>
                            <c:when test="${index >= dayList.size()}">
                                <td></td>
                            </c:when>
			
			                <%-- 日付表示 --%>
                            <c:otherwise>
                            	<%-- dayList から日付データを取得 --%>
                                <c:set var="dayData" value="${dayList[index]}" />
                            	<%-- モーダル開くときに日付とスタンプ、トレーニング内容を渡す --%>
								<td data-date="${dayData.fullDate}"
								    data-stamp="${stampMap[dayData.fullDate] != null ? stampMap[dayData.fullDate] : 0}"
								    data-memo="${fn:escapeXml(memoMap[dayData.fullDate])}"
								    data-weight="${weightMap[dayData.fullDate] != null ? weightMap[dayData.fullDate] : ''}"
								    data-fat="${fatMap[dayData.fullDate] != null ? fatMap[dayData.fullDate] : ''}"
								    onclick="openModalFromTd(this)">
									<%-- 日付数字 --%>
							        <div class="date-num">${dayData.day}</div>
									
									<%-- スタンプがある日だけ表示、0（スタンプなし）のときは表示しない --%>
							        <c:if test="${stampMap[dayData.fullDate] != null && stampMap[dayData.fullDate] != 0}">
							            <img src="/a2/img/stamp${stampMap[dayData.fullDate]}.png" class="stamp">
							        </c:if>
							
							    </td>
                                <c:set var="index" value="${index + 1}" />
                            </c:otherwise>
			            </c:choose>	
			        </c:forEach>
			    </tr>
			</c:forEach>
	    </table>
	</div>
	
	<!-- モーダル -->
	<div id="modal-bg" class="modal-bg">
	    <div class="modal">
	        <h3 id="modal-date"></h3>
	        <!-- スタンプ更新 -->
	        <div class="modal-body-scroll">
	            <form action="/a2/CalendarServlet" method="post" novalidate onsubmit="return validateMainForm(this)">
	                <input type="hidden" name="date" id="modalDate">
	                
	                <div id="main-error-msg" style="color: red;"></div>
	                <label>スタンプ：</label>
	                <select name="stamp">
					    <option value="0">なし</option>
					    <option value="1">スクワット</option>
					    <option value="2">背筋</option>
					    <option value="3">ダンベル</option>
					    <option value="4">脚</option>
					    <option value="5">腹筋</option>
					    <option value="6">やる気</option>
					    <option value="7">ビール</option>
					</select>
					<br>
					
					<label>体重：</label>
				    <input type="number" step="0.1" name="weight" id="modal-weight" style="width: 70px;" required> kg
				    <br>
				    
				    <label>体脂肪率：</label>
				    <input type="number" step="0.1" name="fat" id="modal-fat" style="width: 70px;"> %
				    <br>
				    
					<label>メモ：</label>
					<br>
				    <textarea name="comments" id="modal-memo" rows="3"></textarea>
				
				    <button type="submit">更新</button>
	            </form>
	            <hr>
	            <h4>トレーニング内容</h4>
	            <div id="modal-training-area" class="training-area"></div>
	        </div>
	        <div class="close-btn" onclick="closeModal()">閉じる</div>
	    </div>
	</div>


</main>
<!---------------　メインここまで　--------------->
<!---------------　フッターここから　--------------->
<footer>
<nav class="bottom-bar" id="bar">
  <a href="/a2/GraphServlet"><i class="fa-solid fa-arrow-trend-up"></i></a>
  <a href="/a2/FriendListServlet"><i class="fa-solid fa-user-group"></i></a>
  <a href="/a2/HomeServlet"><i class="fa-regular fa-square-plus"></i></a>
  <a href="/a2/CalendarServlet"><i class="fa-regular fa-calendar nowpage"></i></a>
  <a href="/a2/MyPageServlet"><i class="fa-solid fa-circle-user"></i></a>
</nav>
</footer>
<!---------------　フッターここまで　--------------->
<script>
	// JSON文字列
	const masterItemsJs = ${itemListJson};
	const trainingMapJs = ${trainingMapJson};
	//const weightMapJs = ${weightMapJson};

	// モーダルを開く関数
	function openModal(date, stamp, comments, list, weight, fat) {
	    document.getElementById("modal-date").innerText = date;
	    document.getElementById("modalDate").value = date;
	
	    // スタンプ初期値
	    document.querySelector("select[name='stamp']").value = stamp;
	    // トレーニング内容初期値
	    document.getElementById("modal-memo").value = comments || "";
	 	// 体重初期値
	    document.getElementById("modal-weight").value = weight || "";
	 	// 体脂肪率初期値
	    document.getElementById("modal-fat").value = fat || "";
	    	
	    let area = document.getElementById("modal-training-area");
	    area.innerHTML = "";

	    // 既存トレーニング内容のリスト表示
	    if (!list || list.length === 0) {
	        area.innerHTML = "<div class='no-training'>トレーニングなし</div>";
	    } else {
	        list.forEach(obj => {
	            // 各レコードごとのセレクトボックスのHTML
	            let itemSelectHtml = '<select name="tr_id" class="input-select">';
	            masterItemsJs.forEach(master => {
	                // Gsonで変換されたオブジェクトのプロパティ名に合わせて判定
	                let selected = (master.TrId == obj.tr_id) ? ' selected' : '';
	                itemSelectHtml += '<option value="' + master.TrId + '"' + selected + '>' + master.TrItem + '</option>';
	            });
	            itemSelectHtml += '</select>';

	            area.innerHTML += 
	            	'<form action="/a2/CalendarServlet" method="post" class="training-edit-form" novalidate onsubmit="return validateEditForm(this, ' + obj.id + ')">' +
		                '<input type="hidden" name="id" value="' + obj.id + '">' +
		                '<input type="hidden" name="action" class="action-field" value="update">' +
		                
		                '<div id="edit-error-msg-' + obj.id + '" style="color: red;"></div>' +
	                    	
		                '<div class="training-grid">' +
			                '<div class="grid-item full-width"><span class="label-text">種目ID:</span> ' + itemSelectHtml + '</div>' +
			                '<div class="grid-item"><span class="label-text">重量:</span> <input type="number" name="tr_weight" value="' + obj.tr_weight + '" class="input-small"> kg</div>' +
			                '<div class="grid-item"><span class="label-text">回数:</span> <input type="number" name="counts" value="' + obj.counts + '" class="input-small"> 回</div>' +
			                '<div class="grid-item"><span class="label-text">セット:</span> <input type="number" name="sets" value="' + obj.sets + '" class="input-small"></div>' +
			                '<div class="grid-item full-width"><span class="label-text">メモ:</span> <input type="text" name="tr_memo" value="' + (obj.memo || "") + '" class="input-med"></div>' +
			            '</div>' +
			            '<div class="button-group">' +
			                '<button type="submit" class="btn-edit">変更</button>' +
			                '<button type="button" class="btn-delete" onclick="if(confirm(\'削除しますか？\')){ this.form.querySelector(\'.action-field\').value=\'delete\'; this.form.submit(); }">削除</button>' +
			            '</div>' +
	                '</form>';
	        });
	    }
	    	
	    // 新規追加用のプルダウンフォーム
	    let optionsHtml = '<option value="" disabled selected>選択してください</option>';
	    masterItemsJs.forEach(item => {
	        optionsHtml += '<option value="' + item.TrId + '">' + item.TrItem + '</option>';
	    });
	    
	    const registeredIdsStr = list.map(obj => obj.tr_id).join(",");
	
	    area.innerHTML += 
	    	'<form action="/a2/CalendarServlet" method="post" class="training-add-form" novalidate onsubmit="return validateAddForm(this)">' +
		        '<input type="hidden" name="date" value="' + date + '">' +
		        '<input type="hidden" name="action" value="insert">' +
		        '<input type="hidden" name="registered_ids" value="' + registeredIdsStr + '">' +
		        '<h5>＋ 新しいトレーニングを追加</h5>' +
		        
		        '<div id="add-error-msg" style="color: red;"></div>' +
		        
		        '<div class="training-grid">' +
		            '<div class="grid-item full-width"><span class="label-text">種目:</span> <select name="tr_id" class="input-select">' + optionsHtml + '</select></div>' +
		            '<div class="grid-item"><span class="label-text">重量:</span> <input type="number" name="tr_weight" value="0" class="input-small"> kg</div>' +
		            '<div class="grid-item"><span class="label-text">回数:</span> <input type="number" name="counts" value="0" class="input-small"> 回</div>' +
		            '<div class="grid-item"><span class="label-text">セット:</span> <input type="number" name="sets" value="0" class="input-small"></div>' +
		            '<div class="grid-item full-width"><span class="label-text">メモ:</span> <input type="text" name="tr_memo" class="input-med" placeholder="補足など"></div>' +
		        '</div>' +
		        '<div class="button-group">' +
		            '<button type="submit" class="btn-add">追加</button>' +
		        '</div>' +
	        '</form>';
	
	    document.getElementById("modal-bg").style.display = "block";
	}
	
	// 新規追加時の入力チェックを行う関数
	function validateAddForm(form) {
	    const errorArea = document.getElementById("add-error-msg");
	    errorArea.innerText = ""; // いったんエラーメッセージをクリア

	    const trId = form.tr_id.value;
	    const weight = form.tr_weight.value;
	    const counts = form.counts.value;
	    const sets = form.sets.value;
	    const registeredIds = form.registered_ids.value ? form.registered_ids.value.split(",") : [];

	    // 種目の未選択チェック
	    if (!trId || trId === "") {
	        errorArea.innerText = "種目を選択してください。";
	        form.tr_id.focus(); // 入力箇所にカーソルを合わせる
	        return false; // 送信を中止
	    }
	    
	    if (registeredIds.includes(trId)) {
	        errorArea.innerText = "その種目はすでに登録されています。";
	        form.tr_id.focus();
	        return false; // 送信を中止
	    }

	    // 数値項目の空欄or負の数のチェック
	    if (weight === "" || counts === "" || sets === "") {
	        errorArea.innerText = "重量、回数、セット数は数値を入力してください。";
	        return false;
	    }
	    
	    if (parseFloat(weight) < 0 || parseInt(counts) < 0 || parseInt(sets) < 0) {
	        errorArea.innerText = "数値には0以上の値を入力してください。";
	        return false;
	    }

	    return true; // 何も問題がなければサーブレットに送信
	}
	
	// 体重の入力チェックを行う関数
	function validateMainForm(form) {
	    const errorArea = document.getElementById("main-error-msg");
	    errorArea.innerText = ""; // メッセージをクリア

	    const weight = form.weight.value;
	    const fat = form.fat.value;
	    
	    if (weight.trim() === "") {
	        errorArea.innerText = "体重を入力してください。";
	        form.weight.focus();
	        return false;
	    }
	    if (fat === "" && form.fat.validity.valid) {
	        return true;
	    }

	    // 「e」や「E」など変な文字が含まれていないかチェック
	    if (!form.weight.validity.valid || /[eE]/.test(weight) || isNaN(parseFloat(weight))) {
	        errorArea.innerText = "体重には数値を入力してください";
	        form.weight.focus();
	        return false;
	    }
	    if (!form.fat.validity.valid || /[eE]/.test(fat) || isNaN(parseFloat(fat))) {
	        errorArea.innerText = "体脂肪率には数値を入力してください";
	        form.fat.focus();
	        return false;
	    }

	    // 負の数チェック
	    if (parseFloat(weight) < 0) {
	        errorArea.innerText = "体重には0以上の数値を入力してください。";
	        form.weight.focus();
	        return false;
	    }
	    if (parseFloat(fat) < 0) {
	        errorArea.innerText = "体脂肪率には0以上の数値を入力してください。";
	        form.fat.focus();
	        return false;
	    }

	    return true; // 問題なければ送信
	}
	
	// 既存トレーニングの変更時の入力チェックを行う関数
	function validateEditForm(form, id) {
	    // 該当するフォーム専用のエラーエリアを取得
	    const errorArea = document.getElementById("edit-error-msg-" + id);
	    errorArea.innerText = ""; // メッセージをクリア

	    // もしボタンのonclick等でactionが「delete」に書き換わっていた場合はチェックせず送信を通す
	    const action = form.querySelector('.action-field').value;
	    if (action === "delete") {
	        return true; 
	    }

	    const trId = form.tr_id.value;
	    const weight = form.tr_weight.value;
	    const counts = form.counts.value;
	    const sets = form.sets.value;

	    // 種目の未選択チェック
	    if (!trId || trId === "") {
	        errorArea.innerText = "種目を選択してください。";
	        form.tr_id.focus();
	        return false;
	    }

	    // 数値項目の空欄or負の数のチェック
	    if (weight === "" || counts === "" || sets === "") {
	        errorArea.innerText = "重量、回数、セット数は数値を入力してください。";
	        return false;
	    }
	    
	    if (parseFloat(weight) < 0 || parseInt(counts) < 0 || parseInt(sets) < 0) {
	        errorArea.innerText = "数値には0以上の値を入力してください。";
	        return false;
	    }

	    return true; // 問題なければ送信
	}
	
	function openModalFromTd(td) {
	    const date = td.dataset.date;
	    const stamp = td.dataset.stamp;
	    const memo = td.dataset.memo;
	    const weight = td.dataset.weight;
	    const fat = td.dataset.fat;

	    const list = trainingMapJs[date] || [];

	    console.log("選択された日付:", date);
	    console.log("取得されたリスト:", list);

	    openModal(date, stamp, memo, list, weight, fat);
	}

	function closeModal() {
	    document.getElementById("modal-bg").style.display = "none";
	}
	
	
	//ヘッダー日付表示用
	 window.onload = function(){
	const now =new Date();
	const year = now.getFullYear();
	const month= now.getMonth()+1;
	const date = now.getDate();
	const text = year+"年"+month+"月"+date+"日";
	if( month === 6 && date === 30 ){
	    document.getElementById('anniversary').textContent='発表の日';
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
	}
</script>
</div>
</body>
</html>