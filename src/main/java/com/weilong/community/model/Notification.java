package com.weilong.community.model;

public class Notification {
    private Integer id;

    private Integer notifier;

    private Integer receiver;

    private Integer outerid;

    private Integer type;

    private Long gmtcreate;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotifier() {
        return notifier;
    }

    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getOuterid() {
        return outerid;
    }

    public void setOuterid(Integer outerid) {
        this.outerid = outerid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}