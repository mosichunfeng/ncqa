<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline" >
        <div class="row">
        <!-- begin col-12 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                <div class="panel-heading">
                    <h4 class="panel-title">我的待办任务</h4>
                </div>
                <div class="panel-body">
                    <div class="panel panel-default actionPanel">
                        [#--操作栏--]
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th width="70">序号</th>

                            <th>任务名称</th>
                            <th>任务描述</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        [#if tasks?size>0]
                            [#list tasks as item]
                                <tr>
                                    <td width="70" align="center">${item_index+1}</td>
                                    <td>${item.name!''}</td>
                                    <td>${item.description!''}</td>
                                    <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                                    <td> </td>
                                </tr>
                            [/#list]
                        [#else ]
                             <tr>
                                 <td  colspan="5" >没有待办任务</td>
                             </tr>
                        [/#if]

                        </tbody>
                    </table>
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

    $(function () {
        $(".delete").click(function () {
            var  id =  $(this).attr("id");
            alertServer.cofirm("确认删除","删除",function (flag) {
                if(flag){
                    Ajax.Get("${base}/workflow/model/delete/"+id).done(function (res) {
                        window.location.reload()
                    }).fail(function (err) {
                        swal("错误", '操作失败', "error")
                    })
                }
            });
        });
    })
</script>
