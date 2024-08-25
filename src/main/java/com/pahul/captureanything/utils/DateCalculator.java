package com.pahul.captureanything.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DateCalculator {

    private static final Map<String, DayOfWeek> WEEKDAYS = new HashMap<>();
    static {
        WEEKDAYS.put("monday", DayOfWeek.MONDAY);
        WEEKDAYS.put("tuesday", DayOfWeek.TUESDAY);
        WEEKDAYS.put("wednesday", DayOfWeek.WEDNESDAY);
        WEEKDAYS.put("thursday", DayOfWeek.THURSDAY);
        WEEKDAYS.put("friday", DayOfWeek.FRIDAY);
        WEEKDAYS.put("saturday", DayOfWeek.SATURDAY);
        WEEKDAYS.put("sunday", DayOfWeek.SUNDAY);
    }

    public static LocalDate calculateDate(String when) {
        when = when.toLowerCase();

        if (when.contains("yesterday")) {
            return LocalDate.now().minusDays(1);
        } else if (when.contains("day before yesterday")) {
            return LocalDate.now().minusDays(2);
        } else if (when.contains("last")) {
            if (when.contains("monday")) {
                return lastMonday();
            } else if (when.contains("tuesday")) {
                return lastTuesday();
            } else if (when.contains("wednesday")) {
                return lastWednesday();
            } else if (when.contains("thursday")) {
                return lastThursday();
            } else if (when.contains("friday")) {
                return lastFriday();
            } else if (when.contains("saturday")) {
                return lastSaturday();
            } else if (when.contains("sunday")) {
                return lastSunday();
            } else if (when.contains("week")) {
                return lastWeek();
            }  else if (when.contains("month")) {
                return lastMonth();
            }else if (when.contains("two weeks")) {
                return twoWeeksAgo();
            }
        } else if (when.contains("two days before")) {
            return LocalDate.now().minusDays(2);
        } else if (when.contains("three days before")) {
            return LocalDate.now().minusDays(3);
        }else if (when.contains("four days before")) {
            return LocalDate.now().minusDays(4);
        }else if (when.contains("five days before")) {
            return LocalDate.now().minusDays(5);
        }else if (when.contains("six days before")) {
            return LocalDate.now().minusDays(6);
        }else if (when.contains("7 days before")) {
            return LocalDate.now().minusDays(7);
        }

        throw new UnsupportedOperationException("Unsupported 'when' parameter: " + when);
    }

    private static LocalDate lastMonday() {
        return LocalDate.now().with(DayOfWeek.MONDAY);
    }

    private static LocalDate lastTuesday() {
        return LocalDate.now().with(DayOfWeek.TUESDAY);
    }

    private static LocalDate lastWednesday() {
        return LocalDate.now().with(DayOfWeek.WEDNESDAY);
    }

    private static LocalDate lastThursday() {
        return LocalDate.now().with(DayOfWeek.THURSDAY);
    }

    private static LocalDate lastFriday() {
        return LocalDate.now().with(DayOfWeek.FRIDAY);
    }

    private static LocalDate lastSaturday() {
        return LocalDate.now().with(DayOfWeek.SATURDAY);
    }

    private static LocalDate lastSunday() {
        return LocalDate.now().with(DayOfWeek.SUNDAY);
    }

    private static LocalDate lastWeek() {
        return LocalDate.now().minusWeeks(1);
    }
    private static LocalDate lastMonth() {
        return LocalDate.now().minusMonths(1);
    }

    private static LocalDate twoWeeksAgo() {
        return LocalDate.now().minusWeeks(2);
    }

}