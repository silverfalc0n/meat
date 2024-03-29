import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void createEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
    }

    public void topologicalSort() {
        int[] totalIndegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j : adjacencyList.get(i)) {
                totalIndegree[j]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (totalIndegree[i] == 0) {
                queue.add(i);
            }
        }

        int visitedNodes = 0;
        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);

            for (int i : adjacencyList.get(u)) {
                totalIndegree[i]--;

                if (totalIndegree[i] == 0) {
                    queue.add(i);
                }
            }
            visitedNodes++;
        }

        if (visitedNodes != vertices) {
            System.out.println("There's a cycle present in the Graph.\nGiven graph is not DAG");
        } else {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.println("Enter the edges (source destination):");
        while (true) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            if (source == -1 || destination == -1) {
                break;
            }
            graph.createEdge(source, destination);
        }

        scanner.close();

        graph.topologicalSort();
    }
}
