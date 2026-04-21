<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts">
        <style>
        	/* 検索結果の点数枠 */
        	.score-input {
			    width: 150px !important;
			}
        	
            /* 検索欄の外枠（検索欄だけ） */
            #filter {
                border: 1px solid #ccc !important;
                border-radius: 6px;
            }

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

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <!-- 検索欄（外枠あり） -->
            <form method="get" action="TestRegist.action">
                <div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <!-- 入学年度 -->
                    <div class="col-2">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                            ${year}
                        </option>
                    </c:forEach>
                </select>
               </div>
               
                    <!-- クラス -->
                    <div class="col-2">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-4">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3">
                            <option value="0">--</option>
                            <c:forEach var="subject" items="${school_subject_set}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>
                                    ${subject.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 回数 -->
                    <div class="col-2">
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4">
                            <option value="0">--</option>
                            <c:forEach var="count" items="${num_count_set}">
                                <option value="${count}" <c:if test="${count == f4}">selected</c:if>>
                                    ${count}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>

                </div>
            </form>

            <!-- 検索結果一覧（横線だけ） -->
            <c:if test="${not empty test_list}">
                <div class="mt-4">科目：${subject_name}（${f4}回）</div>

                <table class="table table-bordered mt-3 table-horizontal-only">
                    <thead>
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="t" items="${test_list}">
                            <tr>
                                <td>${t.student.entYear}</td>
                                <td>${t.student.classNum}</td>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>
                                    <input type="text"
                                           name="point_${t.student.no}"
                                           value="${t.point}"
                                           class="form-control score-input">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="text-start mt-3">
                    <button class="btn btn-secondary">登録して終了</button>
                </div>
            </c:if>

        </section>
    </c:param>
</c:import>
