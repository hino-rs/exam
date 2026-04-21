<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
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
    
    <h2 class="title">ユーザー管理</h2>
    
    <div class="link-container">
        <a href="SubjectCreate.action">新規登録</a>
    </div>
    
    <table>
        <thead>
            <tr>
                <th style="width: 15%;">ID</th>
                <th style="width: 15%">名前</th>
                <th>学校コード</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="t" items="${teachers}" >
                <tr>
                    <td>${t.id}</td>
                    <td>${t.name}</td>
                    <td>
                    	${t.school.cd}
                    	<a href="UserUpdate.action?id=${t.id}&name=${t.name}&school_cd=${t.school.cd}">変更</a>
                    	<a href="UserDelete.action?id=${t.id}&name=${t.name}&school_cd=${t.school.cd}">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </c:param>
</c:import>