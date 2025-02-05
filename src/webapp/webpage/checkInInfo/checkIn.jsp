<%--
  Created by IntelliJ IDEA.
  User: sten
  Date: 2024/12/23
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>入住单</title>
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
    <script src="../../js/getTime.js"></script>
    <script src="../../js/Cookie.js"></script>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title " style="margin-top: 20px;">
    <legend>酒店管理 - 入住单</legend>
</fieldset>

<form class="layui-form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">入住单号</label>
            <div class="layui-input-block">
                <input type="text" id="checkId" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预定单号</label>
            <div class="layui-input-inline">
                <input type="text" id="orderId" autocomplete="off" placeholder="预定单号" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonOrderId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入住人</label>
            <div class="layui-input-inline">
                <input type="text" id="orderName" lay-verify="required" autocomplete="off" placeholder="入住姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="tel" id="orderPhone" lay-verify="phone" autocomplete="off" placeholder="入住电话" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input type="text" id="orderIDcard" lay-verify="required|identity" placeholder="公民身份证号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">入住时长</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" lay-verify="required" id="orderAllTime" placeholder="抵店时间 - 离店时间" readonly>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">入住人数</label>
            <div class="layui-input-inline">
                <input type="text" id="checkNum" lay-verify="number" autocomplete="off" placeholder="实际入住人数" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">房间类型</label>
            <div class="layui-input-inline">
                <input type="text" id="typeId" lay-verify="required" autocomplete="off" placeholder="房间类型" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonTypeId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
        <div class="layui-inline layui-hide">
            <label class="layui-form-label">房间id</label>
            <div class="layui-input-inline">
                <input type="text" id="typeName" lay-verify="number" autocomplete="off" placeholder="房间id" class="layui-input" readonly>
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房间信息</label>
            <div class="layui-input-inline">
                <input type="text" id="floorName" lay-verify="required" autocomplete="off" placeholder="选择房间信息" class="layui-input" readonly>
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="buttonRoomId"><i class="layui-icon">&#xe654;</i> 选择</button>
        </div>
        <div class="layui-inline layui-hide">
            <label class="layui-form-label">房间id</label>
            <div class="layui-input-inline">
                <input type="text" id="roomId" lay-verify="num" autocomplete="off" placeholder="房间id" class="layui-input" readonly>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">客房价格</label>
            <div class="layui-input-inline">
                <input type="text" id="price" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入住价格</label>
            <div class="layui-input-inline">
                <input type="text" id="checkPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">折扣</label>
            <div class="layui-input-inline">
                <input type="text" id="discount" lay-verify="number" autocomplete="off" placeholder="折扣请输入，无折扣置空" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">折扣原因</label>
            <div class="layui-input-inline">
                <input type="text" id="discountReason" autocomplete="off" placeholder="请输入折扣原因" class="layui-input">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否加床</label>
            <div class="layui-input-inline">
                <input type="radio" name="addBed" value="Y" title="是" lay-filter="addBedYes">
                <input type="radio" name="addBed" value="N" title="否" lay-filter="addBedNo" checked="">
            </div>
        </div>
        <div class="layui-inline">
            <div id="addBed" class="layui-inline layui-hide">
                <label class="layui-form-label">加床价格</label>
                <div class="layui-input-inline">
                    <input type="text" id="addBedPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-hide">
        <div class="layui-inline ">
            <label class="layui-form-label">是否结账</label>
            <div class="layui-input-inline">
                <input type="radio" name="checkMoney" value="Y" title="是" lay-filter="checkMoneyYes">
                <input type="radio" name="checkMoney" value="N" title="否" lay-filter="checkMoneyNo" checked="">
            </div>
        </div>
        <div class="layui-inline">
            <div id="checkMoney" class="layui-inline layui-hide">
                <label class="layui-form-label">结账金额</label>
                <div class="layui-input-inline">
                    <input type="text" id="checkMoneyPrice" lay-verify="number" autocomplete="off" placeholder="￥" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-inline">
            <div id="checkDate" class="layui-inline layui-hide">
                <label class="layui-form-label">结账时间</label>
                <div class="layui-input-inline">
                    <input type="text" id="checkMoneyDate" lay-verify="number" autocomplete="off" placeholder="结算时间" class="layui-input" readonly>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预收款</label>
            <div class="layui-input-inline">
                <input type="text" id="orderMoney" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">应收账款</label>
            <div class="layui-input-inline">
                <input type="text" id="money" lay-verify="required|number" autocomplete="off" placeholder="￥" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">单据状态</label>
            <div class="layui-input-inline">
                <select name="city" class="layui-input-inline" id="orderState">
                    <option value="预定">预定</option>
                    <option value="入住" selected>入住</option>
                    <option value="结算">结算</option>
                    <option value="延期">延期</option>
                </select>
            </div>
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="insertCheckIn">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //layui的form表单默认的submit提交是真的不会用。
    //以JSON对象传递给后台的话，在传递前无法对数据二次修改。
    //所以就变成了整体传递过去，后端再解析JSON，但是解析时字段为空就又出错。
    //具体起来就是类似房间类型-新增房间那部分，全部字段强制要求全给，后端又set个别字段。
    //所以本文的提交就默认老老实实用ajax提交出去，不采用JSON对象了。

    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
        var isAddBed = false;
        var isCheckMoney = false;

        //日期
        laydate.render({
            elem: '#arrireDate'
        });
        laydate.render({
            elem: '#leaveDate'
        });
        laydate.render({
            elem: '#orderAllTime',
            type: 'datetime',
            min: 0,
            range: '|',
            format: 'yyyy-MM-dd',
            calendar: true
        });

        //设置ID（读取的时间）
        var time = new Date().getTime();
        $(document).ready(function() {
            $("#checkId").val("OD" + time);
        });

        //一个属性的显隐，直接通过修改class实现，使用了layui的class属性
        form.on('radio(addBedYes)', function() {
            $('#addBed').removeClass("layui-hide");
            $('#addBed').addClass("layui-show");
            isAddBed = true;
        });
        form.on('radio(addBedNo)', function() {
            $('#addBed').removeClass("layui-show");
            $('#addBed').addClass("layui-hide");
            isAddBed = false;
        });

        //一个属性的显隐，直接通过修改class实现，使用了layui的class属性
        form.on('radio(checkMoneyYes)', function() {
            $('#checkMoney').removeClass("layui-hide");
            $('#checkMoney').addClass("layui-show");
            $('#checkDate').removeClass("layui-hide");
            $('#checkDate').addClass("layui-show")
            // 获取当前日期
            var today = new Date();
            var mm = today.getMinutes()
            var hh = today.getHours()
            var dd = today.getDate();
            var MM = today.getMonth() + 1; // 月份从0开始，需要加1
            var yyyy = today.getFullYear();
            if(hh < 10) {
                hh = '0' + hh;
            }
            if(mm < 10) {
                mm = '0' + mm;
            }
            if(dd < 10) {
                dd = '0' + dd;
            }
            if(MM < 10) {
                MM = '0' + MM;
            }
            var todayDate = yyyy + '-' + MM + '-' + dd + ' ' + hh + ':' + mm;

            // 设置输入框的值
            $('#checkMoneyDate').val(todayDate);
            isCheckMoney = true;
        });
        form.on('radio(checkMoneyNo)', function() {
            $('#checkMoney').removeClass("layui-show");
            $('#checkMoney').addClass("layui-hide");
            $('#checkDate').removeClass("layui-show");
            $('#checkDate').addClass("layui-hide")
            isCheckMoney = false;
        });

        //房间类型的选择
        $('#buttonTypeId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间类型',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: '../orderInfo/selectRoomType.jsp',
                yes: function(index, layero) {
                    typeId.value = $(layero).find('iframe')[0].contentWindow.tType.value; //将子窗口中的 tId 抓过来
                    typeName.value = $(layero).find('iframe')[0].contentWindow.tId.value;
                    price.value = $(layero).find('iframe')[0].contentWindow.tPrice.value;
                    layer.close(index); //关闭弹窗
                },
                btn2: function(index) {
                    layer.close(index);
                },
                success: function(layero, index) {
                    var obj = $(layero).find('iframe')[0].contentWindow;
                }
            });
        });
        //预定房间的选择
        $('#buttonOrderId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择预定单号',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: './selectRoomOrder.jsp',
                yes: function(index, layero) {
                    orderId.value = $(layero).find('iframe')[0].contentWindow.tOrderId.value; //将子窗口中的 tId 抓过来
                    orderName.value = $(layero).find('iframe')[0].contentWindow.tOrderName.value;
                    orderPhone.value = $(layero).find('iframe')[0].contentWindow.tOrderPhone.value;
                    orderIDcard.value = $(layero).find('iframe')[0].contentWindow.tOrderIDcard.value;
                    orderAllTime.value = $(layero).find('iframe')[0].contentWindow.tOrderAllTime.value;
                    checkNum.value = $(layero).find('iframe')[0].contentWindow.tCheckNum.value;
                    typeId.value = $(layero).find('iframe')[0].contentWindow.tTypeId.value;
                    price.value = $(layero).find('iframe')[0].contentWindow.tPrice.value;
                    checkPrice.value = $(layero).find('iframe')[0].contentWindow.tCheckPrice.value;
                    orderMoney.value = $(layero).find('iframe')[0].contentWindow.tOrderMoney.value;
                    typeId.value = $(layero).find('iframe')[0].contentWindow.tTypeId.value;
                    typeName.value = $(layero).find('iframe')[0].contentWindow.tTypeName.value;
                    layer.close(index); //关闭弹窗
                },
                btn2: function(index) {
                    layer.close(index);
                },
                success: function(layero, index) {
                    var obj = $(layero).find('iframe')[0].contentWindow;
                }
            });
        });

        //房间信息的选择
        $('#buttonRoomId').on('click', function() {
            layer.open({
                type: 2,
                title: '请选择房间信息',
                btn: ['确定', '取消'],
                area: ['880px', '440px'],
                fixed: form,
                content: '../roomInfo/selectRoomInfo.jsp',
                yes: function(index, layero) {
                    var tStatus =  $(layero).find('iframe')[0].contentWindow.tStatus.value;
                    if (tStatus === '满房') {
                        layer.msg('已满房,请选择其他房间')
                    }else {
                        floorName.value = $(layero).find('iframe')[0].contentWindow.tFloorName.value; //将子窗口中的 tId 抓过来
                        roomId.value = $(layero).find('iframe')[0].contentWindow.tRoomId.value;
                        layer.close(index); //关闭弹窗
                    }
                },
                btn2: function(index) {
                    layer.close(index);
                },
                success: function(layero, index) {
                    var obj = $(layero).find('iframe')[0].contentWindow;
                }
            });
        });

        //监听提交
        form.on('submit(insertCheckIn)', function(data) {

            //先获取值
            var orderId = $('#orderId').val();
            var checkId = $('#checkId').val();
            var orderName = $('#orderName').val();
            var orderPhone = $('#orderPhone').val();
            var orderIDcard = $('#orderIDcard').val();

            //返回数据类型： yyyy-mm-dd hh:mm:ss
            var orderAllTime = ($('#orderAllTime').val()).split(" | ");
            var arrireDate = orderAllTime[0];
            var leaveDate = orderAllTime[1];

            var checkNum = $('#checkNum').val();
            var typeId = $('#typeName').val();
            //房间信息
            // var floorName = $('#floorName').val();
            var roomId = $('#roomId').val();
            var price = $('#price').val();
            var checkPrice = $('#checkPrice').val();
            var discount = $('#discount').val();
            var discountReason = $('#discountReason').val();
            var addBedPrice = $('#addBedPrice').val();
            var checkMoneyPrice = $('#checkMoneyPrice').val();
            var checkMoneyDate = $('#checkMoneyDate').val();
            var orderMoney = $('#orderMoney').val();
            var money = $('#money').val();
            var orderState = $('#orderState').val();
            var remark = $('#remark').val();
            //加床：true 不加：false
            var addBed = isAddBed;
            var checkMoney = isCheckMoney;
            // var roomId = $('#roomId').val(); 后台处理 -->直接放一个空类就行了
            var operatorId = getCookie("loginName");

            var params = "checkId=" + checkId + "&orderId=" + orderId + "&orderName=" + orderName + "&orderPhone=" + orderPhone +
                "&orderIDcard=" + orderIDcard + "&typeId=" + typeId + "&arrireDate=" + arrireDate +
                "&leaveDate=" + leaveDate + "&orderState=" + orderState + "&checkNum=" + checkNum +
                "&price=" + price + "&checkPrice=" + checkPrice + "&discount=" + discount +
                "&discountReason=" + discountReason + "&addBed=" + addBed + "&addBedPrice=" + addBedPrice +
                "&checkMoneyPrice=" + checkMoneyPrice + "&money=" + money + "&checkMoney=" + checkMoney +
                "&checkMoneyDate=" + checkMoneyDate + "&roomId=" + roomId +
                "&orderMoney=" + orderMoney + "&operatorId=" + operatorId + "&remark=" + remark + "&make=1";

            $.post(baseUrl + '/insertAndUpdateCheckInServlet', params, function(data) {
                if(data === '1') {
                    layer.alert('入住单登记成功！', {
                        title: '新增成功',
                        icon: 6,
                        shade: 0.6,
                        anim: 3,
                        offset: '220px'
                    });
                } else if(data === '0') {
                    layer.alert('存在相同字段！', {
                        title: '新增失败',
                        icon: 5,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                } else {
                    layer.alert('入住单登记失败！', {
                        title: '新增失败',
                        icon: 2,
                        shade: 0.6,
                        anim: 6,
                        offset: '220px'
                    });
                }
            });
            return false;
        });
    });
</script>
</body>

</html>