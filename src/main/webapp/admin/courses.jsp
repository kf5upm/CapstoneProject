<%@page import="java.util.Collection"%>
<%@page import="entities.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    Collection<Course> courses = (Collection<Course>) session.getAttribute("payload");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Courses</title>
    </head>
    <body>
        <table>
            <caption>All Courses</caption>
            <thead>
                <tr><th>Course Name</th><th>Action</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${courses}" var="course">
                    <tr><td>${course.getTitle()}</td><td>edit | delete</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
