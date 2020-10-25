package com.example.login;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WeightCalc extends AppCompatActivity {
    Button five_button, ten_button, confirm_button, female_button, male_button;
    EditText weight, age;
    String weight_nums, sex, bodyfat, activity_level, age_nums;

    int lbs, body_per;
    private Context wContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        wContext = getApplicationContext();
        weight = (EditText) findViewById(R.id.weight);
//        five_button = (Button)findViewById(R.id.five_per);
//        ten_button = (Button) findViewById(R.id.ten_per);
        confirm_button = (Button) findViewById(R.id.confirm);
        female_button = (Button) findViewById(R.id.female);
        male_button = (Button) findViewById(R.id.male);
        final Spinner spinner = (Spinner) findViewById(R.id.activity_level);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_level, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

//        five_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                five_button.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x000000FF));
//                bodyfat = five_button.getText().toString().substring(0, 1);
//                body_per = Integer.parseInt(bodyfat);
//                System.out.println(body_per);
////                bodyfat = weight.getText().toString();
////                body_per = Integer.parseInt(nums.substring(0,1));
//            }});
//        ten_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ten_button.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x000000FF));
//                bodyfat = ten_button.getText().toString().substring(0, 2);
//                body_per = Integer.parseInt(bodyfat);
//                System.out.println(body_per);
//            }});
        male_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                male_button.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, Color.CYAN));
                sex = male_button.getText().toString();
                System.out.println(sex);
            }});
        female_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                female_button.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, Color.CYAN));
                sex = female_button.getText().toString();
                System.out.println(sex);
            }});
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                view.get
//                view.setBackgroundColor(Color.BLUE);
                activity_level = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                activity_level = "";
            }
            });
        confirm_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_nums = weight.getText().toString();
                age_nums = age.getText().toString();
                if(weight_nums.equals("")) {
                    lbs = 0;
                }
                else {
                    lbs = Integer.parseInt(weight_nums);
                }
                if(sex.equals("")) {
                    String text = "Need to select sex";
                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                if(age_nums.equals("")) {
                    String text = "Need to input age";
                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                System.out.println(lbs);
                System.out.println(body_per);
                System.out.println(activity_level);
        }});

    }
}
