package threads;

public class HiloThread extends Thread{

    @Override
    public void run(){
        System.out.println("Executing Thread: " + currentThread().getName());
    }
}
