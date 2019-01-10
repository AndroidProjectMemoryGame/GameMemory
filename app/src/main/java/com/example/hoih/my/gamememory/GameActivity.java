package com.example.hoih.my.gamememory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements GameAdapter.OnClickListener {
    GridView gridview;
    ArrayList<Integer> mang = new ArrayList<>();
    int i, count =0;
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
         gridview = (GridView) findViewById(R.id.gridview);
        Intent intent = getIntent();
        i = Integer.parseInt(intent.getStringExtra("pos"));


        setData();
        randomArray();
        gridview.setAdapter(new GameAdapter(arrayList1,GameActivity.this,i,this,randomArray()));

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("MISSION", "Number : " + arrayList1 + " : quangkhanhthum1 = ");
            }

            @Override
            public void onFinish() {
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

    public ArrayList<Integer> randomArray() {
        Random rand = new Random();
        while (mang.size() < (i + 2) * 2) {

            int random = rand.nextInt((i + 2) * 2);
            if (!mang.contains(random)) {
                mang.add(random);
            }
        }
        Log.d("MISSION", "Number : " + mang + " : quangkhanhthumhaha5 = ");
        return mang;
    }

    @Override
    public void Image(final int position) {
        int a = mang.get(position);



            arrayList2.remove(a);
            arrayList2.add(mang.get(position), arrayList1.get(mang.get(position)));
            gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, randomArray()));
            count++;
        if (count == 1){
            arrayList2.remove(a);
            arrayList2.add(mang.get(position), arrayList1.get(mang.get(position)));
            gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, randomArray()));

            gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, mang));
            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("MISSION", "Number : " + arrayList1 + " : quangkhanhthum1 = ");
                }

                @Override
                public void onFinish() {
                    arrayList2.clear();
                    setData();

                    gridview.setAdapter(new GameAdapter(arrayList2, GameActivity.this, i, GameActivity.this, randomArray()));
                    Log.d("MISSION", "Number : " + arrayList1 + " : quangkhanhthum2 = ");
                    count = 0;
                }
            }.start();
        }

    }

}
