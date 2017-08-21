package com.br.database.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.database.Linguagem;
import com.br.database.MainActivity;
import com.br.database.R;
import com.br.database.views.OnClick;

import java.util.List;

/**
 * Created by silio on 19/08/17.
 */

public class LinguagemAdapter extends RecyclerView.Adapter<LinguagemAdapter.UFViewHolder> {

    private List<Linguagem> linguagems;
    private Context mContext;

    public LinguagemAdapter(Context c, List<Linguagem> linguagems){
        mContext = c;
        this.linguagems = linguagems;
    }

    @Override
    public UFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linguagem_item, parent, false);

        UFViewHolder vh = new UFViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UFViewHolder holder, int position) {
        holder.lblLinguagem.setText(linguagems.get(position).getNome());
        holder.lblCodigo.setText(linguagems.get(position).getCodigo());
    }

    @Override
    public int getItemCount() {
        return linguagems.size();
    }

    class UFViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView lblLinguagem;
        private TextView lblCodigo;

        public UFViewHolder(View itemView) {
            super(itemView);
            lblLinguagem = (TextView) itemView.findViewById(R.id.lblLinguagem);
            lblCodigo = (TextView) itemView.findViewById(R.id.lblCodigo);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int positionClicked = getAdapterPosition();
            ((MainActivity)mContext).onClick(positionClicked);

        }

        @Override
        public boolean onLongClick(View v) {
            int positionClicked = getAdapterPosition();
            ((MainActivity)mContext).onLonClick(positionClicked);
            return false;
        }
    }

}
