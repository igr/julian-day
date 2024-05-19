package jodd.julianday;

import java.util.Objects;

/**
 * Represents a Julian calendar date and time.
 * <p>
 * It is different from {@link java.time.LocalDateTime} because it is based on Julian calendar.
 */
public class JulianDateTime {
	private final int year;
	private final int month;
	private final int day;
	private final int hour;
	private final int minute;
	private final int second;
	private final int millisecond;

	public int getYear() {
		return this.year;
	}

	public int getMonthValue() {
		return this.month;
	}

	public int getDayOfMonth() {
		return this.day;
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public int getSecond() {
		return this.second;
	}

	public int getMillisecond() {
		return this.millisecond;
	}

	public JulianDateTime(final int year, final int month, final int day, final int hour, final int minute, final int second, final int millisecond) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JulianDateTime that = (JulianDateTime) o;
        return this.year == that.year &&
                this.month == that.month &&
                this.day == that.day &&
                this.hour == that.hour &&
                this.minute == that.minute &&
                this.second == that.second &&
                this.millisecond == that.millisecond;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.year, this.month, this.day, this.hour, this.minute, this.second, this.millisecond);
    }

    @Override
    public String toString() {
        return this.year +
                "-" + pad0(this.month) +
                "-" + pad0(this.day) +
                "J" + pad0(this.hour) +
                ":" + pad0(this.minute) +
                ":" + pad0(this.second) +
                "." + pad00(this.millisecond);
    }

    public boolean isLeapYear() {
        return JulianDayFunctions.isLeapJulianYear(this.year);
    }

    private static String pad0(final int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    private static String pad00(final int value) {
        return value < 10 ? "00" + value : value < 100 ? "0" + value : String.valueOf(value);
    }
}
