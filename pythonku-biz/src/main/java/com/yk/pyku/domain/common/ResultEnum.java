package com.yk.pyku.domain.common;


/**
 * @ClassName ResultEnum
 * @Description 参数
 * 参考微信公众号编码：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234
 * @Author yangkang
 * @Date 2019/7/10 10:45
 * @Version 1.0
 **/
public enum ResultEnum {
    SUCCESS("0","请求成功"),
    FAIL("-1","系统繁忙，请稍候再试"),
    ILLEGAL_ARGUMENT("1000","参数异常"),
    EMPTY_USER_NAME("1001","用户名为空"),
    EMPTY_PWD("1002","密码为空"),
    EMPTY_VERITY_CODE("1003","验证码为空"),
    ILLEGAL_VERITY_CODE("1004","验证码错误"),
    USER_EXIST("1005","用户已注册"),
    USER_NAME_PWD_ERRO("1005","用户名或密码错误"),
    ;


    private String code;
    private String desc;
    ResultEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
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
}
