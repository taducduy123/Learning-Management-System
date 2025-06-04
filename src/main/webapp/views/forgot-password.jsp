<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgot-password.css">

    <title>Document</title>
</head>
<body>

<fmt:bundle basename="regex">
    <fmt:message key="email.regex" var="emailPattern"/>
</fmt:bundle>


<div class="card m-auto col-6 p-3">
    <div class="">
        <h1 class="text-center fw-bold">Reset Password</h1>
        <div class="text-center">Please provide the following fields to retake your password</div>
        <hr>
        <form id="form">
            <div class="mb-4">
                <label for="select" class="form-label fw-bold">Select role</label>
                <select class="form-select" id="select" name="role">
                    <option value="Student" selected>Student</option>
                    <option value="Instructor">Instructor</option>
                    <option value="Manager">Manager</option>
                </select>
            </div>
            <div class="mb-4">
                <label for="username" class="form-label fw-bold">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-4">
                <label for="email" class="form-label fw-bold">Email</label>
                <input type="email" class="form-control" id="email" name="email" pattern="${emailPattern}" required>
                <div class="form-text">Email must end with @gmail.com</div>
            </div>
            <button type="submit" class="btn btn-success d-block mx-auto w-50" id="button">Reset Password</button>
        </form>
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

            var username = $('#username').val();
            var email = $('#email').val();
            var role = $('#select').val();

            $.ajax({
                type: 'POST',
                url: '/controllers/ForgotPasswordController',
                data: {
                    role: role,
                    username: username,
                    email: email
                },
                success: function (data) {
                    if (data.login_status === "fail") {
                        swal("Fail", "", "error");
                        if (data.errorType === "role_username") {
                            swal("Fail", "your account does not exist!", "error");
                        } else if (data.errorType === "username_email") {
                            swal("Fail", "username and email do not match!", "error");
                        }
                    } else if (data.login_status === "success") {
                        swal("Success", "", "success");
                        swal("Success", "New password has been sent to your email. Please check it!", "success").then(function () {
                            window.location.href = data.redirect_url;
                        })
                    }
                },
                error: function (e) {
                    console.log(e)
                    swal("Error!", "AJAX EXCEPTION", "error");
                }
            });
        });
    });

</script>

</body>
</html>
