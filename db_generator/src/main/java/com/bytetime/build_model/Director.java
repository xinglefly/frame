package com.bytetime.build_model;

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