import java.util.PriorityQueue;
import java.util.LinkedList;


public class Scheduler {

  public static LinkedList<Process> schedule (PriorityQueue<Process>  arrival) {

    
    PriorityQueue<Process> eventQueue = new PriorityQueue<>(Process.burstTimeComparator);
    LinkedList<Process> termenated = new LinkedList<>();


    int time = 0;
    int numOfCS = 0;
    int start = 0;
    int cpuI = 0;
    Process running = null;

    
    while (!arrival.isEmpty() || !eventQueue.isEmpty() || running != null) {
      

      while (!arrival.isEmpty() && time == arrival.peek().arrival)
        eventQueue.add(arrival.poll());


      if (running == null && !eventQueue.isEmpty()) {
        if (time > 0 && !Process.firstProcess) {
          System.out.println(time + "-" + (time + 1) + "\t\tCS"); // Context Switch
          time++;
          numOfCS++;
        }
        running = eventQueue.remove();
        Process.firstProcess=false;
        start = time;
        continue;
      }


      else if (running != null && !eventQueue.isEmpty())
        if (running.burstTime > eventQueue.element().burstTime) {
          System.out.println(start + "-" + time + "\t\tP" + running.ID);
          System.out.println(time + "-" + (time + 1) + "\t\tCS");
          numOfCS++;
          time++;
          eventQueue.add(running);
          running = eventQueue.remove();
          start = time;
          continue;
        }



      if (running != null) {

        if (running.first) {
          running.startTime = time;
          running.first = false;
        }

        running.burstTime--;
        time++;

        if (running.burstTime == 0) {
          running.termenationTime = time;
          termenated.add(running);
          System.out.println(start + "-" + time + "\t\tP" + running.ID);
          running = null;
        }
        continue;
      }


      cpuI++;
      time++;

    }
    
    Process.totalIdleTime=numOfCS+cpuI ;
    Process.toutalExecutionTime=time;
    return termenated;

  }


  static void printResults(PriorityQueue<Process> arrival, int contextSwitchTime) {

    System.out.print("\nNumber of processes = " + arrival.size()+" (");
    for(int i=0; i<arrival.size()-1; i++)
    System.out.print("P"+(i+1)+", ");
    System.out.println("P"+arrival.size()+" )\nArrival times and burst times as follows:");

    for (Process p : arrival) {
        System.out.println("Process ID: " + p.ID + ", Arrival Time: " + p.arrival + ", Burst Time: " + p.burstTime);
    }

    System.out.println("Scheduling Algorithm: Shortest remaining time first");
    System.out.println("Context Switch: " + contextSwitchTime + " ms");
    System.out.println("Time\t\tProcess/CS");
}


static double calculateAverageWaitting(LinkedList<Process> processes){
  double averageWaitting=0;
  for(Process process: processes)
  averageWaitting+= process.calculateWaitingTime();
  return averageWaitting/processes.size();
}


static double calculateAverageTurnaround(LinkedList<Process> processes){
  double averageTurnaround=0;
  for(Process process: processes)
  averageTurnaround+= process.calculateTurnaroundTime();
  return averageTurnaround/processes.size();
}

}