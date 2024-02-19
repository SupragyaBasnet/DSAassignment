// Question 1
// a)
// You are a planner working on organizing a series of events in a row of n venues. Each venue can be decorated with one of the k available themes. However, adjacent venues should not have the same theme. The cost of decorating each venue with a certain theme varies.
// The costs of decorating each venue with a specific theme are represented by an n x k cost matrix. For example, costs [0][0] represents the cost of decorating venue 0 with theme 0, and costs[1][2] represents the cost of decorating venue 1 with theme 2. Your task is to find the minimum cost to decorate all the venues while adhering to the adjacency constraint.
// For example, given the input costs = [[1, 5, 3], [2, 9, 4]], the minimum cost to decorate all the venues is 5. One possible arrangement is decorating venue 0 with theme 0 and venue 1 with theme 2, resulting in a minimum cost of 1 + 4 = 5. Alternatively, decorating venue 0 with theme 2 and venue 1 with theme 0 also yields a minimum cost of 3 + 2 = 5.
// Write a function that takes the cost matrix as input and returns the minimum cost to decorate all the venues while satisfying the adjacency constraint.
// Please note that the costs are positive integers.
// Example: Input: [[1, 3, 2], [4, 6, 8], [3, 1, 5]] Output: 7
// Explanation: Decorate venue 0 with theme 0, venue 1 with theme 1, and venue 2 with theme 0. Minimum cost: 1 + 6 + 1 = 7.
// [5 Marks]


import java.util.Arrays;

public class Question1a{
    public static int minCostToDecorateVenues(int[][] costs) {
        int n = costs.length; // Number of venues
        int k = costs[0].length; // Number of themes

        // Initialize dp table to store minimum costs
        int[][] dp = new int[n][k];

        // Initialize the first row with the costs of decorating the first venue
        dp[0] = Arrays.copyOf(costs[0], k);

        // Iterate over each venue starting from the second one
        for (int i = 1; i < n; i++) {
            // Iterate over each theme for the current venue
            for (int j = 0; j < k; j++) {
                // Initialize the minimum cost for the current venue and theme to infinity
                int minCost = Integer.MAX_VALUE;
                // Iterate over each theme for the previous venue
                for (int prevTheme = 0; prevTheme < k; prevTheme++) {
                    // Check if the previous venue was decorated with a different theme
                    if (prevTheme != j) {
                        // Update the minimum cost for the current venue and theme
                        minCost = Math.min(minCost, dp[i - 1][prevTheme]);
                    }
                }
                // Update the dp table with the minimum cost for the current venue and theme
                dp[i][j] = costs[i][j] + minCost;
            }
        }

        // Find the minimum cost of decorating all venues
        int minCost = Integer.MAX_VALUE;
        for (int cost : dp[n - 1]) {
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[][] exampleCosts = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        System.out.println(minCostToDecorateVenues(exampleCosts)); // Output: 7
    }
}






