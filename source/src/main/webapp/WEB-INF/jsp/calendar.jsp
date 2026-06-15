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
	
	<div class="calendar-container">
	    <table>
	        <tr>
	            <th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
	        </tr>
	
	        <!-- カレンダー作成 -->
	        <c:set var="day" value="1" />
			
			<c:forEach var="week" begin="1" end="6">
			    <tr>
			        <c:forEach var="dow" begin="0" end="6">
			
			            <c:choose>
			                <%-- 1週目：1日の曜日まで空白 --%>
			                <c:when test="${week == 1 && dow < startDay}">
			                    <td></td>
			                </c:when>
			
			                <%-- 月末を超えたら空白 --%>
			                <c:when test="${day > lastDay}">
			                    <td></td>
			                </c:when>
			
			                <%-- 日付表示 --%>
                            <c:otherwise>
                                <%-- ★ fullDate を作成（例：2026-07-10） --%>
                                <c:set var="fullDate"
                                       value="${year}-${month < 10 ? '0' : ''}${month}-${day < 10 ? '0' : ''}${day}" />
                                <td onclick="openModal('${month}月${day}日')">
                                    <div class="date-num">${day}</div>
                                    <%-- ★ スタンプがある日だけ表示 --%>
                                    <c:if test="${stampMap[fullDate] != null}">
                                        <img src="/a2/img/stamp${stampMap[fullDate]}.png" class="stamp">
                                    </c:if>
                                </td>
                                <c:set var="day" value="${day + 1}" />
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
	
	        <p><strong>トレーニング内容</strong></p>
	        <p id="modal-tr1"></p>
	        <p id="modal-tr2"></p>
	
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
	function openModal(date, tr1, tr2) {
		// モーダル内の日付表示を更新
	    document.getElementById("modal-date").innerText = date;
	 	// トレーニングの内容を表示（null/空なら「記録なし」）
	    document.getElementById("modal-tr1").innerText = tr1 || "記録なし";
	    document.getElementById("modal-tr2").innerText = tr2 || "記録なし";
	
	    document.getElementById("modal-bg").style.display = "block";
	}
	
	function closeModal() {
	    document.getElementById("modal-bg").style.display = "none";
	}
</script>
</body>
</html>