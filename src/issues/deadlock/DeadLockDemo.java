package issues.deadlock;

public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();

        Thread t1 = new Thread(new SyncThread(ob1, ob2), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob3, ob1), "hilo3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();
        Thread.sleep(5000);

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");
    }
}

class SyncThread implements Runnable {

    public Object ob1;
    public Object ob2;

    public SyncThread(Object ob1, Object ob2){
        this.ob1 = ob1;
        this.ob2 = ob2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " generando lock en " + ob1);
        synchronized (ob1){
            System.out.println(" lock generado en " + ob1);
            work();
            System.out.println(name + " generando lock en " + ob2);
            synchronized (ob2){
                System.out.println("lock generado en " + ob2);
                work();
            }
            System.out.println(name + " lock liberado en " + ob2);
        }
        System.out.println(name + " lock liberado en " + ob1);
        System.out.println("Finalizó ejecución");
    }

    private void work(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}