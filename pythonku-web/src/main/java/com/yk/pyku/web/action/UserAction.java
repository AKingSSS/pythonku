package com.yk.pyku.web.action;

import com.yk.pyku.ao.user.UserAO;
import com.yk.pyku.domain.common.ReqParam;
import com.yk.pyku.domain.common.Result;
import com.yk.pyku.domain.common.ResultResponse;
import com.yk.pyku.domain.common.enums.ResultEnum;
import com.yk.pyku.domain.user.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserAction
 * @Description 用户信息
 * @Author yangkang
 * @Date 2019/7/10 10:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserAO userAO;
    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST )
    public Result register(@RequestBody ReqParam<UserDo> reqParam) {
        try {
            userAO.register(reqParam.getData());
            return ResultResponse.getSuccessResultInfo(ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.getSuccessResultInfo(ResultEnum.FAIL);
        }
    }
}
