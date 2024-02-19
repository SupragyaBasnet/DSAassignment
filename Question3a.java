// Question 3
// a)	You are developing a student score tracking system that keeps track of scores from different assignments. The ScoreTracker class will be used to calculate the median score from the stream of assignment scores. The class should have the following methods:
// ●	ScoreTracker() initializes a new ScoreTracker object.
// ●	void addScore(double score) adds a new assignment score to the data stream.
// ●	double getMedianScore() returns the median of all the assignment scores in the data stream. If the number of scores is even, the median should be the average of the two middle scores.
// Input:
// ScoreTracker scoreTracker = new ScoreTracker();
// scoreTracker.addScore(85.5);    // Stream: [85.5]
// scoreTracker.addScore(92.3);    // Stream: [85.5, 92.3]
// scoreTracker.addScore(77.8);    // Stream: [85.5, 92.3, 77.8]
// scoreTracker.addScore(90.1);    // Stream: [85.5, 92.3, 77.8, 90.1]
// double median1 = scoreTracker.getMedianScore(); // Output: 87.8  (average of 90.1 and 85.5)

// scoreTracker.addScore(81.2);    // Stream: [85.5, 92.3, 77.8, 90.1, 81.2]
// scoreTracker.addScore(88.7);    // Stream: [85.5, 92.3, 77.8, 90.1, 81.2, 88.7]
// double median2 = scoreTracker.getMedianScore(); // Output: 87.1 (average of 88.7 and 85.5)

import java.util.Collections;
import java.util.PriorityQueue;

// Class definition for a score tracker that calculates the median of scores dynamically.
public class Question3a {
    // Two priority queues are used to maintain the lower and higher halves of the scores.
    private PriorityQueue<Double> maxHeap; // To store the lower half of the scores
    private PriorityQueue<Double> minHeap; // To store the higher half of the scores

    // Constructor to initialize the score tracker.
    public Question3a() {
        // Initialize the maxHeap as a priority queue in descending order (for max heap property).
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Initialize the minHeap as a default priority queue (for min heap property).
        minHeap = new PriorityQueue<>();
    }

    // Method to add a new score to the tracker.
    public void addScore(double score) {
        // Determine which heap to add the score to based on its value compared to the current median.
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            maxHeap.offer(score); // Add to the maxHeap if the score is less than or equal to the current median.
        } else {
            minHeap.offer(score); // Add to the minHeap if the score is greater than the current median.
        }

        // Rebalance the heaps to maintain the size property.
        balanceHeaps();
    }

    // Method to ensure that the difference in size between the two heaps is at most 1.
    private void balanceHeaps() {
        // Move elements from maxHeap to minHeap if maxHeap has more elements.
        while (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
        // Move elements from minHeap to maxHeap if minHeap has more elements.
        while (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // Method to calculate and return the current median score.
    public double getMedianScore() {
        int totalSize = maxHeap.size() + minHeap.size(); // Total number of scores

        // If the total number of scores is even, return the average of the two middle scores.
        if (totalSize % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else { // If the total number of scores is odd, return the middle score from the larger heap.
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

    // Main method to test the functionality of the score tracker.
    public static void main(String[] args) {
        // Create an instance of the score tracker.
        Question3a scoreTracker = new Question3a();

        // Add some scores to the tracker.
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);

        // Calculate and print the median after adding the first set of scores.
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1); // Output: 88.9

        // Add more scores to the tracker.
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);

        // Calculate and print the median after adding the second set of scores.
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2); // Output: 86.95
    }
}
