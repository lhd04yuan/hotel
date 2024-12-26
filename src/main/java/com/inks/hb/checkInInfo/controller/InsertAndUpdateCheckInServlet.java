package com.inks.hb.checkInInfo.controller;

import com.google.gson.Gson;
import com.inks.hb.checkInInfo.pojo.CheckIn;
import com.inks.hb.checkInInfo.service.impl.CheckInInfoServiceImpl;
import com.inks.hb.floorinfo.service.FloorInfoServiceImpl;
import com.inks.hb.login.pojo.Login;
import com.inks.hb.roomInfo.pojo.RoomInfo;
import com.inks.hb.roomInfo.service.impl.RoomInfoServiceImpl;
import com.inks.hb.roomtype.pojo.RoomType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sky-Sheep
 * @date 2024/12/23 - 15:22
 */
@WebServlet(name = "InsertAndUpdateCheckInServlet",value = "/insertAndUpdateCheckInServlet")
public class InsertAndUpdateCheckInServlet extends HttpServlet {
    private RoomInfoServiceImpl roomInfoService = new RoomInfoServiceImpl();

    private CheckInInfoServiceImpl checkInInfoService = new CheckInInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码和响应类型
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String orderId = req.getParameter("orderId");  //1
        String orderName = req.getParameter("orderName");  //2
        String orderPhone = req.getParameter("orderPhone"); //3
        String orderIDcard = req.getParameter("orderIDcard");  //4
        RoomType typeId = new RoomType(req.getParameter("typeId")); //5
        String arrireDate = req.getParameter("arrireDate"); //6
        String leaveDate = req.getParameter("leaveDate"); //7
        String orderState = req.getParameter("orderState"); //8
        String checkNum = req.getParameter("checkNum"); //9
        RoomInfo roomId = new RoomInfo(Integer.parseInt(req.getParameter("roomId"))); //10
        String price = req.getParameter("price"); //11
        String checkPrice = req.getParameter("checkPrice"); //12
        String discountReason = req.getParameter("discountReason"); //14
        String addBed = req.getParameter("addBed"); //15
        String addBedPrice = req.getParameter("addBedPrice"); //16
        String orderMoney = req.getParameter("orderMoney"); //17
        String remark = req.getParameter("remark"); //18
        Login operatorId = new Login(req.getParameter("operatorId")); //19
        String checkId = req.getParameter("checkId"); //20
        String checkMoneyPrice = req.getParameter("checkMoneyPrice"); //21
        String checkMoneyDate = req.getParameter("checkMoneyDate"); //22
        String checkMoney = req.getParameter("checkMoney"); //22
        String money = req.getParameter("money"); //23

        int make = Integer.parseInt(req.getParameter("make")); // 24 标志啊
        int discount;
        try { //对折扣值为空的处理
            discount = Integer.parseInt(req.getParameter("discount")); //13
        } catch (NumberFormatException e) {
            discount = 0;
        }

        CheckIn checkIn = new CheckIn(checkId,orderId, orderName, orderPhone, orderIDcard, typeId, arrireDate, leaveDate, orderState, checkNum, roomId, price, checkPrice, discount, discountReason, addBed, addBedPrice, orderMoney,checkMoney,money, checkMoneyPrice,checkMoneyDate, remark, operatorId);

        int code = -1; //返回的状态

        if (make == 1) { //1.新增
            code = checkInInfoService.insertOrderInfo(checkIn);
            //更新房间信息
            roomInfoService.updateRoomInfoStatus(checkIn.getRoomId());

        } else if (make == 2) { //修改
            code = checkInInfoService.updateOrderInfo(checkIn);
        }


        //make=1 -> 1:插入成功 0：存在同名项 -1:插入失败
        //make=2 -> 1:修改成功 -1;修改失败
        Gson gson = new Gson();
        out.print(gson.toJson(code));

    }
}
