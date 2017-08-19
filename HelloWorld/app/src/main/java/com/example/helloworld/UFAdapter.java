package com.example.helloworld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UFAdapter extends RecyclerView.Adapter<UFAdapter.UFViewHolder> {

    private String[] mDataset;

    public UFAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uf, parent, false);

        UFViewHolder vh = new UFViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UFViewHolder holder, int position) {
        holder.lblUf.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class UFViewHolder extends RecyclerView.ViewHolder {

        public TextView lblUf;

        public UFViewHolder(View v) {
            super(v);
            lblUf = (TextView) v.findViewById(R.id.lblUf);
        }
    }
}
