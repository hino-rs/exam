<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        学生情報登録
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
            <form action="StudentCreateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select class="form-select" id="student-f1-select" name="ent_year">
                        <option value="0">--</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                                ${year}
                            </option>
                        </c:forEach>
                    </select>
                    <c:if test='${not empty error1}'>
                        <p class='text-danger mt-1'>${error1}</p>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" class="form-control" name="no" value="${no}" maxlength="10" required placeholder="学生番号を入力してください">
                    <c:if test='${not empty error2}'>
                        <p class='text-danger mt-1'>${error2}</p>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" class="form-control" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">
                </div>

                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="class_num">
                        <c:forEach var="num" items="${class_num}">
                            <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary" name="end">登録して終了</button>
                </div>
            </form>

            <div class="mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>