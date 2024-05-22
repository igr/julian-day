# Julian Day Java Library

![Maven Central](https://img.shields.io/maven-central/v/org.jodd/jodd-julian-day)
[![Build](https://github.com/igr/julian-day/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/igr/julian-day/actions/workflows/build.yml)
[![BSD License](https://img.shields.io/badge/license-BSD--2--Clause-blue.svg)](https://github.com/oblac/jodd-util/blob/master/LICENSE)


**Julian Day** implementation in Java, with the following features:

+ high-precision Julian Day calculation (up to milliseconds)
+ Julian Day to/from **Gregorian calendar** date conversion
+ Julian Day to/from **Julian calendar** date conversion
+ Julian Day to/from **Unix time** conversion
+ Support for **Reduced**, **Truncated**, and **Modified** Julian Days
+ Basic Julian Day arithmetic operations
+ 0 dependencies
+ Java 8 compatible
+ Immutable classes

## 👩‍💻 Usage

Add the library to your project:

Maven:

```xml
<dependency>
    <groupId>org.jodd</groupId>
    <artifactId>jodd-julian-day</artifactId>
    <version>7.0.0</version>
</dependency>
```

Gradle:

```groovy
implementation 'org.jodd:julian-day:7.0.0'
```

Create `JulianDay` in different ways:

```java
import java.math.BigDecimal;

var jd = JulianDay.of(2440000, 0.5);    // split integer and fraction
var jd = JulianDay.of(new BigDecimal("2440000.5"));
var jd = JulianDay.of(2440000.5);       // less precise, avoid
```

Of course, you can create `JulianDay` from a date. `JulianDay` supports both Julian and Gregorian calendars. They are different (see bellow). If you don't know which to use, go with the Gregorian calendar.

```java
val dateTime = LocalDateTime.of(2017, 1, 7, 3, 24, 0, 0);
var jd = JulianDay.ofGregorianDate(dateTime);
var jdNow = JulianDay.now();    // current Julian day
```

For Julian dates use the `JulianDateTime` class to specify the date time information.

```java
val dateTime = new JulianDateTime(2017, 1, 7, 3, 24, 0, 0);
var jd = JulianDay.ofJulianDate(dateTime);
```

Converting `JulianDay` to calendar dates is straightforward:

```java
var dateTime = jd.toGregorianDate();
var julianDateTime = jd.toJulianDate();
```

Unix time in milliseconds is also supported.

Getting numerical values from `JulianDay`:

```java
int day = jd.day();         // integer part of julian day number
double time = jd.time();    // fraction part of julian day number

double dayTime = jd.toDouble(); // less precise, avoid
```

For arithmetic operations, use `JulianDay` methods `add` and `substract`. Both methods return a new `JulianDay` instance.

Finally, you can convert the Julian Day number to the Reduced Julian Day number, Truncated Julian Day number, and Modified Julian Day number.

```java
var rjd = jd.valueAsReducedJulianDay();
var tjd = jd.valueAsTruncatedJulianDay();
var mjd = jd.valueAsModifiedJulianDay();
```

Resulting values' type is a `DayValue` - a simple tuple of integer and double parts. It is used internally to store the Julian Day number as well. You can get it with `jd.value()` method.


## ☀️ Introduction to Julian Day

The **Julian Day** is a _continuous_ and _uniform_ count of days since the beginning of the Julian Period on:

+ January 1, 4713, BC, 12 hours GMT (Julian Calendar),
+ -4712 January 1, 12 hours GMT (Julian proleptic Calendar), or
+ -4713 November 24, 12 hours GMT (Gregorian proleptic Calendar with year 0).
+ 4714 BCE November 24, 12 hours GMT (Gregorian proleptic Calendar)

All these dates are equivalent. At this instant, the **Julian Day** Number is `0`. It is convenient for astronomers to use since it is not necessary to worry about odd numbers of days in a month, leap years, etc. Once you have the **Julian Day** Number of a particular date in history, it is easy to calculate time elapsed between it and any other **Julian Day** Number: just subtract one from the other.

The **Julian Day** is used in astronomy and chronology as a simple way to express dates. The curiosity is that it starts at noon (12 hours), not at midnight; since the astronomical day starts at noon (it is easier to track events that occur during the nighttime).

## 📅 Calendar Systems

The following are the calendar systems used in the Julian Day calculation:

+ **Julian Calendar**: The Julian calendar was introduced by Julius Caesar in 46 BC. It was in use until October 1582 AD. The Julian calendar has a leap year every four years, which is not accurate enough. As a result, the Julian calendar is currently behind the Gregorian calendar.
+ **Julian Proleptic Calendar**: an extension of the Julian calendar to dates before 46 BC. It is used to calculate dates before the introduction of the Julian calendar. It is used in astronomy.
+ **Gregorian Calendar**: The Gregorian calendar was introduced by Pope Gregory XIII in 1582 AD. The Gregorian calendar has a leap year every four years, except for years that are divisible by 100 and not divisible by 400. The Gregorian calendar is the most widely used calendar system today.
+ **Gregorian Proleptic Calendar**: an extension of the Gregorian calendar for dates before October 15th, 1582 AD. It is used to calculate dates before the introduction of the Gregorian calendar.

The year zero is a year in the proleptic Gregorian calendar, which is 1 BC in the proleptic Julian calendar. The year zero does not exist in the Anno Domini system usually used to number years in the Gregorian calendar.

This library supports Julian and Gregorian proleptic calendars **with the year zero**, using astronomical year numbering. 

## 🔬 Precision of Julian Day implementation

The library offers high-precision Julian Day calculation, up to milliseconds. To achieve this, the library separates the integer and fractional part of the Julian Day number. The integer part is the number of days since the beginning of the Julian Period, and the fractional part is the fraction of the day, starting at noon. The Julian Day number is the sum of these two parts.

Separation is required as `double` precision is not enough to store the Julian Day number with high precision (up to milliseconds). The fractional part of the Julian Day number is tiny, and the integer part is huge. When you add these two parts, some of the fractional numerals may be lost.

<details>
<summary>READ MORE about Julian Day</summary>

## 🌛 History

**Julian Day** was invented in the 16th century by _Josephus Justus Scaliger_, a French scholar, who wanted to find a simple method to track astronomical events.

Although the term "Julian Calendar" derives from the name of Julius Caesar, the term "Julian day number" probably does not. Most say that this system was named, not after Julius Caesar, but after its inventor's father, Julius Caesar Scaliger. Perhaps it was simply named after the Julian Calendar.

## 🔭 Astronomical System

Little mention seems to be made whether Joseph Scaliger regarded `-4712-01-01 J` as day `0` or as day `1` in the first Julian period. Astronomers adopted this system and adapted it to their own purposes, and they took noon GMT `-4712-01-01` as their **zero** point.

For astronomers a day begins at noon and runs until the next noon (so that the nighttime falls conveniently within one "day"). Thus, they defined the Julian day number of a day as the number of days (or part of a day) elapsed since noon GMT (or more exactly, UTC) on January 1st, 4713 B.C., in the Proleptic Julian Calendar. Thus, the Julian day number of noon GMT on `-4712-01-01 (Julian)`, or more casually, the Julian day number of `-4712-01-01` itself, is 0.

(Note that 4713 B.C. is the year -4712 according to the astronomical year numbering.) The Julian day number of `1996-03-31` is `2,450,174`, meaning that on `1996-03-31` `2,450,174` days had elapsed since `-4712-01-01` (or more exactly, that at noon on `1996-03-31` `2,450,174` days had elapsed since noon on `-4712-01-01`).

Scaliger preceded the astronomers in introducing the notion of decimal times, designating midnight as `.00`, 6 a.m. as `.25`, midday as `.50` and 6 p.m. as `.75`, thus allowing easier calculation involving dates and times. Astronomers, as noted above, preferred to use `.00` to mean midday and `.50` to mean midnight.

## 🪐 Variants supported by the library

### Modified Julian Day Number (MJD)

This was not to the liking of all scholars using the Julian day number system, in particular, historians. For chronologists who start "days" at midnight, the zero point for the Julian day number system is 00:00 at the start of `-4712-01-01 J`, and this is day `0`. This means that `2000-01-01 G` is `2,451,545 JD`.

Since most days within about 150 years of the present have Julian day numbers beginning with "24", Julian day numbers within this 300-odd-year period can be abbreviated. In 1975 the convention of the modified Julian day number was adopted:

+ Given a Julian day number JD, the **modified Julian day** number MJD is defined as MJD = JD - 2,400,000.5. This has two purposes:

1. Days begin at midnight rather than noon.
2. For dates in the period from 1859 to about 2130 only five digits need to be used to specify the date rather than seven.

`MJD 0` thus corresponds to `JD 2,400,000.5`, which is twelve hours after noon on `JD 2,400,000 = 1858-11-16`. Thus `MJD 0` designates the midnight of November 16th/17th, 1858, so day `0` in the system of modified Julian day numbers is the day `1858-11-17`.

### Reduced Julian Day Number (RJD)

The reduced Julian day number is the integer part of the Julian day number.

### Truncated Julian Day Number (TJD)

The truncated Julian day number is the integer part of the Julian day number minus `2,400,000`. The Truncated Julian Day (TJD) was introduced by NASA/Goddard in 1979 as part of a parallel grouped binary time code (PB-5) "designed specifically, although not exclusively, for spacecraft applications." TJD was a 4-digit day count from MJD 40000, which was May 24, 1968, represented as a 14-bit binary number.

## 🚀 Links

+ [Julian Day - Wikipedia](https://en.wikipedia.org/wiki/Julian_day)
+ [Julian Day Calculator](https://www.fourmilab.ch/documents/calendar/)
+ [JD Converter @ Nasa JPL](https://ssd.jpl.nasa.gov/tools/jdc/#/jd)

</details>

## 🙋‍♀️ FAQ

+ What happened to 10 days in 1582?
  - The Julian calendar was behind the solar calendar by 10 days. To correct this, Pope Gregory XIII introduced the Gregorian calendar in 1582. The day after October 4, 1582, was October 15, 1582. This was done to bring the calendar back in line with the solar calendar.
  - However, this gap is NOT reflected in the Gregorian calendar calculations. Java's `LocalDateTime` also allows dates between `1582-10-15` and `1582-10-04`. Moreover, the Gregorian calendar is accepted at different times in different countries. For example, in England, the Gregorian calendar was adopted in 1752, and the gap was 11 days. So, the gap is not universal.
+ Why is this library moved away from the Jodd repository?
  - This is one of the rare parts of the Jodd framework and tools that I will be able to support. Hence, I need a clean separation from the Jodd repository. This library is now a separate project, and I will maintain it separately.
