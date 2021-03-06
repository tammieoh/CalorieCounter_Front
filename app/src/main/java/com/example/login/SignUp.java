package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

// sign up page for when new users register
public class SignUp extends AppCompatActivity {
    Button signup_button, login_button;
    EditText usernameView, passwordView, emailView;
    String username, password, email;
    private TextView textView, textView2;

    private Context mContext;

    // when the page is rendered
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // taking the XML file that will work with this SignUp class
        setContentView(R.layout.activity_sign_up);
        mContext = getApplicationContext();
        textView = (TextView)findViewById(R.id.textView2);
        textView2 = (TextView)findViewById(R.id.textView3);

        // buttons at the bottom of the page
        signup_button = (Button)findViewById(R.id.signup_button2);
        login_button = (Button) findViewById(R.id.register_login);
        usernameView = (EditText) findViewById(R.id.username);
//        username.getText().toString();

        // edit texts are used to grab the input that the user types in
        passwordView = (EditText)findViewById((R.id.password));
        emailView = (EditText)findViewById((R.id.email));


        // when the signup button is clicked, it will create a request and send to register endpoint call
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("hello");
//                textView.setText("");
                // initialize a new RequestQueue instance
            username = usernameView.getText().toString();
            password = passwordView.getText().toString();
            email = emailView.getText().toString();

            // if the username, password, email is admint, then go straight to the home page
            if(username.equals("admin") && password.equals("admin") && email.equals("admin")) {
                Intent weight_page = new Intent(mContext, WeightCalc.class);
                weight_page.putExtra("username", username);
                startActivity(weight_page);
            }

            // if the username, password, email is of a new user
            // store the information into a hashmap and create a JSON body request to send to API endpoint
            else if(username.equals("") || password.equals("") || email.equals("")) {
                CharSequence text = "Error. Please enter details";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(mContext, text, duration);
                toast.show();
            }
            else {
                HashMap<String, String> map1 = new HashMap<String, String>();
                map1.put("username", username);
                map1.put("password", password);
                map1.put("email", email);

                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                // initialize a new JsonObjectRequest instance
                JsonObjectRequest registerUserRequest = null;
                registerUserRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/registerUser", new JSONObject(map1),
//                        "{\n" +
//                        "\t\"username\": \"amyoh\",\n" +
////                                "\t\"username\": \"amyoh\",\n" +
//                        "\t\"password\": \"amyoh1125\",\n" +
//                        "\t\"email\": \"amyoh@gmail.com\"\n" +
//                        "}"),

                        // once the API endpoint returns a response,
                        // users should be taken to the CalorieCalculator page
                        new Response.Listener<JSONObject>() {
                            //                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                            //                    Toast .makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                            //                }
                            //                else {
                            //                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                            //                    tx2.setVisibility(View.VISIBLE);
                            //                    counter--;
                            //                    tx2.setText("Attempts Left: " + Integer.toString(counter));
                            //
                            //                    if (counter == 0) {
                            //                        login_button.setEnabled(false);
                            //                    }
                            //                }
                            //                RequestQueue queue =  Volley.newRequestQueue(MainActivity.this);
                            //                JsonObjectRequest registerUserRequest = new JsonObjectRequest
                            //                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //                                queue = MySingleton.getInstance(mCtx).getRequestQueue();
                                Intent weight_page = new Intent(mContext, WeightCalc.class);
                                weight_page.putExtra("username", username);
                                startActivity(weight_page);
                                CharSequence text = "User Successfully Registered";
                                int duration = Toast.LENGTH_LONG;
                                Toast toast = Toast.makeText(mContext, text, duration);
                                toast.show();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something when error occurred
                        JSONObject error_object = null;
                        if(error.networkResponse.statusCode == 400) {
                            System.out.println("breakpoint");
                            try {
                                String error_message = new String(error.networkResponse.data,"UTF-8");
                                error_object = new JSONObject(error_message);
                            } catch (UnsupportedEncodingException | JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if(error_object.getString("message").equals("Account already exists")) {
                                    CharSequence text = "Account already exists. Try logging in.";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(mContext, text, duration);
                                    toast.show();
                                }
                                else {
                                    CharSequence text = "Username already exists. Choose a different username.";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(mContext, text, duration);
                                    toast.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(error.getMessage());
                        //                           Toast.makeText(mCtx, e + "error", Toast.LENGTH_LONG).show();
                    }
                });

                // Access the RequestQueue through your singleton class.
                requestQueue.add(registerUserRequest);
            }
            }
        });
    }
}