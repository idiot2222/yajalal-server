package me.bogeun.yajalal.util;

public class Calculator {

    public static String calAVG(int H, int AB) {
        double d = (double) H / AB;

        if(Double.isInfinite(d) || Double.isNaN(d)) {
            return "0.000";
        }

        return String.format("%.3f", d);
    }

    public static String calOBP(int H, int BB, int PA) {
        return calAVG(H + BB, PA);
    }

    public static String calSLG(int H, int H2, int H3, int HR, int AB) {
        int i = H + H2 * 2 + H3 * 3 + HR * 4;
        double d = (double) i / AB;

        if(Double.isInfinite(d) || Double.isNaN(d)) {
            return "0.000";
        }

        return String.format("%.3f", d);
    }
}
