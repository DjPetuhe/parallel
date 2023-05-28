package Task3;

import Common.Document;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class DocumentCommonTask extends RecursiveTask<HashMap<String, Integer>> {
    private final Document document;

    DocumentCommonTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected HashMap<String, Integer> compute() {
        return CommonWordStatistic.wordsCount(document);
    }
}
