import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BetSlip {

    private final LottoNumbers bettorNumbers;

    public BetSlip(Set<Integer> bettorNumbers) {
        if (bettorNumbers == null) {
            throw new IllegalArgumentException("Chosen numbers for bet cannot be null.");
        }
        this.bettorNumbers = new LottoNumbers(new HashSet<>(bettorNumbers));
    }

    public LottoNumbers getBettorNumbers() {
        return bettorNumbers;
    }

    @Override
    public String toString() {
        return "BetSlip [Numbers=" + bettorNumbers.getLottoNumbers() + "]"; // Assuming getLottoNumbers() on LottoNumbers returns Set
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetSlip betSlip = (BetSlip) o;
        return Objects.equals(bettorNumbers, betSlip.bettorNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettorNumbers);
    }
}
