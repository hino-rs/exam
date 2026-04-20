<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>科目管理</h2>

<a href="SubjectCreate.action" method="post">新規登録</a>

<table border="1">
	<tr>
		<th>科目コード</th>
		<th>科目名</th>
	</tr>
	
	<c:forEach var="s" items="${subjects}" >
		<tr>
			<td>${s.cd}</td>
			<td>
				${s.name}
				<form action="SubjectUpdate.action" method="post">
					<input type="hidden" name="cd" value="${s.cd}">
					<input type="hidden" name="name" value="${s.name}">
					<input type="submit" value="変更">
				</form>
				<form action="SubjectDelete.action" method="post">
					<input type="hidden" name="cd" value="${s.cd}">
					<input type="hidden" name="name" value="${s.name}">
					<input type="submit" value="削除">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
