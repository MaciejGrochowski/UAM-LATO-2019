package pl.wizard.software;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryTest {

    private Random random = new Random();
    public final ArrayList<Double> list = new ArrayList<>(1000000);

    @Test
    public void memoryLeakExample() throws InterruptedException {
        Thread.sleep(3000);

        for (int i = 0; i < 20000; i++) {
            for (int j = 0; j < 2000; j++) {
                list.add(random.nextDouble());
            }
            Thread.sleep(1);
        }

        System.gc();
        System.out.println("DONE");
        Thread.sleep(300000000); // to allow GC do its job
    }

    @Test
    public void withoutMemoryLeak() throws InterruptedException {
        Thread.sleep(3000);

        listIteration();

        System.gc();
        System.out.println("DONE");
        Thread.sleep(300000000); // to allow GC do its job
    }

    void listIteration() throws InterruptedException {
        List<Double> localList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 2000; j++) {
                localList.add(random.nextDouble());
            }
            Thread.sleep(1);
        }
    }
}
