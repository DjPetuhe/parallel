package Task2;

import java.util.Random;

public class MatrixHelper {
    private static Matrix CreateRandomMatrix(int height, int width) {
        Matrix matrix = new Matrix(height,width, 0, 0);
        Random r = new Random();
        for (int i = 0; i < height; i++) {
            for (int j =0; j < width; j++) {
                matrix.setValue(i, j, r.nextInt(1999) - 999);
            }
        }
        return matrix;
    }

    private static Matrix CreateIdentityMatrix(int height, int width) {
        Matrix matrix = new Matrix(height, width, 0, 0);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j) matrix.setValue(i, j, 1);
                else matrix.setValue(i, j, 0);
            }
        }
        return matrix;
    }

    public static Matrix CreateMatrix(int height, int width, boolean identity) {
        height = height <= 0 ? 1 : height;
        width = width <= 0 ? 1 : width;
        if (identity) return CreateIdentityMatrix(height, width);
        else return CreateRandomMatrix(height, width);
    }

    public static void PrintMatrix(Matrix matrix, int width) {
        System.out.println();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(i); j++) {
                System.out.print(String.format("%" + width + "s", matrix.getValue(i, j)));
            }
            System.out.println();
        }
    }

    public static boolean CompareMatrix(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getHeight() != matrixB.getHeight()) return false;
        for (int i = 0; i < matrixA.getHeight(); i++) {
            if (matrixA.getWidth(i) != matrixB.getWidth(i)) return false;
            for (int j = 0; j < matrixA.getWidth(i); j++) {
                if (matrixA.getValue(i, j) != matrixB.getValue(i, j)) return  false;
            }
        }
        return true;
    }

    public static Matrix[][] SplitToSubBlocks(Matrix matrix, int blocks) {
        Matrix[][] matrixSubBlocks = new Matrix[blocks][blocks];
        int size = matrix.getHeight() / blocks;
        int k = 0;
        matrixSubBlocks[0][0] = new Matrix(size, size, 0, 0);
        for (int i = 0; i < matrix.getHeight(); i++) {
            if (i % size == 0 && i != 0)
                k++;
            int l = 0;
            for (int j = 0; j < matrix.getWidth(i); j++) {
                if (j % size == 0 && j != 0)
                    l++;
                if (i % size == 0 && j % size == 0)
                    matrixSubBlocks[k][l] = new Matrix(size, size, k, l);
                matrixSubBlocks[k][l].setValue(i % size, j % size, matrix.getValue(i, j));
            }
        }
        return matrixSubBlocks;
    }

}
