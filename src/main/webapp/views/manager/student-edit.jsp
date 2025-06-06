<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<title>Edit Student</title>


<c:set var="student" value="${requestScope['student']}"/>
<c:set var="account" value="${requestScope['account']}"/>

<fmt:bundle basename="regex">
  <fmt:message key="password.regex" var="passwordPattern"/>
  <fmt:message key="email.regex" var="emailPattern"/>
  <fmt:message key="phone.regex" var="phonePattern"/>
</fmt:bundle>

<div>
  <c:set var="alert" value="${param.alert}"/>
  <c:set var="message" value="${param.message}"/>

  <c:if test="${alert != null}">
    <div class="alert alert-${alert}">
        ${message}
    </div>
  </c:if>
</div>
<div>
  <form action="${pageContext.request.contextPath}/controllers/manager/StudentController/edit?action=save&student_id=<c:out value="${student.getUser_id()}"/>&account_id=<c:out value="${student.getAccount_id()}"/>" method="post">
    <input type="hidden" name="account_id" value="${account.getAccount_id()}">
    <input type="hidden" name="role" value="${account.getRole()}">

    <div class="mb-3">
      <label for="username" class="form-label fw-bold">Username</label>
      <input type="text" class="form-control" id="username"  name="username"  value="<c:out value="${account.getUsername()}"/>" readonly>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label fw-bold">Password</label>
      <input type="text" class="form-control" id="password" name="password" required value="<c:out value="${account.getPassword()}"/>" pattern="${passwordPattern}">
      <div class="form-text">Password must contain at least 6 characters and at least 1 uppercase</div>
    </div>
    <div class="mb-3">
      <label for="first-name" class="form-label fw-bold">First name</label>
      <input type="text" class="form-control" id="first-name" name="first_name" required value="<c:out value="${student.getFirst_name()}"/>">
    </div>
    <div class="mb-3">
      <label for="middle-name" class="form-label fw-bold">Middle name</label>
      <input type="text" class="form-control" id="middle-name" name="middle_name" required value="<c:out value="${student.getMiddle_name()}"/>">
    </div>
    <div class="mb-3">
      <label for="last-name" class="form-label fw-bold">Last name</label>
      <input type="text" class="form-control" id="last-name" name="last_name" required value="<c:out value="${student.getLast_name()}"/>" >
    </div>
    <div class="mb-3">
      <label for="email" class="form-label fw-bold">Email</label>
      <input type="email" name="email" class="form-control" id="email" required value="<c:out value="${student.getEmail()}"/>" pattern="${emailPattern}">
      <div class="form-text">Email must end with @gmail.com</div>
    </div>
    <div class="mb-3">
      <label for="phone" class="form-label fw-bold">Phone number</label>
      <input type="text" class="form-control" id="phone" name="phone" required value="<c:out value="${student.getPhone()}"/>" pattern="${phonePattern}">
      <div class="form-text">Please ensure you enter Vietnamese phone number</div>
    </div>

    <button type="submit" class="btn btn-primary">Save</button>
    <button type="reset" class="btn btn-primary">Cancel</button>
    <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/edit?action=back">
      <button type="button" class="btn btn-primary">Back</button>
    </a>
  </form>
</div>