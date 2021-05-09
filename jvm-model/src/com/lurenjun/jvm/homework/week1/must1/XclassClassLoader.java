package com.lurenjun.jvm.homework.week1.must1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XclassClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object hello = new XclassClassLoader().findClass("Hello").newInstance();
            Method method = hello.getClass().getMethod("hello");
            method.invoke(hello);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //文件路径
        String path = "F:\\project\\java-advance-class\\jvm-model\\src\\com\\lurenjun\\jvm\\homework\\week1\\must1\\Hello.xlass";
        //解密255-x
        byte[] bytes = decode(path);
        //加载类
        Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
        return aClass;
    }

    public byte[] decode(String path) {
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
