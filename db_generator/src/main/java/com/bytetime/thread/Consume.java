package com.bytetime.thread;

public class Consume implements Runnable {

    private Cycle cy;

    public Consume(Cycle cy) {
        this.cy = cy;
    }

    public void run() {
        while (true) {
            cy.out();
        }
    }
}