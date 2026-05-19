<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">クラス管理（変更）</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin-bottom: 20px;
}

.form-block {
    width: 60%;
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

.error-strong {
    color: #cc0000 !important;
    font-weight: bold !important;
    font-size: 20px !important;
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

<h2 class="title">クラス管理（変更）</h2>

<div class="form-block">

    <c:if test="${not empty error}">
        <div class="error-strong">
            ${error}
        </div>
    </c:if>

    <form action="ClassNumUpdateExecute.action" method="post">

        <!-- 学校コード（変更不可） -->
        <div class="form-row">
            <label>学校コード</label>
            <input type="text" value="${school_cd}" readonly class="readonly-box">
            <input type="hidden" name="school_cd" value="${school_cd}">
        </div>

        <!-- 学校名（変更不可） -->
        <div class="form-row">
            <label>学校名</label>
            <input type="text" value="${school_name}" readonly class="readonly-box">
        </div>

        <!-- 旧クラス番号 -->
        <input type="hidden" name="old_class_num" value="${class_num}">

        <!-- クラス番号（変更可能） -->
        <div class="form-row">
            <label>クラス番号</label>
            <input type="text" name="class_num" value="${class_num}" maxlength="3" required>
        </div>

        <button class="btn btn-primary">更新</button>
    </form>

    <br><br>

    <!-- 学校名変更へ -->
    <a href="SchoolUpdate.action?school_cd=${school_cd}">
        ※ 学校名を変更する場合はこちら
    </a>

    <br><br>

    <a href="ClassNumList.action">戻る</a>

</div>

    </c:param>
</c:import>
