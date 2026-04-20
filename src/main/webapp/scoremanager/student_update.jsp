<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">


<h2>学生情報変更</h2>

<form action="student_update_done.jsp" method="post">

    <label>入学年度</label>
    <input type="text" name="ent_year" value="${ent_year}" readonly>
        

	<br><br>

    <label>学生番号</label>
    <input type="text" name="no" value="${no}" readonly>
	
	<br><br>

    <label>氏名</label>
    <input type="text" name="name" value="${name}" maxlength="30" required placeholder="氏名を入力してください">

	<br><br>

    <label>クラス</label>
    <select name="class_num">
        <option value="">選択して</option>
    </select>

	<br><br>
	<label>在学中</label>
	<input type="checkbox" name="is_attend">
	
    <button type="submit" name="login">変更</button>

</form>


<a href="student_list.jsp">戻る</a>

