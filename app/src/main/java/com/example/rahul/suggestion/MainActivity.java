package com.example.rahul.suggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String[] itemName= new String[] {
            "Car Search", "Cargo Search", "Computer Search", "Fashion Search", "Flight Search","Football search","Footwear Search","Mobile Search"
    };
    private static final int[] itemImage= new int[] {
           R.drawable.car,R.drawable.cargo,R.drawable.computer,R.drawable.fashion,R.drawable.flight,R.drawable.football,R.drawable.footwear,R.drawable.mobile
    };
    List<Detail> dlist;
    private DisplayMetrics metrics;

    AirportAdapter airportAdapter;
 AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        dlist=new ArrayList<>();
        getData();
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.auto_suggest);
        airportAdapter=new AirportAdapter(this,R.layout.activity_main,R.id.icon_image,R.id.txt_name,dlist,metrics);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,AIRPORT);
        autoCompleteTextView.setAdapter(airportAdapter);
        autoCompleteTextView.setThreshold(1);
//        ListView listView=(ListView)findViewById(R.id.listview);
//        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.fade_in), 2f); //0.5f == time between appearance of listview items.
//        listView.setLayoutAnimation(lac);
//        AnimationSet set = new AnimationSet(true);
//        Animation animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0, (float) 1.0);
//        animation.setDuration(1000);
//        set.addAnimation(animation);
//        LayoutAnimationController controller = new LayoutAnimationController(set, 2f);
//        listView.setLayoutAnimation(controller);


    }
    private void getData() {
        for(int i=0;i<itemName.length;i++){
            Detail ld=new Detail();
            ld.setName(itemName[i]);
            ld.setImage(itemImage[i]);
            dlist.add(ld);
        }

    }
}
