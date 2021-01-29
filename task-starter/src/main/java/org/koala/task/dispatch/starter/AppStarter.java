package org.koala.task.dispatch.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

@ComponentScan(value = "org.koala.task.dispatch")
@SpringBootApplication
public class AppStarter {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AppStarter.class, args);
        toLifeCycle();
    }

    private static void toLifeCycle() {
        try {
            CountDownLatch deadline = new CountDownLatch(1);
            deadline.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
