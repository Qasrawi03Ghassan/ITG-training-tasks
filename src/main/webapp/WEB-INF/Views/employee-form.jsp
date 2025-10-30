<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employees Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .back-div{
            position: absolute;
            top: 0;
            left: 0;
            margin-top: 30px;
            margin-left: 30px;
            padding: 5px;
            border-radius: 2rem;
            box-shadow: 1px 1px 10px 10px rgba(188, 130, 221, 0.267);
        }
        .back-div:hover{
            cursor: pointer;
        }
        .customGrad {
            background: radial-gradient(circle at top left, rgba(107, 15, 161, 0.877), rgb(131, 131, 131), rgba(107, 15, 161, 0.822));
        }
        .maindiv{
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%); 
            background: linear-gradient(
                rgba(187, 97, 209, 0.3),  
                rgba(255, 255, 255, 0.1)   
            ), rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(8px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            padding: 15px;
            border-radius: 2rem;
            width: 40%;
        }
        .input{
            margin-top: 10px;
            margin-bottom: 30px;
            width: 500px;
            align-self: center;

            text-align: center;
            font-size: 25px;
            font-weight: bold;
        }
        label{
            font-weight:bold;
        }
    </style>
</head>
<body>
<section class="vh-100 customGrad">
    <h1 class="text-center text-white mb-5">Employees Form</h1>
    <div class="back-div" title="Go back to employees list" onclick="window.history.back()">
        <i class="fa-solid fa-arrow-left" style="font-size: 25px;color: white;"></i>
    </div>

    <c:if test="${editEmp != null}">
       <div class="maindiv">

            <h1 class="text text-center text-white">Edit employee <strong>${editEmp.name}</strong>'s info</h1>
            <form class="text-center container" method="post" action="<c:url value='/employees/edit/${editEmp.id}/perform-edit'/>">
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;margin-bottom: 5px;text-align: left;">Name</label>
                    <input class="input form-control" type="text" value="${editEmp.name}" name="name" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Email</label>
                    <input class="input form-control" type="email" value="${editEmp.email}" name="email" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px; text-align: left;">Department</label>
                    <input class="input form-control" type="text" value="${editEmp.department}" name="department" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Salary</label>
                    <input class="input form-control" type="number" value="${editEmp.salary}" name="salary" min="0" required>
                </div>

                <input type="reset" title="Cancel changes" class="btn btn-primary btn-lg" value="Reset" style="background-color: transparent; border: solid 1px rgba(107, 15, 161, 0.877); margin-top: 15px;margin-right: 30px;">
                <input type="submit" title="" class="btn btn-primary btn-lg" value="Submit" style="background-color: rgba(107, 15, 161, 0.877);border: none;margin-top: 15px;">
            </form>
       </div>
    </c:if>

    <c:if test="${delEmp != null}">
        <div class="maindiv">
            <form method="post" action="<c:url value='/employees/delete/${delEmp.id}/perform-del'/>" class="text-center">
                <h1 class="h1 text text-center text-white mb-3">Deletion confirmation dialog</h1>
                <p class="text text-white" style="font-size: 20px;">
                    Are you sure you want to delete employee ${delEmp.name}'s record? [ID = ${delEmp.id}]
                </p>
                <input type="reset" onclick="window.history.back()" value="Cancel" class="btn btn-lg btn-primary" style="background-color: transparent;  border: 1px solid rgba(107, 15, 161, 0.877);">
                <input type="submit" value="Confirm" class="btn btn-lg text-white" style="background-color: rgba(107, 15, 161, 0.877);">
            </form>
        </div>
    </c:if>

    <c:if test="${add != null}">
        <div class="maindiv">
            <h1 class="h1 text text-center text-white">Add a new employee</h1>
            <form class="text-center container" method="post" action="/employees/add/perform-add">
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;margin-bottom: 5px;text-align: left;">Name</label>
                    <input class="input form-control" type="text" name="name" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Email</label>
                    <input class="input form-control" type="email" name="email" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px; text-align: left;">Department</label>
                    <input class="input form-control" type="text"  name="department" required>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Salary</label>
                    <input class="input form-control" type="number"  name="salary" min="0" required>
                </div>

                <input type="reset" title="Cancel changes" class="btn btn-primary btn-lg" value="Reset" style="background-color: transparent; border: solid 1px rgba(107, 15, 161, 0.877); margin-top: 15px;margin-right: 30px;">
                <input type="submit" title="" class="btn btn-primary btn-lg" value="Submit" style="background-color: rgba(107, 15, 161, 0.877);border: none;margin-top: 15px;">
            </form>
        </div>
    </c:if>



</section>
</body>
</html>
