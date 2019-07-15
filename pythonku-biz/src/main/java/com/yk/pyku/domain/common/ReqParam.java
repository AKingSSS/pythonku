package com.yk.pyku.domain.common;

import lombok.Data;

/**
 * @ClassName ReqParam
 * @Description 请求参数
 * @Author yangkang
 * @Date 2019/7/10 10:34
 * @Version 1.0
 **/
@Data
public class ReqParam<T> {
    private T data;

    public ReqParam() {
    }
}
