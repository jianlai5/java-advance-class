package com.lurenjun.jvm.homework.week1.optional1;

/**
 * 选做1:
 * 自己写一个简单的 Hello.java，里面需要涉及基本类型，
 * 四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
 */
public class Hello {

    private static int count = 0;

    private static long num = 1;

    private static long add(int i) {
        count++;
        return num = num+i;
    }

    private static long sub(int i){
        count++;
        return num = num - i;
    }

    private static long mul(int i){
        count ++;
        return num = num * i;
    }

    private static long div(int i){
        count ++;
        if (i > 0){
        return num = num/i;
        }
        return num;
    }


    public static void main(String[] args) {
        int[] array = {-5,-1,0,1,5};
        for (int i : array){
            System.out.println(add(i));
            System.out.println(sub(i));
            System.out.println(mul(i));
            System.out.println(div(i));
        }
        System.out.println("Hello World!");
    }
}
