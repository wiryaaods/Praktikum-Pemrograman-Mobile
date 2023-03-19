package com.example.tugas3praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView output;
    String operator = "";
    int result = 0;
    String text = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output=findViewById(R.id.output);;
    }

    public void num (View view){
        if (output.getText().toString().equals("0")) {
            output.setText("");
        }
        String angka = output.getText().toString();
        switch(view.getId()) {
            case R.id.num_0:
                angka += "0";
                break;
            case R.id.num_1:
                angka += "1";
                break;
            case R.id.num_2:
                angka += "2";
                break;
            case R.id.num_3:
                angka += "3";
                break;
            case R.id.num_4:
                angka += "4";
                break;
            case R.id.num_5:
                angka += "5";
                break;
            case R.id.num_6:
                angka += "6";
                break;
            case R.id.num_7:
                angka += "7";
                break;
            case R.id.num_8:
                angka += "8";
                break;
            case R.id.num_9:
                angka += "9";
                break;
        }
        output.setText(angka);
    }

    public void opHitung(View view) {
        if (operator.isEmpty() == false) {
            return;
        }
        switch(view.getId()) {
            case R.id.tambah:
                operator = "+";
                break;
            case R.id.kurang:
                operator = "-";
                break;
            case R.id.kali:
                operator = "x";
                break;
            case R.id.bagi:
                operator = "/" ;
                break;
        }
        output.setText(output.getText().toString() + operator);
    }

    public void btnHapus (View view) {
        StringBuilder stringBuilder = new StringBuilder(output.getText());
        stringBuilder.deleteCharAt(output.getText().length() - 1);
        String del = stringBuilder.toString();

        if (del.equals("")) {
            text = "0";
            operator = "";
            output.setText(text);
        } else {
            output.setText(del);
        }
    }
    public void btnHasil (View view) {
        String[] data = output.getText().toString().split("[x\\+\\-\\/]");
        int num1 = Integer.parseInt(data[0]);
        int num2 = Integer.parseInt(data[1]);
        switch (operator) {
            case "+":
                result = num1 + num2;
                output.setText(String.valueOf(result));
                operator = "";
                break;
            case "-":
                result = num1 - num2;
                output.setText(String.valueOf(result));
                operator = "";
                break;
            case "x":
                result = num1 * num2;
                output.setText(String.valueOf(result));
                operator = "";
                break;
            case "/":
                result = num1 / num2;
                output.setText(String.valueOf(result));
                operator= "";
                break;
                }
        }
    public void btnAC(View view) {
        operator = "";
        output.setText("0");
    }
}