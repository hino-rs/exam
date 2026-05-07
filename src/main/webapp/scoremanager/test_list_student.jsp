<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">成績参照</c:param>

    <c:param name="scripts">
        <style>

            /* 検索結果テーブル：横線だけ（縦線・左右枠なし） */
            .table-horizontal-only,
            .table-horizontal-only th,
            .table-horizontal-only td {
                border-left: none !important;
                border-right: none !important;
            }

            /* table-bordered の外枠（左右）も無効化 */
            .table-horizontal-only.table-bordered {
                border-left: none !important;
                border-right: none !important;
            }
        </style>
    </c:param>

<c:param name="content">
	<section class="me-4">
	
	    <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
	    	成績一覧（学生）
	    </h2>
	    
	    <!-- 共通検索フォーム 追加 -->
	    <jsp:include page="/common/test_list_search.jsp" />
	  
	    <!-- 学生情報メッセージ -->
	    <c:if test="${not empty student}">
		    <div>
		    	氏名：${student.name}（${student.no}）
			</div>
	    </c:if>
		
	    <!-- 成績情報：検索結果がある場合 -->
	    <c:if test="${not empty list}">
	         <table class="table table-bordered mt-3 table-horizontal-only">
	             <thead>
	                 <tr>
	                     <th>科目名</th>
	                     <th>科目コード</th>
	                     <th>回数</th>
	                     <th>点数</th>
	                 </tr>
	             </thead>
	
	             <tbody>
	                 <c:forEach var="t" items="${list}">
	                     <tr>
	                         <td>${t.subjectName}</td>
	                         <td>${t.subjectCd}</td>
	                         <td>${t.num}</td>
	                         <td>${t.point}</td>
	                     </tr>
	                </c:forEach>
	             </tbody>
	         </table>
	     </c:if>
	     <!-- 成績情報：検索結果がない場合 -->
		 <c:if test="${empty list}">
		     <div>
		         成績情報が存在しませんでした
		     </div>
		 </c:if>
		
	</section>
</c:param>

</c:import>
