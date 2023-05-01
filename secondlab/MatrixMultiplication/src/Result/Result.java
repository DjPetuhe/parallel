package Result;

public class Result {
    int[][] resultMatrix;
    public Result(int height, int width) {
        resultMatrix = new int[height][width];
    }

    public synchronized void setValue(int i, int j, int value) {
        resultMatrix[i][j] = value;
    }

    public synchronized int getValue(int i, int j) {
        return  resultMatrix[i][j];
    }

    public synchronized int getHeight() {
        return resultMatrix.length;
    }

    public synchronized int getWidth() {
        return resultMatrix[0].length;
    }

    public synchronized int[][] getResultMatrix() {
        return resultMatrix;
    }

    public synchronized void addSubBlock(int subMatrixI, int subMatrixJ, int[][] subBlock, int size) {
        for (int i = 0; i < subBlock.length; i++) {
            for (int j = 0; j < subBlock[i].length; j++) {
                resultMatrix[subMatrixI * size + i][subMatrixJ * size + j] += subBlock[i][j];
            }
        }
    }
}
