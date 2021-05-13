package com.example.appcarros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    public static void inserir(Carro carro, Context context){
        ContentValues valores = new ContentValues();
        valores.put("modelo", carro.getModelo());
        valores.put("montadora", carro.getMontadora());
        valores.put("ano", carro.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("carro", null, valores);
    }

    public static void editar(Carro carro, Context context){
        ContentValues valores = new ContentValues();
        valores.put("modelo", carro.getModelo());
        valores.put("montadora", carro.getMontadora());
        valores.put("ano", carro.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("carro", valores, "id = " + carro.getId(), null);
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("carro", "id = " + id, null);
    }

    public static List<Carro> getCarros(Context context){
        List<Carro> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM carro ORDER BY modelo", null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Carro carro = new Carro();
                carro.setId(cursor.getInt(0));
                carro.setModelo(cursor.getString(1));
                carro.setMontadora(cursor.getString(2));
                carro.setAno(cursor.getInt(3));

                lista.add(carro);

            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static Carro getCarroById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM carro WHERE id = " + id, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Carro carro = new Carro();
            carro.setId(cursor.getInt(0));
            carro.setModelo(cursor.getString(1));
            carro.setMontadora(cursor.getString(2));
            carro.setAno(cursor.getInt(3));
            return carro;
        } else {
            return null;
        }
    }
}
