package com.bytetime.build_model;


/***
 * Builder:这是构造和表示相分离，调用链方式实现。
 *
 * Builder在Android开发中比较常见，作为配置类的构造器将配置和表示分离开来，
 * 将配置从目标类中隔离出来，避免过多的setter的方法。
 *
 * 优点：1）良好的封装性，使用建造者模式可以使客户端不知道内部组成细节。
 *      2）建造者独立，容易扩展。
 *
 * 缺点: 会产生多余的builder对象以及Directory对象，消耗内存。
 */

public class Director {

    Builder mBuilder = null;

    public Director(Builder builder) {
        this.mBuilder = builder;
    }

    public void constructor(String board,String display){
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOS();
    }


    public static void main(String[] strs){
        Builder builder = new MacbookBuilder();
        Director pcDirector = new Director(builder);
        pcDirector.constructor("2.7 GHz Intel Core i5","Retina display");
        System.out.println("computer info:"+builder.create().toString());
    }
}