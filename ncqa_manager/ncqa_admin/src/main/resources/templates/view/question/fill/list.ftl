[#include "../../../include.ftl"]
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
                                        <option value=""><--请选择--></option>
                                        [#list base1 as questionbase]
                                            <option value="${questionbase.id!''}" [#if questionBaseId??][#if questionbase.id==questionBaseId]selected[/#if][/#if]>${questionbase.name!''}</option>
                                        [/#list]
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </div>
                        </div>
                        <div class="row m-l-1 ">
                            <a href="${basePath}/question/fill/add" class="btn btn-sm btn-success">添加填空题</a>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>所属题库</th>
                                    <th>内容</th>
                                    <th>正确答案</th>
                                    <th>分值</th>
                                    <th>可操作列表</th>
                                </tr>
                                </thead>
                                <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.questionBaseName!''}</td>
                            <td>${item.content!''}</td>
                            [#list item.answerList as answer]
                                <td>${answer.answerContent!''}</td>
                            [/#list]
                            <td>${item.rightAnswer!''}</td>
                            <td>${item.grade!''}</td>
                            <td>
                                <a href="${basePath}/question/fill/edit?questionId=${item.id}" class="edit" data-id="${item.id}">
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
                        [#include "../../../pagination.ftl"]
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
