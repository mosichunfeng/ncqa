[#include "../../include.ftl"]
<style>

    .floatLayer {
        width: 100%;
        height: 100%;
        position: fixed;
        display: none;
        background: #000;
        text-align: center;
        z-index: 9990;
        top: 0px;
        left: 0px;
        filter: alpha(Opacity=50);
        -moz-opacity: 0.50;
        opacity: 0.50;
    }

    .liadloging {
        color: white;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%)
    }

    /*遮罩层 begin*/

</style>
<div id="content" class="content">
    <form id="listForm" action="list" class="form-inline">
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <h4 class="panel-title">题库导入</h4>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">导入题库</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <select id="selecter" class="form-control">
                                        <option value=""><--请选择--></option>
                                        [#list questionbase as qb]
                                            <option value="${qb.id!''}">${qb.name!''}</option>
                                        [/#list]
                                    </select>
                                </div>
                                <a href="${basePath}/excel/题库导入模板.xlsx" download="题库导入模板.xlsx" class="btn btn-sm btn-info">下载批量添加模板</a>
                                <a href="javascript:void(0)"  onclick="uploadExcelFile()" class="btn btn-sm btn-primary upload-subject">上传题库</a>
                                <input type="file" id="file" name="file" style="display: none" multiple="multiple"
                                       accept=".xls,.xlsx" onchange="fileUpload(event)" class="excelFile">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="floatLayer">
    <div class="liadloging">
    [#--<img src="../images/loading-lo.gif"/>--]
        <p>处理中请稍后......</p>
    </div>
</div>
<script>
    function uploadExcelFile() {
        $(".excelFile").click();
    };

    function fileUpload(e) {
        var files = e.currentTarget.files;
        onRead(files[0]);
    }
    function onRead(object) {
        $(".floatLayer").show();
        console.log(object);
        var formData = new FormData();
        formData.append('file', object);
        formData.append('questionBaseId', $("#selecter").val());
        var req = new XMLHttpRequest();
        req.open("post", "${basePath}/questionbase/upload", true);
        req.onreadystatechange = () => {
            if (req.readyState == 4 && (req.status == 200 || req.status == 304)) {
                var result = JSON.parse(req.response);
                if(result.status){
                    window.location.reload();
                }else{
                    swal({
                                title: "信息",
                                text: result.msg,
                                type: "info",
                                showCancelButton: false,
                                confirmButtonColor: "#DD6B55",
                                confirmButtonText: "确定",
                                closeOnConfirm: false
                            },
                            function(){
                                window.location.reload();
                            });
                }
            }
            $(".floatLayer").hide();
        }
        req.send(formData);
    };
</script>