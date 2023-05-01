package Helpers;

import Multipliers.*;

public class EvaluationHelper {
    public static double Evaluate(DefaultMultiplier multi, int size, int testAmount) {
        long totalTime = 0;
        for (int i = 0; i < testAmount; i++) {
            int[][] matrixA = MatrixHelper.CreateMatrix(size, size, false);
            int[][] matrixB = MatrixHelper.CreateMatrix(size, size, false);
            long start = System.currentTimeMillis();
            multi.setMatrixMultiplicand(matrixA);
            multi.setMatrixMultiplier(matrixB);
            multi.Multiply();
            totalTime += System.currentTimeMillis() - start;
        }
        return (totalTime / testAmount) / 1000.0;
    }

    public static void PrintTests(int testAmount) {
        System.out.println("Default multiplier:");
        PrintOneResult(new DefaultMultiplier(), 500, testAmount);
        PrintOneResult(new DefaultMultiplier(), 1000, testAmount);
        PrintOneResult(new DefaultMultiplier(), 1500, testAmount);

        System.out.println("Stripped multiplier 4 threads:");
        PrintOneResult(new StrippedMultiplier(4), 500, testAmount);
        PrintOneResult(new StrippedMultiplier(4), 1000, testAmount);
        PrintOneResult(new StrippedMultiplier(4), 1500, testAmount);
        System.out.println("Stripped multiplier 8 threads:");
        PrintOneResult(new StrippedMultiplier(8), 500, testAmount);
        PrintOneResult(new StrippedMultiplier(8), 1000, testAmount);
        PrintOneResult(new StrippedMultiplier(8), 1500, testAmount);
        System.out.println("Stripped multiplier 16 threads:");
        PrintOneResult(new StrippedMultiplier(16), 500, testAmount);
        PrintOneResult(new StrippedMultiplier(16), 1000, testAmount);
        PrintOneResult(new StrippedMultiplier(16), 1500, testAmount);

        System.out.println("Fox multiplier 4 threads:");
        PrintOneResult(new FoxMultiplier(4), 500, testAmount);
        PrintOneResult(new FoxMultiplier(4), 1000, testAmount);
        PrintOneResult(new FoxMultiplier(4), 1500, testAmount);
        System.out.println("Fox multiplier 8 threads:");
        PrintOneResult(new FoxMultiplier(8), 500, testAmount);
        PrintOneResult(new FoxMultiplier(8), 1000, testAmount);
        PrintOneResult(new FoxMultiplier(8), 1500, testAmount);
        System.out.println("Fox multiplier 16 threads:");
        PrintOneResult(new FoxMultiplier(16), 500, testAmount);
        PrintOneResult(new FoxMultiplier(16), 1000, testAmount);
        PrintOneResult(new FoxMultiplier(16), 1500, testAmount);
    }

    private static void PrintOneResult(DefaultMultiplier d, int size, int testAmount) {
        System.out.println(size + ": " + EvaluationHelper.Evaluate(d, size, testAmount) + " seconds");
    }
}
