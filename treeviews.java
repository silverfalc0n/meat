import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class TreeViews {

    // Helper class to represent a node along with its horizontal distance
    static class NodeWithHD {
        TreeNode node;
        int hd;

        NodeWithHD(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // Function to generate the horizontal view of a binary tree
    public static List<Integer> horizontalView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<NodeWithHD> queue = new LinkedList<>();
        queue.offer(new NodeWithHD(root, 0));

        while (!queue.isEmpty()) {
            NodeWithHD nodeWithHD = queue.poll();
            TreeNode node = nodeWithHD.node;
            int hd = nodeWithHD.hd;

            map.put(hd, node.val);

            if (node.left != null) queue.offer(new NodeWithHD(node.left, hd - 1));
            if (node.right != null) queue.offer(new NodeWithHD(node.right, hd + 1));
        }

        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }

    // Function to generate the vertical view of a binary tree
    public static List<List<Integer>> verticalView(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<NodeWithHD> queue = new LinkedList<>();
        queue.offer(new NodeWithHD(root, 0));

        while (!queue.isEmpty()) {
            NodeWithHD nodeWithHD = queue.poll();
            TreeNode node = nodeWithHD.node;
            int hd = nodeWithHD.hd;

            if (!map.containsKey(hd)) {
                map.put(hd, new ArrayList<>());
            }
            map.get(hd).add(node.val);

            if (node.left != null) queue.offer(new NodeWithHD(node.left, hd - 1));
            if (node.right != null) queue.offer(new NodeWithHD(node.right, hd + 1));
        }

        for (int key : map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }

    // Function to generate the left view of a binary tree
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            result.add(queue.peek().val); // Add the leftmost node to the result

            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }

    // Function to generate the right view of a binary tree
    public static List<Integer> rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode lastNode = null;

            while (size-- > 0) {
                TreeNode node = queue.poll();
                lastNode = node;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(lastNode.val); // Add the rightmost node to the result
        }

        return result;
    }

    // Function to generate the top view of a binary tree
    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<NodeWithHD> queue = new LinkedList<>();
        queue.offer(new NodeWithHD(root, 0));

        while (!queue.isEmpty()) {
            NodeWithHD nodeWithHD = queue.poll();
            TreeNode node = nodeWithHD.node;
            int hd = nodeWithHD.hd;

            if (!map.containsKey(hd)) {
                map.put(hd, node.val);
            }

            if (node.left != null) queue.offer(new NodeWithHD(node.left, hd - 1));
            if (node.right != null) queue.offer(new NodeWithHD(node.right, hd + 1));
        }

        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }

    // Function to generate the bottom view of a binary tree
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<NodeWithHD> queue = new LinkedList<>();
        queue.offer(new NodeWithHD(root, 0));

        while (!queue.isEmpty()) {
            NodeWithHD nodeWithHD = queue.poll();
            TreeNode node = nodeWithHD.node;
            int hd = nodeWithHD.hd;

            map.put(hd, node.val);

            if (node.left != null) queue.offer(new NodeWithHD(node.left, hd - 1));
            if (node.right != null) queue.offer(new NodeWithHD(node.right, hd + 1));
        }

        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of root node: ");
        int rootValue = scanner.nextInt();
        TreeNode root = new TreeNode(rootValue);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            System.out.print("Enter left child value of " + current.val + " (-1 if none): ");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                current.left = new TreeNode(leftValue);
                queue.offer(current.left);
            }

            System.out.print("Enter right child value of " + current.val + " (-1 if none): ");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                current.right = new TreeNode(rightValue);
                queue.offer(current.right);
            }
        }

        scanner.close();

        System.out.println("Horizontal View: " + horizontalView(root));
        System.out.println("Vertical View: " + verticalView(root));
        System.out.println("Left View: " + leftView(root));
        System.out.println("Right View: " + rightView(root));
        System.out.println("Top View: " + topView(root));
        System.out.println("Bottom View: " + bottomView(root));
    }
}
