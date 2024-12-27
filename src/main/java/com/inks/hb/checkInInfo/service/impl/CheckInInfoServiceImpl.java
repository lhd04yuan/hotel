package com.inks.hb.checkInInfo.service.impl;

import com.inks.hb.checkInInfo.dao.CheckInInfoDao;
import com.inks.hb.checkInInfo.pojo.CheckIn;
import com.inks.hb.checkInInfo.service.CheckInInfoService;
import com.inks.hb.orderinfo.pojo.OrderInfo;
import com.inks.hb.roomtype.dao.RoomTypeDao;
import com.inks.hb.roomtype.pojo.RoomType;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/23 - 15:27
 */
public class CheckInInfoServiceImpl implements CheckInInfoService {
    private CheckInInfoDao checkInInfoDao = new CheckInInfoDao();

    @Override
    public int insertOrderInfo(CheckIn checkIn) {
        try {
            String checkId = checkIn.getCheckId();
            if (queryRepeat(checkId, checkId) != 1)
                return 0;
            checkInInfoDao.insertData(checkIn);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }



    @Override
    public int updateOrderInfo(CheckIn checkIn) {
        try {
            checkInInfoDao.updateData(checkIn);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int queryRepeat(String newName, String oldName) {
        CheckIn checkInQuery = new CheckIn();
        checkInQuery.setCheckId(newName);
        CheckIn checkIn;
        try {
            checkIn = (CheckIn) checkInInfoDao.query(checkInQuery);
            if (!checkIn.isNull()) {
                if (checkIn.getCheckId().equals(oldName))
                    return 2; //和旧名称重复
                return 0; //重复
            } else
                return 1; //不重复
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1; //异常
        }
    }

    @Override
    public ArrayList<CheckIn> queryCheckIn(int make, String select) {
        if (make == 2) { //2：查类型
            try {
                // 然而这样写失去了对类型的模糊查询
                RoomType roomType = new RoomTypeDao().queryName(select);
                select = roomType.getTypeId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            return checkInInfoDao.queryCheck(make, select);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteCheckInInfo(String checkInId) {
        CheckIn checkIn = new CheckIn();
        checkIn.setCheckId(checkInId);
        try {
            checkInInfoDao.deleteData(checkInId);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int queryCheckInInfoNum() {
        try {
            return checkInInfoDao.queryDataNum();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
    }

    @Override
    public ArrayList<CheckIn> query(int page, int limit) {
        int start = (page * limit) - limit + 1; //每一页的起始位置

        if (start < 1)  //小于1的话会触发一个错误
            start = 1;  //但是理论上page和limit是由table表格生成的参数

        try {
            return checkInInfoDao.query(start, limit);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }
}
