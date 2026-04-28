<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- サイドバー（ログイン後のみ表示） -->
<c:if test="${not empty sessionScope.loginUser}">
    <div id="sidebar" style="width: 200px; margin-left: 20px;">

        <nav>
            <ul style="list-style: none; padding-left: 0;">

                <!-- メニュー -->
                <li style="margin-bottom: 10px;">
                    <a href="Menu.action">メニュー</a>
                </li>

                <!-- 学生管理 -->
                <li style="margin-bottom: 10px;">
                    <a href="StudentList.action">学生管理</a>
                </li>

                <!-- 成績管理（ラベル） -->
                <li style="margin-bottom: 5px; font-weight: bold;">
                    成績管理
                </li>

                <!-- 成績登録 -->
                <li style="margin-left: 15px; margin-bottom: 5px;">
                    <a href="TestRegist.action">成績登録</a>
                </li>

                <!-- 成績参照 -->
                <li style="margin-left: 15px; margin-bottom: 10px;">
                    <a href="TestList.action">成績参照</a>
                </li>

                <!-- 科目管理 -->
                <li style="margin-bottom: 10px;">
                    <a href="SubjectList.action">科目管理</a>
                </li>
                
                <!-- ユーザー管理（ラベル） -->
                <li style="margin-bottom: 5px; font-weight: bold;">
                    ユーザー管理
                </li>

                <!-- ユーザー登録 -->
                <li style="margin-left: 15px; margin-bottom: 5px;">
                    <a href="UserList.action">ユーザー登録</a>
                </li>

                <!-- ユーザー更新 -->
                <li style="margin-left: 15px; margin-bottom: 10px;">
                    <a href="UserUpdate.action">ユーザー更新</a>
                </li>

                <!-- クラス管理 -->
                <li style="margin-bottom: 10px;">
                    <a href="ClassNumList.action">クラス管理</a>
                </li>

            </ul>
        </nav>

    </div>
</c:if>

