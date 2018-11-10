package com.codingtest.deeplinktest.codingtest.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * @param amount      Currency amount.
     * @param minFraction Number of Min Fraction
     * @param maxFraction Number of Max Fraction
     * @return This Method always return amount in original format
     * 1,000,000  =  $1,000,000
     * 1,000  =  $1,000
     */
    public static String formatCurrency(BigDecimal amount, int minFraction, int maxFraction) {
        if (amount != null) {
            currencyFormat.setMinimumFractionDigits(minFraction);
            currencyFormat.setMaximumFractionDigits(maxFraction);
            currencyFormat.setRoundingMode(RoundingMode.DOWN);

            return currencyFormat.format(amount);
        }
        return null;
    }
}
