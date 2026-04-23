<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp"><c:param name="title">成績参照</c:param>

<c:param name="content">
<section class="mt-3">

    <h2 class="h4 mb-4">成績参照</h2>

    <!-- 1つのフィールドセット -->
    <fieldset class="border rounded p-3 mb-4">

        <!-- 科目情報ブロック -->
        <div class="row mb-4">

            <!-- 左側の横書きタイトル -->
            <div class="col-auto d-flex align-items-center">
                <div style="font-size:1rem;">科目情報</div>
            </div>

            <!-- 入学年度・クラス・科目 -->
            <div class="col">

                <div class="row g-4">

                    <!-- 入学年度 -->
                    <div class="col-auto">
                        <label class="form-label" style="font-size:0.9rem;">入学年度</label>
                        <select name="f1" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="y" items="${ent_year_set}">
                                <option value="${y}" <c:if test="${y == f1}">selected</c:if>>${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-auto">
                        <label class="form-label" style="font-size:0.9rem;">クラス</label>
                        <select name="f2" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="c" items="${class_num_set}">
                                <option value="${c}" <c:if test="${c == f2}">selected</c:if>>${c}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-auto">
                        <label class="form-label" style="font-size:0.9rem;">科目</label>
                        <select name="f3" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="s" items="${school_subject_set}">
                                <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-auto d-flex align-items-end">
                        <button type="submit" class="btn btn-secondary px-4">検索</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- 区切り線 -->
        <hr class="my-3">

        <!-- 学生情報ブロック -->
        <div class="row">

            <!-- 左側タイトル -->
            <div class="col-auto d-flex align-items-center">
                <div style="font-size:1rem;">学生情報</div>
            </div>

            <!-- 学生番号 -->
            <div class="col">

                <div class="row g-4 align-items-end">

                    <!-- 学生番号 -->
                    <div class="col-auto">
                        <label class="form-label" style="font-size:0.9rem;">学生番号</label>
                        <input type="text" name="studentNo" value="${param.studentNo}"
                               class="form-control" style="width:250px;"
                               placeholder="学生番号を入力してください">
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary px-4">検索</button>
                    </div>

                </div>
            </div>
        </div>

    </fieldset>

    <!-- 利用方法案内メッセージ -->
    <div style="color:#33bbff; margin-top:10px;">
    科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
	</div>

</section>
</c:param>


</c:import>
