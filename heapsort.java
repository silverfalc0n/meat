import java.util.Scanner;

class HeapSort {
    static void heapify(int a[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && a[left] > a[largest])
            largest = left;
        
        if (right < n && a[right] > a[largest])
            largest = right;
        
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heapify(a, n, largest);
        }
    }

    static void heapSort(int a[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapify(a, i, 0);
        }
    }

    static void printArr(int a[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int a[] = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextInt();

        scanner.close();

        System.out.println("Original Array:");
        printArr(a, n);

        heapSort(a, n);

        System.out.println("\nSorted Array:");
        printArr(a, n);
    }
}
