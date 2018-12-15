package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onNormal() {
        Intent i = new Intent(this, NormalActivity.class);
        i.putExtra("normal", "2");
        startActivity(i);
    }
}
