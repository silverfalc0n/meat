import java.util.*;

public class WinnerTree {
    private int[] tree;
    private int[] players;

    public WinnerTree(int[] players) {
        this.players = players;
        int n = players.length;
        int treeSize = calculateTreeSize(n);
        tree = new int[2 * treeSize - 1];
        Arrays.fill(tree, -1);
        for (int i = 0; i < n; i++) {
            tree[treeSize - 1 + i] = i;
        }
        buildWinnerTree(0, 0, treeSize - 1);
    }

    private int calculateTreeSize(int n) {
        int treeSize = 1;
        while (treeSize < n) {
            treeSize *= 2;
        }
        return treeSize;
    }

    private void buildWinnerTree(int node, int left, int right) {
        if (left == right) return;
        int mid = (left + right) / 2;
        buildWinnerTree(2 * node + 1, left, mid);
        buildWinnerTree(2 * node + 2, mid + 1, right);
        tree[node] = players[tree[2 * node + 1]] <= players[tree[2 * node + 2]] ? tree[2 * node + 1] : tree[2 * node + 2];
    }

    public int getWinnerIndex() {
        return tree[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int n = scanner.nextInt();
        int[] players = new int[n];
        System.out.println("Enter the scores of players:");
        for (int i = 0; i < n; i++) {
            players[i] = scanner.nextInt();
        }
        scanner.close();

        WinnerTree winnerTree = new WinnerTree(players);
        int winnerIndex = winnerTree.getWinnerIndex();
        int winningScore = players[winnerIndex];
        System.out.println("The player with the highest score is at index: " + winnerIndex);
        System.out.println("The score of the winning player is: " + winningScore);
    }
}
