import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static int numCounter = 1;
    static int[] originalArray;
    static boolean isValidChoice = false;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean isValidSize = false;
        while (!isValidSize) {
            System.out.print("Enter the size of Array (5 - 15) : ");
            try {
                int arraySize = input.nextInt();
                if (arraySize >= 5 && arraySize <= 15) {
                    originalArray = new int[arraySize];
                    isValidSize = true;
                    insertElements(originalArray, numCounter);
                } else {
                    System.out.println("Invalid Size! Only 5 - 15 are accepted.\n");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter an integer.\n");
                input.nextLine();
            }
        }

        printSortingMenu();

        while (!isValidChoice) {
            System.out.print("\nEnter your choice : ");
            try {
                int choice = input.nextInt();
                int[] copyOfArray = copyArray(originalArray);
                switch (choice) {
                    case 1 -> {
                        System.out.println('\u000c');
                        printOperationTitle("BUBBLE SORT");
                        System.out.print("UNSORTED : ");
                        printArray(copyOfArray);
                        printDashLine();

                        System.out.println();
                        numCounter = 1;
                        bubbleSort(copyOfArray);
                        System.out.println();

                        printDashLine();
                        System.out.print("  SORTED : ");
                        printArray(copyOfArray);
                        printDashLine();
                        PressAnyKey();
                    }

                    case 2 -> {
                        System.out.println('\u000c');
                        printOperationTitle("SELECTION SORT");
                        System.out.print("UNSORTED : ");
                        printArray(copyOfArray);
                        printDashLine();

                        System.out.println();
                        numCounter = 1;
                        selectionSort(copyOfArray);
                        System.out.println();

                        printDashLine();
                        System.out.print("  SORTED : ");
                        printArray(copyOfArray);
                        printDashLine();
                        PressAnyKey();
                    }
                    case 3 -> {
                        System.out.println('\u000c');
                        printOperationTitle("INSERTION SORT");
                        System.out.print("UNSORTED : ");
                        printArray(copyOfArray);
                        printDashLine();

                        System.out.println();
                        numCounter = 1;
                        insertionSort(copyOfArray);
                        System.out.println();

                        printDashLine();
                        System.out.print("  SORTED : ");
                        printArray(copyOfArray);
                        printDashLine();
                        PressAnyKey();
                    }
                    case 4 -> {
                        System.out.println('\u000c');
                        input.nextLine();
                        while (true) {
                            System.out.print("\nTry Again (Y/N): ");
                            String tryAgain = input.nextLine();
                            if (tryAgain.equalsIgnoreCase("Y")) {
                                originalArray = null;
                                numCounter = 1;
                                System.out.println('\u000c');
                                main(args);
                                break;
                            } else if (tryAgain.equalsIgnoreCase("N")) {
                                System.out.print("PROGRAM TERMINATED.");
                                System.exit(0);
                            } else {
                                System.out.println("Invalid Input.");
                            }
                        }
                    }
                    default -> System.out.println("Invalid Input. Try Again.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter an integer.\n");
                input.nextLine();
            }

        }

    }


    public static void printSortingMenu() {
        printDashLine();
        System.out.println("      Sorting Operations ");
        System.out.println("\t\t\t Menu");
        printDashLine();
        System.out.println("\n\t[1] Bubble Sort");
        System.out.println("\n\t[2] Selection Sort");
        System.out.println("\n\t[3] Insertion Sort");
        System.out.println("\n\t[4] Exit");
    }

    public static void bubbleSort(int[] array) {
        boolean isSwapped;
        for (int i = 0; i < array.length - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwapped = true;
                }
            }

            if (isSwapped) {
                printArrayAndIncrementCounter(array);
            }


            if (!isSwapped) {
                break;
            }
        }
    }

    public static void selectionSort(int[] array) {
        int smallElement;
        for (int i = 0; i < array.length - 1; i++) {
            smallElement = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[smallElement]) smallElement = j;
            int temp = array[smallElement];
            array[smallElement] = array[i];
            array[i] = temp;
            printArrayAndIncrementCounter(array);
        }
    }

    public static void insertionSort(int[] array) {
        for (int j = 1; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while ((i > -1) && (array[i] > key)) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
            printArrayAndIncrementCounter(array);
        }
    }


    public static void insertElements(int[] array, int numCounter) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput the elements : \n");
        int[] inputElements = new int[array.length];
        for (int i = numCounter - 1; i < inputElements.length; i++) {
            System.out.print(numCounter + ") ");
            try {
                inputElements[i] = input.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nInvalid input. Please enter an integer.\n");
                i--;
                input.nextLine();
                continue;
            }
            if (inputElements[i] == -99) {
                break;
            }
            boolean isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (array[j] == inputElements[i]) {
                    isDuplicate = true;
                    System.out.println("\nYou can't input the same numbers again.");
                    System.out.println("Duplicated numbers found -> " + array[j] + "\n");
                    i--;
                    break;
                }
            }
            if (!isDuplicate) {
                array[i] = inputElements[i];
                numCounter++;
            }
        }
    }

    public static void PressAnyKey() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nPress any key to continue...");
        try {
            input.readLine();
            System.out.println('\u000c');
            printSortingMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void printArrayAndIncrementCounter(int[] array) {
        System.out.print("\t" + numCounter + ")  ");
        printArray(array);
        numCounter++;
    }

    public static void printOperationTitle(String title) {
        printDashLine();
        System.out.println("\t  OPERATION: " + title);
        printDashLine();
    }

    public static void printDashLine() {
        System.out.println("-----------------------------------");
    }

}

