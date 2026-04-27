<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/common/header.jsp" %>


<h2>学生登録情報</h2>

<form action="ClassNumCreateExecute.action" method="post">


	<br>

    <label>新規クラス番号</label><br>
    <input type="text" name="class_num" value="${class_num}" maxlength="3" required placeholder="クラス番号を入力してください">

	<br>

    <label>学校コード</label><br>
    <select name="school_cd">
        <c:forEach var="cd" items="${school_cd}">
        <option value="${cd}">${cd}</option>
    </c:forEach>
    </select>

	<br><br>

    <button type="submit" name="end">登録して終了</button>

</form>


<a href="ClassNumCreateExecute.action">戻る</a>

<%@ include file="/common/footer.jsp" %>
