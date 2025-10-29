<%@ page %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Error</title>
    <style>
        body{
            background: radial-gradient(circle at top center, rgb(226, 23, 23), rgb(124, 65, 65), rgb(0, 0, 0));
            height: 100vh;
        }
        .error-div {
            position: absolute;
            top: 45%;
            left: 50%;
            transform: translate(-50%, -50%);
            border-radius: 2rem;

            
            background: linear-gradient(
                rgba(255, 255, 255, 0.3),  
                rgba(255, 255, 255, 0.1)   
            ), rgba(0, 0, 0, 0.3);       
            backdrop-filter: blur(8px);

            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);

            padding: 2rem;
            text-align: center;
        }

    </style>
</head>
<body>
    <h1 class="h1 text-center text-white">Something wrong happened</h1>
    <div class="error-div text-center" style="padding: 20px;">
        <h2 class="h2 text-center text-white" style="font-weight: bold;">${status} ERROR</h2>
        <p class="text-center text-white" style="font-size: 20px;">
            ${message}
        </p>
    </div>
</body>
</html>