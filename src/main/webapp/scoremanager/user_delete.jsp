<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>
    <c:param name="scripts">
<style>
:root {
    --u-primary:#3b82f6;
    --u-danger:#ef4444; --u-danger-h:#dc2626;
    --u-warn-bg:#fff7ed; --u-warn-bd:#fed7aa; --u-warn-tx:#92400e;
    --u-text:#111827; --u-muted:#6b7280;
    --u-border:#e5e7eb; --u-bg:#f9fafb; --u-card:#fff;
    --u-radius:8px;
    --u-shadow:0 1px 4px rgba(0,0,0,.08),0 1px 2px rgba(0,0,0,.05);
}
.u-page-title {
    font-size:1.05rem; font-weight:600; color:var(--u-text);
    padding:11px 16px; background:var(--u-bg);
    border-left:4px solid var(--u-danger);
    border-radius:0 var(--u-radius) var(--u-radius) 0;
    margin-bottom:24px;
}
.u-card {
    background:var(--u-card); border:1px solid var(--u-border);
    border-radius:var(--u-radius); padding:28px;
    box-shadow:var(--u-shadow); max-width:480px;
}
.u-confirm-msg {
    font-size:0.95rem; color:var(--u-text);
    margin-bottom:20px; line-height:1.6;
}
.u-confirm-msg strong { color:var(--u-danger); }
.u-warn {
    background:var(--u-warn-bg); border:1px solid var(--u-warn-bd);
    color:var(--u-warn-tx); padding:11px 14px;
    border-radius:var(--u-radius); font-size:0.85rem;
    margin-bottom:20px; line-height:1.5;
}
.u-btn-danger {
    background:var(--u-danger); color:#fff; border:none;
    border-radius:var(--u-radius); padding:10px 22px;
    font-size:0.93rem; font-weight:600; cursor:pointer;
    transition:background .15s;
}
.u-btn-danger:hover { background:var(--u-danger-h); }
.u-nav { margin-top:16px; }
.u-link { color:var(--u-primary); text-decoration:none; font-size:0.88rem; }
.u-link:hover { text-decoration:underline; }
</style>
    </c:param>
    <c:param name="content">

<h2 class="u-page-title">ユーザー情報削除</h2>

<div class="u-card">
    <form action="UserDeleteExecute.action" method="post">
        
		
		<c:choose>
	        <c:when test="${not empty targetIsYourself}">
	            <div class="u-warn">${targetIsYourself}</div>
	            <a href="UserList.action">戻る</a>
	        </c:when>
	        <c:otherwise>
	        	<input type="hidden" name="id" value="${id}">

		        <p class="u-confirm-msg">
		            <strong>「${name}（${id}）」</strong> を削除してもよろしいですか？<br>
		            この操作は取り消せません。
		        </p>
		        
		        <button type="submit" name="delete" class="u-btn-danger">削除する</button>
	        </c:otherwise>
        </c:choose>

        
    </form>
</div>

<div class="u-nav">
    <a href="UserList.action" class="u-link">← キャンセルして戻る</a>
</div>

    </c:param>
</c:import>
