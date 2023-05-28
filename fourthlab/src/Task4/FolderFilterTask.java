package Task4;

import Common.*;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

public class FolderFilterTask extends RecursiveTask<HashMap<String, Integer>> {
    private final Folder folder;
    private final LinkedList<String> params;

    FolderFilterTask(Folder folder, LinkedList<String> params) {
        super();
        this.folder = folder;
        this.params = params;
    }

    @Override
    protected HashMap<String, Integer> compute() {
        HashMap<String, Integer> lengths = new HashMap<>();
        List<RecursiveTask<HashMap<String, Integer>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderFilterTask task = new FolderFilterTask(subFolder, params);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentFilterTask task = new DocumentFilterTask(document, params);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<HashMap<String, Integer>> task : forks) {
            task.join().forEach(
                    (key, value) -> lengths.merge(key, value, Integer::sum)
            );
        }
        return lengths;
    }
}
