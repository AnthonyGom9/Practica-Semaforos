public class BinarySemaphore {
    private boolean signal = false; // Estado del semáforo

    // Adquirir el semáforo (entrar a la sección crítica)
    public synchronized void acquire() throws InterruptedException {
        while (signal) { // Si el semáforo está en true, esperar hasta que se libere
            wait(); // Bloquea el hilo hasta que se libere el semáforo
        }
        signal = true; // Toma el semáforo
    }

    // Liberar el semáforo (salir de la sección crítica)
    public synchronized void release() {
        signal = false; // Libera el semáforo
        notify(); // Despierta a los hilos que están esperando en acquire()
    }
}
