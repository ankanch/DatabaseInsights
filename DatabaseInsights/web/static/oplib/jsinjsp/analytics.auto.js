/* 
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Jun 06 2018
 *
 * 存放 autoanalysis.jsp专用的js代码
 */


// 图表容器，用来存放图表和注释
var CHART_CONTAINER = `<div class="card">
                <div class="card-body">
                    <div id="@ID" style="width: 800px;height:400px;display:inline-block;"></div>
                    <div id="@ID_note" style="width:200px;height:400px;display:inline-block;">
                        <textarea class="form-control" id="exampleTextarea" rows="14" placeholder="write notes here if you want to add more information about this chart."></textarea>
                    </div>
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
    showMsg("Loading charts...");
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
    SubmitFormKVF("/api/getAutoAnalysisResult", {dbname: dbname, tbname: tablename, chart: "line"}, function error(data) {
        showMsg("Failed to load table list.");
        // $("#card_title").text("Failed to load Auto Analytics,please try again later.");
    }, function success(data) {
        var line = data.data;
        // data format-> row 1: chart count
        //               row 2: chart id list
        //               row 3: chart option list
        // generate chart 
        for (var i = 0; i < line[0]; i++) {
            var chtid = line[1][i];
            var chtcontainer = CHART_CONTAINER.replace("@ID", chtid);
            $("#chartslist").append(chtcontainer);
            option = eval(line[2][i]);
            initChartWithOption(chtid, option);
        }
    });
    //get histogram charts
    
    
    // show report options
    $("#chartslist").append(CHART_CONTAINER_SAVE_REPORT);

}

