<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学校管理（変更完了）</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin-bottom: 20px;
}

.message {
    font-size: 1.1em;
    margin-bottom: 20px;
}

.btn-primary {
    padding: 8px 15px;
    font-size: 1em;
}
</style>
    </c:param>

    <c:param name="content">

<h2 class="title">学校管理（変更完了）</h2>

<div class="message">
    学校コード：<b>${school.cd}</b><br>
    学校名：<b>${school.name}</b><br><br>
    上記の内容で更新しました。
</div>

<a href="ClassNumList.action">戻る</a>

    </c:param>
</c:import>
