<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp" %>
<html>
<head>
    <%--    TITLE   --%>
    <title><sitemesh:write property="title"/></title>
    <%--    TITLE   --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--    <link rel="icon" href="../../../../favicon.ico">--%>

    <%--        CSS         --%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
          integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
    <link rel="stylesheet" href="<c:url value="/css/manager/dashboard.css"/>">
</head>
<body>
<%--        HEADER      --%>
<%@include file="/commons/admin/header.jsp" %>
<%--        HEADER      --%>

<div class="container-fluid">
    <div class="row">
        <%--        MENU        --%>
        <%@include file="/commons/admin/menu.jsp" %>
        <%--        MENU        --%>

        <main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Overview</li>
                </ol>
            </nav>
            <div class="container w-100 h-100">
                <%--                MAIN CONTENT            --%>
                <sitemesh:write property="body"/>
                <%--                MAIN CONTENT            --%>

            </div>

            <%--            Footer          --%>
            <%@include file="/commons/admin/footer.jsp" %>
            <%--            Footer          --%>
        </main>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
        integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<script>
    new Chartist.Line('#traffic-chart', {
        labels: ['January', 'Februrary', 'March', 'April', 'May', 'June'],
        series: [
            [23000, 25000, 19000, 34000, 56000, 64000]
        ]
    }, {
        low: 0,
        showArea: true
    });
</script>
<%-- ALERT --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

    $(document).ready(function () {
        $('#button').click(function (event) {
            var role = $('#select').val();
            var username = $('#username').val();
            var password = $('#password').val();

            $.ajax({
                type: 'POST',
                url: '/controllers/LoginController',
                data: {
                    role: role,
                    username: username,
                    password: password
                },
                success: function (data) {
                    if (data.login_status === "fail") {
                        swal("Account does not exist", "", "error");
                    }
                    if (data.login_status === "success") {
                        swal("Login successful!", "", "success").then(function() {
                            window.location.href = data.user_dashboard_url;
                        })
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