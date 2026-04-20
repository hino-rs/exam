<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>科目情報変更</h2>

	<form action="SubjectUpdateExecute.action">
	
	<label>科目コード</label>
	<input type="text" name="cd" value="${cd}" readonly>
	
	<label>科目名</label>
	<input type="text" name="name" value="${name}">
	
	<input type="submit" value="変更">

</form>

<a href="SubjectList.action">戻る</a>