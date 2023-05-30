import java.util.Random;

public class MatrixHelper {
    private static int[][] createRandomMatrix(int height, int width) {
        int[][] matrix = new int[height][width];
        Random r = new Random();
        for (int i = 0; i < height; i++) {
            for (int j =0; j < width; j++) {
                matrix[i][j] = r.nextInt(1999) - 999;
            }
        }
        return matrix;
    }

    private static int[][] createIdentityMatrix(int height, int width) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j) matrix[i][j] = 1;
                else matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static int[][] createMatrix(int height, int width, boolean identity) {
        height = height <= 0 ? 1 : height;
        width = width <= 0 ? 1 : width;
        if (identity) return createIdentityMatrix(height, width);
        else return createRandomMatrix(height, width);
    }

    public static int[] toArray(int[][] matrix, int height, int width) {
        int[] array = new int[height * width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                array[i * width + j] = matrix[i][j];
            }
        }
        return array;
    }

    public static int[][] toMatrix(int[] arr, int height, int width) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = arr[i * height + j];
            }
        }
        return  matrix;
    }

    public static int[] multiplyMatrixArrays(int[] matrix1, int[] matrix2, int height, int width, int rows) {
        int[] result = new int[rows * width];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    result[i * height + j] += matrix1[i * height + k] * matrix2[k * height + j];
                }
            }
        }
        return  result;
    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static boolean equal(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length)
            return false;
        if (matrix1.length != 0 && (matrix1[0].length != matrix2[0].length))
            return  false;
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j])
                    return false;
            }
        }
        return true;
    }
}