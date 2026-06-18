<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マメッスル　カレンダー</title>
<link rel="stylesheet" href="/a2/css/calendar.css">
</head>
<body>
<!-----------　ヘッダーここから　----------->
<header>
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
	            <form action="/a2/CalendarServlet" method="post">
	                <input type="hidden" name="date" id="modalDate">
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
				<label>メモ：</label>
				<br>
			    <textarea name="memo" id="modal-memo" rows="3"></textarea>
			
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


</footer>
<!---------------　フッターここまで　--------------->
<script>

	//サーブレットから受け取った種目マスターをJSの配列に変換
	const masterItemsJs = [];
	<c:forEach var="item" items="${itemList}">
	    masterItemsJs.push({
	        id: "${item.TrId}",
	        name: "${item.TrItem}"
	    });
	</c:forEach>

	const trainingMapJs = {};
	
	<c:forEach var="entry" items="${trainingMap}">
	    trainingMapJs["${entry.key}"] = [];
	    <c:forEach var="s" items="${entry.value}">
	        trainingMapJs["${entry.key}"].push({
	            id: "${s.id}",
	            tr_id: "${s.tr_id}",
	            weight: "${s.tr_weight}",
	            counts: "${s.counts}",
	            sets: "${s.sets}",
	            memo: "${fn:escapeXml(s.memo)}"
	        });
	    </c:forEach>
	</c:forEach>

	//モーダルを開く関数
	function openModal(date, stamp, memo, list) {
	    document.getElementById("modal-date").innerText = date;
	    document.getElementById("modalDate").value = date;
	
	    // スタンプ初期値
	    document.querySelector("select[name='stamp']").value = stamp;
	
	    // トレーニング内容初期値
	    document.getElementById("modal-memo").value = memo || "";
	    	
	    let area = document.getElementById("modal-training-area");
	    area.innerHTML = "";

	    // 1. 既存トレーニング内容のリスト表示
	    if (!list || list.length === 0) {
	        area.innerHTML = "<div class='no-training'>トレーニングなし</div>";
	    } else {
	        list.forEach(obj => {
	            area.innerHTML += 
	                '<form action="/a2/CalendarServlet" method="post" class="training-edit-form">' +
	                    // どのレコードかを識別する主キーIDと、処理分岐用のactionパラメータ
	                    '<input type="hidden" name="id" value="' + obj.id + '">' +
	                    '<input type="hidden" name="action" class="action-field" value="update">' +
	                    	
	                    '<div class="training-row">' +
	                        '<span class="item">種目ID: <input type="number" name="tr_id" value="' + obj.tr_id + '" class="input-small"></span>' +
	                        '<span class="item">重量: <input type="number" name="tr_weight" value="' + obj.weight + '" class="input-small">kg</span>' +
	                        '<span class="item">回数: <input type="number" name="counts" value="' + obj.counts + '" class="input-small">回</span>' +
	                        '<span class="item">セット: <input type="number" name="sets" value="' + obj.sets + '" class="input-small"></span>' +
	                        '<span class="item">メモ: <input type="text" name="tr_memo" value="' + obj.memo + '" class="input-med"></span>' +
	                        	
	                        // 変更ボタンと削除ボタン
	                        '<button type="submit" class="btn-edit">変更</button>' +
	                        '<button type="submit" class="btn-delete" onclick="this.form.querySelector(\'.action-field\').value=\'delete\'; return confirm(\'削除しますか？\');">削除</button>' +
	                    '</div>' +
	                '</form>';
	        });
	    }
	    
	    // 2. 新規追加用のプルダウンフォームの自動生成
	    let optionsHtml = '<option value="" disabled selected>選択してください</option>';
	    masterItemsJs.forEach(item => {
	        optionsHtml += '<option value="' + item.id + '">' + item.name + '</option>';
	    });
	
	    area.innerHTML += 
	        '<form action="/a2/CalendarServlet" method="post" class="training-add-form">' +
	            '<input type="hidden" name="date" value="' + date + '">' +
	            '<input type="hidden" name="action" value="insert">' +
	            
	            '<h5>＋ 新しいトレーニングを追加</h5>' +
	            '<div class="training-row">' +
	                '<span class="item">種目: ' +
	                    '<select name="tr_id" required class="input-select">' +
	                        optionsHtml +
	                    '</select>' +
	                '</span>' +
	                '<span class="item">重量: <input type="number" name="tr_weight" value="0" class="input-small">kg</span>' +
	                '<span class="item">回数: <input type="number" name="counts" value="0" class="input-small">回</span>' +
	                '<span class="item">セット: <input type="number" name="sets" value="0" class="input-small"></span>' +
	                '<span class="item">メモ: <input type="text" name="tr_memo" class="input-med" placeholder="補足など"></span>' +
	                '<button type="submit" class="btn-add">追加</button>' +
	            '</div>' +
	        '</form>';
	
	    document.getElementById("modal-bg").style.display = "block";
	}
	
	function openModalFromTd(td) {
	    const date = td.dataset.date;
	    const stamp = td.dataset.stamp;
	    const memo = td.dataset.memo;

	    // 格納されたオブジェクトから該当日のリストを取得（なければ空配列）
	    const list = trainingMapJs[date] || [];

	    // デバッグ用：ブラウザのF12コンソールでクリックした日付のデータが出ているか確認
	    console.log("選択された日付:", date);
	    console.log("取得されたリスト:", list);

	    openModal(date, stamp, memo, list);
	}

	function closeModal() {
	    document.getElementById("modal-bg").style.display = "none";
	}
</script>
</body>
</html>