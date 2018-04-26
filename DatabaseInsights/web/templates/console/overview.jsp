<%-- 
    Document   : overview.jsp
    Created on : Mar 4, 2018, 7:08:43 PM
    Author     : kanch
--%>

<%@page import="dbi.utils.DBIResultSet"%>
<%@page import="dbi.localization.lang"%>
<%@page import="java.util.HashMap"%>
<%@page import="dbi.mgr.user.UserManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserManager um1 = new UserManager();
    HashMap<String, String> hm = um1.getUserInfo(lang.detectLang(request),session.getId());
    String databaseHtml="";
%>
<style>
    .div-a{ float:left;width:36rem;height:20rem;} 
    .div-b{ float:right;width:24rem;height:20rem;margin-left: 1.5rem;} 
    .div-c{ float:left;width:25.7rem;height:20rem;margin-top: 2rem;margin-bottom: 5rem;} 
    .div-d{ float:right;width:34.3rem;height:20rem;margin-left: 1.5rem;margin-top: 2rem;margin-bottom: 5rem;} 
</style>
<div class="card div-a">
  <div class="card-header">
    <h5><font size="5">Account Info</font></h5>
  </div>
    
  <div class="card-body">
    <div class="d-flex w-100 justify-content-between">
      <h5>User Name:</h5>
    </div>
    <p ><%= hm.get("USERNAME")%></p>
    <div class="d-flex w-100 justify-content-between">
        <h5>Last Login</h5>
    </div>
    <p ><%=hm.get("LASTLOGIN")%></p>
  </div>
</div>

<div class="card div-b" >
  <div class="card-header">
    <h5><font size="5">Database Info</font></h5>
  </div>
  <div class="card-body">
    <%
        StringBuilder a=new StringBuilder();
        String html="<div class=\"d-flex w-100 justify-content-between\">"+
      "<h5 class=\"mb-1\">%s</h5>"+
      "<small class=\"text-muted\">3 days ago</small>"+
       "</div>"+
       "<p class=\"mb-1\">%s</p>";
        DBIResultSet database=um1.getUserDatabases(session.getId());
        DBIResultSet databasehost=um1.getUserDatabasehost(session.getId());
        for(int i=0;i<database.rowCount();i++){
            a.append(String.format(html,database.getRow(i+1).get(0),databasehost.getRow(i+1).get(0)));   
        }
        databaseHtml=a.toString();
    %>
    <%=databaseHtml%>
  </div>
</div>

<div class="card div-c" style="clear:both">
  <div class="card-header">
      <h5><font size="5">Database Insights News</font></h5>
  </div>
  <div class="card-body">
    <p class="card-title">用户名：</p>
    <p class="card-text">test_test</p>
  </div>
</div>

<div class="card div-d">
  <div class="card-header">
    <h5><font size="5">Recent Activities</font></h5>
  </div>
  <div class="card-body">
    <p class="card-title">用户名：</p>
    <p class="card-text">test_test</p>
  </div>
</div>



<script>
</script>