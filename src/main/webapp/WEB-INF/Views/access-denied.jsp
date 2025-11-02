<%@ page language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Unauthorized access detected!</title>
    <style>
        body {
            height: 100vh;
            background-color: rgb(145, 201, 201);
        }

    </style>
</head>
<body>
    <div class="container alert alert-danger" role="alert" style="position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">
        <span style="display: flex; flex-direction: row; justify-content: center; align-items: center;">
            <i class="fa-solid fa-triangle-exclamation" style="color: rgba(255, 0, 0, 0.637); font-size: 40px;"></i>
            <h1 class="h1 text-center">Unauthorized access</h1>
        </span>
        <p class="text-center" style="font-size: 20px;">You are not authorized to perform this kind of operation, contact an administrator for more details.</p>
    </div>
</body>
</html>