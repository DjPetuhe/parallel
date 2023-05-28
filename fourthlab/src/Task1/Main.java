package Task1;

import Common.*;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordLengthStatistic wordLengthStatistic = new WordLengthStatistic();
        Folder folder = Folder.fromDirectory(new File("texts"));
        HashMap<Integer, Integer> lengths = wordLengthStatistic.countWordsLengthSerial(folder);
        StatisticAnalyser analyser = new StatisticAnalyser(lengths);
        printResults(lengths, analyser);

        runTest(wordLengthStatistic, folder);
    }

    private static void runTest(WordLengthStatistic wordLengthStatistic, Folder folder) {
        double parallelTime = 0;
        double serialTime = 0;
        for (int i = 0; i < 10; i++) {
            wordLengthStatistic.countWordsLengthSerial(folder);
            wordLengthStatistic.countWordsLengthParallel(folder);
            serialTime += wordLengthStatistic.getSerialTime();
            parallelTime += wordLengthStatistic.getParallelTime();
        }
        System.out.println();
        System.out.println("Serial average time (sec): " + (serialTime / 10 / 1000.0));
        System.out.println("ForkJoinPool average working time (sec): " + (parallelTime / 10 / 1000.0));
    }

    private static void printResults(HashMap<Integer, Integer> lengths, StatisticAnalyser analyser) {
        analyser.AnalyseText(lengths);
        System.out.println("Sample: " + lengths);
        System.out.println("Mean value: " + analyser.getExpectedValue());
        System.out.println("Mean squared value: " + analyser.getSquaredExpectedValue());
        System.out.println("Dispersion: " + analyser.getDispersion());
        System.out.println("Mean squared deviation: " + analyser.getMeanSquareDeviation());
    }
}
