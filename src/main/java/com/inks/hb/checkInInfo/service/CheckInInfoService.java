package com.inks.hb.checkInInfo.service;

import com.inks.hb.checkInInfo.pojo.CheckIn;

import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/23 - 15:26
 */
public interface CheckInInfoService {
    //新增入住信息
    int insertOrderInfo(CheckIn checkIn);

    //修改入住信息
    int updateOrderInfo(CheckIn checkIn);

    int queryRepeat(String orderId, String orderId1);

    //俩查询 1：查名称 2：查类型
    ArrayList<CheckIn> queryCheckIn(int make, String select);

    //删除
    int deleteCheckInInfo(String checkInId);

    //查询长度
    int queryCheckInInfoNum();

    //查询多条
    ArrayList<CheckIn> query(int page, int limit);
}
