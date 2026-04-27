<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts">
<style>
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border-bottom: 1px solid #e0e0e0;
    padding: 12px 10px;
    text-align: left;
    font-weight: normal;
}

.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin: 0 0 20px 0;
    font-weight: normal;
}

.link-container {
    text-align: right;
    margin-bottom: 10px;
}

.operate {
    display: flex;
    align-items: center;
}

.operate form:first-of-type {
    margin-left: auto;
}

.operate form {
    margin-left: 30px;
}

.btn-submit {
    background-color: transparent;
    border: none;
    cursor: pointer;
    outline: none;
    padding: 0;
    appearance: none;
    
    color: #0066cc;
    font-size: 1em;
}

.btn-submit:hover {
    color: #004499;
    text-decoration: underline;
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
    
    <h2 class="title">クラス管理</h2>
    
    <div class="link-container">
        <a href="UserCreate.action">新規登録</a>
    </div>
    
    <table>
        <thead>
            <tr>
                <th style="width: 15%;">クラス番号</th>
                <th style="width: 15%">学校コード</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${class_num_all}" >
                <tr>
                    <td>${c.class_num}</td>
                    <td>${c.school.cd}</td>
                    <td>
                    	${t.school.cd}
                    	<a href="ClassNumUpdate.action?class_num=${c.class_num}&school_cd=${c.school.cd}">変更</a>
       
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </c:param>
</c:import>