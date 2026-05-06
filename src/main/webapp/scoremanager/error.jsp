<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        エラーページ
    </c:param>
    <c:param name="scripts">
    </c:param>
    <c:param name="content">

    <p>${error_message}</p>
    
    
    <a href="Menu.action">メニューへ戻る</a>

    </c:param>
</c:import>