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
        <title>Student Home Page</title>
        <link rel="stylesheet" type="text/css" href="global.css"/>
    </head>
    <body>
        <h1>Welcome <%=user.getName()%></h1>

        <table>
            <caption>My Courses</caption>
            <thead>
                <tr><th>Course Name</th><th>Action</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${user.getCoursesTaken()}" var="course">
                    <tr><td>${course.getTitle()}</td><td>select</td></tr>
                </c:forEach>
            </tbody>
        </table>
        
    </body>
</html>
