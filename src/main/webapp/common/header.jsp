<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div style="width: 100%; display: flex; justify-content: space-between; align-items: center;">

    <!-- 左側：タイトル -->
    <h1 style="margin: 0;">得点管理システム</h1>

    <!-- 右側：ログイン後のみ表示 -->
    <c:if test="${not empty sessionScope.loginUser}">
        <div style="font-size: 1.1rem; align-self: flex-end;">

            <!-- ユーザー名＋様 -->
            <span>${sessionScope.loginUser} 様</span>

            <!-- ログアウトリンク -->
            <a href="/exam/Logout.action" style="margin-left: 20px; color: blue;">
                ログアウト
            </a>

        </div>
    </c:if>

</div>

<hr>
