# Julian Day Java Library

**Julian Day** implementation in Java, with following features:

+ high-precision Julian Day calculation (up to milliseconds)
+ Julian Day to/from **Gregorian calendar** date conversion
+ Julian Day to/from **Julian calendar** date conversion
+ Julian Day to/from **Unix time** conversion
+ Basic Julian Day arithmetic operations

## Usage


## Introduction to Julian Day

The **Julian Day** is a _continuous_ and _uniform_ count of days since the beginning of the Julian Period on:

+ January 1, 4713 BC, 12 hours GMT (Julian Calendar),
+ -4712 January 1, 12 hours GMT (Julian proleptic Calendar), or
+ -4713 November 24, 12 hours GMT (Gregorian proleptic Calendar).
+ 4714 BCE November 24, 12 hours GMT (Gregorian proleptic Calendar)

All these dates are equivalent. At this instant, the **Julian Day** Number is `0`. It is convenient for astronomers to use since it is not necessary to worry about odd numbers of days in a month, leap years, etc. Once you have the **Julian Day** Number of a particular date in history, it is easy to calculate time elapsed between it and any other **Julian Day** Number.

The **Julian Day** is used in astronomy and chronology as a simple way to express dates. The curiosity is that it starts at noon, not at midnight. The **Julian Day** starts at noon because the astronomical day starts at noon (it is easier to track events that occur during the nighttime).
. **Julian Day** is invented in the 16th century by _Josephus Justus Scaliger_, a French scholar, who wanted to find a simple method to track astronomical events.

Although the term "Julian Calendar" derives from the name of Julius Caesar, the term "Julian day number" probably does not. Most say that this system was named, not after Julius Caesar, but after its inventor's father, Julius Caesar Scaliger. Perhaps it was simply named after the Julian Calendar.

<details>
<summary>More on Julian Day</summary>

## Astronomical System

Little mention seems to be made whether Joseph Scaliger regarded `-4712-01-01 J` as day `0` or as day `1` in the first Julian period. Astronomers adopted this system and adapted it to their own purposes, and they took noon GMT `-4712-01-01` as their **zero** point.

For astronomers a day begins at noon and runs until the next noon (so that the nighttime falls conveniently within one "day"). Thus, they defined the Julian day number of a day as the number of days (or part of a day) elapsed since noon GMT (or more exactly, UTC) on January 1st, 4713 B.C., in the Proleptic Julian Calendar. Thus, the Julian day number of noon GMT on `-4712-01-01 (Julian)`, or more casually, the Julian day number of `-4712-01-01` itself, is `0`.

(Note that `4713 B.C.` is the year `-4712` according to the astronomical year numbering.) The Julian day number of `1996-03-31` is `2,450,174`, meaning that on `1996-03-31` `2,450,174` days had elapsed since `-4712-01-01` (or more exactly, that at noon on `1996-03-31` `2,450,174` days had elapsed since noon on `-4712-01-01`).

Scaliger preceded the astronomers in introducing the notion of decimal times, designating midnight as `.00`, `6 a.m.` as `.25`, midday as `.50` and `6 p.m.` as `.75`, thus allowing easier calculation involving dates and times. Astronomers, as noted above, preferred to use `.00` to mean midday and `.50` to mean midnight.


## Modified Julian Day Number

This was not to the liking of all scholars using the Julian day number system, in particular, historians. For chronologists who start "days" at midnight, the zero point for the Julian day number system is `00:00` at the start of `-4712-01-01 J`, and this is day `0`. This means that `2000-01-01 G` is `2,451,545 JD`.

Since most days within about 150 years of the present have Julian day numbers beginning with "`24`", Julian day numbers within this 300-odd-year period can be abbreviated. In 1975 the convention of the modified Julian day number was adopted:

+ Given a Julian day number JD, the **modified Julian day** number MJD is defined as MJD = JD - 2,400,000.5. This has two purposes:

1. Days begin at midnight rather than noon.
2. For dates in the period from 1859 to about 2130 only five digits need to be used to specify the date rather than seven.

`MJD 0` thus corresponds to `JD 2,400,000.5`, which is twelve hours after noon on `JD 2,400,000 = 1858-11-16`. Thus `MJD 0` designates the midnight of November 16th/17th, 1858, so day `0` in the system of modified Julian day numbers is the day `1858-11-17`.

</details>

## Calendar Systems

Following are the calendar systems used in the Julian Day calculation:

+ **Julian Calendar**: The Julian calendar was introduced by Julius Caesar in 46 BC. It was in use until October 1582 AD. The Julian calendar has a leap year every four years, which is not accurate enough. Julian calendar is behind the Gregorian calendar!
+ **Julian Proleptic Calendar**: The Julian proleptic calendar is an extension of the Julian calendar to dates before 46 BC. It is used to calculate dates before the introduction of the Julian calendar. The Julian proleptic calendar is used in astronomy.
+ **Gregorian Calendar**: The Gregorian calendar was introduced by Pope Gregory XIII in 1582 AD. The Gregorian calendar has a leap year every four years, except for years that are divisible by 100 and not divisible by 400. The Gregorian calendar is the most widely used calendar system today.
+ **Gregorian Proleptic Calendar**: The Gregorian proleptic calendar is an extension of the Gregorian calendar to dates before October 15th, 1582 AD. It is used to calculate dates before the introduction of the Gregorian calendar.

This library works with proleptic calendars and have the year zero.

The year zero is a year in the proleptic Gregorian calendar, which is `1 BC` in the proleptic Julian calendar. The year zero does not exist in the Anno Domini system usually used to number years in the Gregorian calendar.

## Precision of Julian Day implementation

The library offer high-precision Julian Day calculation, up to milliseconds. To achieve this, the library separates the integer and fractional part of the Julian Day number. The integer part is the number of days since the beginning of the Julian Period, and the fractional part is the fraction of the day. The Julian Day number is the sum of these two parts.

This is required as `double` precision is not enough to store the Julian Day number with high precision (up to milliseconds). The fractional part of the Julian Day number is very small, and the integer part is very large. When you add these two parts, some of the fractional numerals may be lost.

## Links

+ [Julian Day - Wikipedia](https://en.wikipedia.org/wiki/Julian_day)
+ [Julian Day Calculator](https://www.fourmilab.ch/documents/calendar/)
+ [JD Converter @ Nasa JPL](https://ssd.jpl.nasa.gov/tools/jdc/#/jd)
