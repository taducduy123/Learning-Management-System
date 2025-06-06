<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/change-password.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alert-animation.css">
</head>
<body>

<fmt:bundle basename="regex">
    <fmt:message key="password.regex" var="passwordPattern"/>
</fmt:bundle>


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">
                <h1 class="text-center">Change Password</h1>
                <div class="text-center">Please enter the following fields</div>
                <hr>
                <div>
                    <c:set var="alert" value="${requestScope['alert']}"/>
                    <c:set var="message" value="${requestScope['message']}"/>

                    <c:if test="${alert != null}">
                        <div class="alert alert-${alert} alert-slide-in">
                            <fmt:bundle basename="message">
                                <fmt:message key="${message}"/>
                            </fmt:bundle>
                        </div>
                    </c:if>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/controllers/ChangePasswordController" method="post">
                        <!-- Current Password -->
                        <div class="mb-3">
                            <label for="current_password" class="form-label">Current Password</label>
                            <input type="password" class="form-control" id="current_password" name="current_password"
                                   required>
                        </div>

                        <!-- New Password -->
                        <div class="mb-3">
                            <label for="new_password" class="form-label">New Password</label>
                            <input type="password" class="form-control" id="new_password" name="new_password" required
                                   pattern="${passwordPattern}">
                            <div class="form-text">Password must contain at least 6 characters and at least 1 uppercase
                            </div>
                            
                        </div>

                        <!-- New Password Again -->
                        <div class="mb-3">
                            <label for="confirm_password" class="form-label">Confirm New Password</label>
                            <input type="password" class="form-control" id="confirm_password" name="confirm_password"
                                   required>
                        </div>

                        <!-- Buttons -->
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary px-4">Save</button>
                            <button type="reset" class="btn btn-secondary px-4">Clear</button>
<%--                            <a href="${pageContext.request.contextPath}/controllers/LoginController" class="btn btn-success">Back to login</a>--%>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle (optional, for interactive components) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
