import java.util.PriorityQueue;

public class ArrivalPQ {
    private PriorityQueue<Process> queue;

    public ArrivalTimePQ() {
        queue = new PriorityQueue<>(Process.arrivalComparator);
    }

    public void addProcess(Process process) {
        queue.offer(process);
    }

    public Process getNextProcess() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void printQueue() {
        System.out.println("Arrival Time Priority Queue:");
        for (Process p : queue) {
            System.out.println(p);
        }
    }
}
