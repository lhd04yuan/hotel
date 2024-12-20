package com.inks.hb.listener;


import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author Sky-Sheep
 * @date 2024/12/18 - 14:38
 */
@WebListener
public class JdbcDriverUnregisterListener implements ServletContextListener {
    Logger logger = Logger.getLogger(JdbcDriverUnregisterListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        AbandonedConnectionCleanupThread.uncheckedShutdown();
        Enumeration<Driver> enumeration = DriverManager.getDrivers();
        while (enumeration.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(enumeration.nextElement());
                logger.debug("成功注销JDBC驱动程序: " + enumeration.getClass().getName());
//                System.out.println("成功注销JDBC驱动程序: " + enumeration.getClass().getName());
            } catch (SQLException e) {
                logger.debug("无法注销JDBC驱动程序: " + enumeration.getClass().getName() + "，原因: " + e.getMessage());
//                System.err.println("无法注销JDBC驱动程序: " + enumeration.getClass().getName() + "，原因: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        // 尝试注销所有已注册的JDBC驱动程序
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            System.out.println(System.getProperty("file.encoding"));
//            try {
//                DriverManager.deregisterDriver(driver);
//                System.out.println("成功注销JDBC驱动程序: " + driver.getClass().getName());
//            } catch (SQLException e) {
//                System.err.println("无法注销JDBC驱动程序: " + driver.getClass().getName() + "，原因: " + e.getMessage());
//            }
//        }
//    }

}
