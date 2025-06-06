<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Manage Student</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager/student.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">


<c:set var="students" value="${requestScope['students']}"/>


<%--SEARCH--%>
<form class="container w-50 mb-3"
      action="${pageContext.request.contextPath}/controllers/manager/StudentController/search" method="get">
    <div class="d-flex flex-row justify-content-center">
        <input type="text" class="form-control search-input" placeholder="Search by name" name="keyword">
        <button type="submit" class="btn btn-primary me-1">
            <i class="fas fa-search search-icon"></i>
        </button>
    </div>
</form>

<%--VIEW LIST--%>
<div class="my-table-container">
    <table class="table align-middle mb-0 bg-white ">
        <thead>
        <tr>
            <th class="bg-dark text-light">Student ID</th>
            <th class="bg-dark text-light">Account ID</th>
            <th class="bg-dark text-light">Name</th>
            <th class="bg-dark text-light">Phone</th>
            <th class="bg-dark text-light">Email</th>
            <th class="bg-dark text-light">Is Block?</th>
            <th class="bg-dark text-light">Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${empty students}">
            <tr>
                <td colspan="7" class="text-center">No Result Found</td>
            </tr>
        </c:if>

        <c:if test="${not empty students}">
            <c:forEach items="${students}" var="student">
                <tr>
                    <td><c:out value="${student.getUser_id()}"/></td>
                    <td><c:out value="${student.getAccount_id()}"/></td>
                    <td><c:out
                            value="${student.getFirst_name()} ${student.getMiddle_name()} ${student.getLast_name()}"/></td>
                    <td><c:out value="${student.getPhone()}"/></td>
                    <td><c:out value="${student.getEmail()}"/></td>
                    <td><c:out value="${student.isBlocked()}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/edit?student_id=${student.getUser_id()}&action=showForm">
                            <button type="button" class="btn btn-info">Edit</button>
                        </a>
                        <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/block?student_id=${student.getUser_id()}">
                            <button id="blockBtn" class="btn ${student.isBlocked() ? 'btn-secondary' : 'btn-danger'}">
                                Block
                            </button>
                        </a>
                        <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/un-block?student_id=${student.getUser_id()}">
                            <button id="blockBtn" class="btn ${student.isBlocked() ? 'btn-success' : 'btn-secondary'}">
                                Unblock
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>


<%--CREATE NEW STUDENT--%>
<div class="d-flex justify-content-center my-5">
    <a href="${pageContext.request.contextPath}/controllers/manager/StudentController/create?action=showForm">
        <button type="button" class="btn btn-success d-flex align-items-center gap-2 px-4 py-2 rounded-pill shadow-sm">
            + Create new student
        </button>
    </a>
</div>
