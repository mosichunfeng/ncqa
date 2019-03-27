[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">题目</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">高级查询</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <select id="selecter" class="form-control" name="questionBaseId">
                                        <option value=""><--请选择实习班级--></option>
                                        [#list classinfo as ci]
                                            <option value="${ci.id!''}">${ci.name!''}</option>
                                        [/#list]
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </div>
                        </div>
                        <div class="row m-l-1 ">
                            <a href="${basePath}/register/toIntern" class="btn btn-sm btn-success">设置实习班级</a>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>班级</th>
                                    <th>签到时间</th>
                                    <th>签到地点</th>
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
                            <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                            <td>${item.address!''}</td>
                            <td>
                                <a href="${basePath}/call/image?id=${item.id}" class="edit" data-id="${item.id}">
                                    <li class="fa  fa-edit">查看图片</li>
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
