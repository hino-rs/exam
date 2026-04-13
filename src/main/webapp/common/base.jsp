<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>${param.title}</title>

    <!-- Bootstrap（必要なら） -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    ${param.scripts}
</head>

<body>

    <!-- ▼ 共通ヘッダー -->
    <%@ include file="/header.html" %>

    <!-- ▼ レイアウト（サイドバー + メインコンテンツ） -->
    <div style="display: flex;">

        <!-- サイドバー -->
        <%@ include file="/sidebar.jsp" %>

        <!-- メインコンテンツ -->
        <main style="flex: 1; padding: 20px;">
            ${param.content}
        </main>

    </div>

    <!-- ▼ 共通フッター -->
    <%@ include file="/footer.html" %>

</body>
</html>
