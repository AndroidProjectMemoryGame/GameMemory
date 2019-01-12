package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModeActivity extends Activity implements ListLevelAdapter.OnClickListener{

    TextView txtIconBack, txtMode;
    ListView levelListView;
    ArrayList<Level> levelData = new ArrayList<>();
    int levelSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        levelListView = (ListView)findViewById(R.id.listView);
        txtIconBack = (TextView)findViewById(R.id.txt_icon_back);
        txtMode = (TextView)findViewById(R.id.txt_mode);

        txtIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getMode();


        levelData = getData();

        final ListLevelAdapter adapter = new ListLevelAdapter(this, levelData,this);
        levelListView.setAdapter(adapter);

        levelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0){
                    SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
                    SharedPreferences.Editor editor = positionBuntton.edit();
                    editor.putInt("level", 0);
                    editor.commit();
                }
                Intent i = new Intent(ModeActivity.this, LevelDetailActivity.class);
                i.putExtra("level",levelData.get(position).getLevel());
                startActivity(i);
            }
        });
    }

    private ArrayList<Level> getData(){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
        ArrayList<Level> data = new ArrayList<>();
        int tam =0;
        do {
            data.add(new Level(tam+1));
            tam++;
        }while (tam <= positionBuntton.getInt("level",0));

        Log.d("MISSION", "huhu Number : " + positionBuntton.getInt("level",0) + " : quangkhanhhahaha = ");

        return data;
    }

    @Override
    public void selectLevel(int position) {
        SharedPreferences positionBuntton = getSharedPreferences("myprefer",MODE_PRIVATE);

        if(!positionBuntton.getString("myuser","").equals(""))
        {
            if (positionBuntton.getString("myuser","").equals("1"))
            {
                if(position == 0 || position == 1 || position == 2) {
                    levelSelected = 5;
                    showNotice(this,"You have 5 seconds to memorize all images!","TIME:  NO TIME LIMIT", String.valueOf(position));
                }
                if (position == 3 || position == 4 || position == 5) {
                    levelSelected = 7;
                    showNotice(this,"You have 7 seconds to memorize all images!","TIME:  NO TIME LIMIT", String.valueOf(position));
                }
                if (position == 6 || position == 7 || position == 8 || position == 9) {
                    levelSelected = 10;
                    showNotice(this,"You have 10 seconds to memorize all images!","TIME:  NO TIME LIMIT", String.valueOf(position));
                }
            } else if (positionBuntton.getString("myuser","").equals("2"))
            {
                if(position == 0 || position == 1 || position == 2) {
                    levelSelected = 5;
                    showNotice(this,"You have 5 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  30 s", String.valueOf(position));
                }
                if (position == 3 || position == 4 || position == 5) {
                    levelSelected = 7;
                    showNotice(this,"You have 7 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  30 s", String.valueOf(position));
                }
                if (position == 6 || position == 7 || position == 8 || position == 9) {
                    levelSelected = 10;
                    showNotice(this,"You have 10 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  30 s", String.valueOf(position));
                }
            } else
             {
                if(position == 0 || position == 1 || position == 2) {
                    levelSelected = 5;
                    showNotice(this,"You have 5 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  5 s",String.valueOf(position));
                }
                if (position == 3 || position == 4 || position == 5) {
                    levelSelected = 7;
                    showNotice(this,"You have 7 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  5 s",String.valueOf(position));
                }
                if (position == 6 || position == 7 || position == 8 || position == 9) {
                    levelSelected = 10;
                    showNotice(this,"You have 10 seconds to memorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  5 s",String.valueOf(position));
                }
             }
        }
    }

    public void showNotice(Context context, String message, String title, final String position) {
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_start_game, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(false);
            final AlertDialog dialog = alertDialogBuilder.create();
            Button btnGo = (Button) view.findViewById(R.id.btn_go);
            TextView txvMessage = (TextView) view.findViewById(R.id.txv_message);
            TextView txvTitle = (TextView) view.findViewById(R.id.txv_title);
            txvMessage.setText(message);
            txvTitle.setText(title);
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Intent i = new Intent(ModeActivity.this, GameActivity.class);
                    i.putExtra("levelSelected", levelSelected);
                    i.putExtra("pos", position);
                    startActivity(i);
                    Log.d("MISSION", "Number : " + position + " : succeed1 = "  );
                }
            });
            //   txtMessage.setText(message);
            if (context instanceof Activity && !((Activity) context).isFinishing())
                dialog.show();
        }
    }

    public void getMode(){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer",MODE_PRIVATE);
        if (!positionBuntton.getString("myuser","").equals(""))
        {
            if (positionBuntton.getString("myuser","").equals("1")) {
                txtMode.setText("No Limit Time");
            } else if (positionBuntton.getString("myuser","").equals("2")) {
                txtMode.setText("Normal");
            } else {
                txtMode.setText("Hard");
            }
        }
    }
}
