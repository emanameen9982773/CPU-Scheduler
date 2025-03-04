import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class Scheduler {

  public static void main(String[] args) {

    PriorityQueue<Process> arrival = new PriorityQueue<>(Process.arrivalComparator);
    PriorityQueue<Process> eventQueue = new PriorityQueue<>(Process.burstTimeComparator);
    LinkedList<Process> termenated = new LinkedList<>();

    arrival.add(new Process(0, 8));
    arrival.add(new Process(1, 4));
    arrival.add(new Process(2, 5));
    arrival.add(new Process(3, 5));

    int time = 0;
    int numOfCS = 0;
    int start = 0;
    int cpuI = 0;

    Process running = null;
    while (!arrival.isEmpty() || !eventQueue.isEmpty() || running != null) {
      

      while (!arrival.isEmpty() && time == arrival.peek().arrival)
        eventQueue.add(arrival.poll());


      if (running == null && !eventQueue.isEmpty()) {
        if (time > 0) {
          System.out.println(time + "-" + (time + 1) + "\tCS"); // Context Switch
          time++;
          numOfCS++;
        }
        running = eventQueue.remove();
        start = time;
        continue;
      }


      else if (running != null && !eventQueue.isEmpty())
        if (running.burstTime > eventQueue.element().burstTime) {
          System.out.println(start + "-" + time + "\tP" + running.ID);
          System.out.println(time + "-" + (time + 1) + "\tCS");
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
          System.out.println(start + "-" + time + "\tP" + running.ID);
          running = null;
        }
        continue;
      }


      cpuI++;
      time++;

    }
    double Waiting=0;
   
    for(Process process:termenated ){
       Waiting+=process.calculateWaitingTime();
    }
    System.out.println(Waiting/4);

  }

}