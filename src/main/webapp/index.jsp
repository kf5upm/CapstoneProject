<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Capstone Demo Login</title>
        <link rel="stylesheet" type="text/css" href="global.css"/>
    </head>
    <body>
        <h1>Demo Login</h1>
        <a href="Login?userid=0">Login as an Demo Admin</a><br/>
        <a href="Login?userid=1">Login as an Demo Teacher</a><br/>
        <a href="Login?userid=4">Login as an Demo Student</a><br/>
        
        <c:if test="${sessionScope.error != null}">
        <p style="color:red"><%=session.getAttribute("error")%></p>
        </c:if>
    </body>
</html>
