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
                <button class="btn dropdown-toggle" type="button" id="buttonMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                        onclick="getDatabases()">
                    Select Database
                </button>
                <div class="dropdown-menu" aria-labelledby="buttonMenu1" id="showDatabase">

                </div>
            </div>
            <div class="btn-group">
                <button class="btn dropdown-toggle" type="button" id="buttonMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                       >
                    Select Table
                </button>
                <div class="dropdown-menu" aria-labelledby="buttonMenu2" id="showTables">
                    
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
        GetDataF("/api/getDatabases", function error(data) {
            showMsg("Failed to load database list.");
        }, function success(data) {
            var list=data.data;
            var element=document.getElementById("showDatabase");
            var st="<a class=\"dropdown-item\" onclick=\"getTables(this)\" id=\"iddb\">dbname</a>";
            var str=""
            for(var i=0;i<list.length;i++){
               str=str+st.replace("iddb",list[i]).replace("dbname",list[i]);
            }
            element.innerHTML=str;
        });
    }

    function getTables(v) {
        var dbname= document.getElementById(v.id).innerText;
        document.getElementById("buttonMenu1").innerHTML=dbname;
        SubmitFormKVF("/api/getTables",{dbname:dbname}, function error(data) {
            showMsg("Failed to load table list.");
        }, function success(data) {
            var list=data.data;
            var element=document.getElementById("showTables");
            var st="<a class=\"dropdown-item\" onclick=\"getTablename(this)\" href=\"#\" id=\"idcolumn\">dbname</a>";
            var str=""
            for(var i=0;i<list.length;i++){
               str=str+st.replace("idcolumn","dbc"+i).replace("dbname",list[i]);
            }
            element.innerHTML=str;
        });
    }
    
    function getTablename(v){
        var tablename= document.getElementById(v.id).innerText;
        document.getElementById("buttonMenu2").innerHTML=tablename;
        var dbname= document.getElementById("buttonMenu1").innerText;
        SubmitFormKVF("/api/getAutoAnalysisResult",{dbname: dbname, tbname: tablename, chart: "pie"}, function error(data) {
            showMsg("Failed to load table list.");
        }, function success(data) {
            
        });
    }

</script>
