package com.ujiuye.crmpro.message.pojo;

import java.util.Date;

public class Email {
    private String receive;
    private String title;
    private String content;
    private String isTime;
    private Date time;

    public String getIsTime() {
        return isTime;
    }

    public void setIsTime(String isTime) {
        this.isTime = isTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
