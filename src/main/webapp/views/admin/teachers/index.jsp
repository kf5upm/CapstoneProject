<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css"/>
    <title>Teacher Management</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Manage Teachers</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Home"/>">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value = "/Manage/Teachers"/>">Teachers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Manage/Courses"/>">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Manage/Students"/>">Students</a>
                </li>
<!--                    <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </li>-->
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-link logout"><a href="<c:url value = "/Logout"/>">Logout <%=user.getFirstName()%></a></li>
            </ul>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-2">
              <form method="post">
                <c:if test="${teacher != null}">
                <div style="padding-top: 0.75rem; color: #6c757d"><h5>Edit Teacher</h5></div>
                <input type="hidden" name="action" value="update"/>
                </c:if>
                <c:if test="${teacher == null}">
                <div style="padding-top: 0.75rem; color: #6c757d"><h5>Add Teacher</h5></div>
                <input type="hidden" name="action" value="create"/>
                </c:if>
                <div class="form-group d-flex flex-column">
                    <label for="userid" class="control-label">Teacher ID</label>
                    <input class="form-conrol" type="text" name="id" value="${teacher.id}" <c:if test="${teacher != null}">DISABLED</c:if>/>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="firstname" class="control-label">First Name</label>
                    <input class="form-conrol" type="text" name="firstname" value="${teacher.firstName}"/>
                </div>

                <div class="form-group d-flex flex-column">
                    <label for="lastname" class="control-label">Last Name</label>
                    <input class="form-conrol" type="text" name="lastname" value="${teacher.lastName}"/>
                </div>

                <div class="form-group d-flex flex-column">
                    <label for="gender" class="control-label">Gender</label>
                    <input class="form-conrol" type="text" name="gender" value="${teacher.gender}"/>
                </div>
                <div class="text-right">
                    <div class="buttons btn-group">
                        <input class="form-control" type="submit" value="Save"/>
                        <a class="form-control" href="/Capstone/Manage/Teachers">Reset</a>
                    </div>
                </div
              </form>
            </div>
            <div class="col-10">
                <table class="table">
                  <caption><h5>Teachers List</h5></caption>
                  <thead class="thead-light">
                      <tr>
                          <th>ID</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Gender</th>
                          <th class="text-right">Action</th></tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${sessionScope.payload}" var="teacher">
                          <tr>
                              <td>${teacher.getId()}</td>
                              <td>${teacher.getFirstName()}</td>
                              <td>${teacher.getLastName()}</td>
                              <td>${teacher.getGender()}</td>
                              <td class="text-right"><a href="<c:url value="/Manage/Teachers/Edit/${teacher.getId()}"/>">Edit</a> | <a href="<c:url value="/Manage/Teachers/Delete/${teacher.getId()}"/>">Delete</a></td>
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