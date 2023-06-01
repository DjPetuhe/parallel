import mpi.*;

import static java.lang.System.exit;

public class MultiplierMPI {
    private static final int NR = 2500;
    private static final int NC = 2500;
    private static final int MASTER_RANK = 0;

    public static void main(String[] args) {
        MPI.Init(args);
        int[][] matrix1 = new int[0][];
        int[][] matrix2 = new int[0][];
        int[][] resultMatrix;
        int[] matrix1Array = new int[NR * NC];
        int[] matrix2Array = new int[NR * NC];
        long time = 0;

        int tasksAmount = MPI.COMM_WORLD.Size();
        if(tasksAmount < 2){
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }

        int taskRank = MPI.COMM_WORLD.Rank();

        if (taskRank == MASTER_RANK) {
            matrix1 = MatrixHelper.createMatrix(NR, NC, false);
            matrix2 = MatrixHelper.createMatrix(NR, NC, false);
            time = System.currentTimeMillis();
            matrix1Array = MatrixHelper.toArray(matrix1, NR, NC);
            matrix2Array = MatrixHelper.toArray(matrix2, NR, NC);
        }

        int[] elementsAmount = new int[tasksAmount];
        int[] offsets = new int[tasksAmount];

        int workerRowsAmount = NR / tasksAmount;
        int rowsLeft = NR  % tasksAmount;
        int offset = 0;
        for (int workerRank = 0; workerRank < tasksAmount; workerRank++) {
            int rows = (workerRank < rowsLeft) ? workerRowsAmount + 1 : workerRowsAmount;
            elementsAmount[workerRank] = rows * NC;
            offsets[workerRank] = offset;
            offset += elementsAmount[workerRank];
        }

        int[] workerMatrix1  = new int[elementsAmount[taskRank]];
        int[] resultArray = new int[NR * NC];

        MPI.COMM_WORLD.Scatterv(matrix1Array, 0, elementsAmount, offsets, MPI.INT, workerMatrix1, 0, elementsAmount[taskRank], MPI.INT, MASTER_RANK);
        MPI.COMM_WORLD.Bcast(matrix2Array,0, matrix2Array.length, MPI.INT, MASTER_RANK);

        int[] partOfResult = MatrixHelper.multiplyMatrixArrays(workerMatrix1, matrix2Array, NR, NC, elementsAmount[taskRank] / NC);

        MPI.COMM_WORLD.Gatherv(partOfResult, 0, partOfResult.length, MPI.INT, resultArray,0, elementsAmount, offsets, MPI.INT, MASTER_RANK);
        //MPI.COMM_WORLD.Allgatherv(partOfResult, 0, partOfResult.length, MPI.INT, resultArray, 0, elementsAmount, offsets, MPI.INT);

        if (taskRank == MASTER_RANK) {
            resultMatrix = MatrixHelper.toMatrix(resultArray, NR, NC);

            System.out.println("Total time (sec.) = " + (System.currentTimeMillis() - time) / 1000.0);

            int[][] matrixTest = MatrixHelper.multiply(matrix1, matrix2);
            System.out.println("Multiplication correct = " + MatrixHelper.equal(resultMatrix, matrixTest));
        }
        MPI.Finalize();
    }
}