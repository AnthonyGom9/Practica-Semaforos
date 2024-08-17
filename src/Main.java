import issues.raceCondition.Contador;
import issues.raceCondition.HiloContador;
import threads.BinarySemaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BinarySemaphore semaphore = new BinarySemaphore();
        Contador contador = new Contador(semaphore);

        Runnable tarea1 = new HiloContador(contador);
        Runnable tarea2 = new HiloContador(contador);

        Thread hilo1 = new Thread(tarea1);
        Thread hilo2 = new Thread(tarea2);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final del contador: " + contador.getContador());
    }
}