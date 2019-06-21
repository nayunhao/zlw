package com.zlead.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class ScaleUtils {
    
    public static double scaleDouble(double input) {
        return BigDecimal.valueOf(input).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static String textScale(double number, String pattern, String symbol) {
        if (StringUtils.isBlank(pattern))
            return null;
        String rtn = new DecimalFormat(pattern).format(number);
        if (StringUtils.isNotBlank(symbol))
            rtn = symbol + rtn;
        return rtn;
    }
    
    public static String percentScale(double number, Integer multiple, String pattern, String symbol) {
        if (StringUtils.isBlank(pattern))
            return null;
        Integer multi = multiple;
        if (multi == null || multi <= 0)
            multi = 1;
        double value = number * multi;
        String rtn = new DecimalFormat(pattern).format(value);
        if (StringUtils.isNotBlank(symbol))
            rtn += symbol;
        return rtn;
    }
    
    public static void main(String[] args) {
        double test = 88888888.6666;
        System.out.println(textScale(test, "#,##0.00", "￥"));
        System.out.println(percentScale(test, 100, "#,##0", "%"));
    }
    
}
