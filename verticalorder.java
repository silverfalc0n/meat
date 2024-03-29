import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to perform Vertical Order Traversal of a binary tree
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> verticalMap = new TreeMap<>();
        Queue<Map.Entry<TreeNode, Integer>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new AbstractMap.SimpleEntry<>(root, 0));
        while (!nodeQueue.isEmpty()) {
            Map.Entry<TreeNode, Integer> entry = nodeQueue.poll();
            TreeNode node = entry.getKey();
            int col = entry.getValue();
            verticalMap.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQueue.offer(new AbstractMap.SimpleEntry<>(node.left, col - 1));
            }
            if (node.right != null) {
                nodeQueue.offer(new AbstractMap.SimpleEntry<>(node.right, col + 1));
            }
        }
        for (List<Integer> values : verticalMap.values()) {
            result.add(values);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input binary tree nodes
        System.out.println("Enter the nodes of the binary tree (Enter -1 to denote null nodes):");
        TreeNode root = buildTree(scanner);

        // Perform vertical order traversal
        List<List<Integer>> verticalOrderResult = verticalOrderTraversal(root);

        // Print the Vertical Order Traversal result
        System.out.println("Vertical Order Traversal:");
        for (List<Integer> column : verticalOrderResult) {
            for (int val : column) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        scanner.close();
    }

    // Function to build a binary tree based on user input
    public static TreeNode buildTree(Scanner scanner) {
        System.out.print("Enter the value of the root node: ");
        int rootValue = scanner.nextInt();
        TreeNode root = new TreeNode(rootValue);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print("Enter the left child value of node " + current.val + " (-1 if none): ");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                current.left = new TreeNode(leftValue);
                queue.add(current.left);
            }
            System.out.print("Enter the right child value of node " + current.val + " (-1 if none): ");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                current.right = new TreeNode(rightValue);
                queue.add(current.right);
            }
        }
        return root;
    }
}
