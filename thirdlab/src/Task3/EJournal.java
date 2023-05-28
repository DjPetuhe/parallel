package Task3;

import java.util.ArrayList;

public class EJournal implements Runnable {
    private ArrayList<Group> groups;
    private final int weeksAmount;
    private final int weeksTime;
    private boolean weekend = false;

    public EJournal(ArrayList<Group> groups, int weeksAmount, int weeksTime) {
        this.weeksAmount = weeksAmount;
        this.weeksTime = weeksTime;
        this.groups = groups;
    }

    public void updateWeeks() {
        for (Group group : groups) {
            group.updateStudentsWeek();
        }
    }

    public Group getGroup(int index) {
        return groups.get(index);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    @Override
    public void run() {
        int week = 0;
        while (week != weeksAmount) {
            System.out.println("\nWEEK #" + (week + 1) + " STARTS!");
            updateWeeks();
            try {
                Thread.sleep(weeksTime);
            } catch (InterruptedException ignore) { }
            week++;
        }
        weekend = true;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public boolean allHaveMarks() {
        boolean allMarks = true;
        for (Group group : groups) {
            for (int i = 0; i < group.getSize(); i++) {
                allMarks = allMarks && group.getStudent(i).getHaveMark();
            }
        }
        return allMarks;
    }
}
