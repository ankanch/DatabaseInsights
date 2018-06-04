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
</div>
<script>
    $('#reports').bootstrapMaterialDesign();
    
    function getReports(){
        GetDataF("getReport?gtype=1",function error(data){
           showMsg("loading error :" + data.msg); 
        }, function success(data){
            $("#reportscontainer").html(data.data[0]);
        });
    }
    
    function view(){
        
    }
    
    function del(){
        
    }
    
    function inspect(){
        
    }
    
    getReports();
</script>