[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">学生列表</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">高级查询</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <select id="selecter" class="form-control" name="studentClassId">
                                        <option value=""><--请选择--></option>
                                        [#list classinfo  as ci]
                                            <option value="${ci.id!''}" [#if studentClassId??][#if ci.id==studentClassId]selected[/#if][/#if]>${ci.name!''}</option>
                                        [/#list]
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="search" class="form-control" value="${search!''}" placeholder="请输入姓名"/>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </div>
                        </div>
                        <div class="row m-l-1 ">
                            <a href="${basePath}/student/add" class="btn btn-sm btn-success">添加学生</a>
                            <a href="${basePath}/student/toLead" class="btn btn-sm btn-success">导入学生</a>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>班级</th>
                                    <th>性别</th>
                                    <th>手机号</th>
                                    <th>可操作列表</th>
                                </tr>
                                </thead>
                                <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.studentId!''}</td>
                            <td>${item.studentName!''}</td>
                            <td>${item.studentClass!''}</td>
                            <td>${item.studentGender!''}</td>
                            <td>${item.studentTel!''}</td>
                            <td>
                                <a href="${basePath}/student/edit?id=${item.id}" class="edit" data-id="${item.id}">
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
