package com.example.hoih.my.gamememory;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListGameAdapter  extends BaseAdapter {
    private boolean loadAll = false;
    private Context mContext;
    int  pos1;
    Random r = new Random();
    ArrayList<Integer> mang = new ArrayList<>();
    int random;
    int dem =0;

    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();
    ArrayList<Integer> arrayList3 = new ArrayList<>();
    ArrayList<Integer> arrayList = new ArrayList<>();


    public ListGameAdapter(Context c, int pos, ArrayList<Integer> arrayList) {
        mContext = c;
        pos1 = pos+1;
        arrayList= arrayList;
        setData();
        randomArray();
        if (dem == 1) {
            compare();
        }
        compare();

        Log.d("MISSION", "Number : " + arrayList + " : quangkhanh = "  );

    }



    public void setTrue(){
        loadAll = true;
    }
    public int getCount() {
        Log.d("MISSION", "Number : " +   mThumbIds.length + " : succeed5 = "  );
        Log.d("MISSION", "Number : " + arrayList1.size() + " : thamdaubo = "  );

            return arrayList3.size();


    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton buttonView;
        Log.d("MISSION", "Number : " + arrayList+ " : thamdaubohaha = "  );
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            buttonView = new ImageButton(mContext);
            if(pos1 <4){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(180, 180));
            }

            else if(pos1 == 4){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            }
            else buttonView.setLayoutParams(new ViewGroup.LayoutParams(120, 120));

            buttonView.setBackgroundResource(R.drawable.border_game);
            buttonView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            buttonView.setPadding(4, 4, 4, 4);
        } else {
            buttonView = (ImageButton) convertView;
        }

        buttonView.setImageResource(arrayList3.get(mang.get(position)));
        return buttonView;
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
            for (int j = 0; j <2; j++) {
                for (int i = 0; i < ((pos1 + 1) * 2) / 2; i++) {
                    arrayList1.add(mThumbIds[i]);
                    arrayList2.add(test[0]);
                }
            }

        }

    public ArrayList<Integer> randomArray() {
        Random rand = new Random();
        while (mang.size() < (pos1 + 1) * 2) {

            int random = rand.nextInt((pos1 + 1) * 2);
            if (!mang.contains(random)) {
                mang.add(random);
            }

        }
//        for (int i = 0; i < 8; i++) {
//            int a = mang.get(i);
//            Log.d("MISSION", "Number : " + a + " : khanhcute = ");
//        }
        return mang;
    }

    public void compare(){
        CountDownTimer start = new CountDownTimer(5000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                for (int j = 0; j < 2; j++) {
                    for (int i = 0; i < ((pos1 + 1) * 2) / 2; i++) {
                        arrayList3.add(arrayList1.get(i));
                    }
                }
                Log.d("MISSION", "Number : " + arrayList3 + " : quangkhanhhahaha = "  );
            }

            @Override
            public void onFinish() {
                for (int j = 0; j < 2; j++) {
                    for (int i = 0; i < ((pos1 + 1) * 2) / 2; i++) {
                        arrayList3.add(arrayList2.get(i));
                    }
                }

                Log.d("MISSION", "Number : " + arrayList3 + " : quangkhanhhahaha = "  );
            }
        }.start();
    }
}
