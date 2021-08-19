package com.stone.calculator;

public class OperatorChecker {

    public static Boolean isAddition(Character ch) {
        return Operator.ADDITION.equals(ch);
    }

    public static Boolean isSubtraction(Character ch) {
        return Operator.SUBTRACTION.equals(ch);
    }

    public static Boolean isMultiplication(Character ch) {
        return Operator.MULTIPLICATION.equals(ch);
    }
    public static Boolean isPercent(Character ch){
        return Operator.PERCENT.equals(ch);
    }

    public static Boolean isDivision(Character ch) {
        return Operator.DIVISION.equals(ch);
    }

    public static Boolean isPower(Character ch) {
        return Operator.POWER.equals(ch);
    }

    public static Boolean isOpenBracket(Character ch){return Operator.OPEN_BRACKET.equals(ch);}

    public static Boolean isCloseBracket(Character ch){
        return  Operator.CLOSE_BRACKET.equals(ch);
    }

    public static Boolean isAnyArithmeticOperator(Character ch) {
        return isAddition(ch) || isSubtraction(ch) || isMultiplication(ch) || isDivision(ch) || isPower(ch);
    }

    public static Boolean isMatchedAnyOperators(Character ch) {
        return isAnyArithmeticOperator(ch)||isAnyBrackets(ch);
    }

    public static boolean isAnyBrackets(Character ch) {
        return isOpenBracket(ch) || isCloseBracket(ch);
    }

    public static Boolean isPlusOrMinus(Character ch) {
        return isAddition(ch) || isSubtraction(ch);
    }

    public static Boolean isMultipleOrDivision(Character ch) {
        return isMultiplication(ch) || isDivision(ch);
    }
    public static Boolean isSamePriOptOptn(Character prev, Character cur) {
        if (isSamePriority(prev, cur)||isPreOptHighPriority(prev, cur)) {
            return true;
        }
        return false;
    }
    public static Boolean isPreOptHighPriority(Character prev, Character cur) {
        if (isMultipleOrDivision(prev)&&isPlusOrMinus(cur)) {
            return true;
        }
        if (isPower(prev)&&isPlusOrMinus(cur)) {
            return true;
        }
        if (isPower(prev)&&isMultipleOrDivision(cur)) {
            return true;
        }
        return false;
    }
    public static Boolean isSamePriority(Character prev, Character cur) {
        if (isPlusOrMinus(prev) && isPlusOrMinus(cur)) {
            return true;
        }
        if (isMultipleOrDivision(prev) && isMultipleOrDivision(cur)) {
            return true;
        }
        if (isPower(prev) && isPower(cur)){
            return true;
        }
        return false;
    }
    public static Boolean isPreLowAndCurHighPriority(Character prev,Character cur){
        if (isPlusOrMinus(prev) && isMultipleOrDivision(cur)){
            return true;
        }
        if (isPlusOrMinus(prev) && isPower(cur)){
            return true;
        }
        return isMultipleOrDivision(prev) && isPower(cur);
    }
    public static Boolean isPreOpnBktAndCurArith(Character prev, Character cur) {
        return isOpenBracket(prev)&&isAnyArithmeticOperator(cur);
    }

    public static Boolean isPreClsBktAndCurArith(Character prev, Character cur) {
        return isCloseBracket(prev)&&isAnyArithmeticOperator(cur);
    }

}
