package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

public class LevelDetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);

        int level = getIntent().getIntExtra("level", 0);

        Button btnLevel = (Button)findViewById(R.id.btn_level);

        btnLevel.setText(level);
    }
}
