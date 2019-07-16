package com.yk.pyku.web.action;

import com.yk.pyku.ao.redis.RedisService;
import com.yk.pyku.ao.user.UserAO;
import com.yk.pyku.domain.common.ReqParam;
import com.yk.pyku.domain.common.Result;
import com.yk.pyku.domain.common.ResultResponse;
import com.yk.pyku.domain.common.enums.ResultEnum;
import com.yk.pyku.domain.user.UserDo;
import com.yk.pyku.enums.RedisKeyEnum;
import com.yk.pyku.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
    @Autowired
    private UserAO userAO;
    @Autowired
    private RedisService redisService;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public Result register(@RequestBody ReqParam<UserDo> reqParam) {
        try {
//            userAO.register(reqParam.getData());
            String key = String.format(RedisKeyEnum.SmsLogin.getContent(), "15210785338@163.com");
            redisService.set(key, RandomUtil.getVerifyCode(), 60 * 10);
            return ResultResponse.getSuccessResultInfo(ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.getSuccessResultInfo(ResultEnum.FAIL);
        }
    }
}
