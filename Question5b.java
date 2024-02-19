// b)	Assume you were hired to create an application for an ISP, and there are n network devices, such as routers, that are linked together to provide internet access to users. You are given a 2D array that represents network connections between these network devices. write an algorithm to return impacted network devices, If there is a power outage on a certain device, these impacted device list assist you notify linked consumers that there is a power outage and it will take some time to rectify an issue.
 

 
// Input: edges= {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}}
// Target Device (On which power Failure occurred): 4
// Output (Impacted Device List) = {5,7}

import java.util.*;

class Question5b {
    int[] disc, low; // Declaration of arrays to store discovery and low values for Tarjan's algorithm
    int time = 1; // Initialize time for DFS traversal
    List<List<Integer>> ans = new ArrayList<>(); // Initialize a list to store the bridges found in the graph
    Map<Integer, List<Integer>> edgeMap = new HashMap<>(); // Initialize a map to represent the graph connections

    // Method to find impacted devices given the target device
    public List<Integer> findImpactedDevices(int n, List<List<Integer>> connections, int targetDevice) {
        disc = new int[n]; // Initialize discovery array
        low = new int[n]; // Initialize low array
        
        // Initialize edgeMap with empty lists for each node
        for (int i = 0; i < n; i++)
            edgeMap.put(i, new ArrayList<Integer>());
        
        // Populate edgeMap based on provided connections
        for (List<Integer> conn : connections) {
            edgeMap.get(conn.get(0)).add(conn.get(1));
            edgeMap.get(conn.get(1)).add(conn.get(0));
        }
        
        // Perform Depth-First Search (DFS) to find bridges in the graph
        dfs(targetDevice, -1);

        // Check if the target device is a source node in any connection
        boolean isSourceNode = false;
        for (List<Integer> conn : connections) {
            if (conn.get(0) == targetDevice) {
                isSourceNode = true;
                break;
            }
        }

        // If the target device is not a source node, no devices are impacted
        if (!isSourceNode) {
            return new ArrayList<>();
        }

        // Identify impacted devices based on the found bridges
        Set<Integer> impactedDevicesSet = new HashSet<>();
        for (List<Integer> connection : ans) {
            int u = connection.get(0);
            int v = connection.get(1);

            if (u == targetDevice) {
                impactedDevicesSet.add(v);
            } else if (v == targetDevice) {
                impactedDevicesSet.add(u);
            }
        }

        // Check for additional devices impacted indirectly through connections
        Set<Integer> additionalAffectedDevices = new HashSet<>();
        for (int affectedDevice : impactedDevicesSet) {
            for (int neighbor : edgeMap.get(affectedDevice)) {
                if (!impactedDevicesSet.contains(neighbor)) {
                    additionalAffectedDevices.add(neighbor);
                }
            }
        }

        // Combine all impacted devices into a single set and remove the target device
        impactedDevicesSet.addAll(additionalAffectedDevices);
        impactedDevicesSet.remove(targetDevice);

        // Return impacted devices as a list
        return new ArrayList<>(impactedDevicesSet);
    }

    // Depth-First Search (DFS) function to find bridges
    public void dfs(int curr, int prev) {
        disc[curr] = low[curr] = time++;
        for (int next : edgeMap.get(curr)) {
            if (next == prev)
                continue;
            if (disc[next] == 0) {
                dfs(next, curr);
                low[curr] = Math.min(low[curr], low[next]);
                if (low[next] > disc[curr])
                    ans.add(Arrays.asList(curr, next));
            } else {
                low[curr] = Math.min(low[curr], disc[next]);
            }
        }
    }

    // Main method to test the implementation
    public static void main(String[] args) {
      Question5b question5b = new Question5b();

        int n = 8; // Number of network devices
        List<List<Integer>> connections = new ArrayList<>(); // List to store connections between devices
        connections.add(Arrays.asList(0, 1)); // Sample connections
        connections.add(Arrays.asList(0, 2));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(1, 6));
        connections.add(Arrays.asList(2, 4));
        connections.add(Arrays.asList(4, 6));
        connections.add(Arrays.asList(4, 5));
        connections.add(Arrays.asList(5, 7));

        int targetDevice = 4; // Device on which power failure occurred

        // Find impacted devices given the target device
        List<Integer> impactedDevices = question5b.findImpactedDevices(n, connections, targetDevice);

        // Print the list of impacted devices (excluding the target device)
        System.out.println("Impacted Devices (other than target device " + targetDevice + "): " + impactedDevices);
    }
}
