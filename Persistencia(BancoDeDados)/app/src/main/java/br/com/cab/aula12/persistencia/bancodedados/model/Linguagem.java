package br.com.cab.aula12.persistencia.bancodedados.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Linguagem implements Parcelable {

	private long id;
	private String nome;
	private String codigo;
	
	public Linguagem() {
	}

	public Linguagem(long id, String nome, String codigo){
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
	}

	
	public Linguagem(Parcel in) {
		readFromParcel(in);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(nome);
		dest.writeString(codigo);
	}
	
	private void readFromParcel(Parcel in) {
		id = in.readLong();
		nome = in.readString();
		codigo = in.readString();
	}
	
	public static final Creator<Linguagem> CREATOR = new Creator<Linguagem>() {
		public Linguagem createFromParcel(Parcel source) {
			return new Linguagem(source);
		}
		
		public Linguagem[] newArray(int size) {
			return new Linguagem[size];
		}
	};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
