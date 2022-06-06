<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Demo Login</h1>
        <a href="Login?userid=0">Login as an Admin</a><br/>
        <a href="Login?userid=1">Login as an Teacher</a><br/>
        <a href="Login?userid=2">Login as an Student</a><br/>
        
        <c:if test="${sessionScope.error != null}">
        <p style="color:red"><%=session.getAttribute("error")%></p>
        </c:if>
    </body>
</html>
