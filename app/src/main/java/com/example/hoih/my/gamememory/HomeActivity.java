package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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

    private Button btnSoundStart;
    private Button btnSoundStop;
    private MediaPlayer mediaPlayer;
    Intent shareIntent;
    String shareBody = "this is great app";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnNormal = (Button)findViewById(R.id.btn_normal);
        btnNoLimitTime = (Button)findViewById(R.id.btn_no_limit_time);
        btnHard = (Button)findViewById(R.id.btn_hard);

        btnSoundStart= (Button) this.findViewById(R.id.btn_sound_start);
        btnSoundStop= (Button) this.findViewById(R.id.btn_sound_stop);
        int songId = this.getRawResIdByName("song");
        this.mediaPlayer=   MediaPlayer.create(this, songId);

        int duration = mediaPlayer.getDuration();
        int currentPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.start();
        btnSoundStop.setVisibility(View.GONE);


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

    // Tìm ID của một file nguồn trong thư mục 'raw' theo tên.
    public int getRawResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }

    public void doStart(View view) {
        int duration = mediaPlayer.getDuration();
        int currentPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.start();
        btnSoundStop.setVisibility(View.GONE);
        btnSoundStart.setVisibility(View.VISIBLE);

    }


    public void doStop(View view) {
        mediaPlayer.pause();
        btnSoundStop.setVisibility(View.VISIBLE);
        btnSoundStart.setVisibility(View.GONE);


    }

    public void share(View view) {
        shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/pain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"My app");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,shareBody);
        startActivity(Intent.createChooser(shareIntent,"Share via"));

    }
}
