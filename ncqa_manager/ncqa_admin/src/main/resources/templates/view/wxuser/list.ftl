[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">微信用户</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">高级查询</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <select id="selecter" class="form-control" name="classId">
                                        <option value=""><--请选择--></option>
                                        [#list classinfo as ci]
                                            <option value="${ci.id!''}" [#if classId??][#if classId==ci.id]selected[/#if][/#if]>${ci.name!''}</option>
                                        [/#list]
                                    </select>
                                    <div class="form-group">
                                        <input type="text" name="search" class="form-control" value="${search!''}" placeholder="请输入姓名"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </div>
                        </div>
                        <div class="row m-l-1 ">
                            <a href="${basePath}/question/select/add" class="btn btn-sm btn-success">添加选择题</a>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>openId</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>绑定时间</th>
                                    <th>可操作列表</th>
                                </tr>
                                </thead>
                                <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.openId!''}</td>
                            <td>${item.studentId!''}</td>
                            <td>${item.studentName!''}</td>
                            <td>${item.studentClass!''}</td>
                            <td>${(item.createTime?string("yyyy-MM-dd hh:MM:ss"))!''}</td>
                            <td>
                                <a href="javascript:void (0)" class="delete"
                                   data-id="${item.id}">
                                    <li class="fa fa-trash">解绑</li>
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
            alertServer.cofirm("确认解绑？", "解绑", function (flag) {
                if (flag) {
                    Ajax.Get("del/" + id).done(function (res) {
                        Notify.success("解绑成功!");
                        window.location.reload()
                    }).fail(function (err) {
                        swal("错误", '解绑失败', "error")
                    })
                }
            });
        });
    })
</script>
