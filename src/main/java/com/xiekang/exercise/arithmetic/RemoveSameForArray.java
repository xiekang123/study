package com.xiekang.exercise.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 去除两个数组中相同的元素，
* @Author:  xiekang
* @CreateDate: 2019/11/26 9:38
*/
public class RemoveSameForArray {

    public static void main(String[] args) {
        int[] a = {4,6,2,1,63,21,44,68,99,12};
        int[] b = {21,19,4,6,3,77,34,22,31};
        System.out.println(findSame(a,b));
    }


    /**
     *  归并思想 要求复杂度为m+n
     * @param a
     * @param b
     * @return
     */
    public static List<Integer> findSame(int[] a,int[] b){
        List<Integer> result = new ArrayList<>();
        if(a == null || b == null){
            return result;
        }
        Arrays.sort(a);//先排序
        Arrays.sort(b);
        int i=0,j=0;
        while ( i < a.length && j < b.length){
            if(a[i] == b[j]){
                result.add(a[i]);
                i++;
                j++;
            }else if(a[i]<b[j]){
                i++;
            }else {
                j++;
            }
        }
        return result;

    }
}
