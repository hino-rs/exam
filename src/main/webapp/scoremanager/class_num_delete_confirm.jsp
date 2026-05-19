<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">クラス管理（削除確認）</c:param>

    <c:param name="scripts">
<style>
.title {
    background-color: #f5f5f5;
    padding: 15px 20px;
    font-size: 1.2em;
    margin-bottom: 20px;
}

.message-area {
    display: flex;
    justify-content: space-between; /* メッセージ左、削除ボタン右 */
    align-items: center;
    margin-bottom: 30px;
}

.message {
    font-size: 1.1em;
}

.error {
    color: red;
    margin-bottom: 20px;
    font-weight: bold;
    font-size: 1.4em;
}

.action-bottom {
    margin-top: 20px;
}
</style>
    </c:param>

    <c:param name="content">

<h2 class="title">クラス管理（削除確認）</h2>

<!-- エラーがある場合（削除不可） -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>

    <div class="message">
        ${school.name}（${school.cd}）  クラス「${class_num}」
    </div>

    <div class="action-bottom">
        <a href="ClassNumList.action">戻る</a>
    </div>
</c:if>

<!-- エラーがない場合（通常の削除確認） -->
<c:if test="${empty error}">

    <div class="message-area">
        <div class="message">
            ${school.name}（${school.cd}）のクラス「${class_num}」を削除します。<br>
            本当によろしいですか？
        </div>

        <form action="ClassNumDeleteExecute.action" method="post">
            <input type="hidden" name="school_cd" value="${school.cd}">
            <input type="hidden" name="class_num" value="${class_num}">
            <!-- ★ サイト共通の削除ボタンデザイン -->
            <button class="btn btn-danger">削除する</button>
        </form>
    </div>

    <div class="action-bottom">
        <!-- ★ 戻るは変更なし（リンクのまま） -->
        <a href="ClassNumList.action">戻る</a>
    </div>

</c:if>

    </c:param>
</c:import>
