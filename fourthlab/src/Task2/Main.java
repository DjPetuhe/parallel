package Task2;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Matrix m1 = MatrixHelper.CreateMatrix(1500, 1500, false);
        Matrix m2 = MatrixHelper.CreateMatrix(1500, 1500, false);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Matrix result = forkJoinPool.invoke(new FoxMultiplierTask(m1, m2));

        Matrix result2 = new DefaultMultiplierTask(m1, m2).compute();

        System.out.println(MatrixHelper.CompareMatrix(result, result2));

        runTest(m1, m2, forkJoinPool);
    }

    private static void runTest(Matrix m1, Matrix m2, ForkJoinPool forkJoinPool) {
        long parallelTime = 0;
        for (int i = 0; i < 10; i++) {
            long time = System.currentTimeMillis();
            Matrix matrix = forkJoinPool.invoke(new FoxMultiplierTask(m1, m2));
            parallelTime += System.currentTimeMillis() - time;
        }
        System.out.println();
        System.out.println("ForkJoinPool average working time (sec): " + (parallelTime / 10 / 1000.0));
    }
}
