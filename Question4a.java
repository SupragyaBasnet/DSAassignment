// Question 4
// a)
// You are given a 2D grid representing a maze in a virtual game world. The grid is of size m x n and consists of different types of cells:
// 'P' represents an empty path where you can move freely. 'W' represents a wall that you cannot pass through. 'S' represents the starting point. Lowercase letters represent hidden keys. Uppercase letters represent locked doors.
// You start at the starting point 'S' and can move in any of the four cardinal directions (up, down, left, right) to adjacent cells. However, you cannot walk through walls ('W').
// As you explore the maze, you may come across hidden keys represented by lowercase letters. To unlock a door represented by an uppercase letter, you need to collect the corresponding key first. Once you have a key, you can pass through the corresponding locked door.
// For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the maze. This means that there is exactly one key for each door, and one door for each key. The letters used to represent the keys and doors follow the English alphabet order.
// Your task is to find the minimum number of moves required to collect all the keys. If it is impossible to collect all the keys and reach the exit, return -1.
// Example:
// Input: grid = [ ["S","P","q","P","P"], ["W","W","W","P","W"], ["r","P","Q","P","R"]] 
 
// Output: 8
// The goal is to Collect all key 


// //4. a
// import java.util.HashSet;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.Set;

// public class Question4a {
//     static class State {
//         int x, y, keys, steps;

//         State(int x, int y, int keys, int steps) {
//             this.x = x;
//             this.y = y;
//             this.keys = keys;
//             this.steps = steps;
//         }
//     }

//     public static int collectAllKeys(String[] grid) {
//         int m = grid.length, n = grid[0].length();
//         Queue<State> queue = new LinkedList<>();
//         Set<String> visited = new HashSet<>();
//         int allKeys = 0;

//         // Find starting point and total number of keys
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 char cell = grid[i].charAt(j);
//                 if (cell == 'S') {
//                     queue.offer(new State(i, j, 0, 0));
//                     visited.add(i + "," + j + ",0");
//                 } else if (cell >= 'a' && cell <= 'f') {
//                     allKeys |= (1 << (cell - 'a'));
//                 }
//             }
//         }

//         // Directions: up, down, left, right
//         int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

//         while (!queue.isEmpty()) {
//             State current = queue.poll();

//             // Check if we've collected all keys
//             if (current.keys == allKeys)
//                 return current.steps;

//             for (int[] dir : directions) {
//                 int newX = current.x + dir[0], newY = current.y + dir[1];
//                 int newKeys = current.keys;
//                 if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
//                     char cell = grid[newX].charAt(newY);
//                     // Check if it's a wall
//                     if (cell == 'W')
//                         continue;
//                     // Check if it's a door and we have the key
//                     if (cell >= 'A' && cell <= 'F' && (newKeys & (1 << (cell - 'A'))) == 0)
//                         continue;
//                     // Collect key if found
//                     if (cell >= 'a' && cell <= 'f')
//                         newKeys |= (1 << (cell - 'a'));

//                     String newState = newX + "," + newY + "," + newKeys;
//                     if (!visited.contains(newState)) {
//                         visited.add(newState);
//                         queue.offer(new State(newX, newY, newKeys, current.steps + 1));
//                     }
//                 }
//             }
//         }

//         // If we exit the loop, no solution was found
//         return -1;
//     }

//     public static void main(String[] args) {
//         String[] grid1 = { "SPaPP", "WWWPW", "bPAPB" };
//         System.out.println("Minimum steps to collect all keys: " + collectAllKeys(grid1));
//     }
// }

import java.util.*; // Importing necessary Java utilities

// Class representing a point in the grid
class Point {
    int x, y, keys; // Point's coordinates (x, y) and keys collected

    // Constructor to initialize a Point object
    public Point(int x, int y, int keys) {
        this.x = x;
        this.y = y;
        this.keys = keys;
    }
}

// Main class containing the method to find minimum steps to collect all keys
public class Question4a {
    
    // Method to calculate the minimum steps required to collect all keys
    public int minStepsToCollectAllKeys(char[][] grid) {
        int m = grid.length; // Number of rows in the grid
        int n = grid[0].length; // Number of columns in the grid
        int allKeys = 0; // Bitmask to track collected keys
        int startX = -1, startY = -1; // Starting position
        
        // Loop through the grid to find starting position and collect all keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i][j]; // Current cell in the grid
                
                // If 'S' is found, set starting position
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } 
                // If a key is found, update the bitmask
                else if (cell >= 'a' && cell <= 'f') {
                    allKeys |= (1 << (cell - 'a')); // Set the bit for the key
                }
            }
        }
        
        // Queue to perform breadth-first search
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0)); // Add starting point to the queue
        boolean[][][] visited = new boolean[m][n][64]; // 3D array to track visited cells with keys

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions: Up, Down, Left, Right
        int steps = 0; // Variable to count steps
        
        // Breadth-first search loop
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point curr = queue.poll(); // Get the current point from the queue
                int x = curr.x, y = curr.y, keys = curr.keys; // Current point's coordinates and keys
                
                // If all keys are collected, return the steps
                if (keys == allKeys) {
                    return steps;
                }
                
                // Explore adjacent cells in all four directions
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // Check if the new position is within bounds and not a wall
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] != '#') {
                        char nextCell = grid[newX][newY]; // Next cell's value
                        int newKeys = keys; // Initialize newKeys with the current keys

                        // If the next cell is a locked door, check if the key is available
                        if (nextCell >= 'A' && nextCell <= 'F') {
                            int door = nextCell - 'A'; // Get the index of the door
                            if ((keys & (1 << door)) == 0) {
                                continue; // Skip if the key is missing
                            }
                        } 
                        // If the next cell has a key, collect it
                        else if (nextCell >= 'a' && nextCell <= 'f') {
                            newKeys |= (1 << (nextCell - 'a')); // Collect the key
                        }

                        // If the new position with keys combination is not visited
                        if (!visited[newX][newY][newKeys]) {
                            visited[newX][newY][newKeys] = true; // Mark as visited
                            queue.offer(new Point(newX, newY, newKeys)); // Add to queue for further exploration
                        }
                    }
                }
            }
            steps++; // Increment steps after exploring all points at the current level
        }

        return -1; // Cannot collect all keys
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        // Sample grid representing the maze
        char[][] grid = {
            {'S', 'P', 'q', 'P', 'P'},
            {'W', 'W', 'W', 'P', 'W'},
            {'r', 'P', 'Q', 'P', 'R'}
        };

        // Create an instance of the Question4a class
        Question4a solver = new Question4a();
        // Find and print the minimum steps to collect all keys
        System.out.println("Minimum steps to collect all keys: " + solver.minStepsToCollectAllKeys(grid));
    }
}
