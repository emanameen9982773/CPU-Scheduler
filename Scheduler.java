import java.util.PriorityQueue;
import java.util.LinkedList;

public class Scheduler {

  /*
   * this is the method that do all the scheduling work,
   * it receives a PQ called arrival containning all Processes ordered by the
   * arrival time
   */
  public static LinkedList<Process> schedule(PriorityQueue<Process> arrival) {

    PriorityQueue<Process> readyQueue = new PriorityQueue<>(Process.burstTimeComparator); // ready queue holding the
                                                                                          // processes ready to be
                                                                                          // executed
    LinkedList<Process> termenated = new LinkedList<>(); // list to store the termenated processes, so they dont get
                                                         // lost because we will need them later to calculate the
                                                         // waitting time,....etc

    int time = 0; // clock
    int numOfCS = 0; // number of context swtich
    int start = 0; // the start time for the running process
    int cpuI = 0; // how many times the cpu stay Idle
    Process running = null; // currently running process


    // keep doing this until ther is no process in the Ready queue and no upcoming
    // Processes and no currently running process
    while (!arrival.isEmpty() || !readyQueue.isEmpty() || running != null) {

      while (!arrival.isEmpty() && time == arrival.peek().arrival) { // add the processes with arrival time equals to
                                                                     // the current time to the Ready queue
        readyQueue.add(arrival.poll());

        if (running != null && !readyQueue.isEmpty())
          if (running.burstTime > readyQueue.element().burstTime) {     
           /*
            * Switch between processes in case there is a Process in the Ready queue
            * with burst time less than the currently running process
            */
            System.out.println(start + "-" + time + "\t\tP" + running.ID);
            System.out.println(time + "-" + (time + 1) + "\t\tCS");
            numOfCS++;
            time++;
            readyQueue.add(running);
            running = readyQueue.remove();
            start = time;
            continue;
          }

      }

      if (running == null && !readyQueue.isEmpty()) {
        if (time > 0 && !Process.firstProcess) {
          System.out.println(time + "-" + (time + 1) + "\t\tCS"); // Context Switch
          time++;
          numOfCS++;
        }
        running = readyQueue.remove();
        Process.firstProcess = false;
        start = time;
        continue;
      }

      

      // excute the currently running procee if exists by decrementing its burst time
      if (running != null) {
        running.burstTime--;
        time++;
        if (running.burstTime == 0) { // if the burst time is 0 save the value of current time(clock)in
                                      // termenationTime attribute of the process
          running.termenationTime = time;
          termenated.add(running); // add the process to the termenated processes list
          System.out.println(start + "-" + time + "\t\tP" + running.ID);
          running = null;
        }
        continue;
      }

      cpuI++;
      time++;

    }

    Process.totalIdleTime = numOfCS + cpuI; // save the total time the cpu stay idle in a staic varible of class process
    Process.toutalExecutionTime = time; // save the total time
    return termenated;

  }

  // a void method to display the information of processes
  static void printResults(PriorityQueue<Process> arrival, int contextSwitchTime) {

    System.out.print("\nNumber of processes = " + arrival.size() + " (");
    for (int i = 0; i < arrival.size() - 1; i++)
      System.out.print("P" + (i + 1) + ", ");
    System.out.println("P" + arrival.size() + " )\nArrival times and burst times as follows:");

    for (Process p : arrival) {
      System.out.println("P" + p.ID + ": Arrival Time: " + p.arrival + ", Burst Time: " + p.burstTime+" ms");
    }

    System.out.println("Scheduling Algorithm: Shortest remaining time first");
    System.out.println("Context Switch: " + contextSwitchTime + " ms");
    System.out.println("Time\t\tProcess/CS");
  }

  static double calculateAverageWaitting(LinkedList<Process> processes) {
    double averageWaitting = 0;
    for (Process process : processes)
      averageWaitting += process.calculateWaitingTime();
    return averageWaitting / processes.size();
  }

  static double calculateAverageTurnaround(LinkedList<Process> processes) {
    double averageTurnaround = 0;
    for (Process process : processes)
      averageTurnaround += process.calculateTurnaroundTime();
    return averageTurnaround / processes.size();
  }

}