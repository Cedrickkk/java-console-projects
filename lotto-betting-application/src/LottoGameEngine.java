import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoGameEngine {
    private final int minNumber;
    private final int maxNumber;
    private final int numCombination;
    private final Random random;

    public LottoGameEngine(int minNumber, int maxNumber, int numCombination) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.numCombination = numCombination;
        this.random = new Random();
    }

    public LottoDraw generateWinningDraw() {
        Set<Integer> numbers = new HashSet<>();
        while(numbers.size() < this.numCombination) {
            numbers.add(random.nextInt(this.maxNumber - this.minNumber + 1) + this.minNumber);
        }
        return new LottoDraw(numbers);
    }

    public GameResult checkBet(BetSlip bet, LottoDraw draw) {
        Set<Integer> bettorNumbers = bet.getBettorNumbers().getLottoNumbers();
        Set<Integer> winningNumbers = draw.getWinningNumbers().getLottoNumbers();
        Set<Integer> matchedNumbers = new HashSet<>(bettorNumbers);
        matchedNumbers.retainAll(winningNumbers);
        int matchedCount = matchedNumbers.size();
        int prizeAmount = calculatePrize(matchedCount);
        return new GameResult(bet, draw, matchedCount, prizeAmount  );
     }

     private int calculatePrize(int matchedCount) {
         return switch (matchedCount) {
             case 6 -> 9000000;
             case 5 -> 25000;
             case 4 -> 1000;
             case 3 -> 20;
             default -> 0;
         };
     }

}
