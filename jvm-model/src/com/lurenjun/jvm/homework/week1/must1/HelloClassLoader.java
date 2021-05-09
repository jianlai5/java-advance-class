package com.lurenjun.jvm.homework.week1.must1;


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * （必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 执行 hello 方法，此文件内容是一个 Hello.class
 * 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws IOException {

    }

    public static void printURLForClassLoader(ClassLoader classLoader){
        Object ucp = insightField(classLoader, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object o : ps){
            System.out.println("====>"+o.toString());
        }
    }

    public static Object insightField(Object obj,String fName){
            Field field = null;
        try {
            if (obj instanceof URLClassLoader){
                field = URLClassLoader.class.getDeclaredField(fName);
            }else {
                field = obj.getClass().getDeclaredField(fName);
            }
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
