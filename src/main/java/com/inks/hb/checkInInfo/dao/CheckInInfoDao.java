package com.inks.hb.checkInInfo.dao;

import com.inks.hb.checkInInfo.pojo.CheckIn;
import com.inks.hb.common.CommonDao;
import com.inks.hb.common.DBUtil;
import com.inks.hb.login.pojo.Login;
import com.inks.hb.orderinfo.pojo.OrderInfo;
import com.inks.hb.roomInfo.dao.RoomInfoDao;
import com.inks.hb.roomInfo.pojo.RoomInfo;
import com.inks.hb.roomtype.dao.RoomTypeDao;
import com.inks.hb.roomtype.pojo.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/23 - 15:28
 */
public class CheckInInfoDao implements CommonDao {
    private RoomTypeDao roomTypeDao = new RoomTypeDao();
    private RoomInfoDao roomInfoDao = new RoomInfoDao();
    @Override
    public void insertData(Object o) throws SQLException {
        CheckIn checkIn = (CheckIn) o;
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO checkininfo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, checkIn.getCheckId());
        pstmt.setString(2, checkIn.getOrderId());
        pstmt.setString(3, checkIn.getOrderName());
        pstmt.setString(4, checkIn.getOrderPhone());
        pstmt.setString(5, checkIn.getOrderIDcard());
        pstmt.setString(6, checkIn.getTypeId().getTypeId());
        pstmt.setString(7, checkIn.getArrireDate());
        pstmt.setString(8, checkIn.getLeaveDate());
        pstmt.setString(9, checkIn.getOrderState());
        pstmt.setString(10, checkIn.getCheckNum());
        pstmt.setString(11, String.valueOf(checkIn.getRoomId().getRoomId()));
        pstmt.setString(12, checkIn.getPrice());
        pstmt.setString(13, checkIn.getCheckPrice());
        pstmt.setInt(14, checkIn.getDiscount());
        pstmt.setString(15, checkIn.getDiscountReason());
        pstmt.setString(16, checkIn.getAddBed());
        pstmt.setString(17, checkIn.getAddBedPrice());
        pstmt.setString(18, checkIn.getOrderMoney());
        pstmt.setString(19, checkIn.getMoney());
        pstmt.setString(20, checkIn.getCheckMoney());
        pstmt.setString(21, checkIn.getCheckMoneyPrice());
        pstmt.setString(22, checkIn.getCheckMoneyDate());
        pstmt.setString(23, checkIn.getRemark());
        pstmt.setString(24, checkIn.getOperatorId().getLoginName());

        pstmt.executeUpdate();

        pstmt.close();
    }

    @Override
    public void deleteData(Object o) throws SQLException {
        CheckIn checkIn = (CheckIn) o;
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM checkininfo WHERE checkId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, checkIn.getCheckId());
        pstmt.executeUpdate();
        pstmt.close();

    }

    @Override
    public void updateData(Object o) throws SQLException {

    }

    @Override
    public int queryDataNum() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM checkininfo";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int num;
        if (rs.next()) num = rs.getInt("count(*)");
        else num = 0;
        rs.close();
        pstmt.close();
        return num;
    }

    @Override
    public ArrayList query(int start, int length) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM checkininfo LIMIT ?,?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, start - 1);
        pstmt.setInt(2, length);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<CheckIn> list = new ArrayList<>();
        CheckIn checkIn;
        while (rs.next()) {
            RoomType roomType = (RoomType)roomTypeDao.query(new RoomType(rs.getString(6)));
            RoomInfo roomInfo = (RoomInfo)roomInfoDao.query(new RoomInfo(Integer.parseInt(rs.getString(11))));
            checkIn = new CheckIn(rs.getString(1), rs.getString(2), rs.getString(3)
                    , rs.getString(4) , rs.getString(5) ,roomType
                    , rs.getString(7), rs.getString(8), rs.getString(9)
                    , rs.getString(10), roomInfo, rs.getString(12)
                    , rs.getString(13), rs.getInt(14), rs.getString(15)
                    , rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)
                    , rs.getString(20), rs.getString(21), rs.getString(22)
                    , rs.getString(23), new Login(rs.getString(24)));
            list.add(checkIn);
        }
        return list;
    }

    @Override
    public Object query(Object o) throws SQLException {
        CheckIn checkInQuery = (CheckIn) o;
        Connection connection = DBUtil.getConnection();

        //根据ID查询
        String sql = "SELECT * FROM checkininfo WHERE checkId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, checkInQuery.getCheckId());
        ResultSet rs = pstmt.executeQuery();

        CheckIn checkIn = null;

        while (rs.next()) {
            checkIn = new CheckIn(rs.getString(1), rs.getString(2), rs.getString(3)
                    , rs.getString(4) , rs.getString(5) ,new RoomType(rs.getString(6))
                    , rs.getString(7), rs.getString(8), rs.getString(9)
                    , rs.getString(10), new RoomInfo(Integer.parseInt(rs.getString(11)))
                    , rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)
                    , rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)
                    , rs.getString(20), rs.getString(21), rs.getString(22)
                    , rs.getString(23), new Login(rs.getString(24)));
        }
        if (checkIn == null) {
            checkIn = new CheckIn();
            checkIn.setNull(true);
        }
        rs.close();
        pstmt.close();
        return checkIn;
    }

    /**
     * 查询啊,模糊查询呀
     *
     * @param make   1：查名称 2：查类型
     * @param select 待查的值
     * @return 查询对象
     * @throws SQLException 抛给业务层
     */
    public ArrayList<CheckIn> queryCheck(int make, String select) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql;
        if (make == 1)
            sql = "select * from checkininfo WHERE checkName LIKE ?;";
        else
            sql = "select * from checkininfo WHERE typeId LIKE ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + select + "%");
        ResultSet rs = pstmt.executeQuery();
        ArrayList<CheckIn> list = new ArrayList<>();
        CheckIn checkIn;
        while (rs.next()) {
            checkIn = new CheckIn(rs.getString(1), rs.getString(2), rs.getString(3)
                    , rs.getString(4) , rs.getString(5) ,new RoomType(rs.getString(6))
                    , rs.getString(7), rs.getString(8), rs.getString(9)
                    , rs.getString(10), new RoomInfo(Integer.parseInt(rs.getString(11)))
                    , rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)
                    , rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)
                    , rs.getString(20), rs.getString(21), rs.getString(22)
                    , rs.getString(23), new Login(rs.getString(24)));
            list.add(checkIn);
        }
        rs.close();
        pstmt.close();
        return list;
    }
}
