package com.xbb.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author xiaobaobao
 * @date 2019/8/1 10:02
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 8087338485286619591L;

    private String id;
    private String name;
    private String messageId;

    public Order() {
    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
