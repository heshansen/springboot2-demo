package com.yss.sofa.licensedemo.domain;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 3661987297061629506L;
    private String title;
    private String content;
    private String etraInfo;

    public Message(String title, String content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
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

    public String getEtraInfo() {
        return etraInfo;
    }

    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }
}
