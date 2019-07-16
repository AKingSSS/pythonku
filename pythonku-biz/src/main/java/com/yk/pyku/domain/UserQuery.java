package com.yk.pyku.domain;

import lombok.Data;

/**
 * UserQuery
 *
 * @author user
 */
@Data
public class UserQuery {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮件验证码
     */
    private String verifyCode;
}
