<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
		<section class="me-4">
		    <h2 class="h4 mb-3" style="background-color:#f5f5f5; padding:10px 15px;">メニュー</h2>
		
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
		    </div>
		    <div class="row text-center px-4 fs-3 my-5">
		    	<!-- ユーザー管理（緑）-->
		        <div class="col-4">
		            <div class="d-flex flex-column align-items-center justify-content-center mb-4 p-4 rounded shadow"
		                 style="height: 10rem; background-color: #d5e8d4;">
		                <div>ユーザー管理</div>
		                <div><a href="UserList.action">ユーザー登録</a></div>
		                <div><a href="UserList.action">ユーザー更新</a></div>
		            </div>
		        </div>
		
		        <!-- クラス管理（紫） -->
		        <div class="col-4">
		            <div class="d-flex align-items-center justify-content-center mb-4 p-4 rounded shadow"
		                 style="height: 10rem; background-color: #e1d5e7;">
		                <a href="">クラス管理</a>
		            </div>
		        </div>
		    </div>
		</section>
    </c:param>
</c:import>