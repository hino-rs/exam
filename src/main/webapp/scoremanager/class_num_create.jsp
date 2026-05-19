<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">クラス新規登録</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin-bottom: 20px;
    font-weight: bold;
}

.form-block {
    margin-bottom: 20px;
}

label {
    font-weight: bold;
}

input[type="text"], select {
    padding: 6px;
    width: 250px;
}

.radio-area {
    margin-bottom: 10px;
}

.hidden {
    display: none;
}

.error {
    color: red;
    margin-bottom: 15px;
    font-weight: bold;
}

.disabled-text {
    opacity: 0.5;
}
</style>

<script>
function toggleSchoolMode() {
    const mode = document.querySelector('input[name="school_mode"]:checked').value;

    const existingLabel = document.getElementById("existing_label");
    const existingArea = document.getElementById("existing_school_area");
    const newArea = document.getElementById("new_school_area");

    if (mode === "existing") {
        existingLabel.classList.remove("disabled-text");
        existingArea.style.display = "block";
        newArea.style.display = "none";
    } else {
        existingLabel.classList.add("disabled-text");
        existingArea.style.display = "none"; 
        newArea.style.display = "block";
    }
}
</script>

    </c:param>

    <c:param name="content">

<h2 class="title">クラス新規登録</h2>

<!-- エラー表示 -->
<c:if test="${not empty error2}">
    <div class="error">${error2}</div>
</c:if>

<form action="ClassNumCreateExecute.action" method="post">

    <!-- 学校選択 or 新規作成 -->
    <div class="form-block">
        <label>学校：</label><br>

        <div class="radio-area">
            <label id="existing_label">
                <input type="radio" name="school_mode" value="existing" checked onclick="toggleSchoolMode()">
                既存の学校を選択する
            </label>
        </div>

        <!-- 既存学校プルダウン（新規時は非表示） -->
        <div id="existing_school_area">
            <select name="school_cd">
                <c:forEach var="s" items="${school_list}">
                    <option value="${s.cd}">${s.name}（${s.cd}）</option>
                </c:forEach>
            </select>
        </div>

        <div class="radio-area" style="margin-top:15px;">
            <label>
                <input type="radio" name="school_mode" value="new" onclick="toggleSchoolMode()">
                新しい学校を作成する
            </label>
        </div>

        <!-- 新規学校入力 -->
        <div id="new_school_area" class="hidden">
            <div style="margin-top:10px;">
                <b>学校コード（半角）：</b><br>
                <input type="text" name="new_school_cd">
            </div>
            <div style="margin-top:10px;">
                <b>学校名：</b><br>
                <input type="text" name="new_school_name">
            </div>
        </div>
    </div>

    <!-- クラス番号 -->
    <div class="form-block">
        <label>クラス番号（半角）：</label><br>
        <input type="text" name="class_num">
    </div>

    <button class="btn btn-primary">登録する</button>

</form>

    </c:param>
</c:import>
