<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Account</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">

</head>
<body>
<fmt:bundle basename="regex">
    <fmt:message key="password.regex" var="passwordPattern"/>
</fmt:bundle>

<div class="card">
    <h3 class="text-center text-primary mb-4">Register Account</h3>
    <hr>

    <form id="form">
        <!-- Role (fixed as student) -->
        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <input type="text" class="form-control" id="role" name="role" value="student" readonly>
        </div>

        <!-- Username -->
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>

        <!-- Password -->
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required
                   pattern="${passwordPattern}">
            <div class="form-text">Password must contain at least 6 characters and at least 1 uppercase</div>
        </div>

        <!-- Confirm Password -->
        <div class="mb-4">
            <label for="confirmPassword" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
        </div>

        <!-- Buttons -->
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success">Register</button>
            <button type="button" class="btn btn-secondary" onclick="clearForm()">Clear</button>
        </div>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

<script>
    // Hàm xóa trắng form
    function clearForm() {
        document.getElementById("username").value = "";
        document.getElementById("password").value = "";
        document.getElementById("confirmPassword").value = "";
    }
</script>
<%-- ALERT --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $('#form').on('submit', function (event) {
            event.preventDefault(); // Ngăn form submit mặc định

            var role = $('#role').val();
            var username = $('#username').val();
            var password = $('#password').val();
            var confirm_password = $('#confirmPassword').val();

            $.ajax({
                type: 'POST',
                url: '/controllers/RegisterController',
                data: {
                    role: role,
                    username: username,
                    password: password,
                    confirm_password: confirm_password
                },
                success: function (data) {
                    if (data.login_status === "fail") {
                        swal("Fail", "", "error");
                        if (data.errorType === "username_exist") {
                            swal("Fail", "Username existed!", "error");
                        } else if (data.errorType === "confirm_password_invalid") {
                            swal("Fail", "Password and Confirm password do not match!", "error");
                        }
                    } else if (data.login_status === "success") {
                        swal("Success", "", "success");
                        swal("Register account successfully!", "", "success").then(function () {
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
