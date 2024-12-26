package com.inks.hb.checkInInfo.controller;

import com.google.gson.Gson;
import com.inks.hb.checkInInfo.pojo.CheckIn;
import com.inks.hb.checkInInfo.pojo.CheckInTable;
import com.inks.hb.checkInInfo.service.impl.CheckInInfoServiceImpl;
import com.inks.hb.common.PojotoGson;
import com.inks.hb.floorinfo.service.FloorInfoServiceImpl;
import com.inks.hb.orderinfo.pojo.OrderInfo;
import com.inks.hb.orderinfo.pojo.OrderToTable;
import com.inks.hb.roomInfo.service.impl.RoomInfoServiceImpl;
import com.inks.hb.roomtype.service.RoomTypeService;
import com.inks.hb.roomtype.service.RoomTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/26 - 8:33
 */
@WebServlet(name = "CheckInInfoServlet",value = "/checkInInfoServlet")
public class CheckInInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        int page = Integer.parseInt(req.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(req.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(req.getParameter("make")); //状态标志
        CheckInInfoServiceImpl service = new CheckInInfoServiceImpl();
        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        FloorInfoServiceImpl floorInfoService = new FloorInfoServiceImpl();

        // 默认输出信息
        String code = "0"; //状态码
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数
        ArrayList<CheckIn> list = new ArrayList<>();
        ArrayList<CheckInTable> checkInTables = new ArrayList<>();
        CheckInTable checkInTable;

        //单个全局属性
        String orderId; //预定单号
        String select; //查询值

        //获取对应状态属性
        if (make == 1 || make == 2) { //1和2这部分完全是相同的
            select = req.getParameter("select");
            list = service.queryCheckIn(make, select);
            count = String.valueOf(list.size());
            msg = "结果如下";
        }else if (make == 4) {
            orderId = req.getParameter("checkId");
            if (service.deleteCheckInInfo(orderId) == -1) {
                msg = "删除失败";
                code = "-1";
            }
            count = String.valueOf(service.queryCheckInInfoNum());
        }else {
            list = service.query(page, limit);
            count = String.valueOf(service.queryCheckInInfoNum());
        }

        for (CheckIn checkIn : list) { //转换成前端界面可接收的类型 主要是转 房间类型 和操作员
            checkInTable = new CheckInTable(checkIn.getCheckId(), checkIn.getOrderId(), checkIn.getOrderName(), checkIn.getOrderPhone()
                    , checkIn.getOrderIDcard(),floorInfoService.query(String.valueOf(checkIn.getRoomId().getRoomId())).getFloorName(), checkIn.getArrireDate(), checkIn.getLeaveDate()
                    , roomTypeService.query(checkIn.getTypeId().getTypeId()).getTypeName()
                    , checkIn.getCheckNum(), checkIn.getPrice(), checkIn.getCheckPrice()
                    , checkIn.getDiscount(), checkIn.getDiscountReason(), checkIn.getAddBed()
                    , checkIn.getAddBedPrice(), checkIn.getOrderMoney(), checkIn.getOrderState()
                    , checkIn.getRemark(), checkIn.getOperatorId().getLoginName());
            checkInTables.add(checkInTable);
        }
        PojotoGson pojotoGson = new PojotoGson(code, msg, count, checkInTables);
        Gson gson = new Gson();
        out.print(gson.toJson(pojotoGson));

    }
}
