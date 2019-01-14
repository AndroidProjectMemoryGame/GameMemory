package com.example.hoih.my.gamememory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

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
            if(pos <= 4){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            }

            else if(pos > 4 && pos < 6){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            }else if(pos == 6){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(130, 130));
            }
            else if(pos > 6 && pos <=8){
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
            }else {
                buttonView.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            }
            buttonView.setBackgroundResource(R.drawable.border_game);
            buttonView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
