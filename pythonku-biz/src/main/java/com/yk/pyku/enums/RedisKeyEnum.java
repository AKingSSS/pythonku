package com.yk.pyku.enums;

public enum RedisKeyEnum {
    SmsLogin(1, "SmsLogin", "登录短信验证码", "SMS_LOGIN_%s");
    private Integer index;
    private String code;
    private String desc;
    private String content;

    RedisKeyEnum(Integer index, String code, String desc, String content) {
        this.index = index;
        this.code = code;
        this.desc = desc;
        this.content = content;
    }
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
