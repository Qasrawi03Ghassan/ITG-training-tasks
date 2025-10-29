<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employees List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .customGrad {
            background: radial-gradient(circle at bottom right, rgba(107, 15, 161, 0.877), rgb(131, 131, 131), rgba(107, 15, 161, 0.822));
        }
    </style>
</head>
<body>
<section class="vh-100 customGrad">
    <h1 class="text-center text-white mb-5">Employees List</h1>
    <div style="position: absolute;top: 0;right: 0; background-color: rgb(142, 56, 192); border-radius: 25px;padding: 10px;margin: 20px;" title="Role: ${loggedUser.role}">
        <c:if test="${loggedUser.role == 'ADMIN'}">
            <i class="fa-solid fa-user-tie" style="color: white;font-size: 60px;"></i>
        </c:if>
        <c:if test="${loggedUser.role == 'USER'}">
            <i class="fa-solid fa-user" style="color: white;font-size: 60px;"></i>
        </c:if>
    </div>
    <h2 class="text-center text-white">Welcome back, ${loggedUser.name}</h2>
    <p class="text-center text-white">
        <c:if test="${loggedUser.role == 'USER'}">
                Feel free to view current employees
        </c:if>
        <c:if test="${loggedUser.role == 'ADMIN'}">
                Feel free to manage employees
        </c:if>
    </p>

    <div class="container" style="position: absolute;top: 45%;left: 50%;transform: translate(-50%,-50%);display: flex;flex-direction: column;justify-content: center; align-items: center;">
        <c:choose>
            <c:when test="${not empty employees}">
                <table class="table text-center" style="width: 40%;">
                    <thead class="table-dark">
                        <tr>
                            <th>Employee ID</th>
                            <th>Employee Name</th>
                            <c:if test="${loggedUser.role == 'ADMIN'}">
                                <th>Manage employee</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.name}</td>
                                <c:if test="${loggedUser.role == 'ADMIN'}">
                                    <td>
                                        <div style="display: flex; flex-direction: row;justify-content: center;">
                                            <form action="<c:url value='/employees/edit/${employee.id}'/>" method="post">
                                                <button class="btn btn-outline-light border-radius" style="font-weight: bold;background-color: rgb(142, 56, 192);">Edit</button>
                                            </form>
                                            <form method="post" action="<c:url value='/employees/delete/${employee.id}'/>">
                                                <button class="btn btn-outline-light" style="background-color: red;font-weight: bold;">Delete</button>
                                            </form>
                                        </div>
                                    </td>
                                </c:if>
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
