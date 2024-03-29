import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

public class Main {
    Node first, middle, last, prev;

    void correctBST(Node root) {
        first = middle = last = prev = null;
        correctBSTUtil(root);
        if (last == null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        } else {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
    }

    void correctBSTUtil(Node root) {
        if (root != null) {
            correctBSTUtil(root.left);
            if (prev != null && root.data < prev.data) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    last = root;
                }
            }
            prev = root;
            correctBSTUtil(root.right);
        }
    }

    void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Main tree = new Main();

        System.out.println("Enter the number of nodes in the tree:");
        int numNodes = scanner.nextInt();

        Node root = null;
        Node[] nodes = new Node[numNodes];

        System.out.println("Enter the values of the nodes:");

        for (int i = 0; i < numNodes; i++) {
            int value = scanner.nextInt();
            nodes[i] = new Node(value);
        }

        for (int i = 0; i < numNodes; i++) {
            System.out.println("Enter the left child value of node " + nodes[i].data + " (-1 if none):");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                nodes[i].left = new Node(leftValue);
            }

            System.out.println("Enter the right child value of node " + nodes[i].data + " (-1 if none):");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                nodes[i].right = new Node(rightValue);
            }

            if (root == null) {
                root = nodes[i];
            }
        }

        System.out.println("Inorder traversal before correction:");
        tree.printInorder(root);
        System.out.println();

        tree.correctBST(root);

        System.out.println("Inorder traversal after correction:");
        tree.printInorder(root);

        scanner.close();
    }
}
