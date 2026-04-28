<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin: 0 0 20px 0;
    font-weight: normal;
}

.confirm-text {
    margin-bottom: 20px;
    font-size: 1em;
}

.btn-danger {
    background-color: #dc3545;
    color: white;
    border: none;
    border-radius: 4px;
    padding: 8px 16px;
    cursor: pointer;
    font-size: 1em;
    margin-bottom: 25px;
    display: block;
}

.btn-danger:hover {
    background-color: #bb2d3b;
}

.back-link {
    color: #0d6efd;
    text-decoration: none;
    font-size: 0.9em;
}

.back-link:hover {
    text-decoration: underline;
}
</style>
    </c:param>
    <c:param name="content">

    <h2 class="title">科目情報削除</h2>
    
    <form action="SubjectDeleteExecute.action" method="post">
        
        <input type="hidden" name="cd" value="${cd}">
        <input type="hidden" name="name" value="${name}">
        
        <p class="confirm-text">「${name}(${cd})」を削除してもよろしいですか</p>
        <font color="orange"><c:if test="${alreadyUsed}">${name}は既にテストで使われている科目です。この科目を削除するには、先に関連するテストの履歴を削除する必要があります。</c:if></font>     
        <input type="submit" value="削除" name="delete" class="btn-danger">
    
    </form>
    
    <a href="SubjectList.action" class="back-link">戻る</a>

    </c:param>
</c:import>