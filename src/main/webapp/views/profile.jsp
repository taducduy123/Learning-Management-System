<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/alert-animation.css" rel="stylesheet">
</head>
<body>

<fmt:bundle basename="regex">
    <fmt:message key="email.regex" var="emailPattern"/>
    <fmt:message key="phone.regex" var="phonePattern"/>
</fmt:bundle>


<div class="profile-card">
    <h2 class="form-title">Edit Your Profile</h2>
    <hr>
    <div>
        <c:set var="alert" value="${requestScope['alert']}"/>
        <c:set var="message" value="${requestScope['message']}"/>

        <c:if test="${alert != null}">
            <div class="alert alert-${alert} alert-slide-in">
                <fmt:bundle basename="message">
                    <fmt:message key="edit_profile_success"/>
                </fmt:bundle>
            </div>
        </c:if>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/controllers/ProfileController" method="post">
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <input type="text" class="form-control bg-light text-muted"
                       id="role" name="role"
                       value="${sessionScope.account.role}"
                       readonly style="cursor: not-allowed;">
            </div>
            <c:if test="${fn:toLowerCase(sessionScope.account.role) == 'instructor'}">
                <div class="mb-3">
                    <label for="level" class="form-label">Level</label>
                    <input type="text" class="form-control bg-light text-muted"
                           id="level" name="level"
                           value="${sessionScope.user.level}"
                           readonly style="cursor: not-allowed;">
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4 mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName"
                           value="${sessionScope.user.first_name}" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="middleName" class="form-label">Middle Name</label>
                    <input type="text" class="form-control" id="middleName" name="middleName"
                           value="${sessionScope.user.middle_name}">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName"
                           value="${sessionScope.user.last_name}" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${sessionScope.user.email}"
                       required pattern="${emailPattern}">
                <div class="form-text">Email must end with @gmail.com</div>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" class="form-control" id="phone" name="phone" value="${sessionScope.user.phone}"
                       required pattern="${phonePattern}">
                <div class="form-text">Please ensure you enter Vietnamese phone number</div>
            </div>

            <div class="d-flex justify-content-between mt-4 flex-wrap">
                <button type="submit" class="btn btn-custom btn-save">Save</button>
                <button type="reset" class="btn btn-custom btn-clear">Cancel</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
