<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
        .gradient-custom {
            background: rgb(15, 83, 161);
            background: -webkit-radial-gradient(circle at top left, rgb(15, 83, 161), rgb(219, 219, 219),rgb(53, 142, 245));
            background: radial-gradient(circle at top left, rgb(15, 83, 161), rgb(219, 219, 219),rgb(53, 142, 245));
        }
        .close {
        position: relative;
        background-color: rgb(219, 219, 219);
        display: flex;
        flex-direction: row;
        justify-content: center;
        border-radius: 2rem;
        height: 25px;
        margin-bottom: 5px;

    }

    .close-btn {
        position: relative;
        left: 10%;
        bottom: 8px;
        background: none;
        border: none;
        font-size: 20px;
        font-weight: bold;
        cursor: pointer;
        color: rgb(15, 83, 161);
    }
    </style>
</head>
<body>

    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card bg-dark text-white" style="border-radius: 2rem;">
          <div class="card-body p-5 text-center">

            <div class="mb-md-5 mt-md-4">

              <h1 class="fw-bold mb-2 text-uppercase">Welcome</h1>
              <p class="text-white-50">Please enter your credentials</p>

              <c:if test="${param.error != null}">
                <div class="close" id="logoutMessage">
                    <p style="color:red;;font-size: 15px;font-weight: bold;">Invalid username or password</p>
                    <button class="close-btn" onclick="closeMessage()">x</button>
                </div>
              </c:if>

              <c:if test="${param.logout != null}">
                <div class="close" id="logoutMessage">
                    <p style="color:rgb(15, 83, 161);font-weight: bold;font-size: 15px;">You have been logged out successfully</p>
                    <button class="close-btn" onclick="closeMessage()">x</button>
                </div>
              </c:if>

              <script>
                function closeMessage() {
                  document.getElementById('logoutMessage').style.display = 'none';
                }
              </script>

              <form action="<c:url value='/perform-login'/>" method="post">
                    <div data-mdb-input-init class="form-outline form-white mb-4">
                        <label class="form-label" for="typeEmailX" style="font-family: cursive;font-size: 20px;">Username</label>
                        <input type="text" id="username" name="username" class="form-control form-control-lg" required/>
                    </div>
                    <div data-mdb-input-init class="form-outline form-white mb-4">
                        <label class="form-label" for="typePasswordX" style="font-family: cursive;font-size: 20px;">Password</label>
                        <input type="password" id="password" name="password" class="form-control form-control-lg" required/>
                    </div>
                        <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Sign in</button>
                    </div>
              </form>
            <div>
              <p class="mb-0">Don't have an account? <a href="#!" class="text-white-50 fw-bold">Sign Up</a>
              </p>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js" integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y" crossorigin="anonymous"></script>
</body>
</html>
