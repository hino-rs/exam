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
    margin-bottom:24px;
}
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
input[type="text"], input[type="password"], select {
    width:100%; box-sizing:border-box;
    padding:9px 12px;
    border:1.5px solid var(--u-border); border-radius:var(--u-radius);
    outline:none; font-size:0.93rem; color:var(--u-text);
    background:#fff; transition:border-color .15s, box-shadow .15s;
    font-family:inherit;
}
input:focus, select:focus {
    border-color:var(--u-primary); box-shadow:var(--u-focus);
}
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
.u-error { color:var(--u-danger); font-size:0.83rem; margin-top:4px; }
</style>
    </c:param>
    <c:param name="content">

<h2 class="u-page-title">ユーザー情報登録</h2>

<div class="u-card">
    <form action="UserCreateExecute.action" method="post">

        <div class="u-field">
            <label class="u-label" for="u-id">ID</label>
            <input type="text" id="u-id" name="id" placeholder="IDを入力してください" required>
            <c:if test="${not empty error}">
                <p class="u-error">${error}</p>
            </c:if>
        </div>

        <div class="u-field">
            <label class="u-label" for="u-pw">パスワード</label>
            <input type="password" id="u-pw" name="password"
                   placeholder="パスワードを入力してください（8文字以上）" required minlength="8">
        </div>

        <div class="u-field">
            <label class="u-label" for="u-name">名前</label>
            <input type="text" id="u-name" name="name" placeholder="名前を入力してください" required>
        </div>

        <div class="u-field">
            <label class="u-label" for="u-school">学校コード</label>
            <select id="u-school" name="school_cd" required>
                <c:forEach var="sc" items="${schoolCodes}">
                    <option value="${sc}">${sc}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="u-btn-submit">登録</button>
    </form>
</div>

<div class="u-nav">
    <a href="UserList.action" class="u-link">← ユーザー一覧に戻る</a>
</div>

    </c:param>
</c:import>