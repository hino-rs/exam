<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
</c:import>

<h2>学生登録情報</h2>

<form action="student_create_done.jsp" method="post">

    <label>入学年度</label>
    <select name="ent_year">
        <option value=""></option>
        
    </select>


    <label>学生番号</label>
    <input type="text" name="no" value="${no}" maxlength="10" required placeholder="学生番号を入力してください">


    <label>氏名</label>
    <input type="text" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">


    <label>クラス</label>
    <select name="class_num">
        <option value=""></option>
    </select>


    <button type="submit" name="end">登録して終了</button>

</form>


<a href="student_list.jsp">戻る</a>