package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private FloatingActionButton addEntryButton;
    private Context hContext;
    private RecyclerView recyclerView;
    private RecyclerView parentRecyclerViewItem;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter parentItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String header = "Calories";
    public static final int CODE = 1;
    private List<String> myDataset = new ArrayList<String>(Arrays.asList("Breakfast", "Lunch", "Dinner", "Snack"));

    private List<ChildItem> breakfastItemList = new ArrayList<>();
    private List<ChildItem> lunchItemList = new ArrayList<>();
    private List<ChildItem> dinnerItemList = new ArrayList<>();
    private List<ChildItem> snackItemList = new ArrayList<>();
    private List<ParentItem> itemList = new ArrayList<>();

    private String selectedMeal;
//    SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
    //    SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();
//    editor.putString("key", "value");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hContext = getApplicationContext();
        setContentView(R.layout.activity_home_page);
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        parentRecyclerViewItem = findViewById(R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                HomePage.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()

                parentItemAdapter
                = new ParentAdapter(
                ParentItemList(breakfastItemList, lunchItemList, dinnerItemList, snackItemList));
//        breakfastItemList.add(new ChildItem("raisin bran"));
//        breakfastItemList.add(new ChildItem("kelp"));
//        breakfastItemList.add(new ChildItem("jimmy neutron"));
        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        parentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        parentRecyclerViewItem
                .setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.onCreateViewHolder();

//        String food_name = sharedPreferences.getString("name", "defaultValue");
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(response.getString("calories"), 1);

        addEntryButton = (FloatingActionButton) findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RequestQueue requestQueue;
                PopupMenu popup = new PopupMenu(HomePage.this, addEntryButton);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                        // create a Request Queue
//                        RequestQueue requestQueue = Volley.newRequestQueue(hContext);
//                        // initialize a new JsonObjectRequest instance
//                        JsonObjectRequest searchUserRequest = null;
                        selectedMeal = item.toString();
                        System.out.println("about to go into search page");
                        Intent search_page = new Intent(hContext, SearchPage.class);
                        startActivityForResult(search_page, CODE);

                        Toast.makeText(HomePage.this, "You Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
    private List<ParentItem> ParentItemList(List<ChildItem> breakfastItemList, List<ChildItem> lunchItemList,
                                            List<ChildItem> dinnerItemList, List<ChildItem> snackItemList) {
        ParentItem item
                = new ParentItem(
                "Breakfast",
                breakfastItemList);
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "Lunch",
                lunchItemList);
        itemList.add(item1);
        ParentItem item2
                = new ParentItem(
                "Dinner",
                dinnerItemList);
        itemList.add(item2);
        ParentItem item3
                = new ParentItem(
                "Snack",
                snackItemList);
        itemList.add(item3);

        return itemList;
    }

    // Method to pass the arguments
    // for the elements
    // of child RecyclerView
    private List<ChildItem> BreakfastList(String food)
    {

        breakfastItemList.add(new ChildItem(food));
//        breakfastItemList.add(new ChildItem("Card 2"));
//        breakfastItemList.add(new ChildItem("Card 3"));
//        breakfastItemList.add(new ChildItem("Card 4"));

        return breakfastItemList;
    }
    private List<ChildItem> LunchList(String food)
    {

        lunchItemList.add(new ChildItem(food));

        return lunchItemList;
    }
    private List<ChildItem> DinnerList(String food)
    {

        dinnerItemList.add(new ChildItem(food));


        return dinnerItemList;
    }
    private List<ChildItem> SnackList(String food)
    {
        snackItemList.add(new ChildItem(food));

        return snackItemList;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_OK) {
           String passedItem = data.getExtras().getString("name");
           if(selectedMeal.toLowerCase().equals("breakfast")) {
               BreakfastList((passedItem));
           }
           else if (selectedMeal.toLowerCase().equals("lunch")) {
               LunchList((passedItem));
           }
           else if(selectedMeal.toLowerCase().equals("dinner")) {
                DinnerList((passedItem));
           }
           else {
               SnackList((passedItem));
           }
//           parentItemAdapter = new ParentAdapter(ParentItemList(breakfastItemList, lunchItemList, dinnerItemList, snackItemList));
//           myDataset.add(passedItem);
//            parentItemAdapter.
           parentItemAdapter.notifyDataSetChanged();
           System.out.println(passedItem + "has been added to the home page");
        }
    }
}