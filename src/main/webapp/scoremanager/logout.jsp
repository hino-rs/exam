<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">ログアウト</c:param>

    <c:param name="content">
    	<h2 class="h4 mb-2" 
		    style="background-color:#f5f5f5; padding:10px 15px;">
		    ログアウト
		</h2>

        <p class="p-1 mb-4 text-center"
		     style="background-color:#c8e6c9; border:1px solid #a5d6a7;">
		    ログアウトしました
		</p>

        
        <br> <br>

        <a href="Login.action" class="text-primary">ログイン</a>
    </c:param>
</c:import>
