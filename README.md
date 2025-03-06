# CPU-Scheduler (SRTF)


# Introduction

Process scheduling is an aspect of modern operating systems, aimed at maximizing CPU utilization and ensuring efficient process execution. This project focuses on implementing the Shortest Remaining Time First (SRTF) algorithm, a preemptive scheduling technique that prioritizes the process with the shortest remaining burst time. In cases where two processes have identical CPU bursts, the First Come First Serve (FCFS) method is applied.


# Implementation 

Data Structures used: 
-	Priority Queue
-	Linked List
-	Comparator


# SRTF Explanation:
The Shortest Remaining Time First (SRTF) scheduling algorithm implemented in the project efficiently chooses and executes the process with the smallest remaining CPU burst time at any given moment. The program begins by accepting user input for process arrival and burst times, stores them in a priority queue ordered by arrival time. The scheduler continuously monitors incoming processes and transfers them to a second priority queue sorted by burst time, ensuring that at every time step, the shortest remaining process is executed first. If a new process arrives with a shorter burst time than the currently running process, the system preempts the current execution, triggering a context switch and scheduling the new process. This dynamic decision-making minimizes average waiting time and optimizes CPU utilization. The final output includes process execution order and performance metrics such as average waiting time, and CPU utilization. These results demonstrate that SRTF scheduling effectively prioritizes short tasks, improving response times in multiprogramming environments.


# Key Advantages of SRTF:
-	Minimizes Average Waiting Time.
-	Efficient CPU Utilization.
-	Preemptive Scheduling Ensures Fairness.
-	Ideal for Interactive Systems.


# Sampil run 
Number of processes = 4 (P1, P2, P3, P4 )
Arrival times and burst times as follows:
P:1: Arrival Time: 0, Burst Time: 6
P:2: Arrival Time: 1, Burst Time: 3
P:3: Arrival Time: 2, Burst Time: 8
P:4: Arrival Time: 3, Burst Time: 7
Scheduling Algorithm: Shortest remaining time first
Context Switch: 1 ms
Time            Process/CS
0-1             P1
1-2             CS
2-5             P2
5-6             CS
6-11            P1
11-12           CS
12-19           P4
19-20           CS
20-28           P3
Performance Metrics
Average Turnaround Time: 14.25
Average Waiting Time: 8.25
CPU Utilization: 85.71