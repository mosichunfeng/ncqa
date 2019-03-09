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
                    <h4 class="panel-title">流程详细信息</h4>
                </div>
                <div class="panel-body">
                    <label><h4>流程综合信息-【${detail.instance.category}】-【${detail.instance.name}】</h4></label>

                    <table class="table table-bordered table-striped">
                        <tr>
                            <td width="200">流程ID:</td>
                            <td>${detail.instance.processesInsId}</td>
                            <td width="200">流程定义ID:</td>
                            <td>${detail.instance.processonDefinitionId}</td>
                            <td width="200">业务KEY:</td>
                            <td>${detail.instance.businessKey}</td>

                        </tr>
                        <tr>
                            <td width="200">流程启动时间:</td>
                            <td>${(detail.instance.startTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                            <td width="200">流程结束时间:</td>
                            <td>${detail.instance.endTime?string("yyyy-MM-dd hh:MM:ss")!''}</td>
                            <td width="200">流程状态</td>
                            <td></td>
                        </tr>
                    </table>
                    <label><h4>活动记录</h4></label>

                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <td>活动ID</td>
                                <td>活动名称</td>
                                <td>活动类型</td>
                                <td>任务ID</td>
                                <td>办理人</td>
                                <td>活动开始时间</td>
                                <td>活动结束时间</td>
                                <td>活动耗时(秒)</td>
                            </tr>

                            [#list detail.activityList as item ]
                                 <tr>
                                     <td>${item.id!''}</td>
                                     <td>${item.activityName!''}</td>
                                     <td>${item.activityType!''}</td>
                                     <td>${item.taskId!''}</td>
                                     <td>${item.assignee!''}</td>
                                     <td>${(item.startTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                                     <td>${(item.endTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                                     <td>${item.durationInMillis}</td>

                                 </tr>
                            [/#list]

                        </thead>

                    </table>
                    <label><h4>流程变量</h4></label>

                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <td>变量名称</td>
                                <td>变量类型</td>
                                <td>值</td>
                            </tr>
                        </thead>
                        [#list detail.variableList as vars  ]

                        <tr>
                            <td>  ${vars.variableName}</td>
                            <td>  ${vars.variableTypeName}</td>
                            <td>  ${vars.value}</td>
                        </tr>
                        [/#list]
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
