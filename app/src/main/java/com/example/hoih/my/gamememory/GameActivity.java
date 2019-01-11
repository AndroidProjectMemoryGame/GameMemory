package com.example.hoih.my.gamememory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements GameAdapter.OnClickListener {
    GridView gridview;
    ArrayList<Integer> mang = new ArrayList<>();
    int i, count =0, gan, pos;
    boolean check;

    ArrayList<Integer> arrayPos = new ArrayList<>();
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();

    ImageButton btnBack;

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
        check = false;

        new CountDownTimer(2000, 1000) {
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
            View view = inflater.inflate(R.layout.dialog_end_game, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(false);
            final AlertDialog dialog = alertDialogBuilder.create();
            Button btnGo = (Button) view.findViewById(R.id.btn_end);


            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(GameActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            });
            //   txtMessage.setText(message);
            if (context instanceof Activity && !((Activity) context).isFinishing())
                dialog.show();
        }
    }

}
