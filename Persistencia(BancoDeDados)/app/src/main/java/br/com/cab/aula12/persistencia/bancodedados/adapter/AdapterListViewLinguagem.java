package br.com.cab.aula12.persistencia.bancodedados.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.cab.aula12.persistencia.bancodedados.R;
import br.com.cab.aula12.persistencia.bancodedados.model.Linguagem;

public class AdapterListViewLinguagem extends ArrayAdapter<Linguagem> {

	private List<Linguagem> list;
	private Activity context;
	
	public AdapterListViewLinguagem(Activity context,
			List<Linguagem> linguagens) {
		super(context, R.layout.item_listview_customizado, linguagens);
		this.context = context;
		this.list = linguagens;
	}
	
	private class ItemSuporte{
		TextView lblNome;
		TextView lblCodigo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(R.layout.item_listview_customizado, null);
		
		final ItemSuporte itemHolder = new ItemSuporte();
		itemHolder.lblNome = ((TextView) convertView.findViewById(R.id.lblLinguagem));
		itemHolder.lblCodigo = ((TextView) convertView.findViewById(R.id.lblCodigo));

		itemHolder.lblNome.setText(list.get(position).getNome());
		itemHolder.lblCodigo.setText(list.get(position).getCodigo());
		return convertView;
	}
}
