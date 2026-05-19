<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学校管理（変更）</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin: 0 0 20px 0;
}

.form-block {
    width: 60%;
    margin-left: 0;
}

.form-row {
    margin-bottom: 20px;
}

label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
}

input[type="text"] {
    width: 60%;
    padding: 8px 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.readonly-box {
    background-color: #e9e9e9;
    color: #555;
}

.error {
    color: #cc0000;
    margin-bottom: 15px;
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

<h2 class="title">学校管理（変更）</h2>

<div class="form-block">

    <!-- エラー表示 -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="SchoolUpdateExecute.action" method="post">

        <!-- 学校コード（変更不可） -->
        <div class="form-row">
            <label>学校コード</label>
            <input type="text" name="cd" value="${school.cd}" readonly class="readonly-box">
        </div>

        <!-- 学校名（変更可能） -->
        <div class="form-row">
            <label>学校名</label>
            <input type="text" name="name" value="${school.name}" maxlength="50" required>
        </div>
        
        <button class="btn btn-primary">更新</button>
    </form>

    <br><br>

    <a href="ClassNumList.action">戻る</a>

</div>

    </c:param>
</c:import>
