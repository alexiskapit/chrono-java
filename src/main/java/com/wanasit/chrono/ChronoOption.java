package com.wanasit.chrono;

import com.wanasit.chrono.filter.LowProbabilityFormatFilter;
import com.wanasit.chrono.filter.PrefixCheckFilter;
import com.wanasit.chrono.parser.Parser;
import com.wanasit.chrono.parser.en.ENCasualDateExpressionParser;
import com.wanasit.chrono.parser.en.ENDayOfWeekDateFormatParser;
import com.wanasit.chrono.parser.en.ENInternationalStandardParser;
import com.wanasit.chrono.parser.en.ENMonthNameLittleEndianParser;
import com.wanasit.chrono.parser.en.ENMonthNameMiddleEndianParser;
import com.wanasit.chrono.parser.en.ENSlashBigEndianDateFormatParser;
import com.wanasit.chrono.parser.en.ENSlashDateFormatParser;
import com.wanasit.chrono.parser.en.ENTimeAgoFormatParser;
import com.wanasit.chrono.parser.en.ENTimeDeadlineFormatParser;
import com.wanasit.chrono.parser.en.ENTimeExpressionParser;
import com.wanasit.chrono.parser.en.ENWeekExpressionParser;
import com.wanasit.chrono.parser.fr.FRCasualDateExpressionParser;
import com.wanasit.chrono.parser.fr.FRDayOfWeekDateFormatParser;
import com.wanasit.chrono.parser.jp.JPCasualDateExpressionParser;
import com.wanasit.chrono.parser.jp.JPDateAgoFormatParser;
import com.wanasit.chrono.parser.jp.JPDayOfWeekDateFormatParser;
import com.wanasit.chrono.parser.jp.JPStandartDateFormatParser;
import com.wanasit.chrono.parser.jp.JPTimeExpressionParser;
import com.wanasit.chrono.refiner.ExtractConcordanceRefiner;
import com.wanasit.chrono.refiner.ExtractTimezoneRefiner;
import com.wanasit.chrono.refiner.Refiner;
import com.wanasit.chrono.refiner.en.ENMergeDateAndTimeRefiner;
import com.wanasit.chrono.refiner.en.ENMergeDateRangeRefiner;
import com.wanasit.chrono.refiner.en.ENMergeWeekdayRefiner;
import com.wanasit.chrono.refiner.en.ENRemoveOverlapRefiner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChronoOption {

    public static final ChronoOption sharedOptions = standardOptions();

    public final List<Parser> parsers = new ArrayList<Parser>();
    public final List<Refiner> refiners = new ArrayList<Refiner>();

    public final Map<String, Integer> timezoneMap = new HashMap<String, Integer>();
    public Integer timezoneOffset = null;

    private ChronoOption() {
        this.timezoneOffset = Calendar.getInstance().getTimeZone().getOffset(0);
    }

    public static ChronoOption standardOptions() {
        ChronoOption options = new ChronoOption();

        // All Parsers
        options.parsers.add(new ENInternationalStandardParser());
        options.parsers.add(new ENMonthNameLittleEndianParser());
        options.parsers.add(new ENMonthNameMiddleEndianParser());
        options.parsers.add(new ENSlashBigEndianDateFormatParser());
        options.parsers.add(new ENSlashDateFormatParser());
        options.parsers.add(new ENTimeDeadlineFormatParser());
        options.parsers.add(new ENTimeAgoFormatParser());
        options.parsers.add(new ENTimeExpressionParser());
        options.parsers.add(new JPStandartDateFormatParser());
        options.parsers.add(new JPDayOfWeekDateFormatParser());
        options.parsers.add(new JPDateAgoFormatParser());
        options.parsers.add(new JPTimeExpressionParser());

        // Standard Pipeline
        options.refiners.add(new PrefixCheckFilter());
        options.refiners.add(new ENRemoveOverlapRefiner());
        options.refiners.add(new ENMergeDateAndTimeRefiner());
        options.refiners.add(new ENMergeDateRangeRefiner());
        options.refiners.add(new LowProbabilityFormatFilter());
        options.refiners.add(new ExtractTimezoneRefiner());
        options.refiners.add(new ExtractConcordanceRefiner());

        return options;
    }

    public static ChronoOption casualOptions() {

        ChronoOption options = ChronoOption.standardOptions();
        options.parsers.add(new ENCasualDateExpressionParser());
        options.parsers.add(new ENDayOfWeekDateFormatParser());
        options.parsers.add(new ENWeekExpressionParser());
        options.parsers.add(new FRCasualDateExpressionParser());
        options.parsers.add(new FRDayOfWeekDateFormatParser());
        options.parsers.add(new JPCasualDateExpressionParser());

        options.refiners.add(2, new ENMergeWeekdayRefiner());

        return options;
    }


}
