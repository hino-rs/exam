<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        成績管理システム
    </c:param>
    <c:param name="scripts">
    <style>      
        .menu-card a:hover {
            text-decoration: underline;
        }
        /* モバイル対応：文字サイズの微調整 */
        @media (max-width: 576px) {
            .fs-3 {
                font-size: 1.5rem !important;
            }
        }
    </style>
    </c:param>
    <c:param name="content">
		<section class="me-4">
		    <h2 class="h4 mb-3" style="background-color:#f5f5f5; padding:10px 15px;">メニュー</h2>
		
		    <!-- 1段目：学生・成績・科目 -->
		    <div class="row text-center px-4 fs-3 my-md-5">
		
		        <!-- 学生管理 -->
		        <div class="col-12 col-md-4 mb-4">
		            <div class="menu-card d-flex flex-column align-items-center justify-content-center p-4 rounded shadow"
		                 style="min-height: 10rem; background-color: #f7c6c7;">
		                <div><a href="StudentList.action">学生管理</a></div>
		                <div><a href="StudentCsvUpload.action">CSV学生登録</a></div>
		            </div>
		        </div>
		
		        <!-- 成績管理 -->
		        <div class="col-12 col-md-4 mb-4">
		            <div class="menu-card d-flex flex-column align-items-center justify-content-center p-4 rounded shadow"
		                 style="min-height: 10rem; background-color: #d5e8d4;">
		                <div class="fw-bold text-dark mb-1">成績管理</div>
		                <div><a href="TestRegist.action">成績登録</a></div>
		                <div><a href="TestList.action">成績参照</a></div>
		            </div>
		        </div>
		
		        <!-- 科目管理 -->
		        <div class="col-12 col-md-4 mb-4">
		            <div class="menu-card d-flex align-items-center justify-content-center p-4 rounded shadow"
		                 style="min-height: 10rem; background-color: #e1d5e7;">
		                <a href="SubjectList.action">科目管理</a>
		            </div>
		        </div>
		    </div>

		    <!-- 2段目：ユーザー・クラス -->
		    <div class="row text-center px-4 fs-3">
		    	<!-- ユーザー管理 -->
		        <div class="col-12 col-md-4 mb-4">
		            <div class="menu-card d-flex flex-column align-items-center justify-content-center p-4 rounded shadow"
		                 style="min-height: 10rem; background-color: #d5e8d4;">
		                <div class="fw-bold text-dark mb-1">ユーザー管理</div>
		                <div><a href="UserList.action">ユーザー登録</a></div>
		            </div>
		        </div>
		
		        <!-- クラス管理 -->
		        <div class="col-12 col-md-4 mb-4">
		            <div class="menu-card d-flex align-items-center justify-content-center p-4 rounded shadow"
		                 style="min-height: 10rem; background-color: #e1d5e7;">
		                <a href="ClassNumList.action">クラス管理</a>
		            </div>
		        </div>
		    </div>
		</section>
    </c:param>
</c:import>