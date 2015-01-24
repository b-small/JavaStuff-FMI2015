package graph;

import java.util.*;

/**
 *
 * @author bas
 */
public class Graph {

    private final ArrayList<HashMap<Integer, Double>> adjacencyList;

    public Graph() {
        this.adjacencyList = new ArrayList<>();
    }

    public void addEdge(int from, int to, double weight) {
        if (adjacencyList.size() <= from) {
            adjacencyList.add(from, new HashMap<>());
        }
        if (adjacencyList.size() <= to) {
            adjacencyList.add(to, new HashMap<>());
        }
        adjacencyList.get(from).put(to, weight);
    }

    public void addUndirectedEdge(int from, int to, double weight) {
        addEdge(from, to, weight);
        adjacencyList.get(to).put(from, weight);
    }

    /**
     * @param from
     * @param to
     * @return the weight of the edge from i to j.
     */
    public double getEdge(int from, int to) {
        return adjacencyList.get(from).getOrDefault(to, Double.NaN);
    }

    /**
     * @param from
     * @param to
     * @return true if there is an edge from the two vertices.
     */
    public boolean hasEdge(int from, int to) {
        return adjacencyList.get(from).containsKey(to);
    }

    /**
     * @return the number of vertices in this Graph.
     */
    public int size() {
        return adjacencyList.size();
    }

    /**
     * @param distances
     * @param visited
     * @return the index of the smallest element of distances, ignoring those in
     * visited.
     */
    protected int cheapest(double[] distances, boolean[] visited) {
        int best = -1;
        for (int i = 0; i < size(); i++) {
            if (!visited[i]
                    && ((best < 0) || (distances[i] < distances[best]))) {
                best = i;
            }
        }
        return best;
    }

    /**
     * @param from
     * @param to
     * @return the cost to go directly from one vertex to another
     */
    public double getCost(int from, int to) {
        if (from == to) {
            return 0.0;
        }
        return adjacencyList.get(from).getOrDefault(to, Double.POSITIVE_INFINITY);
    }

    /**
     * @param source
     * @return an array of the distances from source to each other vertex.
     */
    public double[] distancesFrom(int source) {
        double[] result = new double[size()];
        java.util.Arrays.fill(result, Double.POSITIVE_INFINITY);
        result[source] = 0;
        boolean[] visited = new boolean[size()];
        for (int i = 0; i < size(); i++) {
            int vertex = cheapest(result, visited);
            visited[vertex] = true;
            for (int j = 0; j < size(); j++) {
                result[j] = Math.min(result[j],
                        result[vertex] + getCost(vertex, j));
            }
        }
        return result;
    }

    /**
     * @param sourceVertex
     * @return a list of the neighbors of vertex i.
     */
    public ArrayList<Integer> neighbors(int sourceVertex) {
        ArrayList<Integer> result = new ArrayList<>(adjacencyList.get(sourceVertex).keySet());
        return result;
    }

    public ArrayList<Integer> breadthFirstTraverse(int source) {
        ArrayList<Integer> result = new ArrayList<>(size());

        boolean[] visited = new boolean[size()];

        Queue<Integer> q = new LinkedList<>();
        visited[source] = true;
        q.offer(source);

        while (!(q.isEmpty())) {
            int vertex = q.poll();
            result.add(vertex);
            ArrayList<Integer> arrNodes = neighbors(vertex);
            arrNodes.stream().map((arrNode) -> arrNode).filter((i)
                    -> (!visited[i])).map((i) -> {
                        visited[i] = true;
                        return i;
                    }).forEach((i) -> {
                        q.offer(i);
                    });
        }
        return result;
    }

    /**
     * @param source
     * @return a list of the vertices reachable from source, in depth- first
     * order
     */
    public ArrayList<Integer> depthFirstTraverse(int source) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[size()];

        depthFirstTraverse(source, result, visited);
        return result;
    }

    /**
     * Visit the vertices reachable from vertex, in depth-first order. Add
     * vertices to result as they are visited.
     *
     * @param vertex
     * @param result
     * @param visited
     */
    protected void depthFirstTraverse(int vertex,
            ArrayList<Integer> result,
            boolean[] visited) {
        visited[vertex] = true;
        result.add(vertex);
        ArrayList<Integer> arrNodes = neighbors(vertex);

        arrNodes.stream().map((arrNode) -> arrNode).filter((i)
                -> (!visited[i])).forEach((i) -> {
                    depthFirstTraverse(i, result, visited);
                });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < adjacencyList.size(); i++) {
            sb.append(String.format("Node %d ", i));
            sb.append(adjacencyList.get(i).toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
