<%-- 
    Document   : console
    Created on : Mar 2, 2018, 5:16:48 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Console - Database Insights</title>
        <%@ include file="templates/globehead.html" %>
    </head>
    <body>
        <%@ include file="templates/console/console_nav.html" %>
        <div class="container-fluid">

            <div class="row">
                <div class="col-2 pl-0">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-home" role="tab" aria-controls="home">Home</a>
                        <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-profile" role="tab" aria-controls="profile">Profile</a>
                        <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-messages" role="tab" aria-controls="messages">Messages</a>
                        <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">Settings</a>
                    </div>
                </div>
                <div class="col-10 bg-white">
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
                      
                        </div>
                        <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">2</div>
                        <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">3</div>
                        <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">4</div>
                    </div>
                    <footer class="mx-auto py-3 bg-light full-width">
                        <%@ include file="templates/console/console_footer.html" %>
                    </footer>
                </div>
            </div>
        </div>
    </body>
</html>
