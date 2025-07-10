import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class LottoNumbers {
    public static final int NUM_COMBINATIONS = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 42;

    private final Set<Integer> lottoNumbers;

    public LottoNumbers(Set<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers set cannot be null."); // Good message
        }

        if(numbers.size() != NUM_COMBINATIONS) {
            throw new IllegalArgumentException("Provided numbers must contain exactly " + NUM_COMBINATIONS + " unique numbers.");
        }

        for (int num : numbers) {
            if (num < MIN_NUMBER || num > MAX_NUMBER) {
                throw new IllegalArgumentException("All numbers must be between " + MIN_NUMBER + " and " + MAX_NUMBER + " (inclusive).");
            }
        }

        this.lottoNumbers = Collections.unmodifiableSet(new TreeSet<>(numbers));;
    }

    public Set<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
