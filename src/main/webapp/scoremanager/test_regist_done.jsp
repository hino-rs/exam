<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h4 mb-2 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            
			<p class="p-1 mb-4 text-center"
			     style="background-color:#c8e6c9; border:1px solid #a5d6a7;">
			    登録が完了しました
			</p>
			
			<br> <br>
	           
            <a href="TestRegist.action">戻る</a>            
            <a href="TestList.action" style="margin-left:50px;">成績参照</a>
            
        </section>
     </c:param>
</c:import>
            