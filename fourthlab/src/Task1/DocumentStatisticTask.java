package Task1;

import Common.Document;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class DocumentStatisticTask extends RecursiveTask<HashMap<Integer, Integer>> {
    private final Document document;

    DocumentStatisticTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected HashMap<Integer, Integer> compute() {
        return WordLengthStatistic.wordsLengthCount(document);
    }
}
