package Task2;

import java.util.concurrent.RecursiveTask;

public class DefaultMultiplierTask extends RecursiveTask<Matrix> {
    protected Matrix matrixA;
    protected Matrix matrixB;

    public DefaultMultiplierTask() { }

    public DefaultMultiplierTask(Matrix matrixMultiplicand, Matrix matrixMultiplier) {
        this.matrixA = matrixMultiplicand;
        this.matrixB = matrixMultiplier;
    }

    public void setMatrixMultiplicand(Matrix matrixA) {
        this.matrixA = matrixA;
    }

    public void setMatrixMultiplier(Matrix matrixB) {
        this.matrixB = matrixB;
    }

    @Override
    public Matrix compute() {
        Matrix matrix = new Matrix(matrixA.getHeight(), matrixB.getWidth(0), matrixA.getI(), matrixB.getJ());
        if (matrixA.getWidth(0) != matrixB.getHeight()) return matrix;
        for (int i = 0; i < matrixA.getWidth(0); i++) {
            for (int j = 0; j < matrixB.getHeight(); j++) {
                int element = 0;
                for (int k = 0; k < matrixA.getWidth(0); k++) {
                    element += matrixA.getValue(i, k) * matrixB.getValue(k, j);
                }
                matrix.setValue(i, j, element);
            }
        }
        return matrix;
    }
}
