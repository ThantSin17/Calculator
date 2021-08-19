package com.stone.calculator.utils;

public class CharUtils {
    public static Boolean isWholeNumber(Character ch) {
        return ch >= 48 && ch <= 57;
    }

    public static Boolean isDecimalPoint(Character ch) {
        return ch == 46;
    }

    public static Boolean isAnyNumeric(Character ch) {
        return isWholeNumber(ch) || isDecimalPoint(ch);
    }
}
