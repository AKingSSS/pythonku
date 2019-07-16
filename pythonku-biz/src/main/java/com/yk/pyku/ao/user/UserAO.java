package com.yk.pyku.ao.user;

import com.yk.pyku.domain.UserQuery;
import com.yk.pyku.domain.common.Result;
import com.yk.pyku.domain.user.UserDo;

public interface UserAO {
    /**
     * 注册接口
     */
    public Result register(UserQuery userQuery) throws Exception;

    /**
     * 发送邮箱验证码
     */
    public Result sendVerifyCode(String userName) throws Exception;

    /**
     * 登录
     * @param userQuery
     * @return
     * @throws Exception
     */
    public Result login(UserQuery userQuery) throws Exception;
}
