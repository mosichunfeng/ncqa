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
                    <h4 class="panel-title">添加标签</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="save"   method="post">
                        <div class="form-control input-group">
                            <label class="input-group-addon edit-label">标签名称</label>
                            <div class="col-md-6">
                                <input type="text" name="name" class="form-control" placeholder="标签名称"/>
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
        <!-- end col-6 -->

    </div>

</div>

