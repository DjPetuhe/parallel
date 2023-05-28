package Task3;

import Common.*;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

public class FolderCommonTask extends RecursiveTask<HashMap<String, Integer>> {
    private final Folder folder;

    FolderCommonTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected HashMap<String, Integer> compute() {
        HashMap<String, Integer> lengths = new HashMap<>();
        List<RecursiveTask<HashMap<String, Integer>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderCommonTask task = new FolderCommonTask(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentCommonTask task = new DocumentCommonTask(document);
            forks.add(task);
            task.fork();
        }
        if (forks.size() != 0)
            lengths = forks.get(0).join();
        for (int i = 1; i < forks.size(); i++) {
            lengths = CommonWordStatistic.commonWords(lengths, forks.get(i).join());
        }
        return lengths;
    }
}
