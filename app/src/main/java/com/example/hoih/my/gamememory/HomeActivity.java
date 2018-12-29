package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    Button btnNormal, btnNoLimitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnNormal = (Button)findViewById(R.id.btn_normal);
        btnNoLimitTime = (Button)findViewById(R.id.btn_no_limit_time);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NormalActivity.class);
                startActivity(i);
            }
        });

        btnNoLimitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NormalActivity.class);
                startActivity(i);
            }
        });

    }
}
