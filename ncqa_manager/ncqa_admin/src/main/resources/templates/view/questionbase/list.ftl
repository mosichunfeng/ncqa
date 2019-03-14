[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">用户管理</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">高级查询</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <select class="form-control" name="searchProperties">
                                        <option value="">请选择查询条件</option>
                                        <option value="questionbase_name">题库名称</option>
                                    </select>
                                    <div class="input-group input-daterange">
                                        <input type="text" class="form-control" name="searchValue"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </div>
                        </div>
                        <div class="row m-l-1 ">
                            <a href="${basePath}/questionbase/add" class="btn btn-sm btn-success">添加</a>
                            <a href="${basePath}/questionbase/toLead" class="btn btn-sm btn-success">导入</a>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>类别</th>
                                    <th>题库名</th>
                                    <th>描述</th>
                                    <th>创建时间</th>
                                    <th>开始日期</th>
                                    <th>结束日期</th>
                                    <th>是否有效</th>
                                    <th>可操作列表</th>
                                </tr>
                                </thead>
                                <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.kindName!''}</td>
                            <td>${item.name!''}</td>
                            <td>${item.description!''}</td>
                            <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
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
                            <td> ${((item.available == 1)?string("有效","无效"))!''}</td>
                            <td>
                                <a href="${basePath}/question/select/list?questionBaseId=${item.id}">
                                    <li class="fa fa-edit">查看题目列表</li>
                                </a>
                                <a href="${basePath}/exam/export?questionBaseId=${item.id}">
                                    <li class="fa fa-edit">导出成绩</li>
                                </a>
                                <a href="${basePath}/questionbase/edit?id=${item.id}" class="edit" data-id="${item.id}">
                                    <li class="fa  fa-edit">编辑</li>
                                </a>
                                <a href="javascript:void (0)" class="delete"
                                   data-id="${item.id}">
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
            </div>
        </div>
    </form>
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
