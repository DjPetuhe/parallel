package Multipliers;

import Result.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class StrippedMultiplier extends DefaultMultiplier {

    private class StrippedTask implements Runnable {
        private Result result;
        private int[] row;
        private int[] column;
        int i;
        int j;

        public StrippedTask(int[] row, int[] column, int i, int j, Result result) {
            this.result = result;
            this.row = row;
            this.column = column;
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            int element = 0;
            for (int i = 0; i < row.length; i++) {
                element += row[i] * column[i];
            }
            result.setValue(i, j, element);
        }
    }

    private int[][] transMatrixB;
    private int threadsAmount;

    public  StrippedMultiplier(int threadsAmount) {
        super();
        this.threadsAmount = threadsAmount;
    }

    public StrippedMultiplier(int[][] matrixMultiplicand, int[][] matrixMultiplier, int threadsAmount) {
        super(matrixMultiplicand, matrixMultiplier);
        setTransMatrixB(matrixMultiplier);
        this.threadsAmount = threadsAmount;
    }

    public void setThreadsAmount(int threadsAmount) {
        this.threadsAmount = threadsAmount;
    }

    @Override
    public void setMatrixMultiplier(int[][] matrixB) {
        this.matrixB = matrixB;
        setTransMatrixB(matrixB);
    }

    private void setTransMatrixB(int[][] matrixB) {
        transMatrixB = new int[matrixB[0].length][matrixB.length];
        for (int i = 0; i < transMatrixB.length; i++) {
            for (int j = 0; j < transMatrixB[0].length; j++) {
                transMatrixB[i][j] = matrixB[j][i];
            }
        }
    }

    @Override
    public Result Multiply() {
        Result result = new Result(matrixA.length, matrixB[0].length);
        if (matrixA[0].length != matrixB.length) return result;
        ExecutorService executor = Executors.newFixedThreadPool(threadsAmount);
        for (int i = 0; i < matrixA.length; i++) {
            List<Future<?>> futures = new ArrayList<>();
            for (int j = 0; j < transMatrixB.length; j++) {
                int indexB = (j + i) % transMatrixB.length;
                int indexA = i % matrixA.length;
                futures.add(executor.submit(new StrippedTask(matrixA[indexA], transMatrixB[indexB], indexA, indexB, result)));
            }
            try {
                for(Future<?> future : futures)
                    future.get();
            } catch (Exception e) {
                throw  new RuntimeException(e);
            }
        }
        executor.shutdown();
        return result;
    }
}
