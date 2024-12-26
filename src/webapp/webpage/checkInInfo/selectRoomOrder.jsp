<%--
  Created by IntelliJ IDEA.
  User: sten
  Date: 2024/12/23
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
    <script src="../../js/layui/layui.js"></script>
    <script src="../../js/jquery.js"></script>
    <script src="../../js/global.js"></script>
    <title>Title</title>
    <style>
        .fixDiv {
            position: sticky;
            bottom: 0;
            background-color: white;
            BORDER-BOTTOM: #e1e1e1 1px solid;
            BORDER-TOP: #e1e1e1 1px solid;
            BORDER-RIGHT: #e1e1e1 1px solid;
            BORDER-LEFT: #e1e1e1 1px solid;
            border-radius: 10px
        }
    </style>
</head>
<body>
<table id="dataTable" class='layui-table'>
    <tr>
        <th>预定单号</th>
        <th>预定人</th>
        <th>预定电话</th>
        <th>入住价格</th>
        <th>预收款</th>
        <th>抵店时间</th>
        <th>离店时间</th>
    </tr>
</table>
<div class="fixDiv">
    <label class="layui-form-label">当前选中：</label>
    <div class="layui-input-inline">
        <input type="text" id="tOrderId" class="layui-input" placeholder="预定单号" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="tOrderName" class="layui-input" placeholder="预定人姓名" readonly>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="tOrderPhone" class="layui-input" placeholder="预定号码" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tOrderAllTime" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tCheckNum" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tTypeId" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tPrice" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tCheckPrice" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tOrderMoney" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tOrderIDcard" class="layui-input" readonly>
    </div>
    <div class="layui-input-inline layui-hide">
        <input type="text" id="tTypeName" class="layui-input" readonly>
    </div>
</div>
<script>
    $(document).ready(function (){
        //发出ajax请求，调用后端数据
        $.getJSON(baseUrl + '/selectOrderInfoServlet',function (data){
            //遍历响应的json数组
            $.each(data,function (index, el){
                var tOrderId = el.orderId;
                var tOrderName = el.orderName;
                var tOrderPhone = el.orderPhone;
                var tOrderIDcard = el.orderIDcard;
                var tOrderAllTime = el.arrireDate + ' | ' + el.leaveDate;
                var tCheckNum = el.checkNum;
                var tTypeId = el.typeId.typeName;
                var tTypeName = el.typeId.typeId;

                var tPrice = el.price;
                var tCheckPrice = el.checkPrice;
                var tOrderMoney = el.orderMoney;
                var html = '';
                html += '<tr onclick="tOrderId.value=\'' + tOrderId + '\',' +
                    'tOrderName.value=\'' + tOrderName + '\',' +
                    'tOrderIDcard.value=\'' + tOrderIDcard + '\',' +
                    'tOrderAllTime.value=\'' + tOrderAllTime + '\',' +
                    'tCheckNum.value=\'' + tCheckNum + '\',' +
                    'tTypeId.value=\'' + tTypeId + '\',' +
                    'tPrice.value=\'' + tPrice + '\',' +
                    'tCheckPrice.value=\'' + tCheckPrice + '\',' +
                    'tOrderMoney.value=\'' + tOrderMoney + '\',' +
                    'tTypeName.value=\'' + tTypeName + '\',' +
                    'tOrderPhone.value=\'' + tOrderPhone +'\'" >';
                html += '	<td>' + el.orderId + '</td>';
                html += '	<td>' + el.orderName + '</td>';
                html += '	<td>' + el.orderPhone + '</td>';
                html += '	<td>' + el.orderIDcard + '</td>';
                html += '	<td>' + el.orderMoney + '</td>';
                html += '	<td>' + el.arrireDate + '</td>';
                html += '	<td>' + el.leaveDate + '</td>';
                html += '</tr>';

                //追加到表格中
                $('#dataTable').append(html);
            })
        })
    })

</script>
</body>
</html>
