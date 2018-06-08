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
        <div class="progress-line" id="progressbar" style="display:none;"></div>
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
        <div class="col-md-12">
            <div class="card">
                <h5 class="card-header" id="card_title">Auto Analytics</h5>
                <div class="card-body" id="chartslist">

                </div>
            </div>
        </div>
    </div>
</div>

<script src="static/oplib/jsinjsp/analytics.auto.js"></script>
<script src="static/oplib/jsinjsp/analytics.chart.instance.js"></script>
<script src="static/oplib/jsinjsp/analytics.report.js"></script>
<script>
    $('#analytics_auto').bootstrapMaterialDesign();
</script>
