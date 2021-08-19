package com.stone.calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public static String calculate(String data){
        List<String > inorder=Inorder.getData(data);
        Stack<String> stack=new Stack<>();

        for(int i=0;i<inorder.size();i++){
            String value=inorder.get(i);
            Character operator=value.charAt(0);
            if (!OperatorChecker.isAnyArithmeticOperator(operator)){
                stack.push(value);
            }else {
                Double num2=Double.parseDouble(stack.pop());
                Double num1=Double.parseDouble(stack.pop());
                String result=bdoms(num1,num2,operator);
                stack.push(result);
            }
        }
        return stack.pop();
    }
    private static String bdoms(Double num1,Double num2,Character operator){
        double result= 0;
        if (OperatorChecker.isAddition(operator)){
            result = num1+num2;
        }else if (OperatorChecker.isSubtraction(operator)){
            result = num1-num2;
        }else if (OperatorChecker.isMultiplication(operator)){
            result = num1*num2;
        }else if (OperatorChecker.isDivision(operator)){
            result = num1/num2;
        }else if (OperatorChecker.isPower(operator)){
            result = Math.pow(num1,num2);
        }
        return Double.toString(result);

    }
    public static String calculatePercent(String value){
        double num=Double.parseDouble(value);
        num= num / 100.0;
        if (String.valueOf(num).contains("E")) {
            BigDecimal bd = new BigDecimal(num);
            return BigDecimal.valueOf(num).toPlainString();
        }else return String.valueOf(num);
    }

}
