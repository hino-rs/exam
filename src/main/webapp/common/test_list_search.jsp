<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<fieldset class="border rounded p-3 mb-3" style="margin-bottom:0;">

    <!-- 科目検索フォーム -->
    <form method="get" action="TestListSubjectExecute.action">
        <div class="row">

            <!-- 左側タイトル -->
            <div class="col-auto d-flex" style="margin-top:25px;">
                <div style="font-size:1rem;">科目情報</div>
            </div>

            <!-- 入学年度・クラス・科目 -->
            <div class="col">
                <div class="row g-4">
                
                    <!-- 入学年度 -->
                    <div class="col-auto">
                        <label class="form-label">入学年度</label>
                        <select name="f1" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="y" items="${ent_year_set}">
                                <option value="${y}" 
                                    <c:if test="${y == selectedEntYear}">selected</c:if>>
                                    ${y}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-auto">
                        <label class="form-label">クラス</label>
                        <select name="f2" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="c" items="${class_num_set}">
                                <option value="${c}" 
                                    <c:if test="${c == selectedClassNum}">selected</c:if>>
                                    ${c}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-auto">
                        <label class="form-label">科目</label>
                        <select name="f3" class="form-select" style="width:200px;">
                            <option value="">--------</option>
                            <c:forEach var="s" items="${school_subject_set}">
                                <option value="${s.cd}" 
                                    <c:if test="${s.cd == selectedSubjectCd}">selected</c:if>>
                                    ${s.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
					<div class="col-auto d-flex align-items-end" style="margin-bottom:12px;">
					    <button type="submit" class="btn btn-secondary px-4">検索</button>
					</div>
                
	                <!-- 科目検索エラー -->
	                <div style="
	                    height: 20px;
	                    margin-top: 5px;
	                    margin-left: -90px;
	                    color:#ffbb33;
	                    line-height: 20px;
	                    overflow: hidden;
	                ">
                    <c:if test="${not empty inErr}">
                        ${inErr}
                    </c:if>
                </div>
            </div>
        </div>
        <hr style="margin-top: 4px; margin-bottom: 12px;">
    </form>

    <!-- 学生検索フォーム -->
    <form method="get" action="TestListStudentExecute.action">
        <div class="row">

            <!-- 左側タイトル -->
            <div class="col-auto d-flex align-items-center">
                <div style="font-size:1rem;">学生情報</div>
            </div>

            <!-- 学生番号 -->
            <div class="col">
                <div class="row g-4 align-items-end">

                    <div class="col-auto">
                        <label class="form-label">学生番号</label>
                        <input type="text" name="studentNo" value="${param.studentNo}"
                               class="form-control" style="width:250px;"
                               placeholder="学生番号を入力してください"
                               required>
                    </div>

                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary px-4" style="margin-bottom:12px;">検索</button>
                    </div>

                </div>
            </div>
        </div>
    </form>
</fieldset>
