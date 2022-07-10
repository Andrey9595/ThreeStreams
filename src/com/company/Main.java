package com.company;

public class Main {
    static volatile String currentLetter = "A";
    static final Object m = new Object();

    public static void main(String[] args) {
        VordA a = new VordA();
        Thread threadA = new Thread(a);
        threadA.start();
        VordB b = new VordB();
        Thread threadB = new Thread(b);
        threadB.start();
        VordC c = new VordC();
        Thread threadC = new Thread(c);
        threadC.start();
    }
}

class VordA implements Runnable {

    @Override
    public void run() {
        synchronized (Main.m) {
            for (int i = 0; i < 10; i++) {
                try {
                    while (Main.currentLetter != "A") {

                        Main.m.wait();
                    }
                    System.out.print("A");
                    Main.currentLetter = "B";
                    Main.m.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class VordB implements Runnable {

    @Override
    public void run() {

        synchronized (Main.m) {
            for (int i = 0; i < 10; i++) {
                try {
                    while (Main.currentLetter != "B") {

                        Main.m.wait();
                    }
                    System.out.print("B");
                    Main.currentLetter = "C";
                    Main.m.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class VordC implements Runnable {

    @Override
    public void run() {
        synchronized (Main.m) {
            for (int i = 0; i < 10; i++) {
                try {
                    while (Main.currentLetter != "C") {

                        Main.m.wait();
                    }
                    System.out.print("C");
                    Main.currentLetter = "A";
                    Main.m.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
