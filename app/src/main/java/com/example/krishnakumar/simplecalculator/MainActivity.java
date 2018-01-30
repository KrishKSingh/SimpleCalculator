package com.example.krishnakumar.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView _screen;
    private String display="";
    private String currentOperator="";
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = findViewById(R.id.txtView);
        _screen.setText(display);
    }

    private void updateScreen()
    {
        _screen.setText(display);
    }

    private boolean isOperator(char op)
    {
        switch(op)
        {
            case '+':return true;
            case '-':return true;
            case 'X':return true;
            case '/':return true;
            default:return false;
        }
    }
    public void onClickNumber(View v)
    {

        Button b = (Button)v;
        display+=b.getText();
        updateScreen();
    }

    public void onClickOperator(View v)
    {
        if(display=="")return;
        Button b = (Button)v;
        if(result!="")
        {
            String _display=result;
            clear();
            display=_display;


        }
        if(currentOperator!="")
        {
            Log.d("CalcX",""+display.charAt(display.length()-1));
            if(isOperator(display.charAt(display.length()-1)))
            {
                display = display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
                updateScreen();
                return;
            }else {
                getResult();
                display=result;
                result="";
            }
            currentOperator = b.getText().toString();
        }

        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private void clear()
    {
        display="";
        currentOperator="";
        result="";
    }
    public void onClickClear(View v)
    {
        clear();
        updateScreen();
    }
    private double operate(String num1,String num2,String op)
    {

        switch(op)
        {
            case "+": return Double.valueOf(num1)+Double.valueOf(num2);

            case "-": return Double.valueOf(num1)-Double.valueOf(num2);

            case "X": return Double.valueOf(num1)*Double.valueOf(num2);

            case "/": try {
                    return Double.valueOf(num1)/Double.valueOf(num2);
                }
                catch(Exception e){

                    Log.e("Calc",e.getMessage());
                }
            default: return -1;
        }
    }
    private boolean getResult()
    {
        if(currentOperator=="")return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length<2)return false;
        result = String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }
    public void onClickEqual(View v)
    {
        if(display=="")return;
        if(!getResult())return;
        _screen.setText(display + "\n" + String.valueOf(result));
    }

}
