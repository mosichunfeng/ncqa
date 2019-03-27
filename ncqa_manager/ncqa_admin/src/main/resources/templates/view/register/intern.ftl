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
                    <h4 class="panel-title">设置本届实习班级</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="save" method="post">
                        <div class="form-group">
                            <label class="col-md-1 control-label">指定实习班级：</label>
                            <div class="col-md-6">
                                <select id="usertype" name="classes" class="selectpicker show-tick form-control"
                                        multiple data-live-search="false">
                                    [#list classinfo as kd]
                                        <option value="${kd.id!''}">${kd.name!''}</option>
                                    [/#list]
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">在校签到时间：</label>
                            <div class="col-md-6">
                                <select id="usertype" name="classes" class="selectpicker show-tick form-control"
                                        multiple data-live-search="false">
                                    <option value="1">星期一</option>
                                    <option value="2">星期二</option>
                                    <option value="3">星期三</option>
                                    <option value="4">星期四</option>
                                    <option value="5">星期五</option>
                                    <option value="6">星期六</option>
                                    <option value="7">星期日</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">实习签到时间：</label>
                            <div class="col-md-6">
                                <select id="usertype" name="classes" class="selectpicker show-tick form-control"
                                        multiple data-live-search="false">
                                    <option value="1">星期一</option>
                                    <option value="2">星期二</option>
                                    <option value="3">星期三</option>
                                    <option value="4">星期四</option>
                                    <option value="5">星期五</option>
                                    <option value="6">星期六</option>
                                    <option value="7">星期日</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-control input-group">
                            <label class="col-md-4 control-label"></label>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-success m-r-5 m-b-5">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- end panel -->
        </div>
    </div>
</div>
<!-- end col-6 -->
<script type="text/javascript">

</script>

