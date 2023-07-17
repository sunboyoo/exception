package org.lcm.spring.exception.factory.impl;

// 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
public enum FrontEndErrorShowType {
    // 不提示错误
    SILENT(0, "SILENT"),
    // 警告信息提示
    MESSAGE_WARN(1, "MESSAGE_WARN"),
    // 错误信息提示
    MESSAGE_ERROR(2, "MESSAGE_ERROR"),
    // 通知提示
    NOTIFICATION(4, "NOTIFICATION"),
    // 页面跳转
    REDIRECT(9, "REDIRECT");

    private final int code;
    private final String type;
    FrontEndErrorShowType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode(){
        return this.code;
    }

    public String getType(){
        return this.type;
    }
}
