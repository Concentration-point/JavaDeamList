package com.concentration.testforjava;

import org.apache.tomcat.util.digester.RulesBase;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;

public class ThreadTest {
    static int x = 0;
    public static volatile Object lock = new Object();
    public static void main (String[] args){
        Thread thead1 = new Thread(() ->{
            synchronized (lock){
                x ++;
                System.out.println("thead1 x = " + x);
            }
        }
        );

    }

}
