<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title" value="ログイン" />

    <c:param name="content">
        <div class='card shadow-sm' style='max-width: 600px; margin: 40px auto; padding: 0;'>

            <div style='background-color: #f0f0f0; padding: 15px;'>
                <h2 class='text-center m-0'>ログイン</h2>
            </div>

            <div class='p-4'>

                <c:if test='${not empty errors}'>
                    <p class='text-danger text-center mb-3'>${errors}</p>
                </c:if>

                <form action='LoginExecute.action' method='post'>

                    <div class='mb-3'>
                        <label for='idInput' class='form-label' style='font-size: 0.85rem;'>ログインＩＤ</label>
                        <input type='text' name='id' value='${id}'
                               class='form-control'
                               style='background-color: #e8f4ff;'
                               id='idInput'
                               placeholder='半角でご入力ください'
                               required maxlength='10'>
                    </div>

                    <div class='mb-3'>
                        <label for='passwordInput' class='form-label' style='font-size: 0.85rem;'>パスワード</label>
                        <input type='password' name='password'
                               class='form-control'
                               style='background-color: #e8f4ff;'
                               id='passwordInput'
                               placeholder='30文字以内の半角英数字でご入力ください'
                               required maxlength='30'>
                    </div>

                    <div class='mb-3' style='text-align: center;'>
                        <input type='checkbox' name='chk_d_ps' id='chk_d_ps' class='form-check-input'>
                        <label for='chk_d_ps' class='form-check-label'>パスワードを表示</label>
                    </div>

                    <div class='mb-3'>
                        <input type='submit' name='login' value='ログイン' class='btn btn-primary w-100'>
                    </div>

                </form>
            </div>
        </div>

        <script>
            document.getElementById('chk_d_ps').addEventListener('change', function() {
                const pw = document.getElementById('passwordInput');
                pw.type = this.checked ? 'text' : 'password';
            });
        </script>
    </c:param>
</c:import>