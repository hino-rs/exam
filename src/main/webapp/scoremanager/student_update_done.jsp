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

.alert-success {
    background-color: #8cb89f;
    color: #000;
    padding: 12px;
    margin-bottom: 40px;
    text-align: center;
}

.back-link {
    color: #0000ff;
    text-decoration: underline;
    font-size: 0.9em;
}

.back-link:hover {
    color: #0000cc;
}
</style>
    </c:param>
    <c:param name="content">
    	<section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
            
			<p class="p-1 mb-4 text-center"
			     style="background-color:#c8e6c9; border:1px solid #a5d6a7;">
			    変更が完了しました
			</p>
			
			<br><br>
			
			<a href="StudentList.action" class="back-link">学生一覧</a>
	
		</section>
    </c:param>
</c:import>