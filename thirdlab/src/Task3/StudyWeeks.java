package Task3;

public class StudyWeeks implements Runnable {
    private final int weeksAmount;
    private final int weeksTime;
    private EJournal journal;
    private boolean weekend = false;

    public StudyWeeks(int weeksAmount, int weeksTime, EJournal journal) {
        this.weeksAmount = weeksAmount;
        this.weeksTime = weeksTime;
        this.journal = journal;
    }

    @Override
    public void run() {
        int week = 0;
        while (week != weeksAmount) {
            System.out.println("\nWEEK #" + (week + 1) + " STARTS!");
            journal.updateWeeks();
            synchronized (this) {
                notifyAll();
            }
            try {
                Thread.sleep(weeksTime);
            } catch (InterruptedException ignore) { }
            week++;
        }
        weekend = true;
        synchronized (this) {
            notifyAll();
        }
    }

    public boolean isWeekend() {
        return weekend;
    }
}
