package com.zlead.domain;

public class IpEntity {

    private String ipAddress;
    private Long time;
    private Integer recount;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getRecount() {
        return recount;
    }

    public void setRecount(Integer recount) {
        this.recount = recount;
    }
}
