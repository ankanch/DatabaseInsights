<%-- 
    Document   : settings
    Created on : Mar 4, 2018, 7:27:06 PM
    Author     : kanch
--%>

<%@page import="java.util.HashMap"%>
<%@page import="dbi.mgr.user.UserManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserManager um1 = new UserManager();
    HashMap<String, String> hm = um1.getUserInfo(session.getId());
    
%>
<div id="settings">
    <h2>Settings</h2>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <p>Account Registed on: April 22 2017</p>
            <p>Account Status: <%=hm.get("STATUS")%></p>
            <p>Last Login: <%=hm.get("LASTLOGIN")%></p>
        </div>
    </div>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <div class="row">
                <form class="console-mw-500 col-md-8">
                    <div class="form-group is-focused bmd-form-group">
                        <label for="settings_username" class="bmd-label-floating">Username</label>
                        <input type="text" class="form-control console-table-input" id="settings_username" disabled value="<%=hm.get("USERNAME")%>">
                        <span class="bmd-help">You can't change your username.</span>
                    </div>
                    <div class="form-group">
                        <label for="settings_email" class="bmd-label-floating">Email address</label>
                        <input type="email" class="form-control" id="settings_email" value="<%=hm.get("EMAIL")%>">
                        <span class="bmd-help">We'll never share your email with anyone else.</span>
                    </div>
                </form>
                <div class="col-md-4 ">
                    <button type="button" class="btn btn-raised btn-success " onclick="changeInfo(true);">Save Change</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <div class="row">
                <form class="console-mw-500 col-md-8">
                    <div class="form-group">
                        <label for="settings_oldpassword" class="bmd-label-floating">Old Password</label>
                        <input type="password" class="form-control" id="settings_oldpassword">
                    </div>
                    <div class="form-group">
                        <label for="settings_newpassword" class="bmd-label-floating">New Password</label>
                        <input type="password" class="form-control" id="settings_newpassword">
                    </div>
                    <div class="form-group">
                        <label for="settings_confirmnewpassword" class="bmd-label-floating">Confirm New Password</label>
                        <input type="password" class="form-control" id="settings_confirmnewpassword">
                    </div>
                </form>
                <div class="col-md-4 ">
                    <button type="button" class="btn btn-raised btn-success " onclick="changeInfo(false);">Save Change</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <button type="button" class="btn btn-raised btn-danger">Delete Account</button> <small>Watch out, it 
                can not be reversed</small>
        </div>
    </div>
</div>
<script>
    $('#settings').bootstrapMaterialDesign();

    // true for password,
    // false for username
    function changeInfo(typex) {
        if (typex == true) {
            // change email
            var nemail = $("#settings_email").val();
            SubmitFormKVF("/api/UpdateUserinfo", {typex: "email", email:nemail}, function error() {
                showMsg("Failed to change email,please try again later!");
            }, function success() {
                showMsg("Your email now has been changed!");
            });
        } else {
            // change password
            var newpwd = $("#settings_newpassword").val();
            var oldpwd = $("#settings_oldpassword").val();
            var comfirmpwd = $("#settings_confirmnewpassword").val();
            if(newpwd != comfirmpwd || newpwd.length <3){
                showMsg("Password does not match!");return;
            }
            SubmitFormKVF("/api/UpdateUserinfo", {typex: "pwd", npwd:newpwd ,opwd:oldpwd}, function error() {
                showMsg("Failed to change password, please try again later(You may enter a wrong old password)!");
            }, function success() {
                showMsg("Your password now has been changed!");
            });
        }
    }

</script>