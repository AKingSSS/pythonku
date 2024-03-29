package com.yk.pyku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
public class PythonkuApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PythonkuApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PythonkuApplication.class, args);
    }

}
