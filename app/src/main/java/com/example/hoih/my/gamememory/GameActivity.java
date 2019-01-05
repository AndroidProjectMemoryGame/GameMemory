package com.example.hoih.my.gamememory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ListView mListView;
    int i;

    ArrayList<Drawable> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        Intent intent = getIntent();
        i = Integer.parseInt(intent.getStringExtra("pos"));
        Log.d("MISSION", "Number : " + i + " : succeed2 = "  );
        gridview.setAdapter(new ListGameAdapter(this,i));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GameActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();


            }
        });


    }

}
