package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;

//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText usernameView, passwordView;
    String username, password;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    int counter = 3;

    private Context mContext;
    private TextView textView;

//    private String username = "admin";
//    private String password = "12345";
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        textView = (TextView)findViewById(R.id.textView3);
        login_button = (Button)findViewById(R.id.register_login);
        usernameView = (EditText) findViewById(R.id.username);
//        username.getText().toString();
        passwordView = (EditText)findViewById((R.id.password));
//        signup_button = (Button)findViewById(R.id.signup);
//        tx2 = (TextView)findViewById(R.id.textView2);
//        tx2.setVisibility(View.GONE);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameView.getText().toString();
                password = passwordView.getText().toString();

                HashMap<String, String> map1 = new HashMap<String, String>();
                map1.put("username", username);
                map1.put("password", password);
                // create a Request Queue
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                // initialize a new JsonObjectRequest instance
                JsonObjectRequest registerUserRequest = null;
                registerUserRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.15:8080/login", new JSONObject(map1),
//                        "{\n" +
//                        "\t\"username\": \"amyoh\",\n" +
////                                "\t\"username\": \"amyoh\",\n" +
//                        "\t\"password\": \"amyoh1125\",\n" +
//                        "\t\"email\": \"amyoh@gmail.com\"\n" +
//                        "}"),
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
                                //                                    System.out.println((response.getString("username")) + "user successfully logged in!");
                                Intent home_page = new Intent(mContext, HomePage.class);
                                startActivity(home_page);
//                                CharSequence text = "User Successfully Logged In";
//                                int duration = Toast.LENGTH_SHORT;
//                                Toast toast = Toast.makeText(mContext, text, duration);
//                                toast.show();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred'
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
                                if(error_object.getString("message").equals("username not registered")) {
                                    CharSequence text = "Username Not Registered";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(mContext, text, duration);
                                    toast.show();
                                }
                                else {
                                    CharSequence text = "Username/Password is incorrect. Try Again";
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
        });
    }

    public void launchSignUpActivity(View view) {
//        Log.d(LOG_TAG, "Button clicked!");
        Intent sign_up = new Intent(this, SignUp.class);
        startActivity(sign_up);
    }
    public void launchHomePage(View view) {
        Intent home_page = new Intent(this, HomePage.class);
        startActivity(home_page);
    }
}