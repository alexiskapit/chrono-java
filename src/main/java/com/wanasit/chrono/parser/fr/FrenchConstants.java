package com.wanasit.chrono.parser.fr;

import java.util.HashMap;
import java.util.Map;

public class FrenchConstants {
    
    private static Map<String, Integer> DAY_OF_WEEK;
    private static Map<String, Integer> MONTHS;
    private static Map<String, Integer> NUMBERS;

    public static int valueForDayOfWeek(String dayOfWeek) {
	return DAY_OF_WEEK.get(dayOfWeek.toLowerCase());
    }
    
    public static int valueForMonth(String month) {
	return MONTHS.get(month.toLowerCase()) - 1;
    }

    public static int valueForNumber(String number) {
        if (!NUMBERS.containsKey(number)) {
            return -1;
        }
        return NUMBERS.get(number);
    }

    public static String daysOfWeek(String sep) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String val : DAY_OF_WEEK.keySet()) {
            if (i > 0)
                builder.append(sep);
            builder.append(val);
            i++;
        }
        return builder.toString();
    }

    static {
        MONTHS = new HashMap<String, Integer>();
        MONTHS.put("janvier", 1);
        MONTHS.put("jan", 1);
        MONTHS.put("jan.", 1);
        MONTHS.put("février", 2);
        MONTHS.put("fevrier", 2);
        MONTHS.put("fév", 2);
        MONTHS.put("fév.", 2);
        MONTHS.put("fev", 2);
        MONTHS.put("fev.", 2);
        MONTHS.put("mars", 3);
        MONTHS.put("mar", 3);
        MONTHS.put("mar.", 3);
        MONTHS.put("avril", 4);
        MONTHS.put("avr", 4);
        MONTHS.put("avr.", 4);
        MONTHS.put("mai", 5);
        MONTHS.put("juin", 6);
        MONTHS.put("jun", 6);
        MONTHS.put("jun.", 6);
        MONTHS.put("juillet", 7);
        MONTHS.put("jul", 7);
        MONTHS.put("jul.", 7);
        MONTHS.put("août", 8);
        MONTHS.put("aout", 8);
        MONTHS.put("aou.", 8);
        MONTHS.put("aou", 8);
        MONTHS.put("septembre", 9);
        MONTHS.put("sep", 9);
        MONTHS.put("sep.", 9);
        MONTHS.put("sept", 9);
        MONTHS.put("sept.", 9);
        MONTHS.put("octobre", 10);
        MONTHS.put("oct", 10);
        MONTHS.put("oct.", 10);
        MONTHS.put("novembre", 11);
        MONTHS.put("nov", 11);
        MONTHS.put("nov.", 11);
        MONTHS.put("décembre", 12);
        MONTHS.put("déc", 12);
        MONTHS.put("déc.", 12);
        MONTHS.put("decembre", 12);
        MONTHS.put("dec", 12);
        MONTHS.put("dec.", 12);

        DAY_OF_WEEK = new HashMap<String, Integer>();
        DAY_OF_WEEK.put("dimanche", 1);
        DAY_OF_WEEK.put("dim", 1);
        DAY_OF_WEEK.put("lundi", 2);
        DAY_OF_WEEK.put("lun", 2);
        DAY_OF_WEEK.put("mardi", 3);
        DAY_OF_WEEK.put("mar", 3);
        DAY_OF_WEEK.put("mercredi", 4);
        DAY_OF_WEEK.put("mer", 4);
        DAY_OF_WEEK.put("jeudi", 5);
        DAY_OF_WEEK.put("jeu", 5);
        DAY_OF_WEEK.put("vendredi", 6);
        DAY_OF_WEEK.put("ven", 6);
        DAY_OF_WEEK.put("samedi", 7);
        DAY_OF_WEEK.put("sam", 7);


        NUMBERS = new HashMap<String, Integer>();
        NUMBERS.put("un", 1);
        NUMBERS.put("deux", 2);
        NUMBERS.put("trois", 3);
        NUMBERS.put("quatre", 4);
        NUMBERS.put("cinq", 5);
        NUMBERS.put("six", 6);
        NUMBERS.put("sept", 7);
        NUMBERS.put("huit", 8);
        NUMBERS.put("neuf", 9);
        NUMBERS.put("dix", 10);
        NUMBERS.put("onze", 11);
        NUMBERS.put("douze", 12);
        NUMBERS.put("treize", 13);
        NUMBERS.put("quatorze", 14);
        NUMBERS.put("quinze", 15);
        NUMBERS.put("seize", 16);
        NUMBERS.put("dix-sept", 17);
        NUMBERS.put("dix-huit", 18);
        NUMBERS.put("dix-neuf", 19);
        NUMBERS.put("vingt", 20);
    }
}
