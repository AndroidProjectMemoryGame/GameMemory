package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

public class LevelDetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);

        String level = getIntent().getIntExtra("level",0)+"";

        Button btnLevel = (Button)findViewById(R.id.btn_level);

        btnLevel.setText("Level" + level);
    }
}
