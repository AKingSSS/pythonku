package com.yk.pyku.web.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeAction
 * @Description 首页
 * @Author yangkang
 * @Date 2019/7/314:52
 * @Version 1.0
 **/
@RestController
public class HomeAction {
    @RequestMapping(value = "/home.do")
    public String home() {
        return "Hello World!";
    }

}
