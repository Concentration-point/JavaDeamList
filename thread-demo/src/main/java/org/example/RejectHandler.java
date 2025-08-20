package org.example;

public interface RejectHandler {
    void reject(Runnable rejectCommand, MyThreadPool threadPool);
}
