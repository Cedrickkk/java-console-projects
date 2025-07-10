import java.util.Objects;
import java.util.Set;

public class LottoDraw {
   private final LottoNumbers winningNumbers;

    public LottoDraw(Set<Integer> numbers) {
       winningNumbers = new LottoNumbers(numbers);
    }

    public LottoNumbers getWinningNumbers() {
        return winningNumbers;
    }

    @Override
    public String toString() {
        return "LottoDraw [Winning Numbers=" + winningNumbers.getLottoNumbers() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoDraw lottoDraw = (LottoDraw) o;
        return Objects.equals(winningNumbers, lottoDraw.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}
