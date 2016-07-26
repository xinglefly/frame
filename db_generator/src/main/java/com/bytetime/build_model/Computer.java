package com.bytetime.build_model;


public abstract class Computer {

    protected String mDisplay;
    protected String mBoard;
    protected String mOs;

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public abstract void setmOs();

    @Override
    public String toString() {
        return "Computer{" +
                "mDisplay='" + mDisplay + '\'' +
                ", mBoard='" + mBoard + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }
}