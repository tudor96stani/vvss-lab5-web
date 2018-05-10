<%--
  Created by IntelliJ IDEA.
  User: tudorstanila
  Date: 10/05/2018
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <%

        String message = (String) session.getAttribute("result");
        out.println("<div id=\"res\">"+message+"</div>");
    %>
</body>
</html>
