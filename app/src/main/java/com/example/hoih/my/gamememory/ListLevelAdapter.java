package com.example.hoih.my.gamememory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListLevelAdapter extends BaseAdapter{

    private Context mContext;
    OnClickListener mListener;

    private ArrayList<Level> levelData = new ArrayList<>();

    public ListLevelAdapter(Context context, ArrayList<Level> level, OnClickListener Levell) {
        mContext = context;
        levelData = level;
        mListener = Levell;
    }

    @Override
    public int getCount() {
        return levelData.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_level, parent, false);

        Button btnLevel = (Button)rowView.findViewById(R.id.btn_level);
        btnLevel.setText("Level " + levelData.get(position).getLevel());

        btnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!= null){
                    mListener.Levell(position);
                }
            }
        });

        return rowView;
    }

    public interface OnClickListener{
        void Levell(int position);
    }
}
