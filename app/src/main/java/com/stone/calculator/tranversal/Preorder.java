package com.stone.calculator.tranversal;

import android.util.Log;

import com.stone.calculator.OperatorChecker;
import com.stone.calculator.SyntaxConverter;

import java.util.ArrayList;
import java.util.List;

public class Preorder {
    private static StringBuilder createStringBuilder() {
        return new StringBuilder();
    }

    private static <T> List<T> createArrayList(Class<T> className) {
        return new ArrayList<T>();
    }

    public static List<String> getData(String data) {
        String value = SyntaxConverter.convert(data);
        List<String> preorder = createArrayList(String.class);
        StringBuilder sb = createStringBuilder();
        for (int i = 0; i < value.length(); i++) {
            Character cur = value.charAt(i);
            if (OperatorChecker.isMatchedAnyOperators(cur)) {
                if (sb.length() > 0) {
                    preorder.add(sb.toString());
                    preorder.add(String.valueOf(cur));
                    sb.setLength(0);
                }else preorder.add(String.valueOf(cur));
            } else {
                sb.append(cur);
            }

        }
        if (sb.length() > 0) {
            preorder.add(sb.toString());
        }

        Log.i("Log preorder :", preorder.toString());
        return preorder;
    }
}
