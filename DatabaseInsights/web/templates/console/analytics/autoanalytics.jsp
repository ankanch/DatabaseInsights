<%-- 
    Document   : autoanalytics
    Created on : Apr 16, 2018, 8:26:09 PM
    Author     : kanch
--%>

<%@page import="dbi.localization.lang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% lang local = lang.detectLang(request);%>
<div id="analytics_auto">
    <h2>Auto Analytics</h2>
    <div class="card">
        <div class="card-body">
            <button type="button" class="btn" disabled style="color:#4e4e52;">Select your database and table to start</button>
            <div class="btn-group">
                <button class="btn dropdown-toggle" type="button" id="buttonMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Select Database
                </button>
                <div class="dropdown-menu" aria-labelledby="buttonMenu1">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </div>
            <div class="btn-group">
                <button class="btn dropdown-toggle" type="button" id="buttonMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Select Table
                </button>
                <div class="dropdown-menu" aria-labelledby="buttonMenu1">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-8">
            <div class="card">
                <h5 class="card-header">Recently Auto Analytics</h5>
                <div class="card-body">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">List group item heading</h5>
                                <small>3 days ago</small>
                            </div>
                            <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                            <small>Donec id elit non mi porta.</small>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">List group item heading</h5>
                                <small class="text-muted">3 days ago</small>
                            </div>
                            <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                            <small class="text-muted">Donec id elit non mi porta.</small>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">List group item heading</h5>
                                <small class="text-muted">3 days ago</small>
                            </div>
                            <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
                            <small class="text-muted">Donec id elit non mi porta.</small>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    How To
                </div>
                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#analytics_auto').bootstrapMaterialDesign();

    function getDatabases() {
        GetDataF("/api/getDatabases", function error() {
            showMsg("Failed to load database list.");
        }, function success() {

        });
    }

    function getTables() {
        GetDataF("/api/getTables", function error() {
            showMsg("Failed to load table list.");
        }, function success() {

        });
    }

</script>
