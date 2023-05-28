package Common;

import java.util.concurrent.ForkJoinPool;

public class GeneralStatistic {
    protected final ForkJoinPool forkJoinPool = new ForkJoinPool();
    protected long parallelTime;

    public static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public double getParallelTime() {
        return parallelTime;
    }
}
