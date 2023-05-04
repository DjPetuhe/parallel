package Task3;

import java.util.ArrayList;

public class Group {
    private ArrayList<Student> students;

    public Group(ArrayList<Student> students) {
        this.students = students;
    }

    public boolean setStudentMark(int i, int mark) {
        return students.get(i).addMark(mark);
    }

    public void updateStudentsWeek() {
        for (Student student : students) {
            student.weekUpdate();
        }
    }

    public int getSize() {
        return students.size();
    }

    public String getStudentName(int i) {
        return students.get(i).getName();
    }
}
