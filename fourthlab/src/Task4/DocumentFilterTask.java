package Task4;

import Common.Document;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

public class DocumentFilterTask extends RecursiveTask<HashMap<String, Integer>> {
    private final Document document;
    private final LinkedList<String> params;

    DocumentFilterTask(Document document, LinkedList<String> params) {
        super();
        this.document = document;
        this.params = params;
    }

    @Override
    protected HashMap<String, Integer> compute() {
        return FilterDocumentStatistic.filterWordCount(document, params);
    }
}
