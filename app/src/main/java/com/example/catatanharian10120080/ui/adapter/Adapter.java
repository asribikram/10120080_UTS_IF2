package com.example.catatanharian10120080.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.catatanharian10120080.R;


import com.example.catatanharian10120080.ui.model.Data;

import java.util.List;



//10120080_habib asrib_IF2
public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }


    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.list_users, null);

        }
        if(view != null) {
            TextView judul = view.findViewById(R.id.text_judul);
            TextView deskripsi = view.findViewById(R.id.text_deskripsi);
            Data data = lists.get(i);
            judul.setText(data.getJudul());
            deskripsi.setText(data.getDeskripsi());
        }
        return view;
    }
}
