import java.util.*;

public class CPU {
    

    public static void main(String [] args){

        PriorityQueue<Process> arrival = new PriorityQueue<>(Process.arrivalComparator);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int nump = scanner.nextInt();

        for (int i = 0; i < nump; i++) {
            System.out.print("Enter arrival time and burst time for P" + (i + 1) + ": \n");
            int arrivalTime = scanner.nextInt();
            int burst = scanner.nextInt();
            arrival.add(new Process( arrivalTime, burst));
        }
         
        Scheduler.printResults(arrival, 1);
        LinkedList<Process> termenated =  Scheduler.schedule(arrival);
        System.out.printf("Performance Metrics\nAverage Turnaround Time: %.2f",Scheduler.calculateAverageTurnaround(termenated));
        System.out.printf("Average Waiting Time: %.2f",Scheduler.calculateAverageWaitting(termenated));
        System.out.printf("CPU Utilization: %.2f",Process.calculateCPUutilization());

        scanner.close();
    }

    
    
}