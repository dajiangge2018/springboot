package com.jiang.demo.vo;

import java.util.Date;

public class AreaVO {
    public Integer areaId;

    public String areaName;

    public Integer areaIndex;

    public Date createDate;

    public Date lastUpdateDate;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaIndex() {
        return areaIndex;
    }

    public void setAreaIndex(Integer areaIndex) {
        this.areaIndex = areaIndex;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
