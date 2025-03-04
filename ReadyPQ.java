import java.util.PriorityQueue;

public class BurstTimePQ {
    private PriorityQueue<Process> queue;

    public BurstTimePQ() {
        queue = new PriorityQueue<>(Process.burstTimeComparator);
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
        System.out.println("Burst Time Priority Queue:");
        for (Process p : queue) {
            System.out.println(p);
        }
    }
}
