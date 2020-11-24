package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


public class SearchPage extends AppCompatActivity {
    // List View object
    ListView listView;

    // Context
    private Context sContext;
    // Define array adapter for ListView
    ArrayAdapter<String> adapter;

    // Define array List for List View data
    ArrayList<String> mylist = new ArrayList<>();
    ArrayList<String> newList = new ArrayList<>();

    String item = "";
    String calories;
//    private void getArrayValue(){
//        for(int i = 0; i < mylist.size(); i++) {
//            newList.add(mylist.get(i));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sContext = getApplicationContext();
        // initialise ListView with id
        mylist = new ArrayList<>();
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.listView);
        // create a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(sContext);
        // initialize a new JsonObjectRequest instance
        JsonArrayRequest searchUserRequest = null;
        adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mylist);

        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                String item = adapter.getItem(position);
//                Intent intent = new Intent(sContext, HomePage.class);
////                String message = "abcpqr";
////                Toast.makeText(SearchPage.this, item, Toast.LENGTH_SHORT).show();
////                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
//            }
//        });
//        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        searchUserRequest = new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:8080/getFoods", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                               // System.out.println(object.getString("Name"));
                                mylist.add(object.getString("Item"));
//                                getArrayValue();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
//                            System.out.println(mylist.toString());
                            adapter.notifyDataSetChanged();
                    }}
                ,
        new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }
        );
//        System.out.println(mylist.toString());
        requestQueue.add(searchUserRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                item = adapter.getItem(position);

//                SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("name", item);
//                editor.apply();

                System.out.println(item);

                //////////////////////
                HashMap<String, String> map1 = new HashMap<String, String>();
                map1.put("item", item);
                RequestQueue requestQueue = Volley.newRequestQueue(sContext);
                JsonObjectRequest calorieRequest = null;
                calorieRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/getCalories", new JSONObject(map1),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //                                queue = MySingleton.getInstance(mCtx).getRequestQueue();
                                try {
//                                    SharedPreferences pref = sContext.getSharedPreferences("myPref", 0);
//                                    SharedPreferences.Editor editor = pref.edit();
//                                    editor.putString("name", item);
//                                    editor.commit();

                                    calories = response.getString("calories");
                                    Toast toast = Toast.makeText(sContext, response.getString("calories"),Toast.LENGTH_LONG);
                                    toast.show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent home_page = new Intent(sContext, HomePage.class);
//                                Bundle extras = new Bundle();
//                                extras.putString("name", item);
//                                extras.putString("food_calories", calories);
//                                home_page.putExtras(extras);
                                home_page.putExtra("name", item);
                                home_page.putExtra("food_calories", calories);
                                setResult(HomePage.CODE, home_page);
//                                startActivity(home_page);
                                finish();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        JSONObject error_object = null;
                        System.out.println(error.getMessage());
                        //                           Toast.makeText(mCtx, e + "error", Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(calorieRequest);
                //////////////////////
//                Intent intent = new Intent(sContext, HomePage.class);
////                String message = "abcpqr";
////                Toast.makeText(SearchPage.this, item, Toast.LENGTH_SHORT).show();
////                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
            }
        });

        System.out.println(mylist.size());
        System.out.println("In search class");
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", item);
        returnIntent.putExtra("food_calories", calories);
        // setResult(RESULT_OK);
        setResult(RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem
                = menu.findItem(R.id.search_bar);
        SearchView searchView
                = (SearchView) MenuItemCompat
                .getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    // Override onQueryTextSubmit method
                    // which is call
                    // when submitquery is searched

                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
                        // If the list contains the search query
                        // than filter the adapter
                        // using the filter method
                        // with the query as its argument
                        if (newList.contains(query)) {
                            adapter.getFilter().filter(query);
                        }
                        else {
                            // Search query not found in List View
                            Toast
                                    .makeText(SearchPage.this,
                                            "Not found",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return false;
                    }

                    // This method is overridden to filter
                    // the adapter according to a search query
                    // when the user is typing search
                    @Override
                    public boolean onQueryTextChange(String newText)
                    {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }
}