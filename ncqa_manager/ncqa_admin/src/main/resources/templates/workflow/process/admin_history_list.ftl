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
                    <h4 class="panel-title">系统流程历史</h4>
                </div>
                <div class="panel-body">
                    <div class="panel panel-default actionPanel">
                        [#--操作栏--]
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th width="70">序号</th>
                            <th>流程实例ID</th>
                            <th>流程类型</th>
                            <th>所属流程</th>
                            <th>流程定义Id</th>
                            <th>启动时间</th>
                            <th>流程启动人</th>
                            <th>结束时间</th>
                            <th>父级流程ID</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        [#list  page.records as item]
                            <tr>
                                <td width="70" align="center">${item_index+1}</td>
                                <td>${item.processesInsId!''}</td>
                                <th>${item.category}</th>
                                <td>${item.name!''}</td>
                                <td>${item.processonDefinitionId!''}</td>
                                <td>${(item.startTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                                <td>${item.startUser!''}</td>
                                <td>${(item.endTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                                <td>${item.parentId!''}</td>
                                <td><a href="${base}/workflow/history/processIns/detail?processId=${item.processesInsId}">详情</a></td>
                            </tr>
                        [/#list]
                        </tbody>
                    </table>

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
