package issues.raceCondition;

import threads.BinarySemaphore;

public class Contador {

    private int contador = 0;

    private final BinarySemaphore semaphore;

    public Contador(BinarySemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void incrementarContador() {
        try {
            semaphore.acquire(); // Adquiere el semáforo antes de modificar el contador
            contador++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Libera el semáforo después de la modificación
        }
    }

    public int getContador(){
        return contador;
    }
}
