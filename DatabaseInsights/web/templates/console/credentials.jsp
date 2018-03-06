<%-- 
    Document   : credentials
    Created on : Mar 4, 2018, 8:48:37 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Credentials</h2>
<div class="card mb-5">
    <div class="card-body">
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
                    <th scope="col">Account</th>
                    <th scope="col">Password</th>
                    <th scope="col">Operations</th>
                </tr>
            </thead>
            <tbody>
                <tr id="credtable_row_">
                    <th scope="row" style="vertical-align: inherit;">1</th>
                    <td id="credtable_row__col_1" style="vertical-align: inherit;padding: 0rem;">
                        <input class="console-table-input" type="text" name="dbname">
                    </td>
                    <td id="credtable_row__col_2" style="vertical-align: inherit;padding: 0rem;">
                        <input class="console-table-input" type="text" name="dbaccount">
                    </td>
                    <td id="credtable_row__col_3" style="vertical-align: inherit;padding: 0rem;">
                        <input class="console-table-input" type="password" name="dbpassword">
                    </td>
                    <td>
                        <button id="credtable_row__edit" type="button" class="btn btn-secondary">Edit</button>
                        <button id="credtable_row__save" type="button" class="btn btn-success">Save</button>
                        <button type="button" class="btn btn-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</div>

