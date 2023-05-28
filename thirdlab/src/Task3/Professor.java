package Task3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Professor implements Runnable {
    private final EJournal journal;
    private final boolean lecturer;
    private int groupIndex;
    ArrayList<Group> responsibleGroups;
    private static Lock locker = new ReentrantLock();

    public Professor(EJournal journal) {
        this.journal = journal;
        this.responsibleGroups = journal.getGroups();
        lecturer = true;
    }

    public Professor(EJournal journal, int responsibleGroupIndex) {
        this.journal = journal;
        this.groupIndex = responsibleGroupIndex;
        this.responsibleGroups = new ArrayList<>();
        this.responsibleGroups.add(journal.getGroup(responsibleGroupIndex - 1));
        lecturer = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!journal.isWeekend() || !journal.allHaveMarks()) {
            for (Group group : responsibleGroups) {
                for (int i = 0; i < group.getSize(); i++) {
                    int mark = random.nextInt(100) + 1;
                    if (group.setStudentMark(i, mark)) {
                        locker.lock();
                        if (lecturer)
                            System.out.println("Lecturer: " + mark + " " + group.getStudentName(i));
                        else
                            System.out.println("Assistant of group #" + groupIndex + ": " + mark + " " + group.getStudentName(i));
                        locker.unlock();
                    }
                }
            }
        }
    }
}
