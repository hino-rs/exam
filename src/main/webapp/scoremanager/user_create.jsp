<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

input[type="text"] {
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    border: 1px solid #e0e0e0;
    border-radius: 4px;
    outline: none;
}

input[type="text"]:focus {
    border-color: #999;
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

.text-danger {
    color: #dc3545;
}
</style><!-- INSERT INTO teacher VALUES('admin', 'password', '大原花子', 'oom');-->
    </c:param>
    <c:param name="content">

    <h2 class="title">ユーザー情報登録</h2>
    
    <form action="UserCreateExecute.action" method="post">
    
        <div class="form-group">
            <label for="cd">ID</label>
            <input type="text" id="cd" name="id" placeholder="IDを入力してください" required />
        </div>
    
        <c:if test='${not empty error}'>
            <p class='text-danger text-center mb-3'>${error}</p>
        </c:if>
        
        <div class="form-group">
            <label for="cd">パスワード</label>
            <input type="password" id="cd" name="password" placeholder="パスワードを入力してください" required />
        </div>
    
        <div class="form-group">
            <label for="name">名前</label>
            <input type="text" id="name" name="name" placeholder="名前を入力してください" required />
        </div>
        
        <div class="form-group">
	        <label>学校コード</label>
	        <select name="school_cd" required>
	        	<c:forEach var="sc" items="${schoolCodes}">
	        		<option value="${sc}">${sc}</option>
	        	</c:forEach>
	        </select>
        </div>
    
        <input type="submit" value="登録" class="btn-submit">
    
    </form>
    
    <a href="UserList.action" class="back-link">戻る</a>

    </c:param>
</c:import>