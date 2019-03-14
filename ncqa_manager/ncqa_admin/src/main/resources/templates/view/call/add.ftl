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
                    <h4 class="panel-title">开始点名</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="save" method="post">
                        <div class="form-group">
                            <label class="col-md-1 control-label">选择班级：</label>
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
                            <label class="col-md-1 control-label">
                                结束时间:
                            </label>
                            <div class="col-md-6">
                                <input type="text" id="endTime" name="endTime" class="form-control" readonly/>
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
    $(function () {
        var endDate = laydate.render({//渲染结束时间选择
            elem: '#endTime',//通过id绑定html中插入的end
            type: 'datetime',
            min: "2019-3-14",//设置min默认最小值
        });
        $('#usertype').selectpicker({
            'selectedText': 'cat'
        });
    })

    function toVaild() {
        var start = $("#startTime").val();
        var end = $("#endTime").val();
        if (start == "" || end == "") {
            alert("请填写开始时间和结束时间!");
            return false;
        }
        $("#plain").attr("value", p.getContentTxt());
        var k = $("#js_type_select").val();
        $("#js_in_list").val(JSON.stringify(k))
    }

</script>

