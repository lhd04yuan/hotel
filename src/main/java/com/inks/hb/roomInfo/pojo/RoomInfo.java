package com.inks.hb.roomInfo.pojo;

import com.inks.hb.floorinfo.pojo.FloorInfo;
import com.inks.hb.roomtype.pojo.RoomType;

/**
 * @author Sky-Sheep
 * @date 2024/12/24 - 14:41
 */
public class RoomInfo {
    private Integer roomId;         //房间主键
    private RoomType typeId;        //类型编号
    private FloorInfo floorId;      //楼层编号
    private Integer ratedNum;       //额定人数
    private Integer bedNum;         //床数
    private String roomDescription; //客房描述
    private String remark;          //备注
    private String status;          //状态
    private String isSplice;        //是否可拼房\

    public RoomInfo(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "roomId=" + roomId +
                ", typeId=" + typeId +
                ", floorId=" + floorId +
                ", ratedNum=" + ratedNum +
                ", bedNum=" + bedNum +
                ", roomDescription='" + roomDescription + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", isSplice='" + isSplice + '\'' +
                '}';
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public RoomType getTypeId() {
        return typeId;
    }

    public void setTypeId(RoomType typeId) {
        this.typeId = typeId;
    }

    public FloorInfo getFloorId() {
        return floorId;
    }

    public void setFloorId(FloorInfo floorId) {
        this.floorId = floorId;
    }

    public Integer getRatedNum() {
        return ratedNum;
    }

    public void setRatedNum(Integer ratedNum) {
        this.ratedNum = ratedNum;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSplice() {
        return isSplice;
    }

    public void setIsSplice(String isSplice) {
        this.isSplice = isSplice;
    }

    public RoomInfo() {
    }

    public RoomInfo(Integer roomId, RoomType typeId, FloorInfo floorId, Integer ratedNum, Integer bedNum, String roomDescription, String remark, String status, String isSplice) {
        this.roomId = roomId;
        this.typeId = typeId;
        this.floorId = floorId;
        this.ratedNum = ratedNum;
        this.bedNum = bedNum;
        this.roomDescription = roomDescription;
        this.remark = remark;
        this.status = status;
        this.isSplice = isSplice;
    }
}
