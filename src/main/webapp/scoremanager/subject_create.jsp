<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
