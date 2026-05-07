<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>

  <c:param name="content">
	<section class="me-4">
		
		<!-- タイトル -->
	    <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
	    	学生登録：CSVファイルアップロード
	    </h2>
		    
		<!-- エラーメッセージ trueの時だけ表示 -->
		<c:if test = "${not empty error_message}">
            <div class="alert alert-danger mb-3">
                ${error_message}
            </div>
        </c:if>
            
		<!-- CSV アップロードフォーム -->
		<!-- ファイル送信には エンコーディングタイプmultipart/form-data が必須 -->
		<form method="post" action="StudentCsvUploadExecute.action" enctype="multipart/form-data">
			<fieldset class="border rounded p-4 mb-4">
            	<legend>CSVファイル選択</legend>
		
			<!-- CSV ファイル選択欄（accept=".csv" で CSV のみ選択可能） -->
			<div class="mb-2">
                    <input type="file" name="csvFile" accept=".csv" class="form-control" required>
                </div>

                <p class="text-muted mb-0">
                    ※ CSV形式：学生番号, 学生名, 入学年度, クラス番号, 在学中フラグ, 学校コード
                </p>
            </fieldset>
		
            <!-- アップロードボタン -->
            <button type="submit" class="btn btn-primary px-4">
                アップロード
            </button>

            <!-- メニューへ戻る -->
            <a href="StudentList.action" class="btn btn-outline-secondary ms-3 px-4">
                学生一覧へ
            </a>

        </form>
        
        <!-- CSVアップロード結果表示（成功・失敗件数） -->
        <c:if test="${not empty successCount or not empty errorCount}">
            <div class="alert alert-info mt-4">
                <p class="mb-1">CSVアップロード結果：</p>
                <ul class="mb-0">
                    <li>登録成功件数：${successCount}</li>
                    <li>登録失敗件数：${errorCount}</li>
                    <c:if test="${not empty errorList}">
					    <ul>
					        <c:forEach var="err" items="${errorList}">
					            <li>${err}</li>
					        </c:forEach>
					    </ul>
					</c:if>
                </ul>
            </div>
        </c:if>

	</section>		
		
  </c:param>
</c:import>