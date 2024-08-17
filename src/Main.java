import threads.BinarySemaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private int count = 0;
    private final BinarySemaphore semaphore = new BinarySemaphore();

    public void increment() {
        try {
            semaphore.acquire(); // Adquirir acceso exclusivo
            int newCount = count + 1;
            Thread.sleep(50); // Simular un trabajo que podría causar una condición de carrera
            count = newCount; // Actualizar la variable compartida
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Liberar el acceso exclusivo
        }
    }

    public static void main(String[] args) {
        Main example = new Main();

        Runnable task = example::increment;

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final count: " + example.count);

    }
}