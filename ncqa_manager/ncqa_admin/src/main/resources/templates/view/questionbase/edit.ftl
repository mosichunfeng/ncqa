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
                    <h4 class="panel-title">编辑题库</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="${basePath}/questionbase/save" method="post" onsubmit="return toVaild()">
                        <input type="hidden" name="id" value="${(questionbase.id)!''}">
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                题库名称
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" class="form-control" value="${questionbase.name!''}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                题库描述
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="description" class="form-control" value="${questionbase.description!''}"/>
                            </div>
                        </div>
                        <div class="form-group" data-width="70">
                            <label class="col-md-1 control-label">
                                题库类别
                            </label>
                            <div class="col-md-6">
                                <select id="selecter" name="kindId" class="form-control">
                                    <option value=""><--请选择类别--></option>
                                        [#list kind as kd]
                                            <option value="${kd.id!''}" [#if kd.id==questionbase.kindId]selected[/#if]>${kd.name!''}</option>
                                        [/#list]
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                活动开始时间:
                            </label>
                            <div class="col-md-6">
                                <input type="text" id="startTime" name="startTime" class="form-control" value="${questionbase.startTime?number_to_datetime!''}"  readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label">
                                活动结束时间:
                            </label>
                            <div class="col-md-6">
                                <input type="text" id="endTime" name="endTime" class="form-control" value="${questionbase.endTime?number_to_datetime!''}" readonly/>
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

<script type="text/javascript">
    $(function () {
        var startDate= laydate.render({//渲染开始时间选择
            elem: '#startTime', //通过id绑定html中插入的start
            type: 'datetime',
            min: new Date(new Date().getTime() + 1 * 60 * 60 * 1000).toDateString(),
            max:"2099-12-31", //设置一个默认最大值
            done: function (value, dates) {
                endDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });

        var endDate= laydate.render({//渲染结束时间选择
            elem: '#endTime',//通过id绑定html中插入的end
            type: 'datetime',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                startDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });
    })
    function toVaild() {
        var start = $("#startTime").val();
        var end = $("#endTime").val();
        if(start=="" || end==""){
            alert("请填写开始时间和结束时间!");
            return false;
        }
        $("#plain").attr("value",p.getContentTxt());
        var k=$("#js_type_select").val();
        $("#js_in_list").val(JSON.stringify(k))
    }
</script>