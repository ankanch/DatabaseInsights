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
            <button type="button" class="btn btn-raised btn-primary" style="width:20%">显示日志</button>
        </div>
    </div>

    <div class="card">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry</td>
                    <td>the Bird</td>
                    <td>@twitter</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    $('#logs').bootstrapMaterialDesign();
</script>