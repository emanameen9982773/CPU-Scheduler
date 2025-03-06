import java.util.*;

public class CPU {
    

    public static void main(String [] args){

        PriorityQueue<Process> arrival = new PriorityQueue<>(Process.arrivalComparator); //PQ called arrival containning all Processes ordered by the arrival time

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int nump = scanner.nextInt();

        if(nump<=0){ //if invalid number of processes was entered
          System.out.println("Pleas enter a valid number of processes");
          System.exit(0);
        }

        for (int i = 0; i < nump; i++) {
            System.out.print("Enter arrival time and burst time for P" + (i + 1) + ": \n");
            int arrivalTime = scanner.nextInt();
            int burst = scanner.nextInt();
            arrival.add(new Process( arrivalTime, burst));   
        }
         
        Scheduler.printResults(arrival, 1); // go to class Scheduler to see discription of this method
        LinkedList<Process> termenated =  Scheduler.schedule(arrival); //go to class Scheduler to see a brief discription of this method
        System.out.printf("Performance Metrics\nAverage Turnaround Time: %.2f\n",Scheduler.calculateAverageTurnaround(termenated));
        System.out.printf("Average Waiting Time: %.2f\n",Scheduler.calculateAverageWaitting(termenated));
        System.out.printf("CPU Utilization: %.2f",Process.calculateCPUutilization());

        scanner.close();
    }

    
    
}