package jodd.julianday;

import java.math.BigDecimal;
import java.util.Objects;

import static java.lang.StrictMath.abs;

/**
 * Represents a high-precision day value that consists of integer part and fraction part.
 * Using just double for day value is not enough, as it may lead to rounding errors due to number
 * of significant digits in double. Splitting day value into integer and fraction parts helps to
 * avoid such errors for the precision we want to achieve: milliseconds precision.
 * <p>
 * Final day value is calculated as: day + time. {@code DayValue} is only a tuple holding these two values.
 * It has no domain meaning by itself. Therefore, it can hold a Julian day value, Modified Julian day value,
 * or any other day value. It also does not recalculate time value to be in the range [0, 1).
 */
public class DayValue {
    /**
     * Integer part of the day.
     */
    private final int day;

	/**
     * Returns integer part of the day.
     */
    public int day() {
        return this.day;
    }

    /**
     * Fraction part of the day.
     */
    private final double time;

    /**
     * Returns the fraction part of day.
     */
    public double time() {
        return this.time;
    }

    /**
     * Creates a new day value.
     *
     * @param day  integer part of the day
     * @param time fraction part of the day
     */
    public DayValue(final int day, final double time) {
        this.day = day;
        this.time = time;
    }

    /**
     * Converts day value to less precise double value.
     * <b>Warning:</b> double value is not precise enough for high-precision calculations.
     */
    public double toDouble() {
        return this.day + this.time;
    }

    /**
     * Converts day value to BigDecimal.
     */
    public BigDecimal toBigDecimal() {
        return new BigDecimal(this.day).add(new BigDecimal(this.time));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DayValue dayValue = (DayValue) o;
        return this.day == dayValue.day &&
                abs(this.time - dayValue.time) < 1e-10;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.day, this.time);
    }

    @Override
    public String toString() {
        return "DayValue{" +
                "day=" + this.day +
                ", time=" + this.time +
                '}';
    }

    public static DayValue of(final double value) {
        final int day = (int) value;
        final double time = value - day;
        return new DayValue(day, time);
    }
}
