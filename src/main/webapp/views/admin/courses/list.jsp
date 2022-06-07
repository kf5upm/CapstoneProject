<%@page import="java.util.Collection"%>
<%@page import="entities.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Courses</title>
        <link rel="stylesheet" type="text/css" href="../global.css"/>
    </head>
    <body>
        <table>
            <h2>Manage Courses</h2>
            [<a href="<c:url value = "/Home"/>">Home</a>]<br/><br/>
            <caption>All Courses</caption>
            <thead>
                <tr><th>Course Name</th><th>Action</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.payload}" var="course">
                    <tr><td>${course.getTitle()}</td><td>edit | delete</td></tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="100%"><a href="AddCourse">Add Course</a></th>
                </tr>
            </tfoot>
        </table>
    </body>
</html>
