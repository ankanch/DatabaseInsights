<%-- 
    Document   : credentials
    Created on : Mar 4, 2018, 8:48:37 PM
    Author     : kanch
--%>

<%@page import="dbi.utils.DBIResultSet"%>
<%@page import="dbi.mgr.credential.CredentialManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="credential" class="dbi.mgr.credential.CredentialBean" scope="page"/>
<jsp:setProperty name="credential" property="sessionID" value="<%=request.getSession().getId()%>"/>
<jsp:getProperty name="credential" property="dbresult" /> 
<div id="credentials">
    <h2>Credentials</h2>
    <div class="card">
        <div class="progress-line" id="progressbar"></div>
        <div class="card-body" id="credentialscard">
            <div class="row">
                <div class="col-md-4 console-div-text"><p class="console-vc bmd-form-group">You have <span class="" id="cre_count"><jsp:getProperty name="credential" property="dbaccount" /> </span> database credentials in total.</p></div>
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
            </div>
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
                    <jsp:getProperty name="credential" property="certifications" />
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
</div>
<script>
    $('#credentials').bootstrapMaterialDesign();
    $("#progressbar").hide();

    function edit(row) {
        var inputprefix = "credtable_row_" + row + "_col_";
        $("#credtable_row_"+row+"_edit").hide();
        $("#credtable_row_"+row+"_save").show();
        $("#" + inputprefix + "1").removeAttr('disabled');
        $("#" + inputprefix + "2").removeAttr('disabled');
        $("#" + inputprefix + "3").removeAttr('disabled');
        $("#" + inputprefix + "4").removeAttr('disabled');
    }

    function save(row) {
        $("#progressbar").show();
        
        var dbhost = $("#credtable_row_" + row + "_col_2").val();
        var dbname = $("#credtable_row_" + row + "_col_1").val();
        var dbpwd = $("#credtable_row_" + row + "_col_4").val();
        //send updates to server
        SubmitFormKVF("/api/updateCredential", {cid:row,dbhost: dbhost, dbname: dbname,dbpwd: dbpwd}, function error() {
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
        SubmitFormKVF("/api/deleteCredentials", {cid: row}, function error() {
            showMsg("Failed to delete,please try again later.");
        }, function success() {
            showMsg("Credential has been deleted.");
            $("#progressbar").hide();
            $("#credtable_row_" + row).remove();
        });
    }

</script>

