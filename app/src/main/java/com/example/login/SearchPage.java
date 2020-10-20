package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
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
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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
//        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        searchUserRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.0.15:8080/getFoods", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                               // System.out.println(object.getString("Name"));
                                mylist.add(object.getString("Name"));
//                                getArrayValue();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                            System.out.println(mylist.toString());
                            adapter.notifyDataSetChanged();
                            //                        mylist.add(response.getString());
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
        System.out.println(mylist.size());
        System.out.println("In search class");




        // Add items to Array List
//        mylist = new ArrayList<>();
//        mylist.add("C");
//        mylist.add("C++");
//        mylist.add("C#");
//        mylist.add("Java");
//        mylist.add("Advanced java");
//        mylist.add("Interview prep with c++");
//        mylist.add("Interview prep with java");
//        mylist.add("data structures with c");
//        mylist.add("data structures with java");

        // Set adapter to ListView
//        adapter
//                = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                mylist);
//        listView.setAdapter(adapter);
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