package com.example.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WeightCalc extends AppCompatActivity {
    Button five_button, ten_button, confirm_button, female_button, male_button;
    EditText weight;
    String nums, sex, bodyfat;
    int lbs, body_per;
    private Context wContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        wContext = getApplicationContext();
        weight = (EditText) findViewById(R.id.weight);
        five_button = (Button)findViewById(R.id.five_per);
        ten_button = (Button) findViewById(R.id.ten_per);
        confirm_button = (Button) findViewById(R.id.confirm);
        female_button = (Button) findViewById(R.id.female);
        male_button = (Button) findViewById(R.id.male);


        five_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyfat = five_button.getText().toString().substring(0, 1);
                body_per = Integer.parseInt(bodyfat);
                System.out.println(body_per);
//                bodyfat = weight.getText().toString();
//                body_per = Integer.parseInt(nums.substring(0,1));
            }});
        ten_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyfat = ten_button.getText().toString().substring(0, 2);
                body_per = Integer.parseInt(bodyfat);
                System.out.println(body_per);
            }});
        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = male_button.getText().toString();
                System.out.println(sex);
            }});
        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = female_button.getText().toString();
                System.out.println(sex);
            }});
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nums = weight.getText().toString();
                if(nums.equals("")) {
                    lbs = 0;
                }
                else {
                    lbs = Integer.parseInt(nums);
                }
                System.out.println(lbs);
                System.out.println(body_per);
        }});

    }
}
