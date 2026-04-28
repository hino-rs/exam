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
    --u-border:#e5e7eb; --u-bg:#f9fafb;
    --u-radius:8px;
}
.u-page-title {
    font-size: 1.05rem; font-weight: 600; color: var(--u-text);
    padding: 11px 16px;
    background: var(--u-bg);
    border-left: 4px solid var(--u-primary);
    border-radius: 0 var(--u-radius) var(--u-radius) 0;
    margin-bottom: 20px;
}
.u-page-header { display:flex; justify-content:flex-end; margin-bottom:14px; }
.u-btn-new {
    display:inline-block; background:var(--u-primary); color:#fff;
    padding:7px 16px; border-radius:var(--u-radius);
    font-size:0.85rem; font-weight:600; text-decoration:none;
    transition:background .15s;
}
.u-btn-new:hover { background:var(--u-primary-h); text-decoration:none; }

.u-table { width:100%; border-collapse:collapse; }
.u-table th {
    font-size:0.72rem; font-weight:600; text-transform:uppercase;
    letter-spacing:.05em; color:var(--u-muted);
    padding:9px 14px; border-bottom:2px solid var(--u-border); text-align:left;
}
.u-table td {
    padding:12px 14px; border-bottom:1px solid #f3f4f6;
    font-size:0.9rem; color:var(--u-text); vertical-align:middle;
}
.u-table tbody tr:hover td { background:#f5f8ff; }

.u-actions { display:flex; gap:14px; }
.u-link { color:var(--u-primary); text-decoration:none; font-size:0.85rem; }
.u-link:hover { text-decoration:underline; }
.u-link-danger { color:var(--u-danger); text-decoration:none; font-size:0.85rem; }
.u-link-danger:hover { text-decoration:underline; }
</style>
    </c:param>
    <c:param name="content">

<h2 class="u-page-title">ユーザー管理</h2>

<div class="u-page-header">
    <a href="UserCreate.action" class="u-btn-new">＋ 新規登録</a>
</div>

<table class="u-table">
    <thead>
        <tr>
            <th style="width:15%">ID</th>
            <th style="width:22%">名前</th>
            <th>学校コード</th>
            <th style="width:14%"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="t" items="${teachers}">
            <tr>
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td>${t.school.cd}</td>
                <td>
                    <div class="u-actions">
                        <a href="UserUpdate.action?id=${t.id}&amp;name=${t.name}&amp;school_cd=${t.school.cd}"
                           class="u-link">変更</a>
                        <a href="UserDelete.action?id=${t.id}&amp;name=${t.name}"
                           class="u-link-danger">削除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    </c:param>
</c:import>