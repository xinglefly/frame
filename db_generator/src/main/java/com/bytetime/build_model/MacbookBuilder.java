package com.bytetime.build_model;

public class MacbookBuilder extends Builder {


    public Computer mComputer = new MacBook();

    @Override
    public void buildBoard(String board) {
        mComputer.setmBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        mComputer.setmDisplay(display);
    }

    @Override
    public void buildOS() {
        mComputer.setmOs();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}