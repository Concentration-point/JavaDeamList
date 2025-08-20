package org.example;

import javax.swing.plaf.basic.BasicTreeUI;

public class ThrowHandlerImpl implements RejectHandler {
    @Override
    public void reject(Runnable rejectCommand, MyThreadPool threadPool) {
        throw new RuntimeException("阻塞队列满了");
    }
}
