package com.jstar.xt.lock.jdk.array;

import java.util.ArrayList;

public class TestArray {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        System.out.println(list);
        list.set(1,"zhaoliu");
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(1,"ceshi");
        System.out.println(list);
        System.out.println(list.size());

        for (int i = 0; i < 10; i++) {
            if(i==7){
                System.out.println("在这里应该扩容了");
            }
            list.add("string_"+i);
        }
    }
}
