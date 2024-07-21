package pl.mikbac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MikBac on 20.07.2024
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> virtualThreads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(new LogThread());
            virtualThreads.add(virtualThread);
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.start();
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.join();
        }
    }
}
