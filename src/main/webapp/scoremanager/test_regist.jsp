<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    
    <c:param name="scripts"></c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            
            <form method="get">
               <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
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
               
               <div class="col-2">
                   <label class="form-label" for="student-f2-select">クラス</label>
                   <select class="form-select" id="student-f2-select" name="f2">
                       <option value="0">--</option>
                          <c:forEach var="num" items="${class_num_set}">
                              <option value="${num }" <c:if test="${num==f2}">selected</c:if>>
                                  ${num}
                              </option>
                          </c:forEach>
                   </select>
               </div>
               
               <div class="col-4">
                   <label class="form-label" for="student-f3-select">科目</label>
                   <select class="form-select" id="student-f3-select" name="f3">
                       <option value="0">--</option>
                          <c:forEach var="subject" items="${school_subject_set}">
                              <option value="${subject}" <c:if test="${subject==f3 }">selected</c:if>>
                                  ${subject}
                              </option>
                          </c:forEach>
                   </select>
               </div>
               
               <div class="col-2">
                   <label class="form-label" for="student-f4-select">回数</label>
                   <select class="form-select" id="student-f4-select" name="f4">
                       <option value="0">--</option>
                          <c:forEach var="count" items="${num_count_set}">
                              <option value="${count }" <c:if test="${count==f4 }">selected</c:if>>
                                  ${count}
                              </option>
                          </c:forEach>
                   </select>
               </div>
               
               <div class="col-2 text-center">
                   <button class="btn btn-secondary" id="filter-button">検索</button>
               </div>
            </form>
            
            <form method="post" action="TestRegistExecute.action">
            
            <input type="hidden" name="subject" value="${f3}">
            <input type="hidden" name="subject" value="${f4}">
            
               <div>科目：${f3} (${f4}回)</div>
               <table class="table table-hover">
                   <tr>
                       <th>入学年度</th>
                       <th>クラス</th>
                       <th>学生番号</th>
                       <th>氏名</th>
                       <th>点数</th>
                   </tr>
                   
                 <c:forEach var="test" items="${test_list}">
                   <tr>
                       <td>${student.entYear}</td>
                       <td>${student.classNum}</td>
                       <td>${student.no}</td>
                       <td>${student.name}</td>
                       <td>
                           <input type="number" name="point_${test.student.no }" value="${empty test.point  ? '' : test.point}" min=0 max=100>
                       </td>
                   </tr>
                 </c:forEach>  
               </table>
               
               
               
             <div>
               <button type="submit" class="btn btn-secondary custom-btn">
               登録して終了
               </button>
             </div>
            </form>
    </section>
  </c:param>
 </c:import>         