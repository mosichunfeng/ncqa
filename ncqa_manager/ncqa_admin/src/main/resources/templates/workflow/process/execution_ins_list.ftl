<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="findExecutionProcessIns" class="form-inline" >
        <input type="hidden" name="processDefKey" value="${processDefKey}">
        <div class="row">
        <!-- begin col-12 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                <div class="panel-heading">
                    <h4 class="panel-title">进行中的流程实例</h4>
                </div>
                <div class="panel-body">
                    <div class="panel panel-default actionPanel">
                        [#--操作栏--]
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th width="70">序号</th>
                            <td>实例ID</td>
                            <th>任务名称</th>
                            <th>业务标识</th>
                            <th>任务描述</th>
                            <td>创建时间</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        [#if (page.records)?size>0]
                            [#list page.records as item]
                                <tr>
                                    <td width="70" align="center">${item_index+1}</td>
                                    <td>${item.id}</td>
                                    <td>${item.name!''}</td>
                                    <td>${item.businessKey!''}</td>
                                    <td>${item.description!''}</td>
                                    <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>

                                    <td><a  target="_blank" href="${base}/assets/plugins/activiti/diagram-viewer/index.html?processDefinitionId=${item.processDefinitionId}&processInstanceId=${item.id}">查看进度</a></td>
                                </tr>
                            [/#list]
                        [#else ]
                             <tr>
                                 <td  colspan="5" >没有创建实例</td>
                             </tr>
                        [/#if]

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
