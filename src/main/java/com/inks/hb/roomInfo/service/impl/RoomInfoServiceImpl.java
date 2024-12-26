package com.inks.hb.roomInfo.service.impl;

import com.inks.hb.roomInfo.dao.RoomInfoDao;
import com.inks.hb.roomInfo.pojo.RoomInfo;
import com.inks.hb.roomInfo.service.RoomInfoService;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/24 - 15:05
 */
public class RoomInfoServiceImpl implements RoomInfoService {
    private RoomInfoDao dao = new RoomInfoDao();
    @Override
    public ArrayList query(int page, int limit) {
        int start = (page * limit) - limit + 1; //每一页的起始位置

        if (start < 1)  //小于1的话会触发一个错误
            start = 1;  //但是理论上page和limit是由table表格生成的参数

        try {
            return dao.query(start, limit);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public int queryOrderInfoNum() {
        try {
            return dao.queryDataNum();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateRoomInfoStatus(RoomInfo roomId) {
        try {
            dao.updateStatus(roomId);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }
    }

    @Override
    public Object query(Integer roomId) {
        return null;
    }
}
