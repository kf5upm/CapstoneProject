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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <link rel="stylesheet" type="text/css" href="../global.css"/>
        <title>Capstone Demo Login</title>
    </head>
    <body>
        <div class="container-fluid vh-100" style="margin-top:100px">
            <div class="" style="margin-top:100px">
                <div class="rounded d-flex justify-content-center">
                    <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                        <div class="text-center">
                            <h3 class="text-primary">Sign In</h3>
                        </div>
                        <form action="Login" method="post">
                            <div class="p-4">
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary">
                                        <i class="bi bi-person-fill text-white"></i></span>
                                    <input type="text" maxlength="6" pattern="^[0-9]{6}$" class="form-control" placeholder="Enter your Student/Teacher ID (eg. 123456)" name="UserID" title="123456">
                                </div>
                                <% String error = (String) session.getAttribute("error"); %>
                                <% if (error != null) { %>
                                <div class="alert alert-danger" role="alert"><%=error%></div>
                                <% } %>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Remember Me
                                    </label>
                                </div>
                                <button class="btn btn-primary text-center mt-2" type="submit">
                                    Login
                                </button>
                            </div>
                        </form>
                        <br/>
                        <div>
                            <h3>Demo Login</h3>
                            <a href="Login?userid=0">Login as an Demo Admin</a><br/>
                            <a href="Login?userid=1">Login as an Demo Teacher</a><br/>
                            <a href="Login?userid=4">Login as an Demo Student</a><br/>
                        </div>
                    </div>
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