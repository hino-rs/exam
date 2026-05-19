<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
    font-size: 1.5em;
    margin: 0 0 20px 0;
    font-weight: normal;
    font-weight: bold;
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

.btn-delete {
    background-color: transparent;
    border: none;
    cursor: pointer;
    color: #cc0000;
    font-size: 1em;
}

.btn-delete:hover {
    color: #990000;
    text-decoration: underline;
}

.operate {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #e0e0e0;
}

th {
    font-weight: bold;
}

.btn-primary {
    background-color: #007bff;
    color: white !important;
    padding: 6px 14px;
    border-radius: 4px;
    text-decoration: none;
    font-size: 0.95em;
}
.btn-primary:hover {
    background-color: #0056b3;
    text-decoration: none;
}
</style>
    </c:param>
    <c:param name="content">
    
    <h2 class="title">クラス管理</h2>
    
    <div class="link-container">
    	<a href="ClassNumCreate.action" class="btn-primary">新規登録</a>
    </div>
    
    <table>
        <thead>
            <tr>
                <th style="width: 20%;">クラス番号</th>
	            <th style="width: 20%;">学校コード</th>
	            <th style="width: 40%;">学校名</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${class_num_all}" >
                <tr>
                    <td>${c.class_num}</td>
                    <td>${c.school.cd}</td>
                    
                    <td class="operate">
                    	${c.school.name}                 
	                    <!-- 変更 -->
	                    <form action="ClassNumUpdate.action" method="get">
	                        <input type="hidden" name="class_num" value="${c.class_num}">
	                        <input type="hidden" name="school_cd" value="${c.school.cd}">
	                        <button class="btn-submit">変更</button>
	                    </form>	
	                    <!-- 削除 -->
						<form action="ClassNumDelete.action" method="post"
						      onsubmit="return confirm('削除してよろしいですか？');">
						    <input type="hidden" name="class_num" value="${c.class_num}">
						    <input type="hidden" name="school_cd" value="${c.school.cd}">
						    <button class="btn-delete">削除</button>
						</form>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </c:param>
</c:import>