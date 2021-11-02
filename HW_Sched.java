import java.util.*;

class Assignment implements Comparator<Assignment> {
    int number;
    int weight;
    int deadline;


    protected Assignment() {
    }

    protected Assignment(int number, int weight, int deadline) {
        this.number = number;
        this.weight = weight;
        this.deadline = deadline;
    }


    /**
     * This method is used to sort to compare assignment objects for sorting.
     * Return -1 if a1 > a2
     * Return 1 if a1 < a2
     * Return 0 if a1 = a2
     */
    @Override
    public int compare(Assignment a1, Assignment a2) {
        if (a1.weight < a2.weight) {
            return 1;
        } else if (a1.weight > a2.weight) {
            return -1;
        } else if (a1.deadline > a2.deadline) {
            return 1;
        } else if (a1.deadline < a2.deadline) {
            return -1;
        } else
            return 0;
    }
}

public class HW_Sched {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected HW_Sched(int[] weights, int[] deadlines, int size) {
        for (int i = 0; i < size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m = size;
    }


    /**
     * @return Array where output[i] corresponds to the assignment
     * that will be done at time i.
     */
    public int[] SelectAssignments() {

        Collections.sort(Assignments, new Assignment());

        int[] homeworkPlan = new int[lastDeadline];
        for (int i=0; i < homeworkPlan.length; ++i) {
            homeworkPlan[i] = -1;
        }
        int planSize = homeworkPlan.length;
        int aSize = Assignments.size();

        for (int i = 0; i < aSize; i++) {
            for (int j = Assignments.get(i).deadline-1; j > -1 && homeworkPlan[j] == -1; j--) {
                    homeworkPlan[j] = Assignments.get(i).number;
                    j = -1;
                }
            }

        return homeworkPlan;
    }
}

