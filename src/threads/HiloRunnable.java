package threads;

public class HiloRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Executing Thread: " + Thread.currentThread().getName());
    }
}
