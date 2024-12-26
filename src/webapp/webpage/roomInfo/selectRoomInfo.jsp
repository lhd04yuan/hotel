<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../../js/layui/css/layui.css" media="all">
  <script src="../../js/layui/layui.js"></script>
  <script src="../../js/jquery.js"></script>
  <script src="../../js/global.js"></script>
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
    <th>ID</th>
    <th>类型名称</th>
    <th>楼层名称</th>
    <th>状态</th>
    <th>是否可拼房</th>
  </tr>
</table>
<div class="fixDiv">
  <label class="layui-form-label">当前选中：</label>
  <div class="layui-input-inline">
    <input type="text" id="tRoomId" class="layui-input" placeholder="ID" readonly>
  </div>
  <div class="layui-input-inline">
    <input type="text" id="tFloorName" class="layui-input" placeholder="房间号" readonly>
  </div>
  <div class="layui-input-inline">
    <input type="text" id="tTypeName" class="layui-input" placeholder="房间类型" readonly>
  </div>
  <div class="layui-input-inline">
    <input type="text" id="tStatus" class="layui-input" placeholder="房间状态" readonly>
  </div>
  <div class="layui-input-inline layui-hide">
    <input type="text" id="tTypeId" class="layui-input" placeholder="类型ID" readonly>
  </div>
</div>

<script>
  //网页加载完毕
  $(document).ready(function() {

    //发出ajax请求，调用后端数据
    $.getJSON(baseUrl + '/selectRoomInfoIdServlet', function(data) {

      //遍历响应的json数组

      $.each(data, function(index, el) {
        var tRoomId = el.roomId;
        var tFloorName = el.floorId.floorName;
        var tTypeName = el.typeId.typeName;
        var tTypeId = el.typeId.typeId;
        var tStatus = el.status.toString() === "true" ? '有房':'满房';
        var tIsSplice = el.isSplice.toString() === "Y" ? '是':'否';
        var html = '';
        html += '<tr onclick="tRoomId.value=\'' + tRoomId + '\',' +
                'tFloorName.value=\'' + tFloorName + '\',' +
                'tTypeName.value=\'' + tTypeName + '\',' +
                'tTypeId.value=\'' + tTypeId + '\',' +
                'tStatus.value=\'' + tStatus +'\'" >';
        html += '	<td>' + el.roomId + '</td>';
        html += '	<td>' + el.typeId.typeName + '</td>';
        html += '	<td>' + el.floorId.floorName + '</td>';
        html += '	<td>' + tStatus + '</td>';
        html += '	<td>' + tIsSplice + '</td>';
        html += '</tr>';

        //追加到表格中
        $('#dataTable').append(html);

      });

    });
  });
</script>
</body>

</html>