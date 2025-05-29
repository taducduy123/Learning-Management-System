<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<c:set var="student" value="${requestScope['student']}"/>
<c:set var="account" value="${requestScope['account']}"/>


<div>
  <form action="${pageContext.request.contextPath}/controllers/manager/StudentController/edit/save?student_id=<c:out value="${student.getUser_id()}"/>&account_id=<c:out value="${student.getAccount_id()}"/>" method="post">
    <div class="mb-3">
      <label for="username" class="form-label fw-bold">Username</label>
      <input type="text" class="form-control" id="username"  name="username"  value="<c:out value="${account.getUsername()}"/>" readonly>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label fw-bold">Password</label>
      <input type="text" class="form-control" id="password" name="password" required value="<c:out value="${account.getPassword()}"/>">
      <div class="form-text">Password must contain at least 6 characters and at least 1 uppercase</div>
    </div>
    <div class="mb-3">
      <label for="first-name" class="form-label fw-bold">First name</label>
      <input type="text" class="form-control" id="first-name" name="first-name" required value="<c:out value="${student.getFirst_name()}"/>">
    </div>
    <div class="mb-3">
      <label for="middle-name" class="form-label fw-bold">Middle name</label>
      <input type="text" class="form-control" id="middle-name" name="middle-name" required value="<c:out value="${student.getMiddle_name()}"/>">
    </div>
    <div class="mb-3">
      <label for="last-name" class="form-label fw-bold">Last name</label>
      <input type="text" class="form-control" id="last-name" name="last-name" required value="<c:out value="${student.getLast_name()}"/>">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label fw-bold">Email</label>
      <input type="email" pattern="[a-zA-Z0-9._%+-]+@gmail\.com" name="email" class="form-control" id="email" required value="<c:out value="${student.getEmail()}"/>">
    </div>
    <div class="mb-3">
      <label for="phone" class="form-label fw-bold">Phone number</label>
      <input type="text" class="form-control" id="phone" name="phone" required value="<c:out value="${student.getPhone()}"/>">
    </div>

    <button type="submit" class="btn btn-primary">Save</button>
    <button type="reset" class="btn btn-primary">Cancel</button>
    <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/edit/back">
      <button type="button" class="btn btn-primary">Back</button>
    </a>
  </form>
</div>