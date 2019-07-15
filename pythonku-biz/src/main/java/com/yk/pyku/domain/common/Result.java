package com.yk.pyku.domain.common;

import lombok.Data;

/**
 * @ClassName Result
 * @Description 返回结果
 * @Author yangkang
 * @Date 2019/7/10 10:41
 * @Version 1.0
 **/
@Data
public class Result<T> {
    private boolean success = true;
    private String code;
    private String msg;
    private T data;
}
