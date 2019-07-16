package com.yk.pyku.domain.user;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserDo
 * @Description 用户实体类
 * @Author yangkang
 * @Date 2019/7/3 15:55
 * @Version 1.0
 **/
@Data
public class UserDo {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码（MD5加密）
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 注册来源类型
     */
    private Integer  registerSourceType;
    /**
     * 注册来源描述
     */
    private String registerSource;
    /**
     * 试用开始时间
     */
    private Date tryStartTime;
    /**
     * 试用结束时间
     */
    private Date tryEndTime;
    /**
     * 会员状态：0：违规禁用；1：试用；2：vip
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 备注
     */
    private String remark;
}
