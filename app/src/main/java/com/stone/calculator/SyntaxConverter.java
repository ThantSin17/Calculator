package com.stone.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.stone.calculator.utils.CharUtils;

public class SyntaxConverter {
    public static String convert(String data) {
        Character prev = null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {

            Character cur = data.charAt(i);


            if (OperatorChecker.isSubtraction(cur)) {
                if (i == 0) {
                    sb.append('0');
                }
            }
            try {
                prev = data.charAt(i - 1);
            } catch (Exception e) {

                sb.append(cur);
                e.printStackTrace();
                continue;
            }

            if (OperatorChecker.isOpenBracket(prev) && OperatorChecker.isPlusOrMinus(cur)) {
                sb.append('0');
            }


            if (CharUtils.isAnyNumeric(prev) && OperatorChecker.isOpenBracket(cur)) {
                sb.append(Operator.MULTIPLICATION);
            }
            if (OperatorChecker.isCloseBracket(prev) && CharUtils.isAnyNumeric(cur)) {
                sb.append(Operator.MULTIPLICATION);
            }
            if (OperatorChecker.isCloseBracket(prev) && OperatorChecker.isOpenBracket(cur)) {
                sb.append(Operator.MULTIPLICATION);
            }
            sb.append(cur);

        }

        Log.i("Log Syntax Converter :",sb.toString());
        return sb.toString();
    }
}
