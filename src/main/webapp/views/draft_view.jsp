<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Draft View</title>
    </head>
    <body>
        <h1>Draft View</h1>
        
        <c:if test="${action == 'View'}"> 
        <a href="Draft/Create">Create</a><br/>
        <a href="Draft/Update">Update</a><br/>
        <a href="Draft/Delete">Delete</a><br/>
        </c:if>
        
        <% int targetId = 5; %>
        
        
        
        <c:if test="${action == 'Create'}"> 
        <h3>Create</h3>
        <form action="Draft/Create" method="POST">
<!--            <input type="hidden" name="action" value="create"/>-->
            <input type="text" name="data" value="CREATE ME"/>
            <input type="submit" value="create"/>
        </form>
        </c:if>
        
        <c:if test="${action == 'Update'}"> 
        <h3>Update</h3>
        <form action="Draft/Update/<%=targetId%>" method="POST">
<!--            <input type="hidden" name="action" value="update"/>-->
            <input type="text" name="data" value="UPDATE ME"/>
            <input type="submit" value="update"/>
        </form>
        </c:if>
        
        <c:if test="${action == 'Delete'}"> 
        <h3>Delete</h3>
        <form action="Draft/Delete/<%=targetId%>" method="POST">
<!--            <input type="hidden" name="action" value="delete"/>-->
            <input type="text" name="data" value="DELETE ME"/>
            <input type="submit" value="delete"/>
        </form>
        </c:if>
    </body>
</html>
