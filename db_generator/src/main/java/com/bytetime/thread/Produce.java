package com.bytetime.thread;

public class Produce implements Runnable {

    private Cycle cy;

    public Produce(Cycle cy) {
        this.cy = cy;
    }

    public void run() {
        while (true) {
            cy.set("+商品+");
        }
    }
}