package com.yk.pyku.ao.user.impl;

import com.alibaba.druid.util.StringUtils;
import com.yk.pyku.ao.redis.RedisService;
import com.yk.pyku.ao.user.UserAO;
import com.yk.pyku.dao.user.UserDao;
import com.yk.pyku.domain.UserQuery;
import com.yk.pyku.domain.common.Result;
import com.yk.pyku.domain.common.ResultEnum;
import com.yk.pyku.domain.common.ResultResponse;
import com.yk.pyku.domain.user.UserDo;
import com.yk.pyku.enums.BaseEnums;
import com.yk.pyku.enums.RedisKeyEnum;
import com.yk.pyku.enums.user.UserInfoEnums;
import com.yk.pyku.util.DateUtil;
import com.yk.pyku.util.MD5Util;
import com.yk.pyku.util.RandomUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yk.pyku.util.Send163MailUtil.send;

/**
 * @ClassName UserAOImpl
 * @Description 用户接口
 * @Author yangkang
 * @Date 2019/7/315:37
 * @Version 1.0
 **/
@Service
public class UserAOImpl implements UserAO {
    private static final Logger logger = LogManager.getLogger(UserAOImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    @Override
    public Result register(UserQuery userQuery) throws Exception {
        //参数校验
        Result result = checkUserQuery(userQuery,null);
        if (null != result) {
            return result;
        }
        //短信验证码校验
        Result verifyCodeResult = checkVerifyCode(userQuery.getUserName(), userQuery.getVerifyCode());
        if (null != verifyCodeResult) {
            return verifyCodeResult;
        }
        UserDo userDo = new UserDo();
        userDo.setUserName(userQuery.getUserName());
        //校验用户是否注册
        UserDo userInfo = userDao.queryUserInfo(userDo);
        if (null != userInfo) {
            return ResultResponse.getResultInfo(ResultEnum.USER_EXIST);
        }
        //注册用户
        return registerUser(userDo, userQuery);
    }

    @Override
    public Result sendVerifyCode(String userName) throws Exception {
        if (StringUtils.isEmpty(userName)) {
            return ResultResponse.getSuccessResultInfo(ResultEnum.EMPTY_USER_NAME);
        }
        //设置验证码缓存
        String verifyCode = RandomUtil.getVerifyCode();
        String redisKey = String.format(RedisKeyEnum.SmsLogin.getContent(), userName);
        logger.info("====================> redisKey : " + redisKey);
        redisService.set(redisKey, verifyCode, 60 * 10);
        //发送邮件
        String content = String.format(BaseEnums.VERIFY_CODE_MESSAGE, verifyCode);
        String[] TOS = new String[]{userName};
        send(content, TOS);
        return ResultResponse.getSuccessResultInfo(userName);
    }

    /**
     * 登录
     * @param userQuery
     * @return
     * @throws Exception
     */
    @Override
    public Result login(UserQuery userQuery) throws Exception {
        //参数校验
        Result result = checkUserQuery(userQuery, "verifyCode");
        if (null != result) {
            return result;
        }
        UserDo userDo = new UserDo();
        userDo.setUserName(userQuery.getUserName());
        userDo.setPassword(MD5Util.md5Encode(userQuery.getPassword()));
        UserDo userInfo = userDao.queryUserInfo(userDo);
        if (null == userInfo) {
            return ResultResponse.getResultInfo(ResultEnum.USER_NAME_PWD_ERRO);
        }
        return ResultResponse.getSuccessResultInfo(userQuery);
    }

    /**
     * 参数校验
     *
     * @param userQuery
     * @return
     */
    private Result checkUserQuery(UserQuery userQuery, String excludeParam) {
        if (null == userQuery) {
            return ResultResponse.getResultInfo(ResultEnum.ILLEGAL_ARGUMENT);
        }
        if (!"userName".equals(excludeParam)) {
            if (StringUtils.isEmpty(userQuery.getUserName())) {
                return ResultResponse.getResultInfo(ResultEnum.EMPTY_USER_NAME);
            }
        }
        if (!"password".equals(excludeParam)) {
            if (StringUtils.isEmpty(userQuery.getPassword())) {
                return ResultResponse.getResultInfo(ResultEnum.EMPTY_PWD);
            }
        }
        if (!"verifyCode".equals(excludeParam)) {
            if (StringUtils.isEmpty(userQuery.getVerifyCode())) {
                return ResultResponse.getResultInfo(ResultEnum.EMPTY_VERITY_CODE);
            }
        }
        return null;
    }

    /**
     * 校验验证码
     *
     * @param verifyCode
     * @return
     */
    private Result checkVerifyCode(String userName, String verifyCode) {
        String redisKey = String.format(RedisKeyEnum.SmsLogin.getContent(), userName);
        logger.info("====================> redisKey : " + redisKey);
        //判断是否未发送短信验证码
        String smsLoginCode = redisService.get(redisKey) + "";
        logger.info("====================> smsLoginCode :" + smsLoginCode);
        if (StringUtils.isEmpty(smsLoginCode)) {
            return ResultResponse.getResultInfo(ResultEnum.ILLEGAL_VERITY_CODE);
        }
        //校验短信验证码是否错误
        if (!verifyCode.equals(smsLoginCode)
                && !verifyCode.equals(DateUtil.dateToString(new Date(), DateUtil.Format_18))) {
            return ResultResponse.getResultInfo(ResultEnum.ILLEGAL_VERITY_CODE);
        }
        //删除验证码
        redisService.delete(redisKey);
        return null;
    }

    /**
     * 注册用户
     *
     * @param userDo
     * @return
     */
    private Result registerUser(UserDo userDo, UserQuery userQuery) throws Exception {
        userDo.setPassword(MD5Util.md5Encode(userQuery.getPassword()));
        userDo.setStatus(UserInfoEnums.USER_NORMAL_TRY.getIndex());
        userDo.setTryEndTime(DateUtil.addDay(new Date(), UserInfoEnums.USER_TRY_DAYS.getIndex()));
        userDao.insert(userDo);
        return ResultResponse.getSuccessResultInfo(userQuery);
    }
}
