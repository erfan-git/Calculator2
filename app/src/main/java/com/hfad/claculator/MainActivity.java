package com.hfad.claculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    private Button mButtonDelete;
    private Button mButtonDot;
    private Button mButtonZero;
    private Button mButtonEqual;
    private Button mButtonPlus;
    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;
    private Button mButtonMinus;
    private Button mButtonFour;
    private Button mButtonFive;
    private Button mButtonSix;
    private Button mButtonMultiple;
    private Button mButtonSeven;
    private Button mButtonEight;
    private Button mButtonNine;
    private Button mButtonDivide;
    private Button mButtonClear;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        setClickListeners();

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String input = ((Button) v).getText().toString();
            String current = mTextViewResult.getText().toString();
            mTextViewResult.setText(current + input);

        }
    };

    private void setClickListeners() {
        mButtonOne.setOnClickListener(buttonClickListener);
        mButtonTwo.setOnClickListener(buttonClickListener);
        mButtonThree.setOnClickListener(buttonClickListener);
        mButtonFour.setOnClickListener(buttonClickListener);
        mButtonFive.setOnClickListener(buttonClickListener);
        mButtonSix.setOnClickListener(buttonClickListener);
        mButtonSeven.setOnClickListener(buttonClickListener);
        mButtonEight.setOnClickListener(buttonClickListener);
        mButtonNine.setOnClickListener(buttonClickListener);
        mButtonZero.setOnClickListener(buttonClickListener);
        mButtonDot.setOnClickListener(buttonClickListener);
        mButtonPlus.setOnClickListener(buttonClickListener);
        mButtonMinus.setOnClickListener(buttonClickListener);
        mButtonMultiple.setOnClickListener(buttonClickListener);
        mButtonDivide.setOnClickListener(buttonClickListener);
        mButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewResult.setText("");
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current = mTextViewResult.getText().toString();
                if (current.length() > 1)
                    current = current.substring(0, current.length() - 1);
                else
                    current = "";
                mTextViewResult.setText(current);
            }
        });
        mButtonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current = mTextViewResult.getText().toString();
                execute(current + "+1");
            }
        });
    }

    private String execute(String current) {
        if (current == null || current.equals(""))
            return null;
        if (!current.contains("+") && !current.contains("*") && !current.contains("-") && !current.contains("/") && !current.contains("="))
            return current;
        return execute(operate(current));
    }

    private String operate(String current) {
        String numberOneString = "";
        String numberTwoString = "";
        String result = "";
        char operation;
        for (int i = 0; i < current.length() - 1; i++) {
            if ((current.charAt(i) > 47 && current.charAt(i) < 58) || current.charAt(i) == 46) {
                numberOneString += current.charAt(i);
            } else {
                operation = current.charAt(i);
                for (int j = i + 1; j < current.length() - 1; j++) {
                    if ((current.charAt(j) > 47 && current.charAt(j) < 58) || current.charAt(j) == 46) {
                        numberTwoString += current.charAt(j);
                    } else {
                        if (j == i + 1) {
                            Toast.makeText(MainActivity.this, "This Operation is not valid!", Toast.LENGTH_LONG).show();
                            return null;
                        } else {
                            BigDecimal numberOne = BigDecimal.valueOf(Double.parseDouble(numberOneString));
                            BigDecimal numberTwo = BigDecimal.valueOf(Double.parseDouble(numberTwoString));
                            switch (operation) {
                                case '+':
                                    result = String.valueOf(numberOne.add(numberTwo));
                                    break;
                                case '-':
                                    result = String.valueOf(numberOne.subtract(numberTwo));
                                    break;
                                case '*':
                                    result = String.valueOf(numberOne.multiply(numberTwo));
                                    break;
                                case '^':
                                    result = String.valueOf(numberOne.pow((numberTwo.intValue())));
                                    break;
                                case '/':
                                    if (numberTwo.compareTo(BigDecimal.ZERO)==0) {
                                        Toast.makeText(MainActivity.this, "This Operation is not valid!", Toast.LENGTH_LONG).show();
                                        return null;
                                    } else
                                        //the result print with 9 digit after .
                                        result = String.valueOf(numberOne.divide(numberTwo, 9, RoundingMode.HALF_UP));
                                    break;
                                default:
                                    ;
                            }
                            mTextViewResult.setText(result);
                            return result + current.substring(j);
                        }
                    }
                }
            }
        }
        return result;
    }

    private void findAllViews() {
        mButtonZero = findViewById(R.id.button_zero);
        mButtonOne = findViewById(R.id.button_one);
        mButtonTwo = findViewById(R.id.button_two);
        mButtonThree = findViewById(R.id.button_three);
        mButtonFour = findViewById(R.id.button_four);
        mButtonFive = findViewById(R.id.button_five);
        mButtonSix = findViewById(R.id.button_six);
        mButtonSeven = findViewById(R.id.button_seven);
        mButtonEight = findViewById(R.id.button_eight);
        mButtonNine = findViewById(R.id.button_nine);
        mButtonDot = findViewById(R.id.button_dot);
        mButtonPlus = findViewById(R.id.button_plus);
        mButtonMinus = findViewById(R.id.button_minus);
        mButtonMultiple = findViewById(R.id.button_multiple);
        mButtonDivide = findViewById(R.id.button_divide);
        mButtonDelete = findViewById(R.id.button_delete);
        mButtonClear = findViewById(R.id.button_clear);
        mButtonEqual = findViewById(R.id.button_equal);
        mTextViewResult = findViewById(R.id.textView_result);

    }
}
