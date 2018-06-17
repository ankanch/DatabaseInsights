<%-- 
    Document   : crosstableanalytics
    Created on : 2018-6-16, 12:12:18
    Author     : Miss Zhang
--%>
<%@page import="dbi.localization.lang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% lang local = lang.detectLang(request);%>
<div id="crosstableanalysis">
    <button type="button" class="btn btn-primary" onclick="writenewchart()">新建图表</button>
    <button type="button" style="float: right" class="btn btn-secondary" onclick="">生成报告</button>
    <div id="tablediv"></div>
</div>



<script src="static/oplib/jsinjsp/analytics.customize.js"></script>
<script>
        var iii = 1;
        var currentcol = 1;
        $('#crosstableanalysis').bootstrapMaterialDesign();
        function getDatabase(v) {
            GetDataF("/api/getDatabases", function error(data) {
                showMsg("Failed to load database list.");
            }, function success(data) {
                var list = data.data;
                var element = document.getElementById("showDatabase_" + v);
                var st = "<a class=\"dropdown-item\" onclick=\"getTable(ggggggggggggggggggg,hhhhhhhhhhh)\" id=\"iddb\">dbname</a>";
                var str = "";
                for (var i = 0; i < list.length; i++) {
                    str = str + st.replace("iddb", "iddb" + "_" + v + "_" + i).replace("dbname", list[i]).replace("ggggggggggggggggggg", v).replace("hhhhhhhhhhh", i);
                }
                element.innerHTML = str;
            });
        }

        function getTable(a, b) {
            var dbname = document.getElementById("iddb" + "_" + a + "_" + b).innerText;
            document.getElementById("buttonMenu_1_" + a).innerHTML = dbname;
            SubmitFormKVF("/api/getTables", {dbname: dbname}, function error(data) {
                showMsg("Failed to load table list.");
            }, function success(data) {
                var list = data.data;
                var element = document.getElementById("showTables_" + a);
                var st = "<a class=\"dropdown-item\"  onclick=\"getColumns(" + a + ",lllll)\" id=\"idcolumn\">dbname</a>";
                var str = "";
                for (var i = 0; i < list.length; i++) {
                    str = str + st.replace("idcolumn", "dbc_" + a + "_" + i).replace("dbname", list[i]).replace("lllll", i);
                }
                element.innerHTML = str;
            });
        }

        function getColumns(a, b) {
            console.log("dbc_" + a + "_" + b);
            document.getElementById("buttonMenu_2_" + a).innerHTML = document.getElementById("dbc_" + a + "_" + b).innerHTML;
            var columns = document.getElementById("showColumns_" + a);
            var dbname = document.getElementById("buttonMenu_1_" + a).innerHTML;
            var tbname = document.getElementById("buttonMenu_2_" + a).innerHTML;
            SubmitFormKVF("/api/GetColumns", {dbname: dbname, tbname: tbname, idname: a}, function error_func(data) {
                showMsg("Failed to load table field list.");
            }, function success_func(data) {
                columns.innerHTML = data.data[0];
            });
        }

        function addtoColumn(a, v) {
            var neirong = document.getElementById("col_" + a + "_" + v).innerHTML;
            document.getElementById("buttonMenu_3_" + a).innerHTML = neirong;
        }

        function addCol(a) {
            console.log("neirong_" + a);
            $("#neirong_" + a).html($("#neirong_" + a).html() + `<tr><th>Firstname</th><th>Lastname</th><th>Age</th></tr>`);
            $('.modal').modal('hide');
        }

        function deletecolumn(v) {
            var cell = document.getElementById("cnum_" + v.toString());
            var mainbody = document.getElementById("tablediv");
            mainbody.removeChild(cell);
        }

        function removeDiv(v) {
            var div = document.getElementById("coldiv_" + v.toString());
            div.remove();
        }

        function createNewchart(v) {
            console.log("current:" + v);
            currentcol = v;
            $('#myModal_' + v).modal();
        }

        function writenewchart() {
            var html = "<div class=\"card text-white bg-info mb-3\" style=\"max-width: 100%;\" id=\"coldiv_ssss\">" +
                    "        <div class=\"card-header bg-secondary\">" +
                    "            <div style=\"float: left;\">" +
                    "                <input type=\"text\" value=\"图表ssss\" style=\"width: 80%\" class=\"form-control text-white\" aria-label=\"Small\"  aria-describedby=\"inputGroup-sizing-sm\">" +
                    "            </div>" +
                    "            <div style=\"float: left;\">" +
                    "                <input type=\"text\" value=\"描述\" class=\"form-control text-white\" aria-label=\"Small\" aria-describedby=\"inputGroup-sizing-sm\">" +
                    "            </div>" +
                    "            <div class=\"dropdown\" style=\"float: left;\">" +
                    "                <button class=\"btn btn-secondary dropdown-toggle text-white\" type=\"button\" id=\"btn_selecttype_ssss\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
                    "                    数据类型" +
                    "                </button>" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">" +
                    "                    <a class=\"dropdown-item\" onclick=\"chooseNumber(ssss)\">数字</a>" +
                    "                    <a class=\"dropdown-item\" onclick=\"chooseBoolean(ssss)\">布尔型</a>" +
                    "                    <a class=\"dropdown-item\" onclick=\"chooseText(ssss)\">文本</a>" +
                    "                </div>" +
                    "            </div>" +
                    "" +
                    "            <div class=\"dropdown\" style=\"float: left;\" id=\"choosetype_ssss\">" +
                    "                <button class=\"btn btn-secondary dropdown-toggle text-white\" type=\"button\"  data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
                    "                    聚合函数" +
                    "                </button>" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\" >" +
                    "                    " +
                    "                </div>" +
                    "            </div>" +
                    "            <div style=\"float: left;\">" +
                    "                <button type=\"button\" class=\"btn btn-secondary text-white\" onclick=\"createNewchart(ssss)\">添加行</button>" +
                    "            </div>" +
                    "            <div style=\"float: left;\">" +
                    "                <button type=\"button\" class=\"btn btn-secondary text-white\" onclick=\"removeDiv(ssss)\">删除图表</button>" +
                    "            </div>" +
                    "        </div>" +
                    "" +
                    "        <div class=\"card-body bg-light\">" +
                    "               <table class=\"table table-striped text-dark\">" +
                    "                   <tbody id=\"neirong_ssss\">" +
                    "                   </tbody>" +
                    "        </table>" +
                    "        </div>" +
                    "    </div>";
            var yincang = `<div class="modal fade" id="myModal_ssss" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                       <div class="modal-dialog" role="document">
                           <div class="modal-content">
                               <div class="modal-header">
                                   添加新行
                               </div>
                               <div class="modal-body">
                                   <table class="table">
                                       <tbody>
                                           <tr>
                                               <td>
                                                   <div class="btn-group">
                                                       <button type="button" id="buttonMenu_1_ssss" onclick="getDatabase(ssss)" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                           select databases
                                                       </button>
                                                       <div class="dropdown-menu" id="showDatabase_ssss">
                                                       </div>
                                                   </div>
                                               </td>
                                           </tr>
                                           <tr>
                                               <td>
                                                   <div class="btn-group">
                                                       <button type="button" id="buttonMenu_2_ssss" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                           select tables
                                                       </button>
                                                       <div class="dropdown-menu" id="showTables_ssss">
                                                       </div>
                                                   </div>
                                               </td>
                                           </tr>
                                            <tr>
                                                <td>
                                                    <div class="btn-group">
                                                        <button type="button" id="buttonMenu_3_ssss" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            select column
                                                        </button>
                                                        <div class="dropdown-menu" id="showColumns_ssss">
                                                       </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                                    <button type="button" onclick="addCol(ssss)" id="btn_submit" class="btn btn-primary" ><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>添加</button>
                                </div>
                            </div>
                        </div>
                    </div>`;
            var zong = html + yincang;
            $("#tablediv").append(zong.replace(/ssss/g, iii));
            console.log("zong:" + zong);
            iii = iii + 1;
        }

        function chooseNumber(v) {
            document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "数字";
            document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf = "type_number";
            var element = document.getElementById("choosetype_" + v.toString());
            var str = "<button id=\"buttonnames_" + v.toString() + "\" class=\"btn btn-outline-secondary dropdown-toggle text-white\" type=\"button\" data-toggle=\"dropdown\"" +
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
            document.getElementById("buttonnames_" + x.toString()).dataset.poolf = v.dataset.poolf;
            document.getElementById("buttonnames_" + x.toString()).innerHTML = v.innerHTML;
        }

        function chooseText(v) {
            document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "文本";
            document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf = "type_text";
            var element = document.getElementById("choosetype_" + v);
            var str = "<button id=\"buttonnames_" + v.toString() + "\" class=\"btn btn-outline-secondary dropdown-toggle text-white\" type=\"button\" data-toggle=\"dropdown\"" +
                    "                aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\" data-poolf='pf_none'>无</button>" +
                    "                <div class=\"dropdown-menu\">" +
                    "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_none\">无</a>" +
                    "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_count\">计数</a>" +
                    "                <a class=\"dropdown-item\" href='javascript:' onclick='choosetypename(this," + v.toString() + ") ' data-poolf=\"pf_count_norepeat\">非重复计数</a>" +
                    "                </div>";

            element.innerHTML = str;
        }

        function chooseBoolean(v) {
            document.getElementById("btn_selecttype_" + v.toString()).innerHTML = "布尔值";
            document.getElementById("btn_selecttype_" + v.toString()).dataset.poolf = "type_boolean";
            var element = document.getElementById("choosetype_" + v.toString());
            var str = "<button class=\"btn btn-outline-secondary text-white\" type=\"button\" data-toggle=\"dropdown\"" +
                    "aria-haspopup=\"true\" aria-expanded=\"false\" style=\"border-style:none\" data-poolf=\"pf_none\" id=\"buttonnames_" + v.toString() + "\">无</button>";
            element.innerHTML = str;
        }
</script>
