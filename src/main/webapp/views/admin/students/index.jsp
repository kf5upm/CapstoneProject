<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css"/>
    <title>Student Management</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Manage Students</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Home"/>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Manage/Teachers"/>">Teachers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value = "/Manage/Courses"/>">Courses</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value = "/Manage/Students"/>">Students</a>
                </li>
<!--                    <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </li>-->
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-link logout"><a href="<c:url value = "/Logout"/>">Logout ${user.getFirstName()}</a></li>
            </ul>
        </div>
    </nav>
    <p class="text-right small mr-2">View version 0.09</p>
    <div class="container-fluid">
        <div class="row">
            <div class="col-2">
              <form method="post">
                <c:if test="${selected != null}">
                <div style="padding-top: 0.75rem; color: #6c757d"><h5>Edit Student</h5></div>
                <input type="hidden" name="action" value="update"/>
                </c:if>
                <c:if test="${selected == null}">
                <div style="padding-top: 0.75rem; color: #6c757d"><h5>Add Student</h5></div>
                <input type="hidden" name="action" value="create"/>
                </c:if>
                <div class="form-group d-flex flex-column">
                    <label for="userid" class="control-label">Student ID</label>
                    <input class="form-control" type="text" name="id" value="${selected.id}" <c:if test="${selected != null}">READONLY</c:if>/>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="firstname" class="control-label">First Name</label>
                    <input class="form-control" type="text" name="firstname" value="${selected.firstName}"/>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="lastname" class="control-label">Last Name</label>
                    <input class="form-control" type="text" name="lastname" value="${selected.lastName}"/>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="gender" class="control-label">Gender</label>
                    <select class="form-control" name="gender">
                        <option value="" selected disabled>-- Select Gender --</option>
                        <option value="Male"<c:if test="${selected.gender == 'Male'}"> SELECTED</c:if>>Male</option>
                        <option value="Female"<c:if test="${selected.gender == 'Female'}"> SELECTED</c:if>>Female</option>
                    </select>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="gpa" class="control-label">GPA</label>
                    <input class="form-control" type="text" name="gpa" value="${selected.gpa}"/>
                </div>
                <div class="form-group d-flex flex-column">
                    <label for="offerings" class="control-label">Offered Courses</label>
                    <select name="offerings" id="offerings" multiple>
                        <c:forEach items="${offered}" var="offering">
                            <option value="${offering.getId()}"  <c:if test="${fn:contains(selected.getCourseRecords(), offering)}">SELECTED</c:if>>
                                ${offering.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-right">
                    <div class="buttons btn-group">
                        <input class="form-control" type="submit" value="Save"/>
                        <a class="form-control" href="/Capstone/Manage/Students">Reset</a>
                    </div>
                </div
              </form>
            </div>
            <div class="col-10">
                <table class="table">
                  <caption><h5>Students List</h5></caption>
                  <thead class="thead-light">
                      <tr>
                          <th>ID</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Gender</th>
                          <th>GPA</th>
                          <th>Enrolled Courses</th>
                          <th class="action text-center">Action</th></tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${sessionScope.payload}" var="student">
                          <tr>
                              <td>${student.getId()}</td>
                              <td>${student.getFirstName()}</td>
                              <td>${student.getLastName()}</td>
                              <td>${student.getGender()}</td>
                              <td>${student.getGpa()}</td>
                              <td>
                                <c:forEach items="${student.getCourseRecords()}" var="courseRecord" varStatus="loop">
                                    ${courseRecord.getCourse().getName()}<c:if test="${!loop.last}">,</c:if>
                                </c:forEach>
                              </td>
                              <td class="action text-center"><a href="<c:url value="/Manage/Students/Edit/${student.getId()}"/>">Edit</a> | <a class="confirmation" href="<c:url value="/Manage/Students/Delete/${student.getId()}"/>">Delete</a></td>
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
    <script type="text/javascript">
        var elems = document.getElementsByClassName('confirmation');
        var confirmIt = function (e) {
            if (!confirm('Are you sure you wish to delete this user?')) e.preventDefault();
        };
        for (var i = 0, l = elems.length; i < l; i++) {
            elems[i].addEventListener('click', confirmIt, false);
        }
        
        document.querySelectorAll('#offerings option').forEach(el => el.addEventListener('mousedown',(e) => {
                e.preventDefault();
                el.selected = !el.selected;
        }));        
        
        function addCourse() {
            var idxs = offered.options.length - 1;

            for (var i = idxs; i >= 0; i--) {
                if (offered.options[i].selected === true) {
                    var opt = offered.options[i];
                    chosen.appendChild(opt);
                }
            }
        }

        function removeCourse() {
            var idxs = chosen.options.length - 1;

            for (var i = idxs; i >= 0; i--) {
                if (chosen.options[i].selected === true) {
                    var opt = chosen.options[i];
                    offered.appendChild(opt);
                }
            }
        }
        
    </script>        
  </body>
</html>