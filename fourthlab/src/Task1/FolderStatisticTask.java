package Task1;

import Common.*;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

class FolderStatisticTask extends RecursiveTask<HashMap<Integer, Integer>> {
    private final Folder folder;

    FolderStatisticTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected HashMap<Integer, Integer> compute() {
        HashMap<Integer, Integer> lengths = new HashMap<>();
        List<RecursiveTask<HashMap<Integer, Integer>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderStatisticTask task = new FolderStatisticTask(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentStatisticTask task = new DocumentStatisticTask(document);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<HashMap<Integer, Integer>> task : forks) {
            task.join().forEach(
                    (key, value) -> lengths.merge(key, value, Integer::sum)
            );
        }
        return lengths;
    }
}
