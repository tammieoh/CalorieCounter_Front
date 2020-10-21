package com.example.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WeightCalc extends AppCompatActivity {
    Button five_button, ten_button, confirm_button;
    EditText weight;
    String nums;
    int lbs;
    private Context wContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        wContext = getApplicationContext();
        weight = (EditText) findViewById(R.id.weight);
        five_button = (Button)findViewById(R.id.five_per);
        ten_button = (Button) findViewById(R.id.ten_per);
        confirm_button = (Button) findViewById(R.id.confirm);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nums = weight.getText().toString();
                lbs = Integer.parseInt(nums);
        }});
    }
}
