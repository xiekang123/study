package com.xiekang.exercise.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
* @Description:  jdk内置的4个函数式接口
 * 1、消费型接口：Consumer<T> 代表了接受一个输入参数并且无返回的操作
 *        void accept(T t)
 * 2、供给型接口：Supplier<T> 无参数，返回一个结果。
 *        T get()
 * 3、函数型接口：Function<T,R> 接受一个输入参数，返回一个结果。
 *        R apply(T t)
 * 4、断言型接口：Predicate<T> 接受一个输入参数，返回一个布尔值结果。
 *        boolean test(T t)
* @Author:  xiekang
* @CreateDate: 2019/12/18 9:43
*/
public class JavaInlayFun {
    public static void main(String[] args) {
        //testCom("xiekang",(str)-> System.out.println(str.toUpperCase()));
        /*List<Integer> list = testSup(6,() -> (int) (Math.random() * 100));
        System.out.println(list);*/
        String strw = strSub("xiekang",(str) -> str.substring(3));
        System.out.println(strw);
    }

    //用于操作字符串
    static String strSub(String strs, Function<String,String> fun){
        return fun.apply(strs);
    }


    static void testCom(String str,Consumer<String> con){
        con.accpet(str);
    }

    //产生一定数量的整形数
    static List<Integer> testSup(Integer num , Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }
}
