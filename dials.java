import java.util.*;

class Graph {
    private int V;
    private List<List<Node>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Node node = new Node(destination, weight);
        adj.get(source).add(node);
    }

    public void dijkstra(int startVertex) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startVertex] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.weight));
        pq.add(new Node(startVertex, 0));

        while (!pq.isEmpty()) {
            int currentVertex = pq.poll().vertex;
            for (Node neighbor : adj.get(currentVertex)) {
                int newDist = distance[currentVertex] + neighbor.weight;
                if (newDist < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        // Print the distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + distance[i]);
        }
    }

    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices in the graph: ");
        int V = scanner.nextInt();

        Graph graph = new Graph(V);

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges in the format (source destination weight):");
        for (int i = 0; i < E; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        scanner.close();

        graph.dijkstra(source);
    }
}
