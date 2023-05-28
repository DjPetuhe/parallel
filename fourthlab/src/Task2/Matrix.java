package Task2;

public class Matrix {
    int i;
    int j;
    int[][] resultMatrix;
    public Matrix(int height, int width, int i, int j) {
        resultMatrix = new int[height][width];
        this.i = i;
        this.j = j;
    }

    public void setValue(int i, int j, int value) {
        resultMatrix[i][j] = value;
    }

    public int getValue(int i, int j) {
        return  resultMatrix[i][j];
    }

    public int getHeight() {
        return resultMatrix.length;
    }

    public int getWidth(int i) {
        return resultMatrix[i].length;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int[][] getResultMatrix() {
        return resultMatrix;
    }

    public void addSubBlock(Matrix matrix, int size) {
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(i); j++) {
                resultMatrix[matrix.getI() * size + i][matrix.getJ() * size + j] += matrix.getValue(i, j);
            }
        }
    }
}
