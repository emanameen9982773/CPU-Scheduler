public class Process {
  
    public static int counter;
    public int ID;
    public int startTime;
    public int termenationTime;
    public int arrival;
    public int burstTime;
    public Process nextProcess;

    public Process(int arr, int burst){

        ID=counter++;
        arrival=arr;
        burstTime=burst;
        nextProcess=null;
 
    }

    
    
}
