package Task2;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

public class FoxMultiplierTask extends DefaultMultiplierTask {
    private static final int SUB_BLOCK_COUNT = 5;
    private Matrix[][] matrixASubBlocks;
    private Matrix[][] matrixBSubBlocks;
    private int size;

    public FoxMultiplierTask() {
        super();
    }

    public FoxMultiplierTask(Matrix matrixMultiplicand, Matrix matrixMultiplier) {
        super(matrixMultiplicand, matrixMultiplier);
        this.matrixASubBlocks = MatrixHelper.SplitToSubBlocks(matrixMultiplicand, SUB_BLOCK_COUNT);
        this.matrixBSubBlocks = MatrixHelper.SplitToSubBlocks(matrixMultiplier, SUB_BLOCK_COUNT);
        this.size = matrixMultiplicand.getHeight() / SUB_BLOCK_COUNT;
    }

    @Override
    public void setMatrixMultiplicand(Matrix matrixA) {
        this.matrixA = matrixA;
        this.matrixASubBlocks = MatrixHelper.SplitToSubBlocks(matrixA, SUB_BLOCK_COUNT);
    }

    @Override
    public void setMatrixMultiplier(Matrix matrixB) {
        this.matrixB = matrixB;
        this.matrixBSubBlocks = MatrixHelper.SplitToSubBlocks(matrixB, SUB_BLOCK_COUNT);
    }

    @Override
        public Matrix compute() {
        Matrix matrix = new Matrix(matrixA.getHeight(), matrixB.getWidth(0), matrixA.getI(), matrixB.getJ());
        if (!AreMatrixFits()) return matrix;
        List<RecursiveTask<Matrix>> forks = new LinkedList<>();
        for (int k = 0; k < matrixBSubBlocks.length; k++) {
            for (int i = 0; i < matrixASubBlocks.length; i++) {
                for (int j = 0; j < matrixASubBlocks[i].length; j++) {
                    if (matrixASubBlocks[i][j].getHeight() <= 100) {
                        DefaultMultiplierTask task = new DefaultMultiplierTask(matrixASubBlocks[i][(i + k) % matrixASubBlocks[i].length], matrixBSubBlocks[(i + k) % matrixBSubBlocks.length][j]);
                        forks.add(task);
                        task.fork();
                    }
                    else {
                        FoxMultiplierTask task = new FoxMultiplierTask(matrixASubBlocks[i][(i + k) % matrixASubBlocks[i].length], matrixBSubBlocks[(i + k) % matrixBSubBlocks.length][j]);
                        forks.add(task);
                        task.fork();
                    }
                }
            }
        }
        for (RecursiveTask<Matrix> task : forks) {
            matrix.addSubBlock(task.join(), size);
        }
        return matrix;
    }

    private boolean AreMatrixFits() {
        return !(matrixA.getHeight() != matrixA.getWidth(0)
                || matrixB.getHeight() != matrixB.getWidth(0)
                || matrixA.getHeight() != matrixB.getHeight()
                || matrixA.getHeight() % SUB_BLOCK_COUNT != 0);
    }
}
