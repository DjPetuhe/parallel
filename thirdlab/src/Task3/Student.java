package Task3;

import java.util.ArrayList;

public class Student {
    private ArrayList<Integer> marks = new ArrayList<>();
    private final String fullName;
    private boolean haveWeekMark = false;

    public Student(String fullName) {
        this.fullName = fullName;
    }

    public synchronized boolean addMark(int mark) {
        if (!haveWeekMark) {
            marks.add(mark);
            haveWeekMark = true;
            return  true;
        }
        return false;
    }

    public void weekUpdate() {
        haveWeekMark = false;
    }

    public String getName() {
        return fullName;
    }
}
