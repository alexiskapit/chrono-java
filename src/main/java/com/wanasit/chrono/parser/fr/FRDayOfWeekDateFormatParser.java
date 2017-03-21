package com.wanasit.chrono.parser.fr;

import com.wanasit.chrono.ChronoOption;
import com.wanasit.chrono.ParsedDateComponent;
import com.wanasit.chrono.ParsedDateComponent.Components;
import com.wanasit.chrono.ParsedResult;
import com.wanasit.chrono.parser.ParserAbstract;
import com.wanasit.chrono.refiner.en.ENMergeDateRangeRefiner;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FRDayOfWeekDateFormatParser extends ParserAbstract {

    protected static String regPattern = "(?<=\\W|^)"
            + "(?:(ce)\\s*)?"
            +  "(" + FrenchConstants.daysOfWeek("|") +")"
            +  "(?:\\s*(?:\\,|\\)|\\）))?"
            + "(?:\\s*(dernier|prochain)\\s*)?"
            + "(?=\\W|$)";

    public FRDayOfWeekDateFormatParser() {
        this.refiners.add(new ENMergeDateRangeRefiner());
    }

    @Override
    protected Pattern pattern() { return Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE); }

    @Override
    protected ParsedResult extract(String text, Date refDate, Matcher matcher, ChronoOption option) {

        Calendar calendar = Calendar.getInstance(Locale.FRENCH);
        calendar.setTime(refDate);

        ParsedResult result = new ParsedResult(this, matcher.start(), matcher.group());

        int dayOfWeek = FrenchConstants.valueForDayOfWeek(matcher.group(2));
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        if (matcher.group(3) == null && (matcher.group(1) == null || matcher.group(1).toLowerCase().equals("ce"))) {

            if (Math.abs(dayOfWeek - 7 - today) < Math.abs(dayOfWeek - today)) {
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
            } else if (Math.abs(dayOfWeek + 7 - today) < Math.abs(dayOfWeek - today)) {
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
            }
        } else if (matcher.group(3) != null) {

            if (matcher.group(3).toLowerCase().equals("dernier")) {
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
            } else if (matcher.group(3).toLowerCase().equals("prochain")) {
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
            }
        }

        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        result.start = new ParsedDateComponent();
        result.start.imply(Components.Year, calendar.get(Calendar.YEAR));
        result.start.imply(Components.Month, calendar.get(Calendar.MONTH) + 1);
        result.start.imply(Components.DayOfMonth, calendar.get(Calendar.DAY_OF_MONTH));
        result.start.assign(Components.DayOfWeek, dayOfWeek);

        return result;
    }
}
