<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/common/base.jsp">
    <jsp:param name="title" value="ログアウト" />

    <jsp:param name="content" value="
        <h2 class='mb-4'>ログアウト</h2>

        <div class='p-3 mb-4' style='background-color: #d4edda; border: 1px solid #c3e6cb;'>
            ログアウトしました
        </div>

        <a href='Login.action' class='text-primary'>ログイン</a>
    " />
</jsp:include>
