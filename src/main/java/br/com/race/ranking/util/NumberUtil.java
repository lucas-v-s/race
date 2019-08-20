package br.com.race.ranking.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtil {

    private NumberUtil() {
    }

    public static Float formatNumberFloat(String number) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number numberFormated = format.parse(number);
        return numberFormated.floatValue();
    }
}
