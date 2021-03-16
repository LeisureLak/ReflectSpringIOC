package com.lak.reflect;

import com.lak.annotation.AutoWired;
import com.lak.annotation.Bean;
import com.lak.dao.IBookDao;
import com.lak.dao.IUserDao;
import com.lak.dao.impl.BookDaoImpl;
import com.lak.dao.impl.UserDaoImpl;
import com.lak.service.IBookService;
import com.lak.service.IUserService;
import com.lak.service.impl.BookServiceImpl;
import com.lak.service.impl.UserServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ApplicationContext<T> {

    /*private HashMap<Class, Object> beanFactory = new HashMap<>();

    public T getBean(Class clazz) {
        return (T)beanFactory.get(clazz);
    }

    public void initContext() {
        beanFactory.put(IUserDao.class, new UserDaoImpl());
        beanFactory.put(IBookDao.class, new BookDaoImpl());
        beanFactory.put(IUserService.class, new UserServiceImpl());
        beanFactory.put(IBookService.class, new BookServiceImpl());
    }*/

    private HashMap<Class, Object> beanFactory = new HashMap<>();

    public T getBean(Class clazz) {
        return (T)beanFactory.get(clazz);
    }

    //通过配置文件读取class
    public void initContext() {
        InputStream resource = ApplicationContext.class.getClassLoader().getResourceAsStream("config/bean.config");
        Properties properties = new Properties();
        try {
            properties.load(resource);
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                beanFactory.put(Class.forName(key.toString()),
                        Class.forName(properties.getProperty(key.toString())).newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    * 通过注解实现注入
    * */
    private String filePath;

    //加载注解的类的实例
    public void initContextByAnnotation()  {
        filePath = ApplicationContext.class.getClassLoader().getResource("").getFile();
        loadOne(new File(filePath));
        assembleObject();
    }

    /**
     * 加载一个文件夹的类
     * @param fileParent
     */
    private void loadOne(File fileParent) {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if(childrenFiles == null || childrenFiles.length == 0){
                return;
            }
            for (File child : childrenFiles) {
                if (child.isDirectory()) {
                    //如果是个文件夹就继续调用该方法
                    loadOne(child);
                } else {
                    //通过文件路径转变成全类名,第一步把绝对路径部分去掉
                    String pathWithClass = child.getAbsolutePath().substring(filePath.length() - 1);
                    //选中class文件
                    if (pathWithClass.contains(".class")) {
                        //去掉.class后缀，并且把 \ 替换成 .
                        String fullName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        try {
                            Class<?> aClass = Class.forName(fullName);

                            //扫描注解
                            Bean annotation = aClass.getAnnotation(Bean.class);

                            //把非接口的类实例化放在map中
                            if(!aClass.isInterface()){
                                //如果有注解才加载到map
                                if (annotation != null) {
                                    Object instance = aClass.newInstance();
                                    if(aClass.getInterfaces().length > 0) {
                                        beanFactory.put(aClass.getInterfaces()[0], instance);
                                    } else {
                                        beanFactory.put(aClass, instance);
                                    }
                                    beanFactory.put(aClass.getInterfaces()[0], instance); //获取实现的接口作为键，实现类的实例作为值
                                }
                            };
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void assembleObject() {
        for (Map.Entry<Class, Object> entry : beanFactory.entrySet()) {
            Object obj = entry.getValue();
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                AutoWired annotation = declaredField.getAnnotation(AutoWired.class);
                if (annotation != null) {
                    declaredField.setAccessible(true);
                    try {
                        // 不仅要自动装配类，还要自动装配类里面的成员变量，
                        // 这里就是装配@Bean类下的@AutoWired成员变量，实例从工厂里面拿
                        declaredField.set(obj, beanFactory.get(declaredField.getType()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
