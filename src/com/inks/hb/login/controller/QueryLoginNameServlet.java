package com.inks.hb.login.controller;

import com.google.gson.Gson;
import com.inks.hb.login.service.LoginService;
import com.inks.hb.login.service.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/QueryLoginNameServlet", name = "/QueryLoginNameServlet")
public class QueryLoginNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 响应输出流
        PrintWriter out = response.getWriter();

        // 调用service
        LoginService service = new LoginServiceImpl();

        // 获得姓名
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");

        try {

            int check = service.queryByName(loginName,loginPwd);

            //转换为json字符串格式
            Gson gson = new Gson();
            out.print(gson.toJson(check));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}