package com.company;

public class Factoriser {

    /**
     * Returns the prime factors of a given number in String format
     *
     * @param number - the number for which we need to get the prime factors
     */

    public static String factorisation(int number) {

        String result = "";

        while (number % 2 == 0) {
            number /= 2;
            if (number == 1) {
                result += "2";
            } else {
                result += "2*";
            }

        }

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                number /= i;
                if (number == 1) {
                    result += i;
                } else {
                    result += (i + "*");
                }
            }
        }

        if (number > 2) {
            result += (number);
        }
        return result;
    }
}
