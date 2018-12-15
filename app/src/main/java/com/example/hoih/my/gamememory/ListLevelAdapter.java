package com.example.hoih.my.gamememory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListLevelAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<Level> listLevel = new ArrayList<>();

    public ListLevelAdapter(Context context, ArrayList<Level> m) {
        mContext = context;
        listLevel = m;
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_level_detail, parent, false);

        Button btnLevel = (Button)rowView.findViewById(R.id.btn_level);

        btnLevel.setText(listLevel.get(position).getLevel());

        return rowView;
    }
}
