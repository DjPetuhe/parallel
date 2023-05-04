package Task3;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        ArrayList<Student> group1 = new ArrayList<>()
        {
            {
                add(new Student("Bob Marly"));
                add(new Student("Jason Statham"));
                add(new Student("Merlin Monro"));
            }
        };
        ArrayList<Student> group2 = new ArrayList<>()
        {
            {
                add(new Student("Tom Cruise"));
                add(new Student("Bradley Pitt"));
                add(new Student("Angelina Jolie"));
            }
        };
        ArrayList<Student> group3 = new ArrayList<>()
        {
            {
                add(new Student("John Depp"));
                add(new Student("Leonardo DiCaprio"));
                add(new Student("Keanu Reeves"));
            }
        };
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group(group1));
        groups.add(new Group(group2));
        groups.add(new Group(group3));
        EJournal journal = new EJournal(groups);

        StudyWeeks time = new StudyWeeks(5, 5000, journal);

        Professor lecturer = new Professor(time, journal);
        Professor assist1 = new Professor(time, journal, 1);
        Professor assist2 = new Professor(time, journal, 2);
        Professor assist3 = new Professor(time, journal, 3);

        new Thread(lecturer).start();
        new Thread(assist1).start();
        new Thread(assist2).start();
        new Thread(assist3).start();
        new Thread(time).start();
    }
}
