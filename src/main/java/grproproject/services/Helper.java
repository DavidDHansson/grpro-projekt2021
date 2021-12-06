package grproproject.services;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

public class Helper {
    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            System.out.println("🚨 WARNING: Tried to parse: " +  value + " to int 🚨");
            return -1;
        }
    }

    public static double tryParseToDouble(String value) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(value.trim());
            return number.doubleValue();
        } catch (ParseException e) {
            System.out.println("🚨 WARNING: Tried to parse: " +  value + " to double 🚨");
            return -1;
        }
    }

    public static HashMap<Integer, Integer> convertSeasonsStringToHashMap(String value) {
        try {
            HashMap<Integer, Integer> seasons = new HashMap<>();
            String[] seasonsArr = value.trim().split(",");

            for(String i : seasonsArr) {
                String[] s = i.trim().split("-");
                int season = tryParseInt(s[0]);
                int episodes = tryParseInt(s[1]);
                seasons.put(season, episodes);
            }

            return seasons;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("🚨 WARNING: Index out of bounds on value: " + value + " when converting to seasons hashmap 🚨");
        } catch (Exception e) {
            System.out.println("🚨 WARNING: Tried converting: " + value + " to seasons hashmap 🚨");
        }

        return new HashMap<Integer, Integer>();
    }

}
