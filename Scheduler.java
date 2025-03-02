import java.util.PriorityQueue;;

public class Scheduler{
    

  public static void main(String[] args) {
   
     PriorityQueue arrival = new PriorityQueue<Process>();
     PriorityQueue eventQueue= new PriorityQueue<>();

     int time=0;
     for(int i=0; i<4; i++)
     System.out.println(arrival.remove());
    while (!arrival.isEmpty() || !eventQueue.isEmpty()) {
      
      
    }


  }

}