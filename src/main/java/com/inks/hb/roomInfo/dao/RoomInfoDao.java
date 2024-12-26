package com.inks.hb.roomInfo.dao;

import com.inks.hb.common.CommonDao;
import com.inks.hb.common.DBUtil;
import com.inks.hb.floorinfo.dao.FloorInfoDao;
import com.inks.hb.floorinfo.pojo.FloorInfo;
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
 * @date 2024/12/24 - 15:05
 */
public class RoomInfoDao implements CommonDao {
    //房间类型DAO
    private RoomTypeDao roomTypeDao = new RoomTypeDao();
    //楼层信息DAO
    private FloorInfoDao floorInfoDao = new FloorInfoDao();
    @Override
    public void insertData(Object o) throws SQLException {

    }

    @Override
    public void deleteData(Object o) throws SQLException {

    }

    @Override
    public void updateData(Object o) throws SQLException {

    }

    @Override
    public int queryDataNum() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from roominfo;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        int num;
        if (rs.next()) num = rs.getInt("count(*)");
        else num = 0;

        rs.close();
        pstmt.close();

        return num;
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList query(int start, int length) throws SQLException {
        Connection conn = DBUtil.getConnection();

        String sql = "select * from roominfo limit ?, ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, start - 1);
        pstmt.setInt(2, length);
        ResultSet rs = pstmt.executeQuery();


        ArrayList<RoomInfo> list = new ArrayList<>();
        RoomInfo roomInfo;

        while (rs.next()) {
            //查询房间类型
            RoomType roomType = (RoomType)roomTypeDao.query(new RoomType(rs.getString(2)));
            //查询楼层信息
            FloorInfo floorInfo = (FloorInfo)floorInfoDao.query(new FloorInfo(Integer.parseInt(rs.getString(3))));
            //封装到对象
            roomInfo = new RoomInfo(Integer.parseInt(rs.getString(1)),roomType,floorInfo,rs.getInt(4),
                    rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
            //添加至集合
            list.add(roomInfo);
        }

        rs.close();
        pstmt.close();

        return list;
    }

    @Override
    public Object query(Object o) throws SQLException {
        RoomInfo roomInfoQuery = (RoomInfo)o;
        Connection connection = DBUtil.getConnection();
        String sql = "select * from roominfo where roomId=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, roomInfoQuery.getRoomId());
        ResultSet rs = pstmt.executeQuery();
        RoomInfo roomInfo = null;
        while (rs.next()) {
            roomInfo = new RoomInfo(Integer.parseInt(rs.getString(1)),new RoomType(rs.getString(2)),new FloorInfo(rs.getInt(3)),rs.getInt(4),
                    rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        }
        if (roomInfo == null){
            roomInfo = new RoomInfo();
        }
        return roomInfo;
    }

    //更新状态
    public void updateStatus(RoomInfo roomId) throws SQLException {
        Connection conn = DBUtil.getConnection();

        RoomInfo roomInfo = (RoomInfo)query(roomId);
        String sql = "update roominfo set status = ? where roomId = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "false");
        pstmt.setInt(2, roomInfo.getRoomId());
        pstmt.executeUpdate();
        pstmt.close();
    }
}
