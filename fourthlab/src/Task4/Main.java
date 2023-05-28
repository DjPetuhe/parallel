package Task4;

import Common.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FilterDocumentStatistic wordLengthStatistic = new FilterDocumentStatistic();
        LinkedList<String> string = new LinkedList<>();
        string.push("The");
        string.push("find");
        string.push("there");
        string.push("about");
        string.push("Hamlet");
        Folder folder = Folder.fromDirectory(new File("texts"));
        printResults(wordLengthStatistic.countFilterWordsParallel(folder, string), string.size());
    }

    private static void printResults(HashMap<String, Integer> results, int size) {
        SortedSet<Map.Entry<String, Integer>> sorted = Sorter.entriesSortedByValues(results);
        for (Map.Entry<String, Integer> entry : sorted) {
            if (entry.getValue() == 0)
                break;
            System.out.println(entry.getKey() + " " + entry.getValue() + "/" + size);
        }
    }
}
