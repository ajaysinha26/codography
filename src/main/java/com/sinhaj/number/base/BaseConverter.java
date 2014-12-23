package com.sinhaj.number.base;

import java.util.Stack;

/**
 * Created by ajaysinha on 22/12/14.
 */
public class BaseConverter {

    public static void main(String[] args) {
        System.out.println(new BaseConverter().convert("1010", 2, 11));
    }

    public String convert(String number, int fromBase, int toBase) {
        int base10Number = convertToBase10(number, fromBase);
        return covertFromBase10(base10Number, toBase);
    }

    public int convertToBase10(String number, int fromBase) {
        int numberValue = 0;
        int numLength = number.length();
        int power = numLength - 1;
        for(int i = 0; i <= numLength - 1; i++) {
            int ithNumber = Character.getNumericValue(number.charAt(i));
            if(ithNumber >= fromBase)
                throw new IllegalArgumentException("Numeric value " + ithNumber + " is >= base " + fromBase);
            numberValue += ithNumber * Math.pow(fromBase, power);
            power--;
        }
        return numberValue;
    }

    public String covertFromBase10(int number, int toBase) {
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(getChar(number % toBase));
            number /= toBase;
        }while (number != 0);

        return sb.reverse().toString();
    }

    private char getChar(int i) {
        if(i >= 0 && i < 10) {
            return (char) ('0' + i);
        }
        switch (i) {
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
        }
        throw new IllegalArgumentException();
    }
}
