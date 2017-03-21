package com.wanasit.chrono.parser.fr;

import com.wanasit.chrono.ChronoOption;
import com.wanasit.chrono.ParsedDateComponent;
import com.wanasit.chrono.ParsedDateComponent.Components;
import com.wanasit.chrono.ParsedResult;
import com.wanasit.chrono.parser.ParserAbstract;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FRCasualDateExpressionParser extends ParserAbstract {

    private static String regPattern = "(?<=\\W|^)"
            + "(maintenant|aujourd'hui|ajd|cette\\s*nuit|la\\s*veille|(demain|hier)(\\s*(matin|soir|aprem|après-midi))?|ce\\s*(matin|soir)|cet\\s*(après-midi|aprem))"
            + "(?=\\W|$)";

    @Override
    protected Pattern pattern() {
        return Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected ParsedResult extract(String text, Date refDate, Matcher matcher, ChronoOption option) {

        ParsedResult result = new ParsedResult(this, matcher.start(), matcher.group());

        Calendar calendar = Calendar.getInstance(Locale.FRENCH);
        calendar.setTime(refDate);
        result.start = new ParsedDateComponent();

        String firstMatch = matcher.group(1).toLowerCase();


        if(firstMatch.equals("demain")){
            calendar.add(Calendar.DATE, 1);
        }

        if(firstMatch.equals("hier")) {
            result.start.imply(Components.Hour, 0);
            if (calendar.get(Calendar.HOUR_OF_DAY) < 6) {
                calendar.add(Calendar.DATE, -1);
            }
        }

        if(firstMatch.equals("cette") || firstMatch.equals("ce") || firstMatch.equals("cet") || firstMatch.equals("le")) {
            String secondMatch = matcher.group(2).toLowerCase();
            if (secondMatch.equals("nuit")) {
                result.start.imply(Components.Hour, 0);
                if (calendar.get(Calendar.HOUR_OF_DAY) > 6) {
                    calendar.add(Calendar.DATE, 1);
                }
            } else if (secondMatch.equals("veille")) {
                result.start.imply(Components.Hour, 0);
                if (calendar.get(Calendar.HOUR_OF_DAY) < 6) {
                    calendar.add(Calendar.DATE, -1);
                }
            } else if (secondMatch.equals("après-midi") || secondMatch.equals("aprem")) {

                result.start.imply(Components.Hour, 15);

            }else if (secondMatch.equals("soir")) {

                result.start.imply(Components.Hour, 18);

            } else if (secondMatch.equals("matin")) {

                result.start.imply(Components.Hour, 6);
                if (calendar.get(Calendar.HOUR_OF_DAY) < 6) {
                    // When say "this morning" on before 6 AM
                    calendar.add(Calendar.DATE, -1);
                }
            }

        }

        result.start.assign(Components.Year, calendar.get(Calendar.YEAR));
        result.start.assign(Components.Month, calendar.get(Calendar.MONTH) + 1);
        result.start.assign(Components.DayOfMonth, calendar.get(Calendar.DATE));
        return result;
    }
}
