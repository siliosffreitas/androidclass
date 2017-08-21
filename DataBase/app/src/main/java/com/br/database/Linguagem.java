package com.br.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by silio on 20/08/17.
 */

public class Linguagem implements Parcelable {

    private long id;
    private String nome;
    private String codigo;

    protected Linguagem(Parcel in) {

        id = in.readLong();
        nome = in.readString();
        codigo = in.readString();
    }

    public Linguagem() {
    }

    public static final Creator<Linguagem> CREATOR = new Creator<Linguagem>() {
        @Override
        public Linguagem createFromParcel(Parcel in) {
            return new Linguagem(in);
        }

        @Override
        public Linguagem[] newArray(int size) {
            return new Linguagem[size];
        }
    };

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
