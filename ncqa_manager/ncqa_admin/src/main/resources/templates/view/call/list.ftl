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
                        <h4 class="panel-title">点名管理</h4>
                    </div>
                    <div class="panel-body">

                        <div class="row m-l-1 ">
                            <a href="add" class="btn  btn-success">开始点名</a>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th width="70">序号</th>
                                <th>点名班级</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>
                                [#if item.callRecordList??]
                                    [#list item.callRecordList as record]
                                        ${record.className!''},
                                    [/#list]
                                [/#if]
                            </td>
                            <td>
                                [#if item.startTime??]
                                    ${(item.startTime!'')?number_to_datetime!''}
                                [/#if]
                            </td>
                            <td>
                                [#if item.endTime??]
                                    ${item.endTime?number_to_datetime!''}
                                [/#if]
                            </td>
                            <td> ${((item.status == 1)?string("已结束","未结束"))!''}</td>
                            <td>
                                <a href="${basePath}/call/registerList?id=${item.id!''}" class="edit">
                                    <li class="fa  fa-edit">查看签到列表</li>
                                </a>
                                <a href="${basePath}/call/getPhoto">
                                    <li class="fa  fa-edit">查看签到照片</li>
                                </a>
                                <a href="${basePath}/student/list?studentClassId=${item.id!''}" class="edit">
                                    <li class="fa  fa-edit">导出逃课人员</li>
                                </a>
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

    });

</script>
