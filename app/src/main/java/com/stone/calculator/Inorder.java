package com.stone.calculator;

import android.util.Log;

import com.stone.calculator.tranversal.Preorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Inorder {
    public static List<String> getData(String data) {

        List<String> preorder = Preorder.getData(data);
        List<String> inorder = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (int i=0;i<preorder.size();i++){
            String out=preorder.get(i);
            Character cur=out.charAt(0);

            boolean matchOperatorAndStackNotEmpty=false;

            if (!OperatorChecker.isMatchedAnyOperators(cur)){
                inorder.add(out);
            }else if (OperatorChecker.isMatchedAnyOperators(cur)){
                if (stack.isEmpty()){
                    stack.push(cur);
                }else {
                    matchOperatorAndStackNotEmpty=true;
                }
            }

            if (matchOperatorAndStackNotEmpty){

                Character prev=stack.peek();
                if (OperatorChecker.isAnyBrackets(cur)){
                    stack.push(cur);
                }
                if (OperatorChecker.isPreOpnBktAndCurArith(prev,cur)){
                    stack.push(cur);
                }
                if (OperatorChecker.isPreClsBktAndCurArith(prev,cur)){

                }
                if (OperatorChecker.isCloseBracket(cur)){
                    stack.pop();
                    while (!OperatorChecker.isOpenBracket(stack.peek())){
                        inorder.add(String.valueOf(stack.pop()));
                    }
                    stack.pop();
                }
                if (OperatorChecker.isPreLowAndCurHighPriority(prev,cur)){
                    stack.push(cur);
                }
                if (OperatorChecker.isSamePriOptOptn(prev,cur)){
                    inorder.add(String.valueOf(stack.pop()));
                    stack.push(cur);
                }
            }
        }
        while (!stack.isEmpty()){
            Character ch=stack.pop();
            if (!OperatorChecker.isAnyBrackets(ch)){
                inorder.add(String.valueOf(ch));
            }
        }
        Log.i("Log inorder :",inorder.toString());
        return inorder;
    }
}
