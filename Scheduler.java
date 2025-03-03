import java.util.PriorityQueue;;

public class Scheduler{
    

  public static void main(String[] args) {
   
     PriorityQueue arrival = new PriorityQueue<Integer>();
     PriorityQueue eventQueue= new PriorityQueue<Integer>();

     int time=0;
     int numOfCS=0;
     for(int i=0; i<4; i++)
     System.out.println(arrival.remove());
    while (!arrival.isEmpty() || !eventQueue.isEmpty()) {
      if(time==(int) arrival.element())
        eventQueue.add(arrival.remove());
         
      
      
    }


  }

}