<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">クラス管理（削除完了）</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin: 0 0 20px 0;
    font-weight: bold;
}

.message {
    font-size: 1.1em;
    margin-bottom: 30px;
}

a {
    color: #0066cc;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
</style>
    </c:param>

    <c:param name="content">

<h2 class="title">クラス管理（削除完了）</h2>

<div class="message">
    ${school_name}（${school_cd}）の ${class_num} を削除しました。
</div>

<a href="ClassNumList.action">クラス一覧へ戻る</a>

    </c:param>
</c:import>
