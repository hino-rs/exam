<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/common/header.jsp" %>


<h2>学生登録情報</h2>

<form action="StudentCreateExecute.action" method="post">

    <label>入学年度</label>
    <select class="form-select" id="student-f1-select" name="ent_year">
                            <option value="0">--</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                            ${year}
                        </option>
                    </c:forEach>
                </select>
     <br><br>
                
	 <c:if test='${not empty error1}'>
            <p class='text-danger text-center mb-3'>${error1}</p>
     </c:if>
	

    <label>学生番号</label>
    <input type="text" name="no" value="${no}" maxlength="10" required placeholder="学生番号を入力してください">
	 <c:if test='${not empty error2}'>
            <p class='text-danger text-center mb-3'>${error2}</p>
     </c:if>
	<br><br>

    <label>氏名</label>
    <input type="text" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">

	<br><br>

    <label>クラス</label>
    <select name="class_num">
        <c:forEach var="num" items="${class_num}">
        <option value="${num}">${num}</option>
    </c:forEach>
    </select>

	<br><br>

    <button type="submit" name="end">登録して終了</button>

</form>


<a href="StudentList.action">戻る</a>

<%@ include file="/common/footer.jsp" %>
