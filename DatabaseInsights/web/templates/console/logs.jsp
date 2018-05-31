<%-- 
    Document   : logs
    Created on : Mar 4, 2018, 8:50:39 PM
    Author     : kanch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="logs">
    <div class="card">
        <div class="card-body">
            <button type="button" class="btn btn-raised btn-primary" style="width:20%">导出为csv文件</button>
            <button class="btn btn-raised btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                6个月
            </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#" onclick="chooseTime(this)">7天</a>
                <a class="dropdown-item" href="#" onclick="chooseTime(this)">1个月</a>
                <a class="dropdown-item" href="#" onclick="chooseTime(this)">6个月</a>
                <a class="dropdown-item" href="#" onclick="chooseTime(this)">1年</a>
                <a class="dropdown-item" href="#" onclick="chooseTime(this)">全部</a>
              </div>
           
        </div>
    </div>

    <div class="card">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">日志日期</th>
                    <th scope="col">日志类型</th>
                    <th scope="col">相关数据库</th>
                    <th scope="col">相关表</th>
                    <th scope="col">日志内容</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry</td>
                    <td>the Bird</td>
                    <td>@twitter</td>
                    <td>@fat</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    $('#logs').bootstrapMaterialDesign();
    
    function chooseTime(v){
        var element = v.innerText;
        document.getElementById("dropdownMenuButton").innerHTML=element;
    }
</script>