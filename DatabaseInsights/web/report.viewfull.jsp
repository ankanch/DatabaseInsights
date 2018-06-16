<%-- 
    Document   : report.viewfull
    Created on : Jun 8, 2018, 11:01:50 AM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Report - Database Insights</title>
        <%@ include file="templates/globehead.html" %>
        <script src="static/echarts.min.js"></script>
        <script src="static/oplib/jsinjsp/report.viewfull.js"></script>
        <script src="static/oplib/jsinjsp/analytics.chart.instance.js"></script>
        <script src="static/oplib/jsinjsp/analytics.report.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <style>
            textarea{
                margin-top: 35px;
                height:85%;
            }
        </style>
    </head>
    <body>
        <hr/><button type="button" class="btn btn-info bmd-btn-fab btn-raised console-floatbtn-rb-1" onclick="window.location.reload(true);">
            <i class="material-icons">refresh</i><div class="ripple-container"></div>
        </button>
        <button type="button" class="btn btn-danger bmd-btn-fab console-floatbtn-rb-2" onclick="downloadreport()">
            <i class="material-icons">save_alt</i><div class="ripple-container"></div>
        </button>
        <div  class="card" id="conteiner" style="padding-bottom:70px;">
            <div class="progress-line" id="progressbar" style="display:none;"></div>
            <div class="card-body" id="report_content">
                <div class="notes" style="width:90%;margin-top:28px;margin: 0 auto;">
                    <input type="text" class="form-control console-reporttitle-input" id="report_title" placeholder="Add report title here." style="text-align: center;font-size: 3rem;font-weight: 600;">
                    <textarea class="form-control" id="report_des" rows="4" placeholder="Write some description about this report." style="font-size: 1.1rem;"></textarea>
                </div>
                <div id="charts">
                </div>
            </div>
        </div>
    </body>
    <script>
        $('#conteiner').bootstrapMaterialDesign();
    </script>
</html>
