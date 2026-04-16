<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>科目管理</h2>

<a href="#">新規登録</a>

<table border="1">
	<tr>
		<th>科目コード</th>
		<th>科目名</th>
	</tr>
	
	<c:forEach var="s" items="${subjects}" >
		<tr>
			<td>${s.code}</td>
			<td>
				${s.name}
				<a href="#">変更</a>
				<a href="#">削除</a>
			</td>
		</tr>
	</c:forEach>
</table>