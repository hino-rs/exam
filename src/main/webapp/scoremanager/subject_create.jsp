<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">

	<h2>科目情報登録</h2>
	
	<form action="SubjectCreateExecute.action">
	
		<label name="cd">科目コード</label>
		<input type="text" name="cd" required />
	
		<c:if test='${not empty error}'>
			<p class='text-danger text-center mb-3'>${error}</p>
		</c:if>
	
		<label name="name">科目名</label>
		<input type="text" name="name" required />
	
		<input type="submit" value="登録">
	
	</form>
	
	<a href="SubjectList.action">戻る</a>

    </c:param>
</c:import>