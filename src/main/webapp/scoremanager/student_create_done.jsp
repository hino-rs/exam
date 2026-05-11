<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">
        学生情報登録
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

/* ← 追加：リンクを横並びに */
.link-row {
    display: flex;
    gap: 60px;
    margin-top: 20px;
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
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
			<p class="p-1 mb-4 text-center"
			     style="background-color:#c8e6c9; border:1px solid #a5d6a7;">
			    登録が完了しました
			</p>
			
			<br><br>
			
			 <!-- 横並びリンク -->
            <div class="link-row">
                <a href="StudentCreate.action" class="back-link">戻る</a>
                <a href="StudentList.action" class="back-link">学生一覧</a>
            </div>
	
		</section>
    </c:param>
</c:import>