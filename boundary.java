import java.util.*;

class TreeNode {

    int val;
    TreeNode left, right;

    public TreeNode(int value) {
        val = value;
        left = right = null;
    }
}

public class BinaryTreeBoundaryTraversal {

    private static void printLeaves(TreeNode root) {
        if (root != null) {
            printLeaves(root.left);
            if (root.left == null && root.right == null) 
                System.out.print(root.val + " ");
            printLeaves(root.right);
        }
    }

    public static void boundaryTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printLeaves(root.left);
            printLeaves(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value for the root node: ");
        int rootValue = scanner.nextInt();
        TreeNode root = new TreeNode(rootValue);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            System.out.print("Enter the value for the left child of " + current.val + " (-1 if none): ");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                current.left = new TreeNode(leftValue);
                queue.add(current.left);
            }

            System.out.print("Enter the value for the right child of " + current.val + " (-1 if none): ");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                current.right = new TreeNode(rightValue);
                queue.add(current.right);
            }
        }

        scanner.close();

        // Calling the boundaryTraversal function
        System.out.println("Boundary Traversal:");
        boundaryTraversal(root);
    }
}
