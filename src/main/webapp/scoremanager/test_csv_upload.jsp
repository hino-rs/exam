<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>

  <c:param name="content">
	<section class="me-4">
		
		<!-- タイトル -->
	    <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
	    	成績登録：CSVファイルアップロード
	    </h2>
		    
		<!-- エラーメッセージ trueの時だけ表示 -->
		<c:if test = "${not empty error_message}">
            <div class="alert alert-danger mb-3">
                ${error_message}
            </div>
        </c:if>
            
		<!-- CSV アップロードフォーム -->
		<!-- ファイル送信には エンコーディングタイプmultipart/form-data が必須 -->
		<form method="post" action="TestCsvUploadExecute.action" enctype="multipart/form-data">
			<fieldset class="border rounded p-4 mb-4">
            	<legend>CSVファイル選択</legend>
		
			<!-- CSV ファイル選択欄（accept=".csv" で CSV のみ選択可能） -->
			<div class="mb-2">
                    <input type="file" name="csvfile" accept=".csv" class="form-control" required>
                </div>

                <p class="text-muted mb-0">
                    ※ CSV形式：学籍番号, 科目コード, 学校コード, 回数, 点数, クラス番号
                </p>
            </fieldset>
		
            <!-- アップロードボタン -->
            <button type="submit" class="btn btn-primary px-4">
                アップロード
            </button>

            <!-- メニューへ戻る -->
            <a href="Menu.action" class="btn btn-outline-secondary ms-3 px-4">
                メニューへ戻る
            </a>

        </form>
        
        <!-- CSVアップロード結果表示（成功・失敗件数） -->
        <c:if test="${not empty successCount or not empty errorCount}">
            <div class="alert alert-info mt-4">
                <p class="mb-1">CSVアップロード結果：</p>
                <ul class="mb-0">
                    <li>登録成功件数：${successCount}</li>
                    <li>登録失敗件数：${errorCount}</li>
                </ul>
            </div>
        </c:if>

	</section>		
		
  </c:param>
</c:import>