<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin: 0 0 20px 0;
    font-weight: normal;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 8px;
    font-size: 0.9em;
}

.readonly-text {
    padding: 0 10px;
    font-size: 1em;
}

input[type="text"], select {
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    border: 1px solid #e0e0e0;
    border-radius: 4px;
    outline: none;
    font-size: 1em;
    font-family: inherit;
}

input[type="text"]:focus, select:focus {
    border-color: #999;
}

.checkbox-group {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.checkbox-group label {
    margin-bottom: 0;
    margin-right: 5px;
}

.btn-submit {
    background-color: #0d6efd;
    color: white;
    border: none;
    border-radius: 4px;
    padding: 8px 16px;
    cursor: pointer;
    font-size: 1em;
    margin-bottom: 15px;
    display: block;
}

.btn-submit:hover {
    background-color: #0b5ed7;
}

.back-link {
    color: #0d6efd;
    text-decoration: none;
    font-size: 0.9em;
}

.back-link:hover {
    text-decoration: underline;
}
</style>
    </c:param>
    <c:param name="content">

    <h2 class="title">学生情報変更</h2>
    
    <form action="StudentUpdateExecute.action" method="post">
    
        <div class="form-group">
            <label>入学年度</label>
            <div class="readonly-text"><c:out value="${ent_year}" /></div>
            <input type="hidden" name="ent_year" value="${ent_year}">
        </div>
    
        <div class="form-group">
            <label>学生番号</label>
            <div class="readonly-text"><c:out value="${no}" /></div>
            <input type="hidden" name="no" value="${no}">
        </div>
    
        <div class="form-group">
            <label>氏名</label>
            <input type="text" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">
        </div>
    
        <div class="form-group">
            <label>クラス</label>
            <select name="class_num">
                <option value="${class_num}" selected>${class_num}</option>
                <c:forEach var="cl" items="${class_list}">
                    <c:choose>
                        <c:when test="${class_num == cl}">
                        </c:when>
                        <c:otherwise>
                            <option value="${cl}">${cl}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    
        <div class="checkbox-group">
            <label>在学中</label>
            <c:choose>
                <c:when test="${is_attend}">
                    <input type="checkbox" name="is_attend" checked>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="is_attend">
                </c:otherwise>
            </c:choose>
        </div>
        
        <button type="submit" name="login" class="btn-submit">変更</button>
    
    </form>
    
    <a href="SutudentList.action" class="back-link">戻る</a>

    </c:param>
</c:import>