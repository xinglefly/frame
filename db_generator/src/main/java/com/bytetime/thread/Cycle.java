package com.bytetime.thread;

public class Cycle {

    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name) {
        while (flag)//if判断一次，while判断多次
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        this.name = name + "--" + count++;
        System.out.println(Thread.currentThread().getName() + "--生产者...." + this.name);
        flag = true;
        this.notifyAll();
    }

    public synchronized void out() {
        while (!flag)
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.out.println(Thread.currentThread().getName() + "--消费者........" + this.name);
        flag = false;
        this.notifyAll();
    }


    public static void main(String[] args){
        Cycle cy = new Cycle();
        Produce in = new Produce(cy);
        Consume out = new Consume(cy);
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(in);
        Thread t3 = new Thread(out);
        Thread t4 = new Thread(out);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}