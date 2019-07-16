package com.yk.pyku.web.action;

import com.alibaba.druid.util.StringUtils;
import com.yk.pyku.ao.redis.RedisService;
import com.yk.pyku.ao.user.UserAO;
import com.yk.pyku.domain.UserQuery;
import com.yk.pyku.domain.common.ReqParam;
import com.yk.pyku.domain.common.Result;
import com.yk.pyku.domain.common.ResultResponse;
import com.yk.pyku.domain.common.ResultEnum;
import com.yk.pyku.enums.BaseEnums;
import com.yk.pyku.enums.RedisKeyEnum;
import com.yk.pyku.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.yk.pyku.util.Send163MailUtil.send;

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
    private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
    @Autowired
    private UserAO userAO;
    @Autowired
    private RedisService redisService;

    /**
     * 邮箱注册接口：
     * 游客注册分配14天权限
     *
     * @return
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public Result register(@RequestBody ReqParam<UserQuery> reqParam) {
        try {
            return userAO.register(reqParam.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.getSuccessResultInfo(ResultEnum.FAIL);
        }
    }

    /**
     * 发送邮箱验证码
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/sendVerifyCode.do", method = RequestMethod.GET)
    public Result sendVerifyCode(String userName) {
        try {
            return userAO.sendVerifyCode(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.getSuccessResultInfo(ResultEnum.FAIL);
        }
    }

    /**
     * 登录
     * @param reqParam
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public Result login(@RequestBody ReqParam<UserQuery> reqParam) {
        try {
            return userAO.login(reqParam.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.getSuccessResultInfo(ResultEnum.FAIL);
        }
    }
}
