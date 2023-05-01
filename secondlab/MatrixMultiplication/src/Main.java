import Multipliers.*;
import Helpers.*;
import Result.*;

public class Main {
    public static void main(String[] args) {
        int[][] m1 = MatrixHelper.CreateMatrix(10, 10, false);
        int[][] m2 = MatrixHelper.CreateMatrix(10, 10, false);

        DefaultMultiplier dMulti = new Multipliers.DefaultMultiplier(m1, m2);
        Result result1 = dMulti.Multiply();
        StrippedMultiplier sMulti = new StrippedMultiplier(m1, m2, 8);
        Result result2 = sMulti.Multiply();

        MatrixHelper.PrintMatrix(m1, 5);
        MatrixHelper.PrintMatrix(m2, 5);
        MatrixHelper.PrintMatrix(result1.getResultMatrix(), 8);
        MatrixHelper.PrintMatrix(result2.getResultMatrix(), 8);

        System.out.println(MatrixHelper.CompareMatrix(result1.getResultMatrix(), result2.getResultMatrix()));
    }
}