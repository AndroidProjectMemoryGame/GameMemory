package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements GameAdapter.OnClickListener {
    GridView gridview;
    ArrayList<Integer> mang = new ArrayList<>();
    ArrayList<String> save = new ArrayList<>();
    ArrayList<String> save1 = new ArrayList<>();
    String goi;
    int i, count =0, gan, pos;
    boolean check;

    ArrayList<Integer> arrayPos = new ArrayList<>();
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();

    TextView tv_second_left, tv_second_right, tv_time;
    ImageButton btnBack;
    int time;
    int timeCountDownInterval = 1000;
    CountDownTimer cTimer;
    int up = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv_second_left = (TextView)findViewById(R.id.tv_second_left);
        tv_second_right = (TextView)findViewById(R.id.tv_second_right);
        tv_time = (TextView)findViewById(R.id.tv_time);
        gridview = (GridView) findViewById(R.id.gridview);
        btnBack = (ImageButton) findViewById(R.id.btn_icon_back);
        // handle back icon
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        i = Integer.parseInt(intent.getStringExtra("pos"));

        if (getIntent() != null) {
            if (getIntent().getIntExtra("levelSelected", 0) == 5) {
                time = 5000;
            }
            if (getIntent().getIntExtra("levelSelected", 0) == 7) {
                time = 7000;
            }
            if (getIntent().getIntExtra("levelSelected", 0) == 10){
                time = 10000;
            }
        }

        // Start timer
        startTimer(time);

        setData();
        randomArray();
        gridview.setAdapter(new GameAdapter(arrayList1,GameActivity.this,i,this,randomArray()));
        check = false;

        new CountDownTimer(time, timeCountDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("MISSION", "Number : " + arrayList1 + " : quangkhanhthum1 = ");
            }

            @Override
            public void onFinish() {
                check=true;
                gridview.setAdapter(new GameAdapter(arrayList2,GameActivity.this,i,GameActivity.this,randomArray()));
                Log.d("MISSION", "Number : " + arrayList1 + " : quangkhanhthum2 = ");
            }
        }.start();



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GameActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });




    }
    private Integer[] mThumbIds=
            {
                    R.drawable.card1,
                    R.drawable.card2,
                    R.drawable.card3,
                    R.drawable.card4,
                    R.drawable.card5,
                    R.drawable.card6,
                    R.drawable.card7,
                    R.drawable.card8,
                    R.drawable.card9,
                    R.drawable.card10,
                    R.drawable.card11,
                    R.drawable.card12,
                    R.drawable.card13,
                    R.drawable.card14,
                    R.drawable.card15,
                    R.drawable.card16,
                    R.drawable.card17,
                    R.drawable.card18,
                    R.drawable.card19,
                    R.drawable.card20,
                    R.drawable.card21,
            };
    private Integer[] test =
            {
                    R.drawable.btn_an,
            };

    private void setData(){
        for (int j = 0; j < 2; j++) {
            for (int  u = 0;u < ((i + 2) * 2) / 2; u++) {
                arrayList1.add(mThumbIds[u]);
                arrayList2.add(test[0]);
            }
        }
    }

    public void startTimer(int time) {
        new CountDownTimer(time, timeCountDownInterval) {

            public void onTick(long millisUntilFinished) {
                tv_second_left.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv_second_left.setText("0");
                getCountUpTimer(Integer.MAX_VALUE);
            }
        }.start();
    }

    public void getCountUpTimer(int timeCountUp) {

        cTimer = new CountDownTimer(timeCountUp, timeCountDownInterval) {

            public void onTick(long millisUntilFinished) {
                up++;
                tv_second_left.setText("" +up);
            }

            public void onFinish() {
                cTimer.cancel();
            }
        };
        cTimer.start();
    }

    public ArrayList<Integer> randomArray() {
        Random rand = new Random();
        while (mang.size() < (i + 2) * 2) {

            int random = rand.nextInt((i + 2) * 2);
            if (!mang.contains(random)) {
                mang.add(random);
            }
        }
        return mang;
    }

    @Override
    public void Image(final int position) {
        if (check) {
            Boolean compare = true;
            for (int u = 0; u < arrayPos.size(); u++) {
                if (arrayPos.get(u) == position) {
                    compare = false;
                    break;
                }
            }
            if (compare) {
                if (count == 0) {
                    gan = mang.get(position);
                    pos = position;
                }

                final int a = mang.get(position);
                arrayList2.remove(a);
                arrayList2.add(a, arrayList1.get(a));
                gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, mang));
                count++;
                if (count == 2) {
                    if (arrayList2.equals(arrayList1)){
                        cTimer.cancel();
                        Toast.makeText(GameActivity.this, "Your Score: " + up, Toast.LENGTH_SHORT).show();

                        switch (i) {
                            case 0:
                                saveScore(1);
                                break;
                            case 1 :
                                saveScore(2);
                                break;
                            case 2 :
                                saveScore(3);
                                break;
                            case 3 :
                                saveScore(4);
                                break;
                            default: saveScore(5);

                        }
                        showNotice(this);

                    }
                    check = false;
                    if (!arrayList1.get(mang.get(position)).equals(arrayList1.get(gan))) {
                        new CountDownTimer(1300, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                arrayList2.remove(a);
                                arrayList2.add(a, test[0]);
                                Log.d("MISSION", "huhu" + a + "Number : " + gan + " : quangkhanhhahaha = ");
                                arrayList2.remove(gan);
                                arrayList2.add(gan, test[0]);
                                Log.d("MISSION", "Number : " + arrayList2 + " : quangkhanhhahaha = ");
                                gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, mang));
                                check =true;
                            }
                        }.start();
                    } else {
                        arrayPos.add(pos);
                        arrayPos.add(position);
                        check=true;
                    }
                    count = 0;
                }
            }
        }

    }

    public void saveScore( int location){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = positionBuntton.edit();
        for (String w : positionBuntton.getString("scoreLevel1", "").split("\\,", 0)) {
            save.add(w.trim());
        }
        Log.d("MISSION", "huhu Number : " + save.size() + " : quangkhanhcutesze1 = ");
        if (save.size() == location) {
            if (location == 1) {
                editor.putString("scoreLevel1", String.valueOf(up) + ",0");
                editor.commit();
                Log.d("MISSION", "huhu Number : " + save.size() + " : quangkhanhcutesze = ");
                Log.d("MISSION", "huhu Number : " + up + " : quangkhanhcuteup = ");
            }else {
                editor.putString("scoreLevel1", String.valueOf(up) +","+ positionBuntton.getString("scoreLevel1",""));
                editor.commit();
            }
        }else {
            String k = save.get(save.size()-2-i);
            Log.d("MISSION", "huhu Number : " + k + " : quangkhanhcutek1 = ");
            Log.d("MISSION", "huhu Number : " + save + " : quangkhanhcutesave1 = ");
            if (Integer.parseInt(k) > up){
                int size = save.size()-2-i;
                save.remove(size);
                Log.d("MISSION", "huhu Number : " + save + " : quangkhanhcutesave2 = ");
                save.add(size, String.valueOf(up));
                Log.d("MISSION", "huhu Number : " + save + " : quangkhanhcutesave3 = ");
                String f="";
                for (int u = 0;u < save.size(); u++){
                    f = f + save.get(u)+",";
                }
                editor.putString("scoreLevel1", f);
                editor.commit();
                Log.d("MISSION", "huhu Number : " + f + " : quangkhanhcutef = ");
                Log.d("MISSION", "huhu Number : " + up + " : quangkhanhcuteup = ");
            }
        }

        Log.d("MISSION", "huhu Number : " + save + " : quangkhanhcuteok = ");
        Log.d("MISSION", "huhu Number : " + positionBuntton.getString("scoreLevel1","") + " : quangkhanhcuteupok = ");
    }

    public void showNotice(Context context) {
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_level_completed, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(false);
            final AlertDialog dialog = alertDialogBuilder.create();
            Button btnGo = (Button) view.findViewById(R.id.btn_end);


            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveLevel();
                    Intent back = new Intent(GameActivity.this,ModeActivity.class);
                    startActivity(back);
                }
            });
            //   txtMessage.setText(message);
            if (context instanceof Activity && !((Activity) context).isFinishing())
                dialog.show();
        }
    }

    public void saveLevel(){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = positionBuntton.edit();
        if (positionBuntton.getString("myuser","").equals("1")){
            if(i == positionBuntton.getInt("level1",0)) {
                int tam = positionBuntton.getInt("level1", 0);
                tam++;
                editor.putInt("level1", tam);
                Log.d("MISSION", "huhu Number : " + positionBuntton.getInt("level1", 0) + " : level1 = ");
            }
        }else if(positionBuntton.getString("myuser","").equals("2")){
            if(i == positionBuntton.getInt("level2",0)) {
                int tam = positionBuntton.getInt("level2", 0);
                tam++;
                editor.putInt("level2", tam);
            }
        }else{
            if(i == positionBuntton.getInt("level3",0)) {
                int tam = positionBuntton.getInt("level3", 0);
                tam++;
                editor.putInt("level3", tam);
            }
        }
        editor.commit();
    }

}
