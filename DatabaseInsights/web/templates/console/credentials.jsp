<%-- 
    Document   : credentials
    Created on : Mar 4, 2018, 8:48:37 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Credentials</h2>
<div class="card">
    <div class="progress-line" id="progressbar"></div>
    <div class="card-body" id="credentialscard">
        <div class="row">
            <div class="col-md-4 console-div-text"><p class="console-vc bmd-form-group">You have <span class="" id="cre_count"> 1 </span> database credentials in total.</p></div>
            <div class="col-md-2">
                <div class="bmd-form-group console-vc">
                    <button type="button" class="btn btn-primary " data-toggle="modal" data-target="#modal_add_credentials">Add Credentials</button>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0 col-md-5 ml-auto mr-0">
                <div class="form-group console-div-vc">
                    <label for="searchcre" class="bmd-label-floating">Search</label>
                    <input type="text" class="form-control" id="searchcre">
                </div>
                <span class="form-group bmd-form-group console-div-vc"> <!-- needed to match padding for floating labels -->
                    <button type="submit" class="btn btn-primary">Search</button>
                </span>
            </form>
        </div
        <%@ include file="../dialogs/console_add_credentials.html" %>
        <hr>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Database</th>
                    <th scope="col">Host</th>
                    <th scope="col">Account</th>
                    <th scope="col">Password</th>
                    <th scope="col">Operations</th>
                </tr>
            </thead>
            <tbody>
                <tr id="credtable_row_1">
                    <th scope="row" style="vertical-align: inherit;">1</th>
                    <td style="vertical-align: inherit;padding: 0rem;">
                        <input id="credtable_row_1_col_1" class="console-table-input" type="text" name="dbname" disabled="disabled">
                    </td>
                    <td style="vertical-align: inherit;padding: 0rem;">
                        <input id="credtable_row_1_col_2" class="console-table-input" type="text" name="dbhost" disabled="disabled">
                    </td>
                    <td style="vertical-align: inherit;padding: 0rem;">
                        <input id="credtable_row_1_col_3" class="console-table-input" type="text" name="dbaccount" disabled="disabled">
                    </td>
                    <td style="vertical-align: inherit;padding: 0rem;">
                        <input id="credtable_row_1_col_4" class="console-table-input" type="password" name="dbpassword" disabled="disabled">
                    </td>
                    <td>
                        <button id="credtable_row_1_edit" type="button" class="btn btn-secondary" onclick="edit(1)">Edit</button>
                        <button id="credtable_row_1_save" type="button" class="btn btn-success" onclick="save(1)" style="display: none;">Save</button>
                        <button type="button" class="btn btn-danger" onclick="del(1)">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="alert alert-warning alert-dismissible fade show" role="alert" id="alert_no_credentials">
            <strong>Holy guacamole!</strong> You do not have any database credentials added yet.
            <br>Let's start by click ADD CREDENTIALS button on the top.
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
</div>
<script>

    $('#dbimc').bootstrapMaterialDesign();
    $("#progressbar").hide();

    function edit(row) {
        var inputprefix = "credtable_row_" + row + "_col_";
        $("#credtable_row_1_edit").hide();
        $("#credtable_row_1_save").show();
        $("#" + inputprefix + "1").removeAttr('disabled');
        $("#" + inputprefix + "2").removeAttr('disabled');
        $("#" + inputprefix + "3").removeAttr('disabled');
        $("#" + inputprefix + "4").removeAttr('disabled');
    }

    function save(row) {
        $("#progressbar").show();
        var inputprefix = "credtable_row_" + row + "_col_";
        //send updates to server
        SubmitFormF("/api/updateCredential", {dbhost: "", dbname: "", dbuser: "", dbpwd: ""}, function error() {
            showMsg("Failed to update,please try again later.");
        }, function success() {
            showMsg("Update successfully!");
            $("#progressbar").hide();
            $("#credtable_row_1_edit").show();
            $("#credtable_row_1_save").hide();
            $("#" + inputprefix + "1").attr('disabled', 'disabled');
            $("#" + inputprefix + "2").attr('disabled', 'disabled');
            $("#" + inputprefix + "3").attr('disabled', 'disabled');
            $("#" + inputprefix + "4").attr('disabled', 'disabled');
        });
    }

    function del(row) {
        $("#progressbar").show();
        //send updates to server
        SubmitFormF("/api/deleteCredentials", {dbhost: "", dbname: "", dbuser: ""}, function error() {
            showMsg("Failed to delete,please try again later.");
        }, function success() {
            showMsg("Credential has been deleted.");
            $("#progressbar").hide();
            $("#credtable_row_" + row).remove();
        });
    }

</script>

