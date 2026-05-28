class Counter implements Runnable {

    private int c = 0;
    private final int iteracoes;

    Counter(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public void run() {
        for (int i = 0; i < iteracoes; i++) {
            increment();
        }
    }

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }
}
