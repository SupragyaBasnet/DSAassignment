// Task 5
// a) Implement ant colony algorithm solving travelling a salesman problem


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Question5a{

    // Define class variables
    private final int numOfCities; // Number of cities
    private final double[][] distances; // Distance matrix between cities
    private final int numOfAnts; // Number of ants
    private final double[][] pheromones; // Pheromone matrix
    private final double alpha; // Pheromone influence factor
    private final double beta; // Distance influence factor
    private final double evaporationRate; // Rate at which pheromones evaporate
    private final Random random; // Random number generator

    // Constructor for the AntColonyOptimization class
    public Question5a(int numOfCities, double[][] distances, int numOfAnts,
                                 double alpha, double beta, double evaporationRate) {
        // Initialize class variables
        this.numOfCities = numOfCities;
        this.distances = distances;
        this.numOfAnts = numOfAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.pheromones = new double[numOfCities][numOfCities]; // Initialize pheromone matrix
        this.random = new Random(); // Initialize random number generator
    }

    // Method to solve the TSP using ACO
    public void solveTSP(int maxIterations) {
        int[] bestTour = null; // Initialize best tour
        double shortestDistance = Double.POSITIVE_INFINITY; // Initialize shortest distance to infinity

        // Initialize pheromones
        initializePheromones();

        // Main loop for ACO iterations
        for (int iter = 0; iter < maxIterations; iter++) {
            int[][] antTours = new int[numOfAnts][numOfCities]; // Initialize array to store ant tours
            double[] tourLengths = new double[numOfAnts]; // Initialize array to store tour lengths

            // Ants construct solutions
            for (int ant = 0; ant < numOfAnts; ant++) {
                int startCity = random.nextInt(numOfCities); // Select random starting city for the ant
                antTours[ant][0] = startCity; // Add start city to the tour
                List<Integer> unvisitedCities = new ArrayList<>(); // Create list of unvisited cities
                for (int i = 0; i < numOfCities; i++) {
                    if (i != startCity) {
                        unvisitedCities.add(i); // Add unvisited cities to the list
                    }
                }

                // Construct the rest of the tour
                for (int i = 1; i < numOfCities; i++) {
                    int nextCity = selectNextCity(antTours[ant], unvisitedCities); // Select next city for the ant
                    antTours[ant][i] = nextCity; // Add next city to the tour
                    unvisitedCities.remove(Integer.valueOf(nextCity)); // Remove next city from the list of unvisited cities
                }
                tourLengths[ant] = calculateTourLength(antTours[ant]); // Calculate tour length
            }

            // Update pheromones
            updatePheromones(antTours, tourLengths);

            // Update best solution
            for (int ant = 0; ant < numOfAnts; ant++) {
                if (tourLengths[ant] < shortestDistance) {
                    shortestDistance = tourLengths[ant]; // Update shortest distance
                    bestTour = Arrays.copyOf(antTours[ant], numOfCities); // Update best tour
                }
            }

            // Evaporate pheromones
            evaporatePheromones();
        }

        // Output best solution
        System.out.println("Best tour length: " + shortestDistance);
        System.out.println("Best tour: " + Arrays.toString(bestTour));
    }

    // Method to initialize pheromones
    private void initializePheromones() {
        double initialPheromone = 1.0 / numOfCities; // Initial pheromone level
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                pheromones[i][j] = initialPheromone; // Set initial pheromone level for each edge
            }
        }
    }

    // Method to select next city for an ant
    private int selectNextCity(int[] tour, List<Integer> unvisitedCities) {
        double[] probabilities = new double[numOfCities]; // Array to store selection probabilities
        double total = 0; // Total probability
        int lastVisitedCity = tour[tour.length - 1]; // Last visited city

        // Calculate probabilities for unvisited cities
        for (int city : unvisitedCities) {
            probabilities[city] = Math.pow(pheromones[lastVisitedCity][city], alpha) *
                                  Math.pow(1.0 / distances[lastVisitedCity][city], beta); // Calculate probability based on pheromone level and distance
            total += probabilities[city]; // Update total probability
        }

        // Roulette wheel selection
        double rand = random.nextDouble() * total; // Generate random number
        double sum = 0; // Initialize sum of probabilities
        for (int i = 0; i < numOfCities; i++) {
            sum += probabilities[i]; // Increment sum
            if (sum >= rand) {
                return i; // Return index of selected city
            }
        }

        // Shouldn't reach here
        return -1;
    }

    // Method to calculate tour length
    private double calculateTourLength(int[] tour) {
        double length = 0; // Initialize tour length
        for (int i = 0; i < numOfCities - 1; i++) {
            length += distances[tour[i]][tour[i + 1]]; // Add distance between consecutive cities
        }
        length += distances[tour[numOfCities - 1]][tour[0]]; // Add distance from last city back to start
        return length; // Return total tour length
    }

    // Method to update pheromones
    private void updatePheromones(int[][] antTours, double[] tourLengths) {
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                pheromones[i][j] *= (1 - evaporationRate); // Evaporate pheromones
            }
        }

        for (int ant = 0; ant < numOfAnts; ant++) {
            for (int i = 0; i < numOfCities - 1; i++) {
                int city1 = antTours[ant][i];
                int city2 = antTours[ant][i + 1];
                pheromones[city1][city2] += 1.0 / tourLengths[ant]; // Update pheromones based on tour length
                pheromones[city2][city1] += 1.0 / tourLengths[ant]; // Update pheromones for reverse direction
            }
            int lastCity = antTours[ant][numOfCities - 1];
            int firstCity = antTours[ant][0];
            pheromones[lastCity][firstCity] += 1.0 / tourLengths[ant]; // Update pheromones for returning to start city
            pheromones[firstCity][lastCity] += 1.0 / tourLengths[ant]; // Update pheromones for reverse direction
        }
    }

    // Method to evaporate pheromones
    private void evaporatePheromones() {
        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                pheromones[i][j] *= (1 - evaporationRate); // Evaporate pheromones
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example parameters
        int numOfCities = 5;
        double[][] distances = {
            {0, 10, 15, 20, 25},
            {10, 0, 35, 25, 30},
            {15, 35, 0, 30, 10},
            {20, 25, 30, 0, 45},
            {25, 30, 10, 45, 0}
        };
        int numOfAnts = 10;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.1;
        int maxIterations = 1000;

        // Create instance of AntColonyOptimization and solve TSP
        Question5a antcolonyoptimization = new Question5a(numOfCities, distances, numOfAnts,alpha, beta, evaporationRate);
        antcolonyoptimization.solveTSP(maxIterations);
    }
}






