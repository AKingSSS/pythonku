package com.yk.pyku.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName IndexViewConfig
 * @Description Spring boot 默认首页配置
 * @Author yangkang
 * @Date 2019/7/118:03
 * @Version 1.0
 **/
@Configuration
public class IndexViewConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/views/welcome.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
