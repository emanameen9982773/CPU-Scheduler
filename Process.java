import java.util.Comparator;

public class Process {
  
    public static int counter;
    public int ID;
    public int startTime;
    public int termenationTime;
    public int arrival;
    public int burstTime;
    public Process nextProcess;
    public boolean first;
    

    public Process(int arr, int burst){

        ID=++counter;
        arrival=arr;
        burstTime=burst;
        nextProcess=null;
        first=true;
 
    }

      public static Comparator<Process> arrivalComparator = new Comparator<Process>() {
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.arrival, p2.arrival);
        }
    };
    

    public static Comparator<Process> burstTimeComparator = new Comparator<Process>() {
        public int compare(Process p1, Process p2) {
            if (p1.burstTime == p2.burstTime) {
                return Integer.compare(p1.arrival, p2.arrival); // FCFS for ties
            }
            return Integer.compare(p1.burstTime, p2.burstTime);
        }
    };
  
  public double calculateTurnaroundTime(int n){
  double Turnaround=termenationTime - startTime;
  return Turnaround/n ;
}

 public double calculateWaitingTime(int n){
  double Waiting = calculateTurnaroundTime(n) - burstTime;
  return Waiting/n ;
}
}

