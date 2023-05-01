package Multipliers;

import Result.*;

public class DefaultMultiplier {
    protected int[][] matrixA;
    protected int[][] matrixB;

    public DefaultMultiplier(int[][] matrixMultiplicand, int[][] matrixMultiplier) {
        this.matrixA = matrixMultiplicand;
        this.matrixB = matrixMultiplier;
    }

    public void setMatrixMultiplicand(int[][] matrixA) {
        this.matrixA = matrixA;
    }

    public void setMatrixMultiplier(int[][] matrixB) {
        this.matrixB = matrixB;
    }

    public Result Multiply() {
        Result result = new Result(matrixA.length, matrixB[0].length);
        if (matrixA[0].length != matrixB.length) return result;
        for (int i = 0; i < result.getHeight(); i++) {
            for (int j = 0; j < result.getWidth(); j++) {
                int element = 0;
                for (int k = 0; k < matrixA[0].length; k++) {
                    element += matrixA[i][k] * matrixB[k][j];
                }
                result.setValue(i, j, element);
            }
        }
        return result;
    }
}
