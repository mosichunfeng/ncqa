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
                    <h4 class="panel-title">编辑Organization</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="${basePath}/organization/save"   method="post">
                        <input type="hidden" name="id" value="${(organization.id)!''}">
                                                    <div class="form-control input-group">
                                <label class="input-group-addon edit-label">
                                                                            名称: 
                                                                    </label>
                                <div class="col-md-6">
                                    <input type="text" name="name" class="form-control"  value="${organization.name}"/>
                                </div>
                            </div>
                                                     <div class="form-control input-group">
                                <label class="input-group-addon edit-label">
                                                                            标识: 
                                                                    </label>
                                <div class="col-md-6">
                                    <input type="text" name="identifying" class="form-control"  value="${organization.identifying}"/>
                                </div>
                            </div>
                                                     <div class="form-control input-group">
                                <label class="input-group-addon edit-label">
                                                                            排序: 
                                                                    </label>
                                <div class="col-md-6">
                                    <input type="text" name="order" class="form-control"  value="${organization.order}"/>
                                </div>
                            </div>
                                                     <div class="form-control input-group">
                                <label class="input-group-addon edit-label">
                                                                            父级ID: 
                                                                    </label>
                                <div class="col-md-6">
                                    <input type="text" name="parentId" class="form-control"  value="${organization.parentId}"/>
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

