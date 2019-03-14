<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../../include.ftl"]
<!-- begin #content -->
<div id="content" class="content">
    <!-- begin row -->
    <div class="row">
        <!-- begin col-6 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
                <div class="panel-heading">
                    <h4 class="panel-title">新增学生</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="${basePath}/student/save" method="post" onsubmit="return toVaild()">
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                学号
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="studentId" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                姓名
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="studentName" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group" data-width="70">
                            <label class="col-md-1 control-label">
                                班级
                            </label>
                            <div class="col-md-6">
                                <select id="selecter" name="studentClassId" class="form-control">
                                    <option value=""><--请选择班级--></option>
                                        [#list classinfo as kd]
                                            <option value="${kd.id!''}">${kd.name!''}</option>
                                        [/#list]
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                性别
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="studentGender" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                手机号
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="studentTel" class="form-control" />
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

