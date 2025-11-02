<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee ${employee.name}'s details</title>
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
            box-shadow: 1px 1px 10px 10px rgba(221, 220, 130, 0.267);
        }
        .back-div:hover{
            cursor: pointer;
        }
        .customGrad {
            background: radial-gradient(circle at bottom center, rgba(231, 235, 23, 0.877), rgb(131, 131, 131), rgba(139, 161, 15, 0.822));
        }
        .maindiv{
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%); 
            background: linear-gradient(
                rgba(191, 194, 16, 0.877),
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
    <div class="back-div" title="Go back to employees list" onclick="window.location.href='/employees';">
        <i class="fa-solid fa-arrow-left" style="font-size: 25px;color: white;"></i>
    </div>

        <h1 class="h1 text text-center text-white">Employee details</h1>

       <div class="maindiv">

            <div style="display: flex;justify-content: center;">
                <h1 class="text text-center text-white">Employee <strong>${employee.name}</strong>'s info</h1>
                <h1 class="text text-center text-white" style="position: relative; left: 10%;font-weight: bold;">ID: ${employee.id}</h1>
            </div>
            
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;margin-bottom: 5px;text-align: left;">Name</label>
                    <input class="input form-control" type="text" value="${employee.name}" disabled>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Email</label>
                    <input class="input form-control" type="text" value="${employee.email}" disabled>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px; text-align: left;">Department</label>
                    <input class="input form-control" type="text" value="${employee.department}" disabled>
                </div>
                <div style="display: flex;flex-direction: column;">
                    <label class="text text-white" style="font-size: 22px;text-align: left;">Salary</label>
                    <input class="input form-control" type="text" value="${employee.salary} $" disabled>
                </div>                
            
       </div>
</section>
</body>
</html>
