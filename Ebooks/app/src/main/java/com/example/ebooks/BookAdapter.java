package com.example.ebooks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by IvanaPlamella on 18/08/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.UFViewHolder> {

    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public UFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        UFViewHolder vh = new UFViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UFViewHolder holder, int position) {
        holder.lblTitle.setText(books.get(position).getVolumeInfo().getTitle());
        holder.lblDescription.setText(books.get(position).getVolumeInfo().getDescription());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class UFViewHolder extends RecyclerView.ViewHolder {

        public TextView lblTitle;
        public TextView lblDescription;

        public UFViewHolder(View v) {
            super(v);
            lblTitle = (TextView) v.findViewById(R.id.lblTitle);
            lblDescription = (TextView) v.findViewById(R.id.lblDescription);
        }
    }
}

