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
            <table class="table table-striped"> 
                <thead>               
                    <tr>                     
                        <th scope="col">#</th>           
                        <th scope="col">TITLE</th>       
                        <th scope="col">DATE</th>                  
                        <th scope="col">DESCRIPTION</th>                 
                        <th scope="col">RELATION</th>                    
                        <th scope="col">Operations</th>               
                    </tr>               
                </thead>           
                <tbody>                     
                    <tr id="credtable_row_194">   
                        <th scope="row" style="vertical-align: inherit;">1</th>     
                        <td style="vertical-align: inherit;padding: 0rem;">      
                            DatabaseInsights               
                        </td>                        
                        <td style="vertical-align: inherit;padding: 0rem;">                            
                            cd.kcs.akakanch.com            
                        </td>                        <td style="vertical-align: inherit;padding: 0rem;">                    
                            dbaccount                
                        </td>                      
                        <td style="vertical-align: inherit;padding: 0rem;">                            
                            Single Table
                        </td>                       
                        <td>   
                            <button id="credtable_row_194_edit" type="button" class="btn btn-primary" onclick="inspect(194)">DETAILS</button>                      
                            <button id="credtable_row_194_edit" type="button" class="btn btn-info" onclick="view(194)">View</button>                         
                            <button type="button" class="btn btn-danger" onclick="del(194)">Delete</button>                      
                        </td>                   
                    </tr>               
                </tbody>            
            </table>
        </div>
    </div>
</div>
<script>
    $('#reports').bootstrapMaterialDesign();
    
    function view(){
        
    }
    
    function del(){
        
    }
    
    function inspect(){
        
    }
</script>