<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
		<section class="me-4">
		    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>
		
		    <div class="row text-center px-4 fs-3 my-5">
		
		        <!-- 学生管理（ピンク） -->
		        <div class="col">
		            <div class="d-flex align-items-center justify-content-center mb-4 p-4 rounded shadow"
		                 style="height: 10rem; background-color: #f7c6c7;">
		                <a href="StudentList.action">学生管理</a>
		            </div>
		        </div>
		
		        <!-- 成績管理（緑）-->
		        <div class="col">
		            <div class="d-flex flex-column align-items-center justify-content-center mb-4 p-4 rounded shadow"
		                 style="height: 10rem; background-color: #d5e8d4;">
		                <div>成績管理</div>
		                <div><a href="TestRegist.action">成績登録</a></div>
		                <div><a href="TestList.action">成績参照</a></div>
		            </div>
		        </div>
		
		        <!-- 科目管理（紫） -->
		        <div class="col">
		            <div class="d-flex align-items-center justify-content-center mb-4 p-4 rounded shadow"
		                 style="height: 10rem; background-color: #e1d5e7;">
		                <a href="SubjectList.action">科目管理</a>
		            </div>
		        </div>
		
				<a href="UserList.action">ユーザー管理</a>
		    </div>
		</section>
    </c:param>
</c:import>