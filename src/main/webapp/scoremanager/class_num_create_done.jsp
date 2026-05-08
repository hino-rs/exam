<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">
        学生情報登録
    </c:param>

	<c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            
			<p>登録が完了しました</p>
			
			<div class="mt-3">
				<a href="ClassNumCreate.action">戻る</a>
			</div>
			<div class="mt-3">
				<a href="ClassNumList.action">クラス一覧</a>
			</div>
	
		</section>
    </c:param>
</c:import>