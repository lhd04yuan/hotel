package com.inks.hb.login.controller;

import com.inks.hb.common.CaptchaImageUtil;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Sky-Sheep
 * @date 2024/12/19 - 13:23
 */
@WebServlet(value = "/captcha")
public class FrontCodeImageServlet extends HttpServlet {
    Logger log = Logger.getLogger(FrontCodeImageServlet.class);
    private String words = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPSDFGHJKLZXCVBNM";
    private Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
//        尺寸
        int width = 130;
        int height = 45;
        //创建画板
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建验证码背景
        CaptchaImageUtil.captchaImageBackgroundGenerator(bufferedImage, width, height);
        //绘画干扰点
        CaptchaImageUtil.captchaImagePointGenerator(width,height,800);
        //绘制验证码字符
        CaptchaImageUtil.captchaImageGenerator(4,30,10,35);
        //绘制干扰线
        CaptchaImageUtil.captchaImageLinesGenerator(2,11,width,height);
        StringBuffer stringBuffer = CaptchaImageUtil.getStringBuffer();
        log.debug("生成的code=" + stringBuffer.toString());
        // 获取响应流
        ServletOutputStream outputStream = resp.getOutputStream();
        // 输出图片到客户端
        ImageIO.write(bufferedImage, "png", outputStream);;
    }
}
