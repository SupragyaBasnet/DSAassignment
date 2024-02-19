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

import java.util.*;

class Maze {
    static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Define directions: Up, Down, Left, Right

    // Method to find the shortest path to collect all keys in the maze
    public static int shortestPathAllKeys(String[] grid) {
        int m = grid.length; // Number of rows in the grid
        int n = grid[0].length(); // Number of columns in the grid
        Queue<Node> queue = new LinkedList<>(); // Queue for BFS traversal
        Set<String> visited = new HashSet<>(); // Set to store visited states
        int startX = 0, startY = 0, totalKeys = 0; // Initialize starting position and total number of keys

        // Scan the grid to find the starting point and total number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j); // Get the character at position (i, j)
                if (cell == 'S') { // If starting point is found
                    startX = i; // Set starting x-coordinate
                    startY = j; // Set starting y-coordinate
                } else if (cell >= 'a' && cell <= 'f') { // If the cell contains a key
                    totalKeys |= (1 << (cell - 'a')); // Mark the key as needed to be collected
                }
            }
        }

        // Initialize BFS traversal with starting state
        queue.offer(new Node(startX, startY, 0, 0)); // Add starting point to the queue
        visited.add(startX + "," + startY + ",0"); // Mark starting state as visited

        // BFS traversal of the maze
        while (!queue.isEmpty()) {
            Node current = queue.poll(); // Dequeue the current state
            if (current.keys == totalKeys) return current.steps; // If all keys are collected, return the number of steps

            // Explore all possible directions from the current position
            for (int[] dir : directions) {
                int newX = current.x + dir[0], newY = current.y + dir[1]; // Calculate new position
                int newKeys = current.keys; // Initialize new set of keys

                // Check if the new position is within the grid boundaries
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    char nextCell = grid[newX].charAt(newY); // Get the character at the new position

                    // Check if the next cell is a wall or a locked door without a key
                    if (nextCell == 'W') continue; // If it's a wall, skip to the next direction
                    if (nextCell >= 'A' && nextCell <= 'F' && (newKeys & (1 << (nextCell - 'A'))) == 0) continue; // If it's a locked door without a key, skip to the next direction

                    // Check if the next cell contains a key
                    if (nextCell >= 'a' && nextCell <= 'f') newKeys |= (1 << (nextCell - 'a')); // Collect the key

                    String newState = newX + "," + newY + "," + newKeys; // Create a string to represent the new state
                    if (!visited.contains(newState)) { // If the new state is not visited
                        visited.add(newState); // Mark the new state as visited
                        queue.offer(new Node(newX, newY, current.steps + 1, newKeys)); // Enqueue the new state with updated information
                    }
                }
            }
        }

        return -1; // If it's not possible to collect all keys
    }

    // Define a class to represent a state in the maze
    static class Node {
        int x, y, steps, keys; // Position (x, y), number of steps taken, and keys collected

        Node(int x, int y, int steps, int keys) { // Constructor to initialize a state
            this.x = x; // Initialize x-coordinate
            this.y = y; // Initialize y-coordinate
            this.steps = steps; // Initialize number of steps
            this.keys = keys; // Initialize keys collected
        }
    }

    public static void main(String[] args) {
        String[] grid = {"SPaPP", "WWWPW", "bPAPB"}; // Define the maze grid
        System.out.println("Minimum steps: " + shortestPathAllKeys(grid)); // Print the minimum steps to collect all keys
    }
}
