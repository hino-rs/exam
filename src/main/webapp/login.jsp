<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/common/header.html" %>

<h2>ログイン</h2>

<c:if test="${not empty errors}">
    <ul>
        <c:forEach var="e" items="${errors}">
            <li>${e}</li>
        </c:forEach>
    </ul>
</c:if>

<form action="Login.action" method="post">
    <p>ID <input type="text" name="id" value="${id}" required></p>
    <p>パスワード <input type="password" name="password" required></p>
    <p><input type="submit" value="ログイン"></p>
</form>

<%@ include file="/common/footer.html" %>


