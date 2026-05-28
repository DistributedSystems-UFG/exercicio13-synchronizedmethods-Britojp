public class CounterConcurrencyTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Counter sem sincronização:");
        testarSemSincronizacao();

        System.out.println("Counter com métodos synchronized:");
        testarComSincronizacao();
    }

    private static void testarSemSincronizacao() throws InterruptedException {
        for (int execucao = 1; execucao <= 10; execucao++) {
            Counter contador = new Counter(100_000);
            iniciarEAguardar(criarThreads(contador));
            imprimirResultado(execucao, contador.value());
        }
    }

    private static void testarComSincronizacao() throws InterruptedException {
        for (int execucao = 1; execucao <= 10; execucao++) {
            CounterSynchronized contador = new CounterSynchronized(100_000);
            iniciarEAguardar(criarThreads(contador));
            imprimirResultado(execucao, contador.value());
        }
    }

    private static void imprimirResultado(int execucao, int valorFinal) {
        String estado = valorFinal == 200_000 ? "consistente" : "inconsistente";
        System.out.println("Execução " + execucao + " - valor final: " + valorFinal + " (" + estado + ")");
    }

    private static Thread[] criarThreads(Runnable tarefa) {
        Thread[] threads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            threads[i] = new Thread(tarefa);
        }
        return threads;
    }

    private static void iniciarEAguardar(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
