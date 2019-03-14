<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">标签管理</h4>
                    </div>
                    <div class="panel-body">

                        <div class="row m-l-1 ">
                            <a href="add" class="btn  btn-success">添加标签</a>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th width="70">序号</th>
                                <th>名称</th>
                                <th>创建时间</th>
                                <th>更新时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.name!''}</td>
                            <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                            <td>${(item.updateTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                            <td>
                                <a href="javascript:void (0)" class="delete" data-id="${item.id}">
                                    <li class="fa fa-trash">删除</li>
                                </a>
                            </td>
                        </tr>
                        [/#list]
                            </tbody>
                        </table>
                    </div>
                    [@pagination pageNumber = page.current totalPages = page.pages]
                        [#include "../../pagination.ftl"]
                    [/@pagination]

                </div>
            </div>
            <!-- end panel -->
        </div>
        <!-- end col-12 -->
</div>
    </form>
    <!-- end row -->
</div>




<script type="application/javascript">
    [@flash_message /]

    $(function () {
        $(".delete").click(function () {
            var id = $(this).attr("data-id");
            alertServer.cofirm("确认删除", "删除", function (flag) {
                if (flag) {
                    Ajax.Get("del/" + id).done(function (res) {
                        Notify.success("删除成功");
                        window.location.reload()
                    }).fail(function (err) {
                        swal("错误", '操作失败', "error")
                    })
                }
            });
        });
    })
</script>
