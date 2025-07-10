import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("\n   \"The lottery is a tax on people who are bad at math\"\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("\"What's your winning combination? Enter your numbers now!\"");
        System.out.println("--------------------------------------------------------------");

        try (Scanner scanner = new Scanner(System.in)) {
            LottoGameEngine lottoGameEngine = new LottoGameEngine(
                    LottoNumbers.MIN_NUMBER,
                    LottoNumbers.MAX_NUMBER,
                    LottoNumbers.NUM_COMBINATIONS
            );

            System.out.print("\n\nEnter your combination: ");
            String userInput = scanner.nextLine();

            Set<Integer> parsedNumbers = Arrays.stream(userInput.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());

            BetSlip betSlip = new BetSlip(parsedNumbers);

            LottoDraw winningDraw = lottoGameEngine.generateWinningDraw();

            GameResult result = lottoGameEngine.checkBet(betSlip, winningDraw);


            result.displayResult();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}