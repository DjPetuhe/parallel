package Task1;

import Common.*;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class WordLengthStatistic extends GeneralStatistic {
    private long serialTime;
    public HashMap<Integer, Integer> countWordsLengthParallel(Folder folder) {
        long start = System.currentTimeMillis();
        HashMap<Integer, Integer> result = forkJoinPool.invoke(new FolderStatisticTask(folder));
        parallelTime = System.currentTimeMillis() - start;
        return result;
    }

    public HashMap<Integer, Integer> countWordsLengthSerial(Folder folder) {
        long start = System.currentTimeMillis();
        HashMap<Integer, Integer> result = new HashMap<>();
        for (Folder subFolder : folder.getSubFolders()) {
            countWordsLengthSerial(subFolder).forEach(
                    (key, value) -> result.merge(key, value, Integer::sum)
            );
        }
        for (Document document : folder.getDocuments()) {
            wordsLengthCount(document).forEach(
                    (key, value) -> result.merge(key, value, Integer::sum)
            );
        }
        serialTime = System.currentTimeMillis() - start;
        return result;
    }

    public static HashMap<Integer, Integer> wordsLengthCount(Document document) {
        HashMap<Integer, Integer> lengths = new HashMap<>();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                int len = word.length();
                if (len != 0)
                    lengths.merge(len, 1, Integer::sum);
            }
        }
        return lengths;
    }

    public double getSerialTime() {
        return serialTime;
    }
}
