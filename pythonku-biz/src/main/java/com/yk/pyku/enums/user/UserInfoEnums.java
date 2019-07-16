package com.yk.pyku.enums.user;

public enum UserInfoEnums {
    USER_FORBIDDEN(0,"禁用"),
    USER_NORMAL_TRY(1,"试用"),
    USER_NORMAL_VIP(2,"会员"),
    USER_TRY_DAYS(14,"试用默认天数")
    ;
    private Integer index;
    private String content;

    UserInfoEnums(Integer index, String content) {
        this.index = index;
        this.content = content;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
