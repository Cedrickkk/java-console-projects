import java.util.Objects;

public class GameResult {

    private final BetSlip bet;
    private final LottoDraw draw;
    private final int matchedCount;
    private final int prizeAmount;

    public GameResult(BetSlip bet, LottoDraw draw, int matchedCount, int prizeAmount) {
        if (bet == null || draw == null) {
            throw new IllegalArgumentException("Bet and Draw cannot be null for GameResult.");
        }
        if (matchedCount < 0) {
            throw new IllegalArgumentException("Matched count cannot be negative.");
        }
        if (prizeAmount < 0) {
            throw new IllegalArgumentException("Prize amount cannot be negative.");
        }

        this.bet = bet;
        this.draw = draw;
        this.matchedCount = matchedCount;
        this.prizeAmount = prizeAmount;
    }

    public BetSlip getBet() {
        return bet;
    }

    public LottoDraw getDraw() {
        return draw;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public void displayResult() {
        System.out.println("\n\n-----------------------------------------------");
        System.out.println("\t\t\t\tLotto Game Result ");
        System.out.println("-----------------------------------------------");
        System.out.println("Your Bet: " + bet.getBettorNumbers().getLottoNumbers());
        System.out.println("Winning Numbers: " + draw.getWinningNumbers().getLottoNumbers());
        System.out.println("Matched Numbers: " + matchedCount);
        System.out.println("Prize Won: $" + prizeAmount);
        System.out.println("-----------------------------------------------");
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "bet=" + bet +
                ", draw=" + draw +
                ", matchedCount=" + matchedCount +
                ", prizeAmount=" + prizeAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return matchedCount == that.matchedCount &&
                prizeAmount == that.prizeAmount &&
                Objects.equals(bet, that.bet) &&
                Objects.equals(draw, that.draw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet, draw, matchedCount, prizeAmount);
    }
}
