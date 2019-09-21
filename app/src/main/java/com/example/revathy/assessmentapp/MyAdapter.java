package com.example.revathy.assessmentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class



MyAdapter extends BaseAdapter {

    ArrayList list;
    Context context;
    LayoutInflater inflater;
    public MyAdapter(ArrayList list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
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
        convertView = inflater.inflate(R.layout.test_list,null,true);
        Testmodel model=(Testmodel) list.get(position);
        TextView name=convertView.findViewById(R.id.name);
        TextView difficulty=convertView.findViewById(R.id.difficulty);
        name.setText(model.getName());
        difficulty.setText(model.getDesc());

        return convertView;
    }
}

