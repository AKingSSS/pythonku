package com.yk.pyku.ao.user.impl;

import com.yk.pyku.ao.user.UserAO;
import com.yk.pyku.dao.user.UserDao;
import com.yk.pyku.domain.user.UserDo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    @Override
    public void register(UserDo userDo) throws Exception {
            Date now = new Date();
            userDo.setCreateTime(now);
            userDo.setModifyTime(now);
            userDo.setLastLoggedTime(now);
            userDo.setDeleted(0);
            userDo.setStatus(0);
            userDao.insert(userDo);
            logger.info("添加新用户成功！");
    }
}
