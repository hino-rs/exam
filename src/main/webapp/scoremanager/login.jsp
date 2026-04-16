<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/common/base.jsp">
    <jsp:param name="title" value="ログイン" />

    <jsp:param name="content" value="
        <!-- ログインカード  -->
        <div class='card shadow-sm' style='max-width: 600px; margin: 40px auto; padding: 0;'>

            <!-- タイトル部分（薄いグレー帯） -->
            <div style='background-color: #f0f0f0; padding: 15px;'>
                <h2 class='text-center m-0'>ログイン</h2>
            </div>

            <!-- 本文部分 -->
            <div class='p-4'>

                <!-- エラー表示 -->
                <c:if test='${not empty errors}'>
                    <p class='text-danger text-center mb-3'>${errors}</p>
                </c:if>

                <form action='LoginExecute.action' method='post'>

                    <!-- ID（入力欄の左上に表示：floating label） -->
                    <div class='form-floating mb-3'>
                        <input type='text' name='id' value='${id}'
                               class='form-control'
                               style='background-color: #e8f4ff;'
                               id='idInput'
                               required maxlength='30'>
                        <label for='idInput' style='font-size: 0.85rem;'>ID</label>
                    </div>

                    <div class='form-floating mb-3'>
                        <input type='password' name='password'
                               class='form-control'
                               style='background-color: #e8f4ff;'
                               id='passwordInput'
                               required maxlength='30'>
                        <label for='passwordInput' style='font-size: 0.85rem;'>パスワード</label>
                    </div>

                    <!-- パスワード表示チェック -->
                    <div class='mb-3' style='text-align: center;'>
                        <input type='checkbox' id='showPassword' class='form-check-input'>
                        <label for='showPassword' class='form-check-label'>パスワードを表示</label>
                    </div>

                    <!-- ログインボタン -->
                    <button type='submit' class='btn btn-primary w-100'>ログイン</button>

                </form>
            </div>
        </div>

        <!-- パスワード表示スクリプト -->
        <script>
            document.getElementById('showPassword').addEventListener('change', function() {
                const pw = document.getElementById('passwordInput');
                pw.type = this.checked ? 'text' : 'password';
            });
        </script>
    " />
</jsp:include>
