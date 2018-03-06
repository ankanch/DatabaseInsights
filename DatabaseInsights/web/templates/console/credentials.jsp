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
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry</td>
                    <td>the Bird</td>
                    <td>@twitter</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</div>

