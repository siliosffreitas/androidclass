package br.com.cab.aula12.persistencia.bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String BANCO_DADOS = "BancoDeDados";
    private static int VERSAO = 1;
    
	public DatabaseHelper(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS Linguagem ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome TEXT NOT NULL,"
				+ "codigo TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE Linguagem ADD COLUMN pais TEXT");
	}

}
