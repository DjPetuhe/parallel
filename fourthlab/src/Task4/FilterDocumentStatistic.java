package Task4;

import Common.*;

import java.util.HashMap;
import java.util.LinkedList;

public class FilterDocumentStatistic extends GeneralStatistic {
    public HashMap<String, Integer> countFilterWordsParallel(Folder folder, LinkedList<String> params) {
        long start = System.currentTimeMillis();
        HashMap<String, Integer> result = forkJoinPool.invoke(new FolderFilterTask(folder, params));
        parallelTime = System.currentTimeMillis() - start;
        return result;
    }

    public static HashMap<String, Integer> filterWordCount(Document document, LinkedList<String> params) {
        HashMap<String, Integer> lengths = new HashMap<>();
        lengths.put(document.getName(), 0);
        LinkedList<String> localParam = (LinkedList<String>) params.clone();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                LinkedList<String> toRemove = new LinkedList<>();
                for (String param : localParam) {
                    if (word.length() != 0 && word.equals(param)) {
                        lengths.merge(document.getName(), 1, Integer::sum);
                        toRemove.add(param);
                    }
                }
                for (String rem : toRemove)
                    localParam.remove(rem);
                if (localParam.size() == 0)
                    return lengths;
            }
        }
        return lengths;
    }
}
