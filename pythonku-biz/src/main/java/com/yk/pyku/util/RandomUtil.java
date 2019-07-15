package com.yk.pyku.util;

import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Description 随机数
 * @Author yangkang
 * @Date 2019/7/15 15:12
 * @Version 1.0
 **/
public class RandomUtil {
    public static String getVerifyCode() {
       return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    public static void main(String[] args) {
        System.out.println(getVerifyCode());
    }

}
