package xCounter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int number = 0;
    private Lock locker = new ReentrantLock();

    public int getNumber() {
        return number;
    }

    public void resetNumber() {
        number = 0;
    }

    public void asyncIncrement() {
        number++;
    }

    public void asyncDecrement() {
        number--;
    }

    public synchronized void syncIncrementFirst() {
        number++;
    }

    public synchronized void syncDecrementFirst() {
        number--;
    }

    public void syncIncrementSecond() {
        synchronized (this) {
            number++;
        }
    }

    public void syncDecrementSecond() {
        synchronized (this) {
            number--;
        }
    }

    public void syncIncrementThird() {
        try {
            locker.lock();
            number++;
        } finally {
            locker.unlock();
        }
    }

    public void syncDecrementThird() {
        try {
            locker.lock();
            number--;
        } finally {
            locker.unlock();
        }
    }
}
