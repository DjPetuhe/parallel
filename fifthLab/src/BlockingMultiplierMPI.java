import mpi.MPI;

import static java.lang.System.exit;

public class BlockingMultiplierMPI {
    private static final int NR = 1000;
    private static final int NC = 1000;
    private static final int MASTER_RANK = 0;
    private static final int TAG_FROM_MASTER = 1;
    private static final int TAG_FROM_WORKER = 2;

    public static void main(String[] args) {
        int[][] matrix1, matrix2, resultMatrix;

        MPI.Init(args);

        int tasksAmount = MPI.COMM_WORLD.Size();
        if(tasksAmount < 2){
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }

        int workersAmount = tasksAmount - 1;
        int taskRank = MPI.COMM_WORLD.Rank();

        if(taskRank == MASTER_RANK){
            matrix1 = MatrixHelper.createMatrix(NR, NC, false);
            matrix2 = MatrixHelper.createMatrix(NR, NC, false);
            int[] matrix1Array = MatrixHelper.toArray(matrix1, NR, NC);
            int[] matrix2Array = MatrixHelper.toArray(matrix2, NR, NC);
            int[] resultArray = new int[NR * NC];
            long startTime = System.currentTimeMillis();

            int workerRowsAmount = NR / workersAmount;
            int rowsLeft = NR % workersAmount;
            int offset = 0;
            for (int workerRank = 1; workerRank <= workersAmount; workerRank++) {
                int rows = (workerRank <= rowsLeft) ? workerRowsAmount + 1 : workerRowsAmount;

                MPI.COMM_WORLD.Send(new int[] { offset }, 0, 1, MPI.INT, workerRank, TAG_FROM_MASTER);
                MPI.COMM_WORLD.Send(new int[] { rows }, 0, 1, MPI.INT, workerRank, TAG_FROM_MASTER);

                MPI.COMM_WORLD.Send(matrix1Array,offset * NC, rows * NC, MPI.INT, workerRank, TAG_FROM_MASTER);
                MPI.COMM_WORLD.Send(matrix2Array,0, matrix2Array.length, MPI.INT, workerRank, TAG_FROM_MASTER);

                offset += rows;
            }

            int[] buffFrom = new int[1];
            int[] buffCount = new int[1];
            for (int workerRank = 1; workerRank <= workersAmount; workerRank++) {
                MPI.COMM_WORLD.Recv(buffFrom,0,1, MPI.INT, workerRank, TAG_FROM_WORKER);
                MPI.COMM_WORLD.Recv(buffCount,0,1, MPI.INT, workerRank, TAG_FROM_WORKER);
                MPI.COMM_WORLD.Recv(resultArray, buffFrom[0], buffCount[0] , MPI.INT, workerRank, TAG_FROM_WORKER);
            }
            resultMatrix = MatrixHelper.toMatrix(resultArray, NR, NC);

            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Total time (sec.) = " + totalTime / 1000.0);

            int[][] matrixTest = MatrixHelper.multiply(matrix1, matrix2);
            System.out.println("Multiplication correct = " + MatrixHelper.equal(resultMatrix, matrixTest));
        }
        else {
            int[] buffOffset = new int[1];
            int[] buffRow = new int[1];
            MPI.COMM_WORLD.Recv(buffOffset,0,1, MPI.INT, MASTER_RANK, TAG_FROM_MASTER);
            MPI.COMM_WORLD.Recv(buffRow,0,1, MPI.INT, MASTER_RANK, TAG_FROM_MASTER);

            int[] workerMatrix1 = new int[buffRow[0] * NC];
            int[] workerMatrix2 = new int[NR * NC];
            MPI.COMM_WORLD.Recv(workerMatrix1,0, workerMatrix1.length, MPI.INT, MASTER_RANK, TAG_FROM_MASTER);
            MPI.COMM_WORLD.Recv(workerMatrix2,0, workerMatrix2.length, MPI.INT, MASTER_RANK, TAG_FROM_MASTER);

            int[] partOfResultArr = MatrixHelper.multiplyMatrixArrays(workerMatrix1, workerMatrix2, NR, NC, buffRow[0]);
            MPI.COMM_WORLD.Send(new int[] {buffOffset[0] * NC},0, 1, MPI.INT, MASTER_RANK, TAG_FROM_WORKER);
            MPI.COMM_WORLD.Send(new int[] {buffRow[0] * NC},0, 1, MPI.INT, MASTER_RANK, TAG_FROM_WORKER);
            MPI.COMM_WORLD.Send(partOfResultArr,0, partOfResultArr.length, MPI.INT, MASTER_RANK, TAG_FROM_WORKER);
        }
        MPI.Finalize();
    }
}
