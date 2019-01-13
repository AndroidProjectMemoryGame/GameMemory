package com.example.hoih.my.gamememory;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameAdapter extends BaseAdapter {
    private ArrayList<Integer> listData;
    ArrayList<Integer> mang = new ArrayList<>();
    private Context context;
    private int pos;
    OnClickListener mListener;

    public GameAdapter(ArrayList<Integer> listData, Context context, int pos,OnClickListener Image,ArrayList<Integer> mang  ) {
        this.listData = listData;
        this.context = context;
        this.pos = pos+1;
        mListener =Image;
        this.mang =mang;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        ImageButton buttonView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            buttonView = new ImageButton(context);
            if(pos <4){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
            }

            else if(pos == 4){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            }
            else buttonView.setLayoutParams(new ViewGroup.LayoutParams(120, 120));

            buttonView.setBackgroundResource(R.drawable.border_game);
            buttonView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            buttonView.setPadding(2, 2, 2, 2);
        } else {
            buttonView = (ImageButton) convertView;
        }


        buttonView.setImageResource(listData.get(mang.get(position)));
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!= null){
                    mListener.Image(position);
                }
            }
        });
        return buttonView;
    }



    public interface OnClickListener{
        void Image  (int position);
    }
}
