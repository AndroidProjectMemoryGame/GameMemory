package com.example.hoih.my.gamememory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
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
                Intent i = new Intent(ModeActivity.this, LevelDetailActivity.class);
                i.putExtra("level",levelData.get(position).getLevel());
                startActivity(i);
            }
        });
    }

    private ArrayList<Level> getData(){
        ArrayList<Level> data = new ArrayList<>();
        data.add(new Level(1));
        data.add(new Level(2));
        data.add(new Level(3));
        data.add(new Level(4));
        data.add(new Level(5));
        data.add(new Level(6));
        data.add(new Level(7));
        data.add(new Level(8));
        data.add(new Level(9));
        data.add(new Level(10));
        return data;
    }

    @Override
    public void Levell(int position) {

        if (getIntent() != null) {
            if (getIntent().getStringExtra("mode").equals("1")) {
                showNotice(this,"You have 5 seconds to rememorize all images!","TIME:  NO TIME LIMIT", String.valueOf(position));
            } else if (getIntent().getStringExtra("mode").equals("2")) {
                showNotice(this,"You have 5 seconds to rememorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  30 s", String.valueOf(position));
            } else {
                showNotice(this,"You have 5 seconds to rememorize all images! \n\n Bonus: 5 seconds extra per match","TIME:  5 s",String.valueOf(position));
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
        if (getIntent() != null) {
            if (getIntent().getStringExtra("mode").equals("1")) {
                txtMode.setText("No Limit Time");
            } else if (getIntent().getStringExtra("mode").equals("2")) {
                txtMode.setText("Normal");
            } else {
                txtMode.setText("Hard");
            }
        }
    }
}
