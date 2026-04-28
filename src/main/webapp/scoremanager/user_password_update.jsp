<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>
    <c:param name="scripts">
<style>
:root {
    --u-primary:#3b82f6; --u-primary-h:#2563eb;
    --u-danger:#ef4444;
    --u-text:#111827; --u-muted:#6b7280;
    --u-border:#e5e7eb; --u-bg:#f9fafb; --u-card:#fff;
    --u-radius:8px;
    --u-shadow:0 1px 4px rgba(0,0,0,.08),0 1px 2px rgba(0,0,0,.05);
    --u-focus:0 0 0 3px rgba(59,130,246,.22);
}
.u-page-title {
    font-size:1.05rem; font-weight:600; color:var(--u-text);
    padding:11px 16px; background:var(--u-bg);
    border-left:4px solid var(--u-primary);
    border-radius:0 var(--u-radius) var(--u-radius) 0;
    margin-bottom:8px;
}
.u-sub-nav { margin-bottom:20px; }
.u-sub-link { color:var(--u-muted); font-size:0.85rem; text-decoration:none; border-bottom:1px dashed var(--u-muted); }
.u-sub-link:hover { color:var(--u-primary); border-bottom-color:var(--u-primary); }
.u-card {
    background:var(--u-card); border:1px solid var(--u-border);
    border-radius:var(--u-radius); padding:28px;
    box-shadow:var(--u-shadow); max-width:480px;
}
.u-field { margin-bottom:20px; }
.u-label {
    display:block; margin-bottom:6px;
    font-size:0.75rem; font-weight:600;
    color:var(--u-muted); text-transform:uppercase; letter-spacing:.04em;
}
.u-readonly {
    padding:9px 12px; background:var(--u-bg);
    border:1.5px solid var(--u-border); border-radius:var(--u-radius);
    font-size:0.93rem; color:var(--u-text);
}
input[type="password"] {
    width:100%; box-sizing:border-box;
    padding:9px 12px;
    border:1.5px solid var(--u-border); border-radius:var(--u-radius);
    outline:none; font-size:0.93rem; color:var(--u-text);
    background:#fff; transition:border-color .15s, box-shadow .15s;
    font-family:inherit;
}
input[type="password"]:focus { border-color:var(--u-primary); box-shadow:var(--u-focus); }
.u-hint {
    font-size:0.82rem; color:var(--u-muted);
    margin-bottom:16px; line-height:1.5;
}
.u-hint a { color:var(--u-muted); }
.u-btn-submit {
    background:var(--u-primary); color:#fff; border:none;
    border-radius:var(--u-radius); padding:10px 22px;
    font-size:0.93rem; font-weight:600; cursor:pointer;
    transition:background .15s; margin-top:4px;
}
.u-btn-submit:hover { background:var(--u-primary-h); }
.u-nav { margin-top:16px; }
.u-link { color:var(--u-primary); text-decoration:none; font-size:0.88rem; }
.u-link:hover { text-decoration:underline; }
.u-error { color:var(--u-danger); font-size:0.83rem; margin:0 0 12px; text-align:center; }
</style>
    </c:param>
    <c:param name="content">

<h2 class="u-page-title">パスワード変更</h2>

<div class="u-sub-nav">
    <a href="UserUpdate.action" class="u-sub-link">基本情報を変更する場合はこちら</a>
</div>

<div class="u-card">
    <form action="UserPasswordUpdateExecute.action" method="post">

        <div class="u-field">
            <span class="u-label">変更するユーザー</span>
            <div class="u-readonly"><c:out value="${id}" /> さん</div>
            <input type="hidden" name="id" value="${id}">
        </div>

        <c:if test="${not empty error}">
            <p class="u-error">${error}</p>
        </c:if>

        <p class="u-hint">
            新しいパスワードと現在のパスワードの入力が必要です。<br>
            <a href="#">パスワードをお忘れの場合は管理者までご連絡ください。</a>
        </p>

        <div class="u-field">
            <label class="u-label" for="u-old-pw">現在のパスワード</label>
            <input type="password" id="u-old-pw" name="old_password" required>
        </div>

        <div class="u-field">
            <label class="u-label" for="u-new-pw">新しいパスワード</label>
            <input type="password" id="u-new-pw" name="new_password" required>
        </div>

        <button type="submit" class="u-btn-submit">変更を保存</button>
    </form>
</div>

<div class="u-nav">
    <a href="UserList.action" class="u-link">← ユーザー一覧に戻る</a>
</div>

    </c:param>
</c:import>