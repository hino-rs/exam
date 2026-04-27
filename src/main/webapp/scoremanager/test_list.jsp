<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
    <section class="me-4">

        <h2 class="h4 mb-2" style="background-color:#f5f5f5; padding:10px 15px;">
            成績参照
        </h2>

        <fieldset class="border rounded p-3 mb-4">
        
            <form method="get" action="TestListSubjectExecute.action">
                <div class="row mb-4">
                    <div class="col-auto d-flex align-items-center">
                        <div style="font-size:1rem;">科目情報</div>
                    </div>

                    <div class="col">
                        <div class="row g-4">
                            <div class="col-auto">
                                <label class="form-label" style="font-size:0.9rem;">入学年度</label>
                                <select name="f1" class="form-select" style="width:200px;" required>
                                    <option value="">--------</option>
                                    <c:forEach var="y" items="${ent_year_set}">
                                        <option value="${y}" <c:if test="${y == f1}">selected</c:if>>${y}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto">
                                <label class="form-label" style="font-size:0.9rem;">クラス</label>
                                <select name="f2" class="form-select" style="width:200px;" required>
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${class_num_set}">
                                        <option value="${c}" <c:if test="${c == f2}">selected</c:if>>${c}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto">
                                <label class="form-label" style="font-size:0.9rem;">科目</label>
                                <select name="f3" class="form-select" style="width:200px;" required>
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${school_subject_set}">
                                        <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto d-flex align-items-end">
                                <button type="submit" class="btn btn-secondary px-4">検索</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <hr class="my-3">

            <form method="get" action="TestListStudentExecute.action">
                <div class="row">
                    <div class="col-auto d-flex align-items-center">
                        <div style="font-size:1rem;">学生情報</div>
                    </div>

                    <div class="col">
                        <div class="row g-4 align-items-end">
                            <div class="col-auto">
                                <label class="form-label" style="font-size:0.9rem;">学生番号</label>
                                <input type="text" name="studentNo" value="${param.studentNo}"
                                       class="form-control" style="width:250px;"
                                       placeholder="学生番号を入力してください"
                                       required>
                            </div>

                            <div class="col-auto">
                                <button type="submit" class="btn btn-secondary px-4">検索</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            
        </fieldset>

        <div style="color:#33bbff;">
        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
        </div>
        
    </section>
    </c:param>

</c:import>