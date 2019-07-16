package com.yk.pyku.domain.common;

/**
 * @ClassName ResultResponse
 * @Description
 * @Author yangkang
 * @Date 2019/7/10 10:52
 * @Version 1.0
 **/
public class ResultResponse {
    public static <T> Result<T> getResultInfo(ResultEnum resultEnum){
        Result<T> result=new Result<T>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getDesc());
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> getResultInfo(ResultEnum resultEnum,T data){
        Result<T> result=new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getDesc());
        result.setData(data);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> getSuccessResultInfo(ResultEnum resultEnum,T data){
        Result<T> result=new Result<T>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getDesc());
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> getSuccessResultInfo(T data){
        Result<T> result=new Result<T>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getDesc());
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> getFailResultInfo(){
        Result<T> result=new Result<T>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMsg(ResultEnum.FAIL.getDesc());
        result.setSuccess(false);
        return result;
    }

}
