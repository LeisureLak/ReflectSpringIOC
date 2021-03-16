package com;

import com.lak.reflect.ApplicationContext;

public class BootStrap {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        /*applicationContext.initContext();
        System.out.println(applicationContext.getBean(IUserService.class));*/
        applicationContext.initContextByAnnotation();
    }
}
