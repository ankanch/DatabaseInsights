<%-- 
    Document   : settings
    Created on : Mar 4, 2018, 7:27:06 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="settings">
    <h2>Settings</h2>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <p>Account Registed on: April 22 2017</p>
            <p>Account Status: Normal</p>
            <p>Last Login: March 13 2018 at Los Angeles</p>
        </div>
    </div>
    <div class="card mb-3 pl-5 pr-5">
        <div class="card-body pl-5 pr-5">
            <div class="row">
                <form class="console-mw-500 col-md-8">
                    <div class="form-group">
                        <label for="settings_username" class="bmd-label-floating">Username</label>
                        <input type="text" class="form-control" id="settings_username" readonly>
                        <span class="bmd-help">You can't change your username.</span>
                    </div>
                    <div class="form-group">
                        <label for="settings_email" class="bmd-label-floating">Email address</label>
                        <input type="email" class="form-control" id="settings_email">
                        <span class="bmd-help">We'll never share your email with anyone else.</span>
                    </div>
                </form>
                <div class="col-md-4 ">
                    <button type="button" class="btn btn-raised btn-success ">Save Change</button>
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
                    <button type="button" class="btn btn-raised btn-success ">Save Change</button>
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
</script>