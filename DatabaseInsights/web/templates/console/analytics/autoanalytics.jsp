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
        <div class="col-md-12">
            <div class="card">
                <div class="progress-line" id="progressbar" style="display:none;"></div>
                <h5 class="card-header" id="card_title">Auto Analytics</h5>
                <div class="card-body" id="chartslist">

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#analytics_auto').bootstrapMaterialDesign();

    var CHART_CONTAINER = `<div class="card">
                                <div class="card-body">
                                    <div id="@ID" style="width: 600px;height:400px;"></div>
                                </div>
                            </div>`;

    function initChartWithOption(id, option) {
        var myChart = echarts.init(document.getElementById(id));
        myChart.setOption(option);
    }

    function getDatabases() {
        GetDataF("/api/getDatabases", function error(data) {
            showMsg("Failed to load database list.");
        }, function success(data) {
            var list = data.data;
            var element = document.getElementById("showDatabase");
            var st = "<a class=\"dropdown-item\" onclick=\"getTables(this)\" id=\"iddb\">dbname</a>";
            var str = "";
            for (var i = 0; i < list.length; i++) {
                str = str + st.replace("iddb", list[i]).replace("dbname", list[i]);
            }
            element.innerHTML = str;
        });
    }

    function getTables(v) {
        var dbname = document.getElementById(v.id).innerText;
        document.getElementById("buttonMenu1").innerHTML = dbname;
        SubmitFormKVF("/api/getTables", {dbname: dbname}, function error(data) {
            showMsg("Failed to load table list.");
        }, function success(data) {
            var list = data.data;
            var element = document.getElementById("showTables");
            var st = "<a class=\"dropdown-item\" onclick=\"getCharts(this)\" href=\"#\" id=\"idcolumn\">dbname</a>";
            var str = "";
            for (var i = 0; i < list.length; i++) {
                str = str + st.replace("idcolumn", "dbc" + i).replace("dbname", list[i]);
            }
            element.innerHTML = str;
        });
    }

    function getCharts(v) {
        $("#card_title").text("Loading Auto Analytics");
        $("#chartslist").html("please wait while we loading your charts.");
        var tablename = document.getElementById(v.id).innerText;
        document.getElementById("buttonMenu2").innerHTML = tablename;
        var dbname = document.getElementById("buttonMenu1").innerText;

        //get pie charts
        SubmitFormKVF("/api/getAutoAnalysisResult", {dbname: dbname, tbname: tablename, chart: "pie"}, function error(data) {
            showMsg("Failed to load table list.");
            $("#card_title").text("Failed to load Auto Analytics,please try again later.");
        }, function success(data) {
            $("#card_title").text("Auto Analytics");
            $("#chartslist").html("");
            var pie = data.data;
            // data format-> row 1: chart count
            //               row 2: chart id list
            //               row 3: chart option list
            // generate chart 
            for (var i = 0; i < pie[0]; i++) {
                var chtid = pie[1][i];
                var chtcontainer = CHART_CONTAINER.replace("@ID", chtid);
                $("#chartslist").append(chtcontainer);
                option = eval(pie[2][i]);
                initChartWithOption(chtid, option);
            }
        });

        //get line charts

        //get histogram charts

    }

</script>
