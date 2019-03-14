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
                    <h4 class="panel-title">编辑填空题</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="save"   method="post">
                        <input type="hidden" name="id" value="${(question.id)!''}">

                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                问题
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="content" class="form-control" value="${question.content!''}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                答案
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="rightAnswer" class="form-control" value="${question.rightAnswer!''}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                分值
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="grade" class="form-control" value="${question.grade!''}"/>
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

