package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class SignUp extends AppCompatActivity {
    Button signup_button, login_button;
    EditText usernameView, passwordView, emailView;
    String username, password, email;
    private TextView textView, textView2;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext = getApplicationContext();
        textView = (TextView)findViewById(R.id.textView2);
        textView2 = (TextView)findViewById(R.id.textView3);
        signup_button = (Button)findViewById(R.id.signup_button2);
        login_button = (Button) findViewById(R.id.register_login);
        usernameView = (EditText) findViewById(R.id.username);
//        username.getText().toString();
        passwordView = (EditText)findViewById((R.id.password));
        emailView = (EditText)findViewById((R.id.email));

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("hello");
//                textView.setText("");
                // initialize a new RequestQueue instance
            username = usernameView.getText().toString();
            password = passwordView.getText().toString();
            email = emailView.getText().toString();
            HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put("username", username);
            map1.put("password", password);
            map1.put("email", email);

            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            // initialize a new JsonObjectRequest instance
            JsonObjectRequest registerUserRequest = null;
            registerUserRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.15:8080/registerUser", new JSONObject(map1),
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
                            CharSequence text = "User Successfully Registered";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(mContext, text, duration);
                            toast.show();
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
        });
    }
}