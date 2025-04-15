import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numCounter = 1;
        int[] bettorNumber = new int[6];
        int winningPrize = 0;

        System.out.println("\n   \"The lottery is a tax on people who are bad at math\"\n");
        System.out.println("\"What's your winning combination? Enter your numbers now!\"\n");

        try {
            for (int i = 0; i < bettorNumber.length; i++) {
                System.out.print(numCounter++ + ") ");
                bettorNumber[i] = input.nextInt();
                if (bettorNumber[i] < 1 || bettorNumber[i] > 42) {
                    System.out.println("\nYour numbers should be between 1 and 42 only.");
                    System.exit(0);
                }
                for (int j = 0; j < i; j++) {
                    if (bettorNumber[j] == bettorNumber[i]) {
                        System.out.println("\nYou can't pick the same numbers again.");
                        System.exit(0);
                    }
                }
            }

            System.out.print("\n\n  Winning combinations: ");

            //Generate 6 Random Numbers
//			int[] winningCombinations = new int [6];
//			System.out.print(" [ ");
//			for(int i = 0; i < 6; i++) {
//				winningCombinations[i] = 1 + (int)(Math.random() * 42);
//				System.out.print(winningCombinations[i] + " ");
//			}
//			System.out.print("]");
//			// Storing the total number of matched numbers and their actual values
//			int matchCounter = countMatchingNumbers(bettorNumber, winningCombinations);
//			int[] matchingNumbers = getMatchingNumbers(bettorNumber, winningCombinations);

            //START OF SAMPLE PRESENTATION OF MATCHING NUMBERS

            int[] sample = {1, 2, 3, 4, 5, 6};
            System.out.print(" [ ");
            for (int printSample : sample) {
                System.out.print(printSample + " ");
            }
            System.out.print("]");
            int matchCounter = countMatchingNumbers(bettorNumber, sample);
            int[] matchingNumbers = getMatchingNumbers(bettorNumber, sample);


            // Printing the match numbers found
            if (matchCounter > 0) {
                System.out.print("\n\n  Matching number/s: ");
                for (int i = 0; i < matchCounter; i++) {
                    System.out.print(matchingNumbers[i] + " ");
                }
            } else System.out.print("\n\n  No matching numbers found.");

            switch (matchCounter) {
                case 6 -> {
                    winningPrize = 9000000;
                    System.out.println();
                    System.out.println("\n\n  Luck is definitely on your side! \n\n  Congratulations you won P" + winningPrize);
                }
                case 5 -> {
                    winningPrize = 25000;
                    System.out.println("\n\n  Congratulations  You won P" + winningPrize);
                }
                case 4 -> {
                    winningPrize = 1000;
                    System.out.println("\n\n  Congratulations  You won P" + winningPrize);
                }
                case 3 -> {
                    winningPrize = 20;
                    System.out.println("\n\n  You Won P" + winningPrize);
                }
                default -> {
                    System.out.println("\n\n  No Prize.");
                    System.exit(0);
                }
            }

        } catch (InputMismatchException ime) {
            System.out.println("\n\t  This only accept an integer value!");
            System.out.println("\nError = " + ime + " caught at line " + --numCounter);
        }
    }

    public static int countMatchingNumbers(int[] bettorNumbers, int[] winningCombinations) {
        int totalMatchedNumbers = 0;
        for (int bettorNumber : bettorNumbers) {
            for (int winningCombination : winningCombinations) {
                if (bettorNumber == winningCombination) {
                    totalMatchedNumbers++;
                    break;
                }
            }
        }
        return totalMatchedNumbers;
    }

    public static int[] getMatchingNumbers(int[] bettorNumbers, int[] winningCombinations) {
        int index = 0;
        int[] matchingNumbers = new int[bettorNumbers.length];
        for (int bettorNumber : bettorNumbers) {
            for (int winningCombination : winningCombinations) {
                if (bettorNumber == winningCombination) {
                    matchingNumbers[index] = bettorNumber;
                    index++;
                    break;
                }
            }
        }
        return matchingNumbers;
    }
}