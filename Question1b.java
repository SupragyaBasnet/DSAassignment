

//1 b)
// You are the captain of a spaceship and you have been assigned a mission to explore a distant galaxy. Your spaceship is equipped with a set of engines, where each engine represented by a block. Each engine requires a specific amount of time to be built and can only be built by one engineer.
// Your task is to determine the minimum time needed to build all the engines using the available engineers. The engineers can either work on building an engine or split into two engineers, with each engineer sharing the workload equally. Both decisions incur a time cost.
// The time cost of splitting one engineer into two engineers is given as an integer split. Note that if two engineers split at the same time, they split in parallel so the cost would be split.
// Your goal is to calculate the minimum time needed to build all the engines, considering the time cost of splitting engineers.
// Input: engines= [3, 4, 5, 2] Split cost (k)=2
// Output: 4
// Example:
// Imagine you have the list of engines: [3, 4, 5, 2] and the split cost is 2. Initially, there is only one engineer available.
// The optimal strategy is as follows:
// 1. The engineer splits into two engineers, increasing the total count to two. (Time: 2)
// 2. Each engineer takes one engine, with one engineer building the engine that requires 3 units of time and the other engineer building the engine that requires 4 units of time.
// 3. Once the engineer finishes building the engine that requires 3 units of time, the engineer splits into two, increasing the total count to three. (Time: 4)
// 4. Each engineer takes one engine, with two engineers building the engines that require 2 and 5 units of time, respectively.
// Therefore, the minimum time needed to build all the engines using optimal decisions on splitting engineers and assigning them to engines is 4 units.
// Note: The splitting process occurs in parallel, and the goal is to minimize the total time required to build all the engines using the available engineers while considering the time cost of splitting.

public class Question1b {
    public static void main(String[] args) {
        int[] engines = {1, 2, 3}; // Array of engine repair times
        int k = 1; // Maximum number of engineers that can work together
        System.out.println(minimumTime(engines, k)); // Print the result of minimumTime method
    }

    // Method to calculate minimum time required to repair all engines
    public static int minimumTime(int[] engines, int k) {
        int maxTime = 0; // Variable to store the maximum time
        int engineers = 1; // Variable to represent the number of engineers initially set to 1

        // Loop through the engine repair times starting from the last engine
        for (int i = engines.length - 1; i >= 0; i--) {
            int time = engines[i]; // Get the repair time for the current engine
            while (engineers < time) { // Continue until the number of engineers is less than the repair time
                int splitTime = Math.min(time - engineers, k); // Calculate the split time based on available engineers and maximum number of engineers allowed
                engineers += splitTime; // Increment the number of engineers by the split time
                maxTime = Math.max(maxTime, time); // Update the maximum time if necessary
            }
            engineers--; // Decrease the number of engineers after finishing the repair of an engine
        }

        return maxTime + 1; // Return the maximum time required to repair all engines
    }
}
