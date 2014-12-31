package com.sinhaj.number;

import java.util.Arrays;

/**
 * Created by ajaysinha on 31/12/14.
 */
public class BigInteger {
    private char[] digits;
    public boolean positive;

    public BigInteger(int size) {
        digits = new char[size];
        for(int i = 0; i < size; i++) {
            digits[i] = '0';
        }
    }

    public BigInteger(String number) {
        positive = !(number.charAt(0) == '-');
        int length = number.charAt(0) == '-' ? number.length() - 1 : number.length();
        digits = new char[length];
        for(int i = number.length() - 1, j = 0; j < length; i--, j++) {
            digits[j] = number.charAt(i);
        }
    }

    public BigInteger multiple(BigInteger that) {
        BigInteger result = new BigInteger(this.digits.length + that.digits.length);
        result.positive = this.positive && that.positive;
        int carry = 0;
        for(int i = 0; i < this.digits.length; i++) {
            for(int j = 0; j < that.digits.length || carry != 0; j++) {
                int current = (result.digits[i + j] - '0') + carry;
                if(j < that.digits.length) {
                    current += ((this.digits[i] - '0') * (that.digits[j] - '0'));
                }
                char c = Character.forDigit(current % 10, 10);
                result.digits[i + j] = c;
                carry = current / 10;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!positive) {
            sb.append("-");
        }
        for(int i = digits.length - 1; i >= 0; i--) {
            sb.append(digits[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BigInteger("7854723").multiple(new BigInteger("5214")));
    }
}
