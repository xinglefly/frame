package com.bytetime.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    static List<Integer> lists = new ArrayList();
    static StringBuffer sb = new StringBuffer();
    public static void main(String [] args){
        lists.add(1);
        lists.add(2);
        lists.add(3);

        List<Integer> listAdds = new ArrayList<>();
        listAdds.addAll(lists);
        System.out.print("list-->"+listAdds.size());

        for (int i :lists)
            sb.append(i+",");
        System.out.print("lists-->"+lists.get(0)+","+sb.substring(0,sb.length()-1).toString());


        String imgFile = "/storage/emulated/0/yidian/app/photo_sd/1467790882837.jpg1";
        if (imgFile.contains(".jpg1")){
            System.out.print("file-->"+imgFile.substring(0,imgFile.length()-1));
        }
    }
}