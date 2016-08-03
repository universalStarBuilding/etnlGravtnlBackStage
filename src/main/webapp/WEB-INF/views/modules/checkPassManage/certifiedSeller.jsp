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
        <h4 class="panel-title">企业认证审批</h4></div>
    <div class="alert alert-info fade in">
        <button type="button" class="close" data-dismiss="alert">
            <span aria-hidden="true">&times;</span></button>您可以在此表单上，审核企业入驻平台开店的资格.....该功能将直接影响到用户店铺，请谨慎维护管理</div>
        <div class="panel-body">
            <%--<div class="table-responsive" >--%>
               <table id="example" class="table table-striped table-bordered display nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>资质认证申请书图片</th>
                            <th>营业执照图片</th>
                            <th>审核状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                    <!-- tbody是必须的 -->
                </table>
            <%--</div>--%>
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
                                <input type="text" class="form-control" id="applyPic" placeholder="资质认证申请书图片"></div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="busiPic" placeholder="营业执照图片"></div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="auth" placeholder="审核状态"></div>
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
    <button type="button" class="btn btn-{{this.type}} btn-sm"  style="margin: 2px" onclick="{{this.fn}}">{{this.name}}</button>
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
             url:HOMEADRESS+"storeRegulatory/getCertifiedSellerInfo",
             contentType: "application/json; charset=utf-8",
             dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
             type:"post", //传参方式，get 或post
             },
            //"ajax":"/rest/storeRegulatory/getStoreInfo",
            "scrollY": 400,
            "scrollX": true,
            serverSide: true,
            columns: [
                {"data": "id"},
                {"data": "applyPic"},
                {"data": "busiPic"},
                {"data": "auth"},
                {"data": null}
            ],
            columnDefs: [
                {
                    targets: 4,
                    render: function (a, b, c, d)
                    {

                        var context =
                        {
                            func: [
                                {"name": "修改", "fn": "edit(\'" + c.id + "\',\'" + c.applyPic + "\',\'" + c.busiPic + "\',\'" + c.auth + "\')", "type": "primary"},
                                {"name": "删除", "fn": "del(\'" + c.id + "\')", "type": "danger"},
                                {"name": "审批通过", "fn": "certifiedSeller(\'" + c.id +  "\',\'" + '1' +"\')", "type": "danger"},
                                {"name": "审批拒绝", "fn": "certifiedSeller(\'" + c.id +  "\',\'" + '2' +"\')", "type": "danger"}
                            ]
                        };
                        var html = template(context);
                        return html;
                    }
                },
                {
                    targets: 2,
                    render: function (a, b, c, d)
                    {
                        var imgSrc = FILESSERVERURL;
                        if(c.busiPic)
                        {
                            html=html+c.busiPic;
                        }
                        var html='<img src="'+imgSrc+'" alt="">'
                        return html;
                    }
                } ,
                {
                    targets: 1,
                    render: function (a, b, c, d)
                    {
                        var imgSrc = FILESSERVERURL;
                        if(c.applyPic)
                        {
                            html=html+c.applyPic;
                        }
                        var html='<img src="'+imgSrc+'" alt="">'
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
        $("#applyPic").val("");
        $("#busiPic").val("");
        $("#auth").val("");

    }

    /**
     * 清除
     */
    function clear() {
        $("#id").val("");
        $("#applyPic").val("");
        $("#busiPic").val("");
        $("#auth").val("");
        editFlag = false;
    }

    /**
     * 添加数据
     **/
    function add() {
        var addJson = {
            "id":               $("#id").val(),
            "applyPic":         $("#applyPic").val(),
            "busiPic":           $("#busiPic").val(),
            "auth":        $("#auth").val()
        };

        ajax(addJson);
    }

    /**
     *编辑方法
     **/
    function edit(id,applyPic,busiPic,auth) {
        editFlag = true;
        $("#myModalLabel").text("修改");
        $("#id").val(id).attr("disabled",true);
        $("#applyPic").val(applyPic);
        $("#busiPic").val(busiPic);
        $("#auth").val(auth);

        $("#myModal").modal("show");
    }

    function ajax(obj) {
        var url;// =HOMEADRESS+"storeRegulatory/logicallyDeleted";
        if(editFlag){
            console.log(obj);
            url =HOMEADRESS+"storeRegulatory/certifiedSellerUpdate";
        }
        $.ajax({
            url:url ,
            contentType: "application/json; charset=utf-8",
            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
            type:"post", //传参方式，get 或post
            data: JSON.stringify({
                "id":               obj.id,
                "userName":         obj.applyPic,
                "userId":           obj.busiPic,
                "storeName":        obj.auth

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
     * 关闭禁用店铺s
     * @param name
     */
    function certifiedSeller(id,certifiedFlag) {
        $.ajax({
            url :HOMEADRESS+"storeRegulatory/certifiedSeller",
            contentType: "application/json; charset=utf-8",
            dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
            type:"post", //传参方式，get 或post
            data: JSON.stringify({
                "id": id,
                "certifiedFlag":certifiedFlag
            }), success: function (data) {
                table.ajax.reload();
            }
        });
    }

</script>
</body>
</html>


