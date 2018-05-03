<%-- 
    Document   : costomizeAnalysis
    Created on : 2018-4-26, 15:32:04
    Author     : Miss Zhang
--%>

<%@page import="dbi.localization.lang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% lang local = lang.detectLang(request);%>
<div id="analytics_auto">
    <h2>costomize Analytics</h2>
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
                <div class="alert alert-danger" role="alert" id="alarmcard">
                    请选择数据库和表，选后可进行下一步操作
                </div>
                <table class="table table-striped" style="text-align:center;" id="columnstable">
                    <thead>
                        <tr>
                            <th scope="col">索引</th>
                            <th scope="col">字段</th>
                            <th scope="col">类型</th>
                            <th scope="col">汇总</th>
                            <th scope="col">说明</th>
                        </tr>
                    </thead>
                    <tbody id="DBtbody">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    $("#columnstable").hide();
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

    function chooseNumber(v) {
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "数字";
        var element = document.getElementById("choosetype_" + v.toString());
        var str = "<button id=\"buttonnames_" + v.toString() + "\" class=\"btn btn-outline-secondary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\"" +
                "                aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\">无</button>" +
                "                <div class=\"dropdown-menu\">" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('无'," + v.toString() + ") \">无</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('平均'," + v.toString() + ") \">平均</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('计数'," + v.toString() + ") \">计数</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('非重复计数'," + v.toString() + ") \">非重复计数</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('最大'," + v.toString() + ") \">最大</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('最小'," + v.toString() + ") \">最小</a>" +
                "                <a class=\"dropdown-item\" href=\"javascript:choosetypename('总和'," + v.toString() + ") \">总和</a>" +
                "                </div>";

        element.innerHTML = str;
    }

    function choosetypename(v, x) {
        document.getElementById("buttonnames_" + x.toString()).innerHTML = v;
    }

    function chooseText(v) {
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "文本";
        var element = document.getElementById("choosetype_" + v);
        var str = "<button class=\"btn btn-outline-secondary\" type=\"button\" data-toggle=\"dropdown\"" +
                "aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\">无</button>";
        element.innerHTML = str;
    }

    function chooseBoolean(v) {
        console.log("btn_selecttype_" + v.toString());
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "布尔值";
        var element = document.getElementById("choosetype_" + v.toString());
        var str = "<button class=\"btn btn-outline-secondary\" type=\"button\" data-toggle=\"dropdown\"" +
                "aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\">无</button>";
        element.innerHTML = str;
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

    function getCharts(v) {
        var tbname = document.getElementById(v.id).innerText;
        document.getElementById("buttonMenu2").innerHTML = tbname;
        var dbname = document.getElementById("buttonMenu1").innerHTML;
        
            $("#alarmcard").hide();
            $("#columnstable").show();

        SubmitFormKVF("/api/GetTableFields", {dbname: dbname, tbname: tbname}, function error_func(data) {
            showMsg("Failed to load table field list.");
        }, function success_func(data) {
            var element = document.getElementById("DBtbody");
            element.innerHTML = data.data[0];
            console.log(data.data);
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

</script>

