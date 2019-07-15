package com.yk.pyku.domain.user;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserDo
 * @Description
 * @Author yangkang
 * @Date 2019/7/3 15:55
 * @Version 1.0
 **/
@Data
public class UserDo {
    /**
     * ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 是否已删除，1:是，0:否
     */
    private Integer deleted;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 邮件
     */
    private String email;
    /**
     * 上次登录时间
     */
    private Date lastLoggedTime;
    /**
     * 状态
     */
    private Integer status;

}
