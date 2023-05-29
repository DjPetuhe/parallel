package Task3;

import Common.*;

import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.util.SortedSet;

public class Main {
    public static void main(String[] args) throws IOException {
        CommonWordStatistic wordLengthStatistic = new CommonWordStatistic();
        Folder folder = Folder.fromDirectory(new File("texts"));
        printResults(Sorter.entriesSortedByValues(wordLengthStatistic.countWordsParallel(folder)));
    }

    private static void printResults(SortedSet<Map.Entry<String, Integer>> entries) {
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
