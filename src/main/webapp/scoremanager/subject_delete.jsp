<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">

	<h2>科目情報削除</h2>
	<form action="SubjectDeleteExecute.action">
		
		<input type="hidden" name="cd" value="${s.cd}">
		<input type="hidden" name="name" value="${s.name}">
		
		<label name="delete">
			<p>「${name}(${cd})」を削除してもよろしいですか</p>
		</label>
		
		<input type="submit" value="削除" name="delete">
	
	</form>
	
	<a href="SubjectList.action">戻る</a>

    </c:param>
</c:import>