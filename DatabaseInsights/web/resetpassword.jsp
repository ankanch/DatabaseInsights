<%-- 
    Document   : resetpassword
    Created on : Mar 1, 2018, 11:45:08 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset your password - Database Insights</title>
        <%@ include file="templates/globehead.html" %>
    </head>
    <body>
        <%@ include file="templates/index/indexnav.html" %>
        <div class="pt-md-4 pb-md-5">
            <div class="card bg-light mx-auto " style="max-width: 400px;">
                <div class="card-header"><h4>Password Reset</h4></div>
                <div class="card-body">
                    <form action="api/login" method="post">
                        <p>Please enter the E-mail when you registered your account, we will send you an email guiding you to reset the password.</p>
                        <div class="form-group">
                            <label for="reset_email" class="bmd-label-floating">Email address</label>
                            <input type="email" required="required" class="form-control" id="reset_email" name="email">
                        </div>
                        <button type="submit" class="btn btn-raised btn-success btn-lg btn-block">Send Reset Link</button>
                    </form>
                </div>
            </div>
        </div>
        <footer class="container py-5">
            <%@ include file="templates/index/footer.html" %>
        </footer>
        <script>

        </script>
    </body>
</html>
