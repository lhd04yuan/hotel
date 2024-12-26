package com.inks.hb.checkInInfo.pojo;

import com.inks.hb.floorinfo.pojo.FloorInfo;
import com.inks.hb.login.pojo.Login;
import com.inks.hb.roomInfo.pojo.RoomInfo;
import com.inks.hb.roomtype.pojo.RoomType;

/**
 * @author Sky-Sheep
 * @date 2024/12/26 - 9:01
 */
public class CheckInTable {
    private String checkId;
    private String orderId; //预订单号->直接获取时间戳?存进去 1

    private String orderName; //预定人 2

    private String orderPhone; //联系电话 3

    private String orderIDcard; // 4

    private String floorName;

    private String arrireDate; //抵店时间 5

    private String leaveDate; //离店时间 6

    private String typeId; //房间类型->对应roomtype表 7<-------------------

    private String checkNum; //入住人数 8

    private String price; //客房价格 9

    private String checkPrice; //入住价格 10

    private int discount; //折扣 11<-------------------

    private String discountReason; //折扣原因 12

    private String addBed; //是否加床 13

    private String addBedPrice; //加床价格 14

    private String orderMoney; //预收款 15

    private String orderState; //单据状态->该字段前端做下拉框写死 16

    private String remark; //备注 17

    private String operatorId; //操作员->登录 19<-------------------

    public CheckInTable() {
    }

    public CheckInTable(String checkId, String orderId, String orderName, String orderPhone, String orderIDcard, String floorName, String arrireDate, String leaveDate, String typeId, String checkNum, String price, String checkPrice, int discount, String discountReason, String addBed, String addBedPrice, String orderMoney, String orderState, String remark, String operatorId) {
        this.checkId = checkId;
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.orderIDcard = orderIDcard;
        this.floorName = floorName;
        this.arrireDate = arrireDate;
        this.leaveDate = leaveDate;
        this.typeId = typeId;
        this.checkNum = checkNum;
        this.price = price;
        this.checkPrice = checkPrice;
        this.discount = discount;
        this.discountReason = discountReason;
        this.addBed = addBed;
        this.addBedPrice = addBedPrice;
        this.orderMoney = orderMoney;
        this.orderState = orderState;
        this.remark = remark;
        this.operatorId = operatorId;
    }
}
