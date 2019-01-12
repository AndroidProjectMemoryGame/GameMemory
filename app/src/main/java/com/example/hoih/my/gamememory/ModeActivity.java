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
import android.widget.Toast;

import java.util.ArrayList;

public class ModeActivity extends Activity implements ListLevelAdapter.OnClickListener{

    TextView txtIconBack, txtMode;
    ListView levelListView;
    ArrayList<Level> levelData = new ArrayList<>();
    int levelSelected;
    String arrScore = "";

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
                Intent back = new Intent(ModeActivity.this,HomeActivity.class);
                startActivity(back);
            }
        });
        getMode();
        levelData = getData();
        final ListLevelAdapter adapter = new ListLevelAdapter(this, levelData,this);
        levelListView.setAdapter(adapter);
    }

    private ArrayList<Level> getData(){
        SharedPreferences positionBuntton = getSharedPreferences("myprefer", MODE_PRIVATE);
        ArrayList<Level> data1 = new ArrayList<>();
        ArrayList<Level> data2 = new ArrayList<>();
        ArrayList<Level> data3 = new ArrayList<>();
        ArrayList<Level> data4 = new ArrayList<>();
        if (positionBuntton.getString("myuser","").equals("1")){
            // luu level hien tai roi check, then moi cho chay level tiep theo
                int tam =0;
            do {
                    data1.add(new Level(tam + 1, positionBuntton.getInt("scoreLevel1NoLimitTime", 0)));
                    arrScore = arrScore + positionBuntton.getInt("scoreLevel1NoLimitTime",0);
                    Toast.makeText(ModeActivity.this, "arrScore 1: " + arrScore, Toast.LENGTH_SHORT).show();
                    tam++;
                if (positionBuntton.getInt("positionLevel",0) == 1) {
                    data1.add(new Level(tam + 1, positionBuntton.getInt("scoreLevel2NoLimitTime", 0)));
                    arrScore = arrScore + ", " + positionBuntton.getInt("scoreLevel2NoLimitTime",0);
                    Toast.makeText(ModeActivity.this, "arrScore 2: " + arrScore, Toast.LENGTH_SHORT).show();
                    tam++;
                }
                if (positionBuntton.getInt("positionLevel",0) == 2) {
                    data1.add(new Level(tam + 1, positionBuntton.getInt("scoreLevel3NoLimitTime", 0)));
                    arrScore = arrScore + ", " + positionBuntton.getInt("scoreLevel3NoLimitTime",0);
                    Toast.makeText(ModeActivity.this, "arrScore 3: " + arrScore, Toast.LENGTH_SHORT).show();
                    tam++;
                }
            }while (tam < positionBuntton.getInt("noLimitTime",0));

            data4.addAll(data1);
        }
        else if(positionBuntton.getString("myuser","").equals("2")){
            int tam =0;
            do {
                data2.add(new Level(tam+1,1));
                tam++;
            }while (tam <= positionBuntton.getInt("normal",0));
            data4.addAll(data2);

        }
        else{
            int tam =0;
            do {
                data3.add(new Level(tam+1,1));
                tam++;
            }while (tam <= positionBuntton.getInt("hard",0));
            data4.addAll(data3);
        }
        return data4;
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
                    showNotice(this,"You have 5 seconds to memorize all images!","TIME:  NO LIMIT TIME", String.valueOf(position));
                }
                if (position == 3 || position == 4 || position == 5) {
                    levelSelected = 7;
                    showNotice(this,"You have 7 seconds to memorize all images!","TIME:  NO LIMIT TIME", String.valueOf(position));
                }
                if (position == 6 || position == 7 || position == 8 || position == 9) {
                    levelSelected = 10;
                    showNotice(this,"You have 10 seconds to memorize all images!","TIME:  NO LIMIT TIME", String.valueOf(position));
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
            alertDialogBuilder.setCancelable(true);
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
                }
            });
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
