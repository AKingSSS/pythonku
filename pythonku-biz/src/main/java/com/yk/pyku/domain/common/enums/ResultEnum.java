package com.yk.pyku.domain.common.enums;


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
    FAIL("-1","系统繁忙，请稍候再试");


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
