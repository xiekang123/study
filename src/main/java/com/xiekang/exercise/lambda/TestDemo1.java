package com.xiekang.exercise.lambda;

import java.util.Comparator;

public class TestDemo1 {
    public static void main(String[] args) {
        //test1();
        test2();
    }


    public static void test1(){
        //jdk1.7之前  需要将a修饰为final,1.8后可以不用final修饰，但是不能修改a的值
        int a = 1;
        Runnable r = () -> System.out.println("hello lambda!"+a);
        r.run();
    }

    public static void test2(){
        //只有一个参数()可以省略不写
        //Consumer<String> consumer= (x) -> System.out.println(x);
        Consumer<String> consumer= x -> System.out.println(x);
        consumer.accpet("hello,xiekang!");
    }

    public static void test3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
    }
}
