package com.yk.pyku.ao.user;

import com.yk.pyku.domain.user.UserDo;

public interface UserAO {
    /**
     * 登陆接口
     */
    public void register(UserDo userDo) throws Exception;
}
