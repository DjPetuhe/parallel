package Task3;

import java.util.ArrayList;

public class EJournal {
    private ArrayList<Group> groups;

    public EJournal (ArrayList<Group> groups) {
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
}
