<%-- 
    Document   : reports
    Created on : Mar 4, 2018, 7:37:03 PM
    Author     : kanch
--%>

<%@page import="dbi.localization.lang"%>
<%@page import="dbi.localization.langID"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% lang local = lang.detectLang(request);%>
<div id="reports">

    <h1>Reports</h1>
    <div class="card">
        <div class="progress-line" id="progressbar" style="display:none;"></div>
        <div class="card-body">
            <div class="row">
                <form class="form-inline my-2 my-lg-0 col-md-5">
                    <div class="form-group console-div-vc">
                        <label for="searchcre" class="bmd-label-floating"><%=local.getString(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SEARCH)%></label>
                        <input type="text" class="form-control" id="searchcre">
                    </div>
                    <span class="form-group bmd-form-group console-div-vc"> <!-- needed to match padding for floating labels -->
                        <button type="submit" class="btn btn-primary"><%=local.getString(langID.JSP_CONSOLE_CREDENTIAL_MGR_BUTTON_SEARCH)%></button>
                    </span>
                </form>
            </div>
            <hr/>
            <div id="reportscontainer">
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Report Details</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p class="console-report-details-note">Title:<span class="console-report-details-text" id="modelinfo_title">111</span></p>
                    <p class="console-report-details-note">Created on:<span class="console-report-details-text" id="modelinfo_created">111</span></p>
                    <p class="console-report-details-note">Description:<span class="console-report-details-text" id="modelinfo_des">111</span></p>
                    <p class="console-report-details-note">Relations:<span class="console-report-details-text" id="modelinfo_ralation">111</span></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">View Full Report</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="static/oplib/jsinjsp/report.manager.js"></script>
<script src="static/oplib/jsinjsp/analytics.chart.instance.js"></script>
<script>
    $('#reports').bootstrapMaterialDesign();
    getReports();
</script>