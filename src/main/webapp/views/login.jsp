<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title>Login</title>
</head>
<body>
<c:set var="role_C" value="${cookie.role_C.value}"/>
<c:set var="username_C" value="${cookie.username_C.value}"/>
<c:set var="password_C" value="${cookie.password_C.value}"/>

<div class="card col-10 h-100 d-flex flex-row justify-content-center m-auto">
    <div class="w-50">
        <img class="w-100 h-100" src="${pageContext.request.contextPath}/resources/images/canvas-LMS.png"
             alt="canvas-LMS.png">
    </div>
    <div class="w-50 m-3">
        <h1 class="text-center fw-bold">LOGIN</h1>
        <form id="form">
            <div class="mb-4">
                <label for="select" class="form-label fw-bold">Select role</label>
                <select class="form-select" id="select" name="role">
                    <option value="Student" selected>Student</option>
                    <option value="Instructor" ${role_C == 'Instructor' ? 'selected' : ''}>Instructor</option>
                    <option value="Manager" ${role_C == 'Manager' ? 'selected' : ''}>Manager</option>
                </select>
            </div>
            <div class="mb-4">
                <label for="username" class="form-label fw-bold">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="${username_C}" required>
            </div>
            <div class="mb-4">
                <label for="password" class="form-label fw-bold">Password</label>
                <a class="float-end" href="${pageContext.request.contextPath}/controllers/ForgotPasswordController">forgot password?</a>
                <input type="password" class="form-control" id="password" name="password" value="${password_C}" required>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="remember_id">
                <label class="form-check-label" for="remember_id">Remember me for 60 seconds</label>
            </div>
            <input type="hidden" name="action" value="login">
            <button type="submit" class="btn btn-primary d-block mx-auto w-50" id="button">Login</button>
        </form>
        <hr>
        <div class="text-center">
            Don't have an account? <a href="${pageContext.request.contextPath}/controllers/RegisterController">Register New Account</a>
        </div>

    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

<%-- ALERT --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

    $(document).ready(function () {
        $('#form').on('submit', function (event) {
            event.preventDefault(); // Ngăn form submit mặc định

            var role = $('#select').val();
            var username = $('#username').val();
            var password = $('#password').val();
            var remember = $('#remember_id').val();

            $.ajax({
                type: 'POST',
                url: '/controllers/LoginController',
                data: {
                    role: role,
                    username: username,
                    password: password,
                    remember: remember
                },
                success: function (data) {
                    if (data.login_status === "fail") {
                        swal("Account does not exist", "", "error");
                    }
                    else if (data.login_status === "success") {
                        swal("Login successful!", "", "success").then(function() {
                            window.location.href = data.user_dashboard_url;
                        })
                    }
                    else if (data.login_status === "blocked") {
                        swal("Your account is blocked", "Please contact admin to resolve the problem!", "warning");
                    }
                },
                error: function (e) {
                    swal("Error!", "AJAX EXCEPTION", "error");
                }
            });
        });
    });

</script>

</body>
</html>
