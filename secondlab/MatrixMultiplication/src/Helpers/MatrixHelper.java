package Helpers;

import java.util.Random;

public class MatrixHelper {
    private static int[][] CreateRandomMatrix(int height, int width) {
        int[][] matrix = new int[height][width];
        Random r = new Random();
        for (int i = 0; i < height; i++) {
            for (int j =0; j < width; j++) {
                matrix[i][j] = r.nextInt(1999) - 999;
            }
        }
        return matrix;
    }

    private static int[][] CreateIdentityMatrix(int height, int width) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j) matrix[i][j] = 1;
                else matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static int[][] CreateMatrix(int height, int width, boolean identity) {
        height = height <= 0 ? 1 : height;
        width = width <= 0 ? 1 : width;
        if (identity) return CreateIdentityMatrix(height, width);
        else return CreateRandomMatrix(height, width);
    }

    public static void PrintMatrix(int[][] matrix, int width) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format("%" + width + "s", matrix[i][j]));
            }
            System.out.println();
        }
    }

    public static boolean CompareMatrix(int[][] matrixA, int[][] matrixB) {
        if (matrixA.length != matrixB.length) return false;
        for (int i = 0; i < matrixA.length; i++) {
            if (matrixA[i].length != matrixB[i].length) return false;
            for (int j = 0; j < matrixA[i].length; j++) {
                if (matrixA[i][j] != matrixB[i][j]) return  false;
            }
        }
        return true;
    }

    public static int[][][][] SplitToSubBlocks(int[][] matrix, int size) {
        int[][][][] matrixSubBlocks = new int[matrix.length / size][matrix[0].length / size][size][size];
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i % size == 0 && i != 0) k++;
            int l = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j % size == 0 && j != 0) l++;
                matrixSubBlocks[k][l][i % size][j % size] = matrix[i][j];
            }
        }
        return matrixSubBlocks;
    }

}
