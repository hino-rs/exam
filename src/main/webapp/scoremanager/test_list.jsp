<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
                成績参照
            </h2>

            <!-- 共通検索フォーム -->
            <jsp:include page="/common/test_list_search.jsp" />

            <!-- 利用方法案内メッセージ -->
            <div style="color:#33bbff; margin-top:0px;">
                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
            </div>

        </section>
    </c:param>
</c:import>

