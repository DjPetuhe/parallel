package Task3;

import Common.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommonWordStatistic wordLengthStatistic = new CommonWordStatistic();
        Folder folder = Folder.fromDirectory(new File("texts"));
        System.out.println(Sorter.entriesSortedByValues(wordLengthStatistic.countWordsParallel(folder)));
    }

}
