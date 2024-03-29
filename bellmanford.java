import java.util.Scanner;

class GFG {
    static void BellmanFord(int graph[][], int V, int E, int src) {
        int[] dis = new int[V];
        for (int i = 0; i < V; i++)
            dis[i] = Integer.MAX_VALUE;

        dis[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < E; j++) {
                if (dis[graph[j][0]] != Integer.MAX_VALUE && dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]])
                    dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
            }
        }

        for (int i = 0; i < E; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            int weight = graph[i][2];
            if (dis[x] != Integer.MAX_VALUE && dis[x] + weight < dis[y])
                System.out.println("Graph contains negative weight cycle");
        }

        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t\t" + dis[i]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices in graph: ");
        int V = scanner.nextInt();
        
        System.out.print("Enter the number of edges in graph: ");
        int E = scanner.nextInt();
        
        int[][] graph = new int[E][3];
        System.out.println("Enter the edges in the format (u v w), where the edge is from vertex u to v and the weight is w:");
        for (int i = 0; i < E; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            graph[i][0] = scanner.nextInt();
            graph[i][1] = scanner.nextInt();
            graph[i][2] = scanner.nextInt();
        }
        
        System.out.print("Enter the source vertex: ");
        int src = scanner.nextInt();
        
        scanner.close();

        BellmanFord(graph, V, E, src);
    }
}
