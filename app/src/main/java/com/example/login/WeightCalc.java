package com.example.login;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class WeightCalc extends AppCompatActivity {
    Button five_button, ten_button, confirm_button, female_button, male_button;
    EditText weight, age;
    String username, weight_nums, bodyfat, activity_level, feet, inches;
    String sex = "";
    double bmr;
    boolean sexConfirm = false, ageConfirm = false, weightConfirm = false, heightConfirm = true, activityConfirm = true;
    int lbs, body_per, age_int, weight_int, feet_int, inches_int, height, recommend;
    private Context wContext;

    // code for when the page renders
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        wContext = getApplicationContext();
        username = getIntent().getStringExtra("username");
        System.out.println(username);
        weight = (EditText) findViewById(R.id.weight);
//        five_button = (Button)findViewById(R.id.five_per);
//        ten_button = (Button) findViewById(R.id.ten_per);
        confirm_button = (Button) findViewById(R.id.confirm);
//        female_button = (Button) findViewById(R.id.female);
//        male_button = (Button) findViewById(R.id.male);
        final Spinner sex_spinner = (Spinner) findViewById(R.id.sex_dropdown);
        ArrayAdapter<CharSequence> sex_adapter = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sex_spinner.setAdapter(sex_adapter);

        age = (EditText)findViewById((R.id.age_input));
        final Spinner activity_spinner = (Spinner) findViewById(R.id.activity_level);
        ArrayAdapter<CharSequence> activity_adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_level, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        activity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        activity_spinner.setAdapter(activity_adapter);

        final Spinner feet_spinner = (Spinner) findViewById(R.id.feet);
        ArrayAdapter<CharSequence> feet_adapter = ArrayAdapter.createFromResource(this,
                R.array.feet, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        feet_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        feet_spinner.setAdapter(feet_adapter);
//        feet_spinner.setSelection(0, true);

        final Spinner inches_spinner = (Spinner) findViewById(R.id.inches);
        ArrayAdapter<CharSequence> inches_adapter = ArrayAdapter.createFromResource(this,
                R.array.inches, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        inches_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        inches_spinner.setAdapter(inches_adapter);
//        inches_spinner.setSelection(0, true);

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
//        male_button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                male_button.getBackground().setColorFilter(new LightingColorFilter(Color.CYAN, Color.CYAN));
//                sex = male_button.getText().toString();
//                System.out.println(sex);
//            }});
//        female_button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                female_button.getBackground().setColorFilter(new LightingColorFilter(Color.CYAN, Color.CYAN));
//                sex = female_button.getText().toString();
//                System.out.println(sex);
//            }});
        sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                view.get
//                view.setBackgroundColor(Color.BLUE);
               sex = sex_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sex = "";
            }
        });
        activity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                view.get
//                view.setBackgroundColor(Color.BLUE);
                activity_level = activity_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                activity_level = "";
            }
            });

        feet_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                view.get
//                view.setBackgroundColor(Color.BLUE);
                feet = feet_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                feet = "";
            }
        });

        inches_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                view.get
//                view.setBackgroundColor(Color.BLUE);
                inches = inches_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                inches = "";
            }
        });

        confirm_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                weight_nums = weight.getText().toString();
//                age_string = age.getText().toString();
//                if(weight_nums.equals("")) {
//                    lbs = 0;
//                }
//                else {
//                    lbs = Integer.parseInt(weight_nums);
//                }
                if(sex.equals("")) {
                    String text = "Need to select sex";
                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    sexConfirm = true;
                }
//                if(age_string.equals("")) {
//                    String text = "Need to input age";
//                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
//                    toast.show();
//                }
                try {
                    age_int = Integer.parseInt(age.getText().toString());
                    ageConfirm = true;
                }
                catch(Exception e) {
                    String text = "Age needs to be a number";
                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                try {
                    weight_int = Integer.parseInt(weight.getText().toString());
                    weightConfirm = true;
                }
                catch(Exception e) {
                    String text = "Weight needs to be a number";
                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
                    toast.show();
                }
//                try {
//                    feet_int = Integer.parseInt(feet);
//                }
//                catch(Exception e) {
//                    String text = "Must select number for feet";
//                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
//                    toast.show();
//                }
//                try {
//                    inches_int = Integer.parseInt(inches);
//                }
//                catch(Exception e) {
//                    String text = "Must select number for inches";
//                    Toast toast = Toast.makeText(wContext, text, Toast.LENGTH_LONG);
//                    toast.show();
//                }
                feet_int = Integer.parseInt(feet.substring(0,1)) * 12;
                inches_int = Integer.parseInt(inches.substring(0,1));
                height = feet_int + inches_int;
//                System.out.println(lbs);
//                System.out.println(body_per);
                if(sexConfirm && ageConfirm && weightConfirm && heightConfirm && activityConfirm) {
                    // create object and direct to home page
                    CalorieCalc userCalc = new CalorieCalc(username, sex, activity_level, age_int, height, weight_int);
                    bmr = userCalc.calculateBMR(sex, age_int, weight_int, height);
                    recommend = userCalc.recommendedCalories(bmr, activity_level);
                    String recommendCal = Integer.toString(recommend);
//                    Toast toast = Toast.makeText(wContext, recommendCal, Toast.LENGTH_LONG);
//                    toast.show();

                    HashMap<String, String> map1 = new HashMap<String, String>();
                    map1.put("username", username);
                    map1.put("calories", recommendCal);

                    System.out.println(map1);
                    RequestQueue requestQueue = Volley.newRequestQueue(wContext);
                    JsonObjectRequest calorieRequest = null;
                    calorieRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/calories", new JSONObject(map1),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //                                queue = MySingleton.getInstance(mCtx).getRequestQueue();
                                    Intent home_page = new Intent(wContext, HomePage.class);
//                                    home_page.putExtra("username", username);
//                                    System.out.println(response.get("calories").toString());
                                    try {
                                        home_page.putExtra("userCalories", response.get("calories").toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(home_page);
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when error occurred
                            JSONObject error_object = null;
                            if(error.networkResponse.statusCode == 400) {
                                System.out.println("breakpoint");
                                try {
                                    String error_message = new String(error.networkResponse.data,"UTF-8");
                                    error_object = new JSONObject(error_message);
                                } catch (UnsupportedEncodingException | JSONException e) {
                                    e.printStackTrace();
                                }
//                                try {
//                                    if(error_object.getString("message").equals("Account already exists")) {
//                                        CharSequence text = "Account already exists. Try logging in.";
//                                        int duration = Toast.LENGTH_SHORT;
//                                        Toast toast = Toast.makeText(mContext, text, duration);
//                                        toast.show();
//                                    }
//                                    else {
//                                        CharSequence text = "Username already exists. Choose a different username.";
//                                        int duration = Toast.LENGTH_SHORT;
//                                        Toast toast = Toast.makeText(mContext, text, duration);
//                                        toast.show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
                            }
                            System.out.println(error.getMessage());
                            //                           Toast.makeText(mCtx, e + "error", Toast.LENGTH_LONG).show();
                        }
                    });
                    requestQueue.add(calorieRequest);
                            }
                            // add each caloriecalc to the database based on the user's username
                    // call the caloriecalc into the homepage (display)
                    // direct user to homepage after clicking confirm

                else {
                    System.out.println("still have errors");
                }
                System.out.println(sex);
                System.out.println(activity_level);
                System.out.println(height);
        }});

    }
}
