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
    int i, count =0, gan, pos, k =0;
    boolean check;

    ArrayList<Integer> arrayPos = new ArrayList<>();
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();

    TextView tv_second_left, tv_second_right, tv_time;
    ImageButton btnBack;
    CountDownTimer cTimer = null;
    int time, timeCountUp = 0;
    int timeCountDownInterval = 1000;
    boolean isCountDownFinish = false;

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
//                getCountUpTimer(8000);
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
                // chua xong
                isCountDownFinish = true;
                if (isCountDownFinish == true) {
                    getCountUpTimer(30000);
                }
            }
        }.start();
    }


    public void getCountUpTimer(int timeCountUp) {

        new CountDownTimer(timeCountUp, timeCountDownInterval) {

            public void onTick(long millisUntilFinished) {
                time = 31000;
                tv_second_left.setText("" + (time - millisUntilFinished) / 1000);
            }

            public void onFinish() {
                cancelTimer();
//                tv_second_left.setText("00");
            }
        }.start();
    }

    public void cancelTimer() {
        if(cTimer != null)
            cTimer.cancel();
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
                    SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
                    SharedPreferences.Editor editor = positionBuntton.edit();
                    Log.d("MISSION", "huhu Number : " + positionBuntton.getString("myuser","") + " : level1 = ");
                    if (positionBuntton.getString("myuser","").equals("1")){
                        int tam = positionBuntton.getInt("level1",0);
                        tam++;
                        editor.putInt("level1", tam);
                        Log.d("MISSION", "huhu Number : " + positionBuntton.getInt("level1",0) + " : level1 = ");
                    }else if(positionBuntton.getString("myuser","").equals("2")){
                        int tam = positionBuntton.getInt("level2",0);
                        tam++;
                        editor.putInt("level2", tam);
                    }else{
                        int tam = positionBuntton.getInt("level3",0);
                        tam++;
                        editor.putInt("level3", tam);
                    }
                    editor.commit();

                    Log.d("MISSION", "huhu Number : " + positionBuntton.getInt("level2",0) + " : level2 = ");
                    Log.d("MISSION", "huhu Number : " + positionBuntton.getInt("level3",0) + " : level3 = ");
                    Intent back = new Intent(GameActivity.this,ModeActivity.class);
                    startActivity(back);
                }
            });
            //   txtMessage.setText(message);
            if (context instanceof Activity && !((Activity) context).isFinishing())
                dialog.show();
        }
    }

}
