package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends Activity {

    Button btnNormal, btnNoLimitTime, btnHard;
    ArrayList<Integer> mang = new ArrayList<>();
    Intent shareIntent;
    String shareBody = "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnNormal = (Button)findViewById(R.id.btn_normal);
        btnNoLimitTime = (Button)findViewById(R.id.btn_no_limit_time);
        btnHard = (Button)findViewById(R.id.btn_hard);


        btnNoLimitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePosition("1");
                Intent i = new Intent(HomeActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePosition("2");
                Intent i = new Intent(HomeActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePosition("3");
                Intent i = new Intent(HomeActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });
    }

    private void savePosition(String a){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer",MODE_PRIVATE);
        SharedPreferences.Editor editor = positionBuntton.edit();
        editor.putString("myuser", a);
        editor.commit();
    }



    public void share(View view) {
        shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/pain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"My app");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,shareBody);
        startActivity(Intent.createChooser(shareIntent,"Share via"));
    }
    public void moreGame(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=%3Cgame%3E&c=apps")));
    }

}
