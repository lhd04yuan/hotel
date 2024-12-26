package com.inks.hb.roomInfo.service;

import com.inks.hb.roomInfo.pojo.RoomInfo;

import java.util.ArrayList;

/**
 * @author Sky-Sheep
 * @date 2024/12/24 - 15:04
 */
public interface RoomInfoService {

    //查询多条
    ArrayList query(int page, int limit);

    //查询数条数
    int queryOrderInfoNum();

    //更新房间状态
    void updateRoomInfoStatus(RoomInfo roomId);


    //根据Id查询
    Object query(Integer roomId);
}
