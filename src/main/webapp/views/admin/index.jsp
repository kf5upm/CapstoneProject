<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home Page</title>
        <link rel="stylesheet" type="text/css" href="../global.css"/>
    </head>
    <body>
        <h1>Welcome <%=user.getName()%></h1>
        
        <h3>Manage</h3>
        <a href="Manage/Courses">Courses</a><br/>
        <a href="Manage/Teachers">Teacher</a><br/>
        <a href="Manage/Students">Students</a><br/>
    </body>
</html>
