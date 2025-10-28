<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employees List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .customGrad {
            background: radial-gradient(circle at bottom right, rgb(107, 15, 161), rgb(131, 131, 131), rgb(107, 15, 161));
        }
    </style>
</head>
<body>
<section class="vh-100 customGrad">
    <h1 class="text-center text-white mb-5">Employees List</h1>

    <div class="container" style="position: absolute;top: 45%;left: 50%;transform: translate(-50%,-50%);">
        <c:choose>
            <c:when test="${not empty employees}">
                <table class="table text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Department</th>
                            <th>Salary</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.name}</td>
                                <td>${employee.email}</td>
                                <td>${employee.department}</td>
                                <td>${employee.salary} $</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h3 class="text-center text-white">There are currently no employees</h3>
            </c:otherwise>
        </c:choose>

        <form action="/logout" class="text-center mt-4">
            <button class="btn btn-outline-light btn-lg">Sign out</button>
        </form>
    </div>
</section>
</body>
</html>
