package Task3;

import Common.*;

import java.util.Map;
import java.util.HashMap;

public class CommonWordStatistic extends GeneralStatistic{
    public HashMap<String, Integer> countWordsParallel(Folder folder) {
        long start = System.currentTimeMillis();
        HashMap<String, Integer> result = forkJoinPool.invoke(new FolderCommonTask(folder));
        parallelTime = System.currentTimeMillis() - start;
        return result;
    }

    public static HashMap<String, Integer> wordsCount(Document document) {
        HashMap<String, Integer> lengths = new HashMap<>();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                int len = word.length();
                if (len != 0)
                    lengths.merge(word, 1, Integer::sum);
            }
        }
        return lengths;
    }

    public static HashMap<String, Integer> commonWords(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        HashMap<String, Integer> map3 = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String word = entry.getKey();
            if (map2.containsKey(word)) {
                map3.put(word, entry.getValue() + map2.get(word));
            }
        }
        return map3;
    }
}
