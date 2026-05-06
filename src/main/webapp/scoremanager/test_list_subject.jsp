<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp"><c:param name="title">成績参照</c:param>

<c:param name="scripts">
<style>
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border-bottom: 1px solid #e0e0e0;
    padding: 8px 10px;
    text-align: left;
    font-weight: normal;
}
/* 追加：ヘッダーのみ太字 */
th {
    font-weight: bold;
}

</style>
</c:param>

<c:param name="content">

    <section class="me-4">
   	    <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
    		成績一覧（科目）
	    </h2>
	    
	    <!-- 共通検索フォーム 追加 -->
	    <jsp:include page="/common/test_list_search.jsp" />
	    
		<!-- 科目：◯◯ -->
        <c:if test="${not empty data}">
            <div class="mt-1">
			    科目：${data[0].subjectName}
			</div>
        </c:if>

        <!-- データなしエラー -->
        <c:if test="${not empty outErr}">
            <div class="mt-2">
                ${outErr}
            </div>
        </c:if>

		<!-- 成績一覧 -->
        <c:if test="${not empty data}">
            <table>
				<thead>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>1回</th>
						<th>2回</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="d" items="${data}">
						<tr>
							<td>${d.entYear}</td>
							<td>${d.classNum}</td>
							<td>${d.studentNo}</td>
							<td>${d.studentName}</td>
							
							<!-- -1の場合は未受験「－」を表示する -->
							<td>
							    <c:choose>
							        <c:when test="${d.getPoint(1) == '-1'}">－</c:when>
							        <c:otherwise>${d.getPoint(1)}</c:otherwise>
							    </c:choose>
							</td>
							
							<td>
							    <c:choose>
							        <c:when test="${d.getPoint(2) == '-1'}">－</c:when>
							        <c:otherwise>${d.getPoint(2)}</c:otherwise>
							    </c:choose>
							</td>

						</tr>
					</c:forEach>
				</tbody>
             </table>
        </c:if>	
	</section>
</c:param>
</c:import>
