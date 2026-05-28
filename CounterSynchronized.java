class CounterSynchronized implements Runnable {

    private int c = 0;
    private final int iteracoes;

    CounterSynchronized(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public void run() {
        for (int i = 0; i < iteracoes; i++) {
            increment();
        }
    }

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
