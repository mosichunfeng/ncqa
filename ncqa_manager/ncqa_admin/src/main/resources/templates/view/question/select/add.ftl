<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../../../include.ftl"]
<!-- begin #content -->
<div id="content" class="content">
    <!-- begin row -->
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
                <div class="panel-heading">
                    <h4 class="panel-title">新增选择题</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="${basePath}/question/select/save" method="post" onsubmit="return toVaild()">
                        <div class="form-group" data-width="70">
                            <label class="col-md-1 control-label">
                                所属题库
                            </label>
                            <div class="col-md-6">
                                <select id="selecter" name="questionBaseId" class="form-control">
                                    <option value=""><--请选择题库--></option>
                                        [#list questionbase as qb]
                                            <option value="${qb.id!''}">${qb.name!''}</option>
                                        [/#list]
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                问题
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="content" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                分值
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="grade" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                选项A
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="answerA" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                选项B
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="answerB" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                选项C
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="answerC" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                选项D
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="answerD" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                正确选项
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="rightAnswer" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label"></label>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-success m-r-5 m-b-5">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

