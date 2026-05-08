<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts">

</c:param>
    <c:param name="content">
    
    <h2 class="title">クラス管理</h2>
    
<form action="ClassNumCreateExecute.action" method="post">


	<br>

    <label>新規クラス番号</label><br>
    <input type="text" name="class_num" value="${class_num}" maxlength="3" required placeholder="クラス番号を入力してください">
    <c:if test='${not empty error2}'>
        <p class='text-danger mt-1'>${error2}</p>
    </c:if>
	<br>

    <label>学校コード</label><br>
    <select name="school_cd">
        <c:forEach var="c" items="${class_num_list}">
        <option value="${c}">${c}</option>
    </c:forEach>
    </select>

	<br><br>

    <button type="submit" name="end">登録して終了</button>

</form>


<a href="ClassNumList.action">戻る</a>

	</c:param>
</c:import>
