<%-- 
    Document   : signup
    Created on : Mar 1, 2018, 1:36:42 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup - Database Insights</title>
        <%@ include file="templates/globehead.html" %>
    </head>
    <body>
        <%@ include file="templates/index/indexnav.html" %>
        <div class="pt-md-5 pb-md-5">
            <div class="card bg-light mx-auto " style="max-width: 400px;">
                <div class="card-header">Sign up</div>
                <div class="card-body">
                   <%@ include file="templates/components/form_signup.html" %>
                </div>
            </div>
           
        </div>
        <footer class="container py-5">
            <%@ include file="templates/index/footer.html" %>
        </footer>

    </body>
</html>