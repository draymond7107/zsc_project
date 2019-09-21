package com.zsc.java8.stream;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List oldList() {

        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("aa");
        list.add("bb");
        return list;
    }
    public static List newList() {

        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("g");
        list.add("h");
        list.add("aa");
        list.add("bb");

        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        return list;
    }

}
