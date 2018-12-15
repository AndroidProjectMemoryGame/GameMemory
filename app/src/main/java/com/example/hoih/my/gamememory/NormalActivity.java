package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NormalActivity extends Activity {

    ListView levelListView;
    ArrayList<Level> levelData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        levelListView = (ListView)findViewById(R.id.listView);

        levelData = getData();

        final ListLevelAdapter adapter = new ListLevelAdapter(this, levelData);
        levelListView.setAdapter(adapter);

        levelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(NormalActivity.this, LevelDetailActivity.class);
                i.putExtra("level",levelData.get(position).getLevel());
                startActivity(i);
            }
        });
    }

    private ArrayList<Level> getData(){
        ArrayList<Level> data = new ArrayList<>();
        data.add(new Level(1));
        data.add(new Level(2));
        data.add(new Level(3));
        data.add(new Level(4));
        data.add(new Level(5));
        data.add(new Level(6));
        data.add(new Level(7));
        data.add(new Level(8));
        data.add(new Level(9));
        data.add(new Level(10));
        return data;
    }
}
