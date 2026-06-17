<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            	<td onclick="openModal(
							            '${dayData.fullDate}',
							            '${stampMap[dayData.fullDate]}',
							            '${memoMap[dayData.fullDate]}')">
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
	//モーダルを開く関数
	function openModal(date, stamp, memo) {
	    document.getElementById("modal-date").innerText = date;
	    document.getElementById("modalDate").value = date;
	
	    // スタンプ初期値
	    document.querySelector("select[name='stamp']").value = stamp;
	
	    // トレーニング内容初期値
	    document.getElementById("modal-memo").value = memo || "";
	    
	 	
	
	    document.getElementById("modal-bg").style.display = "block";
	}
	
	function openModalFromTd(td) {
	    const trainingStr = trainingData[date] || "";
	    openModal(date, stamp, memo, trainingStr);
	}

	function closeModal() {
	    document.getElementById("modal-bg").style.display = "none";
	}
</script>
</body>
</html>