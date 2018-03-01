<%-- 
    Document   : signin
    Created on : Mar 1, 2018, 9:20:04 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signin - Database Insights Console</title>
        <%@ include file="templates/globehead.html" %>
    </head>
    <body>
        <%@ include file="templates/index/indexnav_titleonly.html" %>
        <div class="pt-md-4 pb-md-5">
            <div class="card bg-light mx-auto " style="max-width: 400px;">
                <div class="card-header"><h4>Login</h4></div>
                <div class="card-body">
                    <form action="api/login" method="post">
                        <div class="form-group">
                            <label for="login_email" class="bmd-label-floating">Email address</label>
                            <input type="email" required="required" class="form-control" id="login_email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="login_password" class="bmd-label-floating">Password</label>
                            <input type="password" required="required" class="form-control" id="login_password" name="password">
                        </div>
                        <div class="checkbox pt-md-5 pb-md-4">
                            <label>
                                <input type="checkbox" name="rememberme" id="optionsRadios1" value="option1">
                                Remember me
                            </label>
                        </div>
                        <button type="submit" class="btn btn-raised btn-success btn-lg btn-block">Login</button>
                        <button type="button" class="btn btn-link btn-block" onclick="window.location='resetpassword.jsp'"><small>Problem in login?</small></button>
                    </form>
                    <script>
                        $('body').bootstrapMaterialDesign();
                        $("#nav_1").text("Database Insights Console");
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
