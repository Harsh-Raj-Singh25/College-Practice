import java.util.Scanner;
import java.util.Arrays;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[20];
        int n = 0; // current size

        while (true) {
            System.out.println("\n Array Menu:- ");
            System.out.println("1. Insert 2. Delete 3. LinearSearch 4. BinarySearch");
            System.out.println("5. Max Value 6. Count the Even/Odd 7. Insertion Sort 8. Exit ");
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    arr[n++] = sc.nextInt();
                    break;
                case 2:
                    System.out.print("Enter the index to delete: ");
                    int index = sc.nextInt();
                    for (int i = index; i < n - 1; i++) arr[i] = arr[i+1];
                    n--;
                    break;
                case 3:
                    System.out.print("Enter the target: ");
                    int target = sc.nextInt();
                    int res = -1;
                    for(int i=0; i<n; i++) if(arr[i] == target) res = i;
                    System.out.println("Found at index: " + res);
                    break;
                case 4:
                    Arrays.sort(arr, 0, n);
                    System.out.print("Enter target: ");
                    System.out.println("Binary Search Result index: " + Arrays.binarySearch(arr, 0, n, sc.nextInt()));
                    break;
                case 5:
                    int max = arr[0];
                    for(int i=1; i<n; i++) if(arr[i] > max) max = arr[i];
                    System.out.println("Max: " + max);
                    break;
                case 6:
                    int e = 0, o = 0;
                    for(int i=0; i<n; i++) if(arr[i]%2==0) e++; else o++;
                    System.out.println("Even: " + e + ", Odd: " + o);
                    break;
                case 7:
                    for (int i = 1; i < n; i++) {
                        int key = arr[i];
                        int j = i - 1;
                        while (j >= 0 && arr[j] > key) {
                            arr[j + 1] = arr[j];
                            j--;
                        }
                        arr[j + 1] = key;
                    }
                    System.out.println("Sorted Array: " + Arrays.toString(Arrays.copyOf(arr, n)));
                    break;
                case 8: System.exit(0);
            }
        }
    }
}