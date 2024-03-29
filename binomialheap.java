import java.util.*;

class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent, sibling, child;

    public BinomialHeapNode(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    public BinomialHeapNode reverse() {
        BinomialHeapNode prev = null;
        BinomialHeapNode current = this;
        BinomialHeapNode next = null;
        while (current != null) {
            next = current.sibling;
            current.sibling = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public BinomialHeapNode findMinNode() {
        BinomialHeapNode minNode = this;
        int min = this.key;
        BinomialHeapNode current = this;
        while (current != null) {
            if (current.key < min) {
                min = current.key;
                minNode = current;
            }
            current = current.sibling;
        }
        return minNode;
    }

    public BinomialHeapNode findNodeWithKey(int value) {
        if (key == value)
            return this;
        BinomialHeapNode foundNode = null;
        if (child != null)
            foundNode = child.findNodeWithKey(value);
        if (foundNode == null && sibling != null)
            foundNode = sibling.findNodeWithKey(value);
        return foundNode;
    }

    public int getSize() {
        int size = 1;
        if (child != null)
            size += child.getSize();
        if (sibling != null)
            size += sibling.getSize();
        return size;
    }
}

class BinomialHeap {
    private BinomialHeapNode Nodes;
    private int size;

    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }

    public boolean isEmpty() {
        return Nodes == null;
    }

    public int getSize() {
        return size;
    }

    public void makeEmpty() {
        Nodes = null;
        size = 0;
    }

    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            } else {
                unionNodes(temp);
                size++;
            }
        }
    }

    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode temp1 = Nodes;
        BinomialHeapNode temp2 = binHeap;

        BinomialHeapNode prev = null;
        while (temp1 != null && temp2 != null) {
            if (temp1.degree <= temp2.degree) {
                prev = temp1;
                temp1 = temp1.sibling;
            } else {
                if (prev != null)
                    prev.sibling = temp2;
                BinomialHeapNode next = temp2.sibling;
                temp2.sibling = temp1;
                temp1 = temp2;
                temp2 = next;
            }
        }
        if (prev != null)
            prev.sibling = temp2;
        else
            Nodes = temp2;
    }

    private void unionNodes(BinomialHeapNode binHeap) {
        merge(binHeap);
        if (Nodes == null)
            return;

        BinomialHeapNode prev = null;
        BinomialHeapNode x = Nodes;
        BinomialHeapNode next = x.sibling;

        while (next != null) {
            if (x.degree != next.degree || (next.sibling != null && next.sibling.degree == x.degree)) {
                prev = x;
                x = next;
            } else {
                if (x.key <= next.key) {
                    x.sibling = next.sibling;
                    next.parent = x;
                    next.sibling = x.child;
                    x.child = next;
                    x.degree++;
                } else {
                    if (prev == null)
                        Nodes = next;
                    else
                        prev.sibling = next;
                    x.parent = next;
                    x.sibling = next.child;
                    next.child = x;
                    next.degree++;
                    x = next;
                }
            }
            next = x.sibling;
        }
    }

    public int findMinimum() {
        if (Nodes == null)
            return -1;
        return Nodes.findMinNode().key;
    }

    public void delete(int value) {
        BinomialHeapNode nodeToDelete = Nodes.findNodeWithKey(value);
        if (nodeToDelete != null) {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }

    public void decreaseKeyValue(int old_value, int new_value) {
        BinomialHeapNode nodeToUpdate = Nodes.findNodeWithKey(old_value);
        if (nodeToUpdate == null)
            return;
        nodeToUpdate.key = new_value;
        BinomialHeapNode parent = nodeToUpdate.parent;
        while (parent != null && nodeToUpdate.key < parent.key) {
            int temp = nodeToUpdate.key;
            nodeToUpdate.key = parent.key;
            parent.key = temp;
            nodeToUpdate = parent;
            parent = parent.parent;
        }
    }

    public int extractMin() {
        if (Nodes == null)
            return -1;

        BinomialHeapNode minNode = Nodes.findMinNode();
        BinomialHeapNode temp = Nodes;
        BinomialHeapNode prev = null;

        while (temp != minNode) {
            prev = temp;
            temp = temp.sibling;
        }

        if (prev == null)
            Nodes = temp.sibling;
        else
            prev.sibling = temp.sibling;

        BinomialHeapNode child = temp.child;
        if (child != null) {
            BinomialHeapNode reversedChild = child.reverse();
            temp.child = null;
            unionNodes(reversedChild);
        }
        size = getSize();
        return minNode.key;
    }

    public void displayHeap() {
        System.out.print("Heap: ");
        displayHeap(Nodes);
        System.out.println();
    }

    private void displayHeap(BinomialHeapNode node) {
        if (node != null) {
            displayHeap(node.child);
            System.out.print(node.key + " ");
            displayHeap(node.sibling);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinomialHeap binHeap = new BinomialHeap();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter elements to insert into the binomial heap (enter -1 to stop):");
        int element;
        while ((element = scanner.nextInt()) != -1) {
            binHeap.insert(element);
        }

        System.out.print("Initial heap: ");
        binHeap.displayHeap();

        System.out.println("Enter elements to delete from the binomial heap (enter -1 to stop):");
        while ((element = scanner.nextInt()) != -1) {
            binHeap.delete(element);
        }

        System.out.print("Updated heap: ");
        binHeap.displayHeap();

        scanner.close();
    }
}

