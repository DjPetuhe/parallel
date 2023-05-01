package Multipliers;

import Helpers.*;
import Result.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class FoxMultiplier extends DefaultMultiplier {

    private class FoxTask implements Runnable {
        private Result result;
        private int[][] subBlockA;
        private int[][] subBlockB;
        int i;
        int j;

        public FoxTask(int[][] subBlockA, int[][] subBlockB, int i, int j, Result result) {
            this.result = result;
            this.subBlockA = subBlockA;
            this.subBlockB = subBlockB;
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            DefaultMultiplier dMulti = new DefaultMultiplier(subBlockA, subBlockB);
            Result defaultResult = dMulti.Multiply();
            result.addSubBlock(i, j, defaultResult.getResultMatrix(), SUB_BLOCK_SIZE);
        }
    }

    private static final int SUB_BLOCK_SIZE = 100;
    private int[][][][] matrixASubBlocks;
    private int[][][][] matrixBSubBlocks;
    private int threadsAmount;

    public  FoxMultiplier(int threadsAmount) {
        super();
        this.threadsAmount = threadsAmount;
    }

    public FoxMultiplier(int[][] matrixMultiplicand, int[][] matrixMultiplier, int threadsAmount) {
        super(matrixMultiplicand, matrixMultiplier);
        this.threadsAmount = threadsAmount;
        this.matrixASubBlocks = MatrixHelper.SplitToSubBlocks(matrixMultiplicand, SUB_BLOCK_SIZE);
        this.matrixBSubBlocks = MatrixHelper.SplitToSubBlocks(matrixMultiplier, SUB_BLOCK_SIZE);
    }

    public void setThreadsAmount(int threadsAmount) {
        this.threadsAmount = threadsAmount;
    }

    @Override
    public void setMatrixMultiplicand(int[][] matrixA) {
        this.matrixA = matrixA;
        this.matrixASubBlocks = MatrixHelper.SplitToSubBlocks(matrixA, SUB_BLOCK_SIZE);
    }

    @Override
    public void setMatrixMultiplier(int[][] matrixB) {
        this.matrixB = matrixB;
        this.matrixBSubBlocks = MatrixHelper.SplitToSubBlocks(matrixB, SUB_BLOCK_SIZE);
    }

    @Override
    public Result Multiply() {
        Result result = new Result(matrixA.length, matrixB[0].length);
        if (!AreMatrixFits()) return result;
        ExecutorService executor = Executors.newFixedThreadPool(threadsAmount);
        for (int k = 0; k < matrixBSubBlocks.length; k++) {
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < matrixASubBlocks.length; i++) {
                for (int j = 0; j < matrixASubBlocks[i].length; j++) {
                    futures.add(executor.submit(new FoxTask(matrixASubBlocks[i][(i + k) % matrixASubBlocks[i].length], matrixBSubBlocks[(i + k) % matrixBSubBlocks.length][j], i, j, result)));
                }
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

    private boolean AreMatrixFits() {
        return !(matrixA.length != matrixA[0].length
                 || matrixB.length != matrixB[0].length
                 || matrixA.length != matrixB.length
                 || matrixA.length % SUB_BLOCK_SIZE != 0);
    }
}
