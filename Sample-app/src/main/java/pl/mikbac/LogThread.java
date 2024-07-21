package pl.mikbac;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by MikBac on 20.07.2024
 */

@Slf4j
public class LogThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            LOGGER.warn("Important log!");
            Thread.sleep(10000);
        }
    }

}
