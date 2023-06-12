package com.mega.tweaks.api.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class SpringUtils {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static String getLocaleMessage(Integer code) {
        return applicationContext.getBean(MessageSource.class).getMessage(code + "", null, Locale.CHINA);
    }

    /**
     * 获取Spring中注册的Bean
     *
     * @param beanClass
     * @param beanId
     * @return
     */
    public static <T> T getSpringBean(String beanId, Class<T> beanClass) {
        return getApplicationContext().getBean(beanId, beanClass);
    }

    /**
     * 获取Spring中注册的Bean
     *
     * @param beanClass
     * @return
     */
    public static <T> T getSpringBean(Class<T> beanClass) {
        return getApplicationContext().getBean(beanClass);
    }
}
