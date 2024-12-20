package com.inks.hb.common;

import com.inks.hb.login.controller.FrontCodeImageServlet;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Sky-Sheep
 * @date 2024/12/20 - 12:51
 */
public class CaptchaImageUtil {
    private static Random random = new Random();
    private static String words = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPSDFGHJKLZXCVBNM";
    private static Graphics graphics;
    private static StringBuffer stringBuffer;


    public static StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    /**
     * 验证码背景生成
     * @param width  宽
     * @param height 高
     * @param bufferedImage 画板
     * @return 将画笔返回
     */
    public static void captchaImageBackgroundGenerator(BufferedImage bufferedImage,int width, int height) {
        // 获取笔触
        graphics = bufferedImage.getGraphics();
        // 设置笔的颜色
        graphics.setColor(Color.LIGHT_GRAY);
        // 图形,填充矩形
        graphics.fillRect(0, 0, width, height);
    }

    /**
     * 生成干扰点
     * @param width 宽
     * @param height 高
     * @param points 干扰点数
     * @return 将画笔返回
     */
    public static void captchaImagePointGenerator(int width, int height,int points) {
//        int points = 800;
        for (int i = 0; i < points; i++) {
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
        }
    }

    /**
     * 生成验证码字符
     * @param count 验证码字符数量
     * @param size 字体大小
     * @param x 滑板的x轴位置
     * @param y 滑板的y轴位置
     * @return  将画笔返回
     */
    public static void captchaImageGenerator(int count,int size,int x,int y) {
        // 记录生成的字符
        stringBuffer = new StringBuffer();
        // 设置绘制的文本字体
        graphics.setFont(new Font("宋体", Font.BOLD + Font.ITALIC, size));
        //绘制验证码
        for (int i = 0; i < count; i++) {
            // 设置笔触的随机颜色
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            // 随机数
            int index = random.nextInt(words.length());
            // 获取随机数的字符
            char c = words.charAt(index);
            stringBuffer.append(c);
            graphics.drawString(String.valueOf(c), x + (i * size), y);
        }
    }

    /**
     * 绘制干扰线
     * @param size 干扰线数量
     * @param turningPoint 线条转折点
     * @param width 验证码图片宽度
     * @param height 验证码图片高度
     */
    public static void captchaImageLinesGenerator(int size,int turningPoint,int width,int height){
        // 绘制干扰线
        int line = random.nextInt(size) + 1;
        //获得最大宽度的最小分成
        int turningPointWidthLine =  width / (turningPoint - 1);
        //获取y仲中间位置
        int turningPointHeightLine = height / 2;
        for (int j = 0; j < line; j++) {
            graphics.setColor(Color.BLACK);
            int[] xPoints = new int[turningPoint];
            int[] yPoints = new int[turningPoint];
            xPoints[0] = 0;
            //xPoints[9] = width;
            for (int i = 1; i < xPoints.length; i++) {
                xPoints[i] = xPoints[i-1] + turningPointWidthLine;
            }
            for (int i = 0; i < yPoints.length; i++) {
                yPoints[i] = random.nextInt(turningPointHeightLine) + 10;
            }
            // 绘制折线，通过多次调用 drawLine() 方法
            for (int i = 0; i < xPoints.length - 1; i++) {
                graphics.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
            }
        }
    }

}
