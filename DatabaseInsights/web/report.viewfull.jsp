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
    </head>
    <body>
        <div  class="card" id="conteiner" style="padding-bottom:70px;">
            <div class="progress-line" id="progressbar" style="display:none;"></div>
            <div class="card-body" id="report_content">
                <div class="notes" style="width:1000px;display:inline-block;margin-top:28px;">
                    <input type="text" class="form-control console-reporttitle-input" id="report_title" placeholder="Add report title here." style="text-align: center;font-size: 3rem;font-weight: 600;" readonly>
                    <textarea class="form-control" id="report_des" rows="4" placeholder="Write some description about this report." style="font-size: 1.1rem;" readonly></textarea>
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
