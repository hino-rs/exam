<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/common/header.html" %>

<div style="display: flex; justify-content: center; margin-top: 40px;">

    <!-- ログインボックス -->
    <div style="
        width: 350px;
        background: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    ">

        <h2 style="text-align: center; margin-bottom: 20px;">ログイン</h2>

        <!-- エラー表示 -->
        <c:if test="${not empty errors}">
            <ul style="color: red;">
                <c:forEach var="e" items="${errors}">
                    <li>${e}</li>
                </c:forEach>
            </ul>
        </c:if>

        <form action="Login.action" method="post">

            <!-- ID -->
            <div style="margin-bottom: 15px;">
                <label>ID</label><br>
                <input type="text" name="id" value="${id}" required
                    style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- パスワード -->
            <div style="margin-bottom: 10px;">
                <label>パスワード</label><br>
                <input type="password" id="password" name="password" required
                    style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- パスワード表示チェック -->
            <div style="margin-bottom: 20px;">
                <label>
                    <input type="checkbox" id="showPassword">
                    パスワードを表示
                </label>
            </div>

            <!-- ログインボタン -->
            <div style="text-align: center;">
                <input type="submit" value="ログイン"
                    style="
                        width: 100%;
                        padding: 10px;
                        background-color: #0d6efd;
                        color: white;
                        border: none;
                        border-radius: 4px;
                        font-size: 16px;
                        cursor: pointer;
                    ">
            </div>

        </form>
    </div>
</div>

<script>
    // パスワード表示 / 非表示
    document.getElementById("showPassword").addEventListener("change", function() {
        const pw = document.getElementById("password");
        pw.type = this.checked ? "text" : "password";
    });
</script>

<%@ include file="/common/footer.html" %>




