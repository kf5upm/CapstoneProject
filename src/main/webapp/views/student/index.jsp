<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css"/>
        <title>Student Home</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="">Student Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="nav navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value = "/Home"/>">Home</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-link logout"><a href="<c:url value = "/Logout"/>">Logout ${user.getFirstName()}</a></li>
                </ul>
            </div>
        </nav>
        <p class="text-right small mr-2">View version 0.02</p>
        <div class="container col-12">
            <div class="row">
                <div class="col-2">
                    <table class="table ">
                        <caption><h5>Student Information</h5></caption>
                        <tr>
                            <th class="text-right">First Name:</th>
                            <td>${user.getFirstName()}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Last Name:</th>
                            <td>${user.getLastName()}</td>
                        </tr>
                        <tr>
                            <th class="text-right">Gender:</th>
                            <td>${user.getGender()}</td>
                        </tr>
                        <tr>
                            <th class="text-right">GPA:</th>
                            <td>${user.getGpa()}</td>
                        </tr>
                    </table>
                </div>
            
                <div class="col-10">
                    <table class="table">
                      <caption><h5>Course List</h5></caption>
                      <thead class="thead-light">
                          <tr>
                              <th>Course Name</th>
                              <th>Instructor</th>
                              <th>Credits Possible</th>
                              <th>Credits Earned</th>
                          </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${user.getCourseRecords()}" var="courseRecord">
                            <tr>
                                <td>${courseRecord.getCourse().getName()}</td>
                                <td>${courseRecord.getCourse().getTeacher().getFirstName()} ${courseRecord.getCourse().getTeacher().getLastName()}</td>
                                <td>${courseRecord.getCourse().getCredits()}</td>
                                <td>${courseRecord.getCredits()}</td>
                            </tr>
                        </c:forEach>
                      </tbody>
                  </table>
                </div>
            </div>
        </div>
        
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
