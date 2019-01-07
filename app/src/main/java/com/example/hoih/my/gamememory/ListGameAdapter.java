package com.example.hoih.my.gamememory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import static android.content.ContentValues.TAG;
import static android.content.Intent.getIntent;

public class ListGameAdapter  extends BaseAdapter {
    private boolean loadAll = false;
    private Context mContext;
    int  pos1;
    Random r = new Random();
    ArrayList<Integer> mang = new ArrayList<>();
    int random;
    int j =0;

    ArrayList<Integer> arrayList = new ArrayList<>();


    public ListGameAdapter(Context c,int pos ) {
        mContext = c;
        pos1 = pos+1;
        setData();

        randomArray();

    }
    public void setTrue(){
        loadAll = true;
    }
    public int getCount() {
        Log.d("MISSION", "Number : " +   mThumbIds.length + " : succeed5 = "  );
        Log.d("MISSION", "Number : " + arrayList.size() + " : thamdaubo = "  );

        return arrayList.size();


    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(pos1 <4){
                imageView.setLayoutParams(new ViewGroup.LayoutParams(180, 180));
            }

            else if(pos1 == 4){
                imageView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            }
            else imageView.setLayoutParams(new ViewGroup.LayoutParams(120, 120));

            imageView.setBackgroundResource(R.drawable.border_game);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(arrayList.get(mang.get(position)));

        return imageView;

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

        private void setData(){
            for (int j = 0; j <2; j++) {
                for (int i = 0; i < ((pos1 + 1) * 2) / 2; i++) {
                    arrayList.add(mThumbIds[i]);
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
            Log.d("MISSION", "Number : " + mang + " : succeed5 = ");
        }
//        for (int i = 0; i < 8; i++) {
//            int a = mang.get(i);
//            Log.d("MISSION", "Number : " + a + " : khanhcute = ");
//
//        }
        return mang;
    }


}
