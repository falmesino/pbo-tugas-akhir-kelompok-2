/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dadu;

import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author falmesino
 */
public class CurrencyFormatter {
    /**
     * Formats an integer as Indonesian Rupiah (Rp).
     *
     * @param amount The integer amount to be formatted.
     * @return A string representing the amount in Indonesian Rupiah format.
     */
    
    public static String formatAsRupiah(int amount) {
        // Create a NumberFormat instance for currency
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        // Customize the currency symbol to "Rp"
        if (formatter instanceof java.text.DecimalFormat) {
            java.text.DecimalFormat decimalFormat = (java.text.DecimalFormat) formatter;
            decimalFormat.setNegativePrefix("-Rp"); // Handle negative numbers
            decimalFormat.setNegativeSuffix("");
            decimalFormat.setPositivePrefix("Rp");  // Handle positive numbers
            decimalFormat.setPositiveSuffix("");
        }

        // Format the integer as currency and replace "$" with "Rp"
        return formatter.format(amount).replace("$", "Rp");
    }
}
