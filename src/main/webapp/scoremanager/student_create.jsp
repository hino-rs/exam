<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/common/header.jsp" %>


<h2>学生登録情報</h2>

<form action="StudentCreateExecute.action" method="post">

    <label>入学年度</label><br>
    <select class="form-select" id="student-f1-select" name="ent_year">
                            <option value="0">--</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                            ${year}
                        </option>
                    </c:forEach>
                </select>
    
                
	 <c:if test='${not empty error1}'>
	 <font color="#fcc800">
            <p class='text-danger text-center mb-3'>${error1}</p>
     </font>
     </c:if>
	
	 <br>

    <label>学生番号</label><br>
    <input type="text" name="no" value="${no}" maxlength="10" required placeholder="学生番号を入力してください">
	 <div>
	 <c:if test='${not empty error2}'>
	 <font color="#fcc800">
            <p class='text-danger text-center mb-3'>${error2}</p>
     </font>
     </c:if>
     </div>
	<br>

    <label>氏名</label><br>
    <input type="text" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">

	<br>

    <label>クラス</label><br>
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
