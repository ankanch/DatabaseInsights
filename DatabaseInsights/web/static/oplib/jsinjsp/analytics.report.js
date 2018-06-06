/* 
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Jun 06 2018
 *
 * 该文件负责首先分析完成后，生成 保存/下载报告的fab按钮，以及开始分析到得到分析结果这一段的逻辑
 */

//报告生成后右下角的fab按钮
var CHART_CONTAINER_SAVE_REPORT = `<button type="button" class="btn btn-info bmd-btn-fab btn-raised console-floatbtn-rb-1" onclick="savereport()">
                                        <i class="material-icons">save</i><div class="ripple-container"></div>
                                       </button>
                                        <button type="button" class="btn btn-danger bmd-btn-fab console-floatbtn-rb-2" onclick="downloadreport()">
                                        <i class="material-icons">save_alt</i><div class="ripple-container"></div>
                                       </button>`;

// 用于下载生成的报告
function downloadreport() {

}

// 用于保存报告到数据库
function savereport() {
    // 所有报告信息都存放在 chartslist 中
    var noteslist = [];
    var optionlist = [];
    $("#chartslist > div > div > div.notes > textarea").each(function () {
        noteslist.push($(this).val());
    });
    $("#chartslist > div > div > div.option").each(function () {
        optionlist.push( $(this).html());
    });
    var data = [noteslist,optionlist];
    var chartsdata = DBIEX.toString(data);
    var repdata = encodeReport("","",chartsdata,"");
    
    // send to sevlet
    SubmitFormKVF("addReport", {repdata:repdata}, function error(data){
        showMsg(data.msg);
    }, function success(data){
        showMsg(data.msg);
    });

}


function encodeReport(title,des,chtdata,relations){
    return title + "<@REPSP!>" + des + "<@REPSP!>" + chtdata + "<@REPSP!>" + relations;
}