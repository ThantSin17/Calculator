package com.stone.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.stone.calculator.utils.CharUtils;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private int openBracketCount = 0;
    private int closeBracketCount = 0;
    private String tempValue = "";
    private TextView input;
    private TextView result;
    private final Stack<String> stack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input_text);
        result = findViewById(R.id.edit_ans);
    }

    private String stackOperation() {
        StringBuilder sb = new StringBuilder();
        for (String out : stack) {
            sb.append(out);
        }
        return sb.toString();
    }
    private String percent(){
        tempValue="";
        int stackNumber=0;
        for (String out:stack){
            Character cur=out.charAt(0);
            if (!OperatorChecker.isAnyArithmeticOperator(cur)){
                stackNumber++;
            }else {
                break;
            }
        }
        for (int i=0;i<stackNumber;i++){
            tempValue+=stack.pop();
        }
        return tempValue;
    }


    public void onClickNumber(View view) {
        Character prev = '!';
        String previous;
        if (!stack.isEmpty()) {
            previous = stack.peek();
            prev = previous.charAt(0);
        }


        Button btn = (Button) view;
        String value = btn.getText().toString();
        Character cur = value.charAt(0);
        if (CharUtils.isAnyNumeric(cur)){
            tempValue+=cur.toString();
        }else if (OperatorChecker.isPercent(cur)){
            String data=Calculator.calculatePercent(percent());
            stack.push(data);
            input.setText(stackOperation());
            return;
//            result.setText(stackOperation());
//            Toast.makeText(this, tempValue+" -> "+data, Toast.LENGTH_SHORT).show();
//            percent();
//            stack.push(data);
//            input.setText(stackOperation());
//            return;
        }else {
            tempValue="";
        }
        if (OperatorChecker.isOpenBracket(cur)) {
            openBracketCount++;
            Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
        } else if (OperatorChecker.isCloseBracket(cur)) {
            closeBracketCount++;
        }
        if (stack.isEmpty() && !OperatorChecker.isSubtraction(cur) && OperatorChecker.isAnyArithmeticOperator(cur)) {
            stack.push("0");
        }
        if (OperatorChecker.isSamePriority(prev, cur)) {
            stack.pop();
        }
        if (OperatorChecker.isMultipleOrDivision(prev) && OperatorChecker.isPlusOrMinus(cur)) {
            stack.push("(");
            openBracketCount++;
        }
        if (OperatorChecker.isPlusOrMinus(prev) && OperatorChecker.isMultipleOrDivision(cur)) {
            stack.pop();

        }
        if (CharUtils.isAnyNumeric(prev) && OperatorChecker.isOpenBracket(cur)) {
            stack.add("×");
        }

        stack.add(value);
        Toast.makeText(this, stack.peek(), Toast.LENGTH_SHORT).show();
        input.setText(stackOperation());

    }

    private void fillCloseBracket() {
        if (openBracketCount > closeBracketCount && openBracketCount != 0) {
            while ((openBracketCount - closeBracketCount) != 0) {
                stack.add(")");
                openBracketCount--;
            }
        }
    }

    public void onClickOperator(View view) {
        onClickNumber(view);
    }

    public void onClickEqualBtn(View view) {
        if (stack.isEmpty()) return;
        fillCloseBracket();
        String data=Calculator.calculate(stackOperation());
        //String syntax = SyntaxConverter.convert(stackOperation());
//        Log.i("Log before calculate :", stackOperation());
//        Log.i("Log Syntax converter :", syntax);

        input.setText(stackOperation());
        result.setText(data);

    }

    public void onClickACBtn(View view) {
        stack.clear();
        tempValue="";
        input.setText("");

    }

    public void onClickDeleteBtn(View view) {
        if (!stack.isEmpty()) {
            String ch=stack.pop();
            if (OperatorChecker.isOpenBracket(ch.charAt(0))){
                openBracketCount--;
            }else if (OperatorChecker.isCloseBracket(ch.charAt(0))){
                closeBracketCount--;
            }
            input.setText(stackOperation());
        }
    }

}