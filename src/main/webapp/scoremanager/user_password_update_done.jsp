<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>
    <c:param name="scripts">
<style>
:root {
    --u-primary:#3b82f6;
    --u-success-bg:#ecfdf5; --u-success-bd:#6ee7b7; --u-success-tx:#065f46;
    --u-text:#111827; --u-bg:#f9fafb;
    --u-radius:8px;
}
.u-page-title {
    font-size:1.05rem; font-weight:600; color:var(--u-text);
    padding:11px 16px; background:var(--u-bg);
    border-left:4px solid var(--u-primary);
    border-radius:0 var(--u-radius) var(--u-radius) 0;
    margin-bottom:24px;
}
.u-alert-success {
    background:var(--u-success-bg); border:1px solid var(--u-success-bd);
    color:var(--u-success-tx); padding:14px 18px;
    border-radius:var(--u-radius); margin-bottom:24px;
    font-weight:500; display:flex; align-items:center; gap:8px;
}
.u-alert-success::before { content:"✓"; font-size:1.1rem; }
.u-link { color:var(--u-primary); text-decoration:none; font-size:0.88rem; }
.u-link:hover { text-decoration:underline; }
</style>
    </c:param>
    <c:param name="content">

<h2 class="u-page-title">パスワード変更</h2>

<div class="u-alert-success">変更が完了しました</div>

<a href="UserList.action" class="u-link">← ユーザー一覧に戻る</a>

    </c:param>
</c:import>