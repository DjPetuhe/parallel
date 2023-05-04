package Task3;

import java.util.ArrayList;
import java.util.Random;

public class Professor implements Runnable {
    private final StudyWeeks studyWeeks;
    private final boolean lecturer;
    private int groupIndex;
    private ArrayList<Group> groups = new ArrayList<>();

    public Professor(StudyWeeks studyWeeks, EJournal journal) {
        this.studyWeeks = studyWeeks;
        groups = journal.getGroups();
        lecturer = true;
    }

    public Professor(StudyWeeks studyWeeks, EJournal journal, int responsibleGroupIndex) {
        this.studyWeeks = studyWeeks;
        this.groupIndex = responsibleGroupIndex;
        groups.add(journal.getGroup(responsibleGroupIndex - 1));
        lecturer = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!studyWeeks.isWeekend()) {
            synchronized(studyWeeks) {
                try {
                    studyWeeks.wait();
                } catch (InterruptedException ignore) { }
            }
            for (Group group : groups) {
                for (int i = 0; i < group.getSize(); i++) {
                    int mark = random.nextInt(100) + 1;
                    if (group.setStudentMark(i, mark)) {
                        if (lecturer)
                            System.out.println("Lecturer: " + mark + " " + group.getStudentName(i));
                        else
                            System.out.println("Assistant of group #" + groupIndex + ": " + mark + " " + group.getStudentName(i));
                    }
                }
            }
        }
    }
}
