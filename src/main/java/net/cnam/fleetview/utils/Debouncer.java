package net.cnam.fleetview.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Debouncer {
    private Timer timer;
    private long delay;
    private TimerTask task;

    public Debouncer(long delay) {
        this.delay = delay;
        timer = new Timer();
    }

    public synchronized void debounce(final Runnable runnable) {
        if (task != null) {
            task.cancel();
        }

        task = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };

        timer.schedule(task, delay);
    }
}
