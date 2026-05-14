<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title" value="ログイン" />

    <c:param name="content" value='

        <div class="card shadow-sm"
             style="max-width:600px; margin:40px auto;">

            <!-- タイトル -->
            <div style="background-color:#f0f0f0; padding:15px;">
                <h2 class="text-center m-0">
                    ログイン
                </h2>
            </div>

            <!-- 本文 -->
            <div class="p-4">

                <!-- エラー -->
                <p class="text-danger text-center mb-4">
                    ${errors}
                </p>

                <form action="LoginExecute.action" method="post">

                    <!-- ID -->
                    <div class="position-relative mb-4">

                        <label
                            for="idInput"
                            style="
                                position:absolute;
                                top:8px;
                                left:12px;
                                font-size:0.75rem;
                                color:#666;
                                z-index:10;">

                            ID

                        </label>

                        <input
                            type="text"
                            name="id"
                            id="idInput"
                            class="form-control"
                            value="${id}"
                            placeholder="半角でご入力ください"
                            maxlength="10"
                            required
                            style="
                                height:58px;
                                padding-top:20px;
                                padding-left:32px;
                                background-color:#e8f4ff;">

                    </div>

                    <!-- パスワード -->
                    <div class="position-relative mb-4">

                        <label
                            for="passwordInput"
                            style="
                                position:absolute;
                                top:8px;
                                left:12px;
                                font-size:0.75rem;
                                color:#666;
                                z-index:10;">

                            パスワード

                        </label>

                        <input
                            type="password"
                            name="password"
                            id="passwordInput"
                            class="form-control"
                            placeholder="30文字以内の半角英数字でご入力ください"
                            maxlength="30"
                            required
                            style="
                                height:58px;
                                padding-top:20px;
                                padding-left:32px;
                                background-color:#e8f4ff;">

                    </div>

                    <!-- パスワード表示 -->
                    <div class="mb-4 text-center">

                        <input
                            type="checkbox"
                            id="showPassword"
                            class="form-check-input">

                        <label
                            for="showPassword"
                            class="form-check-label">

                            パスワードを表示

                        </label>

                    </div>

                    <!-- ボタン -->
                    <div class="text-center">

					    <button
					        type="submit"
					        class="btn btn-primary"
					        style="width:150px;">
					
					        ログイン
					
					    </button>					
					</div>
                </form>
            </div>
        </div>
    '/>

</c:import>

<script>

document.addEventListener("DOMContentLoaded", function() {

    document
        .getElementById("showPassword")
        .addEventListener("change", function() {

            const pw =
                document.getElementById("passwordInput");

            pw.type =
                this.checked ? "text" : "password";

        });

});

</script>