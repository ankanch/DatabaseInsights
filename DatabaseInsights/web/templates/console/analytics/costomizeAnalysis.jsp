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
            <button type="button" class="btn btn-raised btn-success" id="btn_start" onclick="startAnalyze()"  style="display:none">Start Analyze</button>
        </div>
    </div>
    <hr>
    <div id="accordion">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link console-text-button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        1.选择字段
                    </button>
                </h5>
            </div>
            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
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
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed console-text-button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        2.查看分析结果
                    </button>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                    <div class="alert alert-danger" role="alert" id="alarmcard">
                        请完成第一步后，再查看分析结果
                    </div>
                </div>
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

    function startAnalyze() {
        var rowcolnum = showRowcolnum() - 1;
        var fields = "";
        var type = "";
        var summary = "";
        var instructions = "";
        var lastfields = "";
        console.log("rowcolnum:" + rowcolnum.toString());
        for (var i = 1; i < rowcolnum + 1; i++) {
            lastfields = lastfields+document.getElementById("lastfield_" + i.toString()).value+",";
            fields = fields+document.getElementById("field_" + i.toString()).value+",";
            type = type+document.getElementById("btn_selecttype_" + i.toString()).dataset.poolf+",";
            summary = summary+document.getElementById("buttonnames_" + i.toString()).dataset.poolf+",";
            instructions = instructions+document.getElementById("instructions_" + i.toString()).value+",";
        }
        SubmitFormKVF("/api/servletGetCostomAnalyzeResult", {lastfields: lastfields, fields: fields, type: type, summary: summary, instructions: instructions}, function error_func(data) {
            console.log("error:" + lastfields);
            console.log("error:" + fields);
            console.log("error:" + type);
            console.log("error:" + summary);
            console.log("error:" + instructions);
        }, function success_func(data) {
            console.log("success:" + lastfields);
            console.log("success:" + fields);
            console.log("success:" + type);
            console.log("success:" + summary);
            console.log("success:" + instructions);
        });
    }

    function chooseNumber(v) {
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "数字";
        document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf="type_number";
        var element = document.getElementById("choosetype_" + v.toString());
        var str = "<button id=\"buttonnames_" + v.toString() + "\" class=\"btn btn-outline-secondary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\"" +
                "                aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\" data-poolf='pf_none'>无</button>" +
                "                <div class=\"dropdown-menu\">" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_none\">无</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_avg\" data-text='平均'>平均</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_count\">计数</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_count_norepeat\">非重复计数</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_max\">最大</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_min\">最小</a>" +
                "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_sum\">总和</a>" +
                "                </div>";

        element.innerHTML = str;
    }

    function choosetypename(v, x) {
        document.getElementById("buttonnames_" + x.toString()).dataset.poolf=v.dataset.poolf;
        document.getElementById("buttonnames_" + x.toString()).innerHTML = v.innerHTML;
    }

    function chooseText(v) {
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "文本";
        document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf="type_text";
        var element = document.getElementById("choosetype_" + v);
        var str = "<button class=\"btn btn-outline-secondary\" type=\"button\" data-toggle=\"dropdown\"" +
                "aria-haspopup=\"true\" data-poolf=\"pf_none\" aria-expanded=\"false\" style=\"border-style:none\" id=\"buttonnames_" + v.toString() + "\">无</button>";
        element.innerHTML = str;
    }

    function chooseBoolean(v) {
        document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "布尔值";
        document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf="type_boolean";
        var element = document.getElementById("choosetype_" + v.toString());
        var str = "<button class=\"btn btn-outline-secondary\" type=\"button\" data-toggle=\"dropdown\"" +
                "aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\" data-poolf=\"pf_none\" id=\"buttonnames_" + v.toString() + "\">无</button>";
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
            document.getElementById("btn_start").style.display = "";
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

    function showRowcolnum() {
        var tab = document.getElementById("columnstable");
        var rows = tab.rows.length;
        return rows;
    }

</script>

