<!-- begin #content -->
<!-- ================== BEGIN BASE CSS STYLE ================== -->
[#include "../include.ftl"]
<div id="content" class="content">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">Organization列表</h4>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-5" >
                            <div class="btn-group">
                                <button type="button" class="btn btn-success">编辑</button>
                                <button type="button" class="btn btn-success">编辑</button>
                                <button type="button" class="btn btn-success">删除</button>
                            </div>
                            <div  class="row" style="margin-top: 20px">
                                <div id="jstree-checkable"></div>
                            </div>


                        </div>
                        <div class="col-md-7">
                            <h4>详细信息</h4>
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">名称:</label>
                                    <div class="col-md-6">
                                        <input type="text" name="name" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">标识:</label>
                                    <div class="col-md-6">
                                        <input type="text" name="name" class="form-control" />
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
                <!-- end panel -->
            </div>
            <!-- end col-12 -->
        </div>
    <!-- end row -->
</div>

<script type="application/javascript">
    [@flash_message /]
    $(function () {
        Ajax.Get("${basePath}/organization/tree").done(function (res) {
            $('#jstree-checkable').jstree({
                'plugins': ["state", "types"],
                'core': {
                    'data': res,
                    "themes": {
                        "responsive": false
                    }
                },
                "types": {
                    "default": {
                        "icon": "fa fa-folder text-primary fa-lg"
                    },
                    "file": {
                        "icon": "fa fa-file text-success fa-lg"
                    }
                }
            });
        });
    })
</script>
