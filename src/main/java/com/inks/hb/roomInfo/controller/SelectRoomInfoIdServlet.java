package com.inks.hb.roomInfo.controller;

import com.google.gson.Gson;
import com.inks.hb.orderinfo.service.OrderInfoServiceImpl;
import com.inks.hb.roomInfo.service.impl.RoomInfoServiceImpl;

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
 * @date 2024/12/24 - 14:58
 */
@WebServlet(name = "SelectRoomInfoIdServlet",value = "/selectRoomInfoIdServlet")
public class SelectRoomInfoIdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //响应输出流
        PrintWriter out = resp.getWriter();
        RoomInfoServiceImpl roomInfoService = new RoomInfoServiceImpl();

        ArrayList list = roomInfoService.query(1, roomInfoService.queryOrderInfoNum());
        //转换Json字符串
        Gson gson = new Gson();
        out.println(gson.toJson(list));
    }
}
