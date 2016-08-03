<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.6/css/jquery.dataTables.min.css"/>-->
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.6/css/jquery.dataTables.min.css"/>-->
    <%--<link rel="stylesheet" type="text/css"
          href="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.css"/>--%>
    <link href="/plugins/DataTables/css/data-table.css" rel="stylesheet" />
    <script src="http://cdn.gbtags.com/datatables/1.10.5/js/jquery.dataTables.min.js"></script>
    <!--<script src="dt.js"></script>-->
    <script type="text/javascript" language="javascript"
            src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script src="/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

    <script src="/plugins/bootstrap-datetimepicker/js/handlebars-v3.0.1.js"></script>

</head>
<body>
<!-- begin panel -->
<div class="panel panel-inverse">
    <div class="panel-heading">
        <div class="panel-heading-btn">
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand">
                <i class="fa fa-expand"></i>
            </a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload">
                <i class="fa fa-repeat"></i>
            </a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse">
                <i class="fa fa-minus"></i>
            </a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove">
                <i class="fa fa-times"></i>
            </a>
        </div>
        <h4 class="panel-title">店铺维护</h4></div>
    <div class="alert alert-info fade in">
        <button type="button" class="close" data-dismiss="alert">
            <span aria-hidden="true">&times;</span></button>您可以在此表单上，管理平台店铺.....该功能将直接影响到用户店铺，请谨慎维护管理</div>
    <div class="panel-body">
        <div class="table-responsive" >
            <table id="example" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>卖家名称</th>
                    <th>卖家ID</th>
                    <th>店铺名称</th>
                    <th>行业类别</th>
                    <th>店铺签名</th>
                    <th>店铺描述</th>
                    <th>店铺创建时间</th>
                    <th>状态码</th>
                    <th>店铺状态</th>
                    <th>操作</th></tr>
                </thead>
                <tbody></tbody>
                <!-- tbody是必须的 --></table>
        </div>
        <!-- Button trigger modal -->
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">新增</h4></div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input type="text" class="form-control" id="id" placeholder="编号"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="userName" placeholder="卖家名称"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="userId" placeholder="卖家ID"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="storeName" placeholder="店铺名称"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="industryName" placeholder="行业类别"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="signature" placeholder="店铺签名"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="storeDesc" placeholder="店铺描述"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="createDate" placeholder="店铺创建时间" data-date-format="yyyy/mm/dd"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="statusCode" placeholder="状态码"></div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="statusName" placeholder="店铺状态"></div>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-info" id="initData">添加模拟数据</button>--%>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="save">保存</button></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end panel -->
<!--定义操作列按钮模板-->
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

<script>
    var table;
    var editFlag = false;
    $(function () {

        $('#start_date').datetimepicker();

        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);

        table = $('#example').DataTable({
             ajax: {
             //url: "/dataTable/list.jsp"
             url:HOMEADRESS+"storeRegulatory/getStoreInfo",
             contentType: "application/json; charset=utf-8",
             dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
             type:"post", //传参方式，get 或post
             },
            //"ajax":"/rest/storeRegulatory/getStoreInfo",

            serverSide: true,
            columns: [
                {"data": "id"},
                {"data": "userName"},
                {"data": "userId"},
                {"data": "storeName"},
                {"data": "industryName"},
                {"data": "signature"},
                {"data": "storeDesc"},
                {"data": "createDate"},
                {"data": "statusCode"},
                {"data": "statusName"},
                {"data": null}
            ],
            columnDefs: [
                {
                    targets: 10,
                    render: function (a, b, c, d)
                    {
                        var closeBtnDesc="封店";
                        var closeFlag=true;
                        if(c.statusCode=='close')
                        {
                            closeBtnDesc="解封";
                            closeFlag=false;
                        }
                        var context =
                        {
                            func: [
                                {"name": "修改", "fn": "edit(\'" + c.id + "\',\'" + c.userName + "\',\'" + c.userId + "\',\'" + c.storeName + "\',\'" + c.industryName + "\',\'"+ c.signature + "\',\'"+ c.storeDesc + "\',\'"+ c.createDate + "\',\'"+ c.statusCode + "\',\'" + c.statusName + "\')", "type": "primary"},
                                {"name": "删除", "fn": "del(\'" + c.id + "\')", "type": "danger"},
                                {"name": closeBtnDesc, "fn": "closeShop(\'" + c.id +  "\',\'" + closeFlag +"\')", "type": "danger"}
                            ]
                        };
                        var html = template(context);
                        return html;
                    }
                }

            ],
            "language": {
                "lengthMenu": "_MENU_ 条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页"
                }
            },
            "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
            "t" +
            "<'row'<'col-xs-6'i><'col-xs-6'p>>",
            initComplete: function () {
               /* $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
                $("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
                $("#datainit").on("click", initData);*/
            }

        });

        $("#save").click(add);

        $("#initData").click(initSingleData);

    });

    /**
     * 初始化基础数据
     */
    function initData() {
        var flag = confirm("本功能将添加数据到数据库，你确定要添加么？");
        if (flag) {
            $.get("/objects.txt", function (data) {
                var jsondata = JSON.parse(data);
                $(jsondata.data).each(function (index, obj) {
                    ajax(obj);
                });
            });
        }
    }

    /**
     * 初始化基础数据
     */
    function initSingleData() {
        $("#id").val("");
        $("#userName").val("");
        $("#userId").val("");
        $("#storeName").val("");
        $("#industryName").val("");
        $("#signature").val("");
        $("#storeDesc").val("");
        $("#createDate").val("");
        $("#statusCode").val("");
        $("#statusName").val("");
    }

    /**
     * 清除
     */
    function clear() {
        $("#id").val(""),
        $("#userName").val(""),
        $("#userId").val(""),
        $("#storeName").val(""),
        $("#industryName").val(""),
        $("#signature").val(""),
        $("#storeDesc").val(""),
        $("#createDate").val(""),
        $("#statusCode").val(""),
        $("#statusName").val(""),
        editFlag = false;
    }

    /**
     * 添加数据
     **/
    function add() {
        var addJson = {
            "id":               $("#id").val(),
            "userName":         $("#userName").val(),
            "userId":           $("#userId").val(),
            "storeName":        $("#storeName").val(),
            "industryName":     $("#industryName").val(),
            "signature":        $("#signature").val(),
            "storeDesc":        $("#storeDesc").val(),
            "createDate":       $("#createDate").val(),
            "statusCode":       $("#statusCode").val(),
            "statusName":       $("#statusName").val()
        };

        ajax(addJson);
    }

    /**
     *编辑方法
     **/
    function edit(id,userName,userId,storeName,industryName,signature,storeDesc,createDate,statusCode,statusName) {
        editFlag = true;
        $("#myModalLabel").text("修改");
        $("#id").val(id).attr("disabled",true);
        $("#userName").val(userName);
        $("#userId").val(userId);
        $("#storeName").val(storeName);
        $("#industryName").val(industryName);
        $("#signature").val(signature);
        $("#storeDesc").val(storeDesc);
        $("#createDate").val(createDate);
        $("#statusCode").val(statusCode);
        $("#statusName").val(statusName);

        $("#myModal").modal("show");
    }

    function ajax(obj) {
        var url;// =HOMEADRESS+"storeRegulatory/logicallyDeleted";
        if(editFlag){
            console.log(obj);
            url =HOMEADRESS+"storeRegulatory/updateStoreInfo";
        }
        $.ajax({
            url:url ,
            contentType: "application/json; charset=utf-8",
            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
            type:"post", //传参方式，get 或post
            data: JSON.stringify({
                "id":               obj.id,
                "userName":         obj.userName,
                "userId":           obj.userId,
                "storeName":        obj.storeName,
                "industryName":     obj.industryName,
                "signature":        obj.signature,
                "storeDesc":        obj.storeDesc,
                "createDate":       obj.createDate,
                "statusCode":       obj.statusCode,
                "statusName":       obj.statusName
            }), success: function (data) {
                table.ajax.reload();
                $("#myModal").modal("hide");
                $("#myModalLabel").text("新增");
                clear();
                console.log("结果" + data);
            }
        });
    }


    /**
     * 删除数据
     * @param name
     */
    function del(id) {
        $.ajax({
            url :HOMEADRESS+"storeRegulatory/logicallyDeleted",
            contentType: "application/json; charset=utf-8",
            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
            type:"post", //传参方式，get 或post
            data: JSON.stringify({
                "id": id
            }), success: function (data) {
                table.ajax.reload();
                console.log("删除成功" + data);
            }
        });
    }

    /**
     * 关闭禁用店铺
     * @param name
     */
    function closeShop(id,closeFlag) {

        $.ajax({
            url :HOMEADRESS+"storeRegulatory/closeStore",
            contentType: "application/json; charset=utf-8",
            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
            type:"post", //传参方式，get 或post
            data: JSON.stringify({
                "id": id,
                "closeFlag":closeFlag
            }), success: function (data) {
                table.ajax.reload();
            }
        });
    }

</script>
</body>
</html>


