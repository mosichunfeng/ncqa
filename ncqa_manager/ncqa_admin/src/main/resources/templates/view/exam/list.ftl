[#include "../../include.ftl"]
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">成绩列表</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">高级查询</h3>
                            </div>
                            <div class="panel-body">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <select id="selecter" class="form-control" name="questionBaseId">
                                            <option value=""><--请选择题库--></option>
                                        [#list questionbase as questionbase]
                                            <option value="${questionbase.id!''}" [#if questionBaseId??][#if questionbase.id==questionBaseId]selected[/#if][/#if]>${questionbase.name!''}</option>
                                        [/#list]
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-default">查询</button>
                                </div>
                            </div>
                        </div>


                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th width="70">序号</th>
                                    <th>题库</th>
                                    <th>姓名</th>
                                    <th>班级</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>是否完成</th>
                                    <th>分数</th>
                                </tr>
                                </thead>
                                <tbody>

                        [#list  page.records as item]
                        <tr>
                            <td width="70" align="center">${item_index+1}</td>
                            <td>${item.questionBaseName!''}</td>
                            <td>${item.studentName!''}</td>
                            <td>${item.studentClass!''}</td>
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
                            <td> ${((item.status == 0)?string("未完成","已完成"))!''}</td>
                            <td>
                            [#if item.status==1]
                                ${item.grade!''}
                            [/#if]
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

