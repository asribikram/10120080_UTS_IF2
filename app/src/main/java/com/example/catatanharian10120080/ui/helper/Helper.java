package com.example.catatanharian10120080.ui.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


//10120080_habib asrib_IF2
public class Helper extends SQLiteOpenHelper {

   private static final int DATABASE_VERSION =1;
   static final String DATABASE_NAME = "crud";

   public Helper(Context context){
       super(context, DATABASE_NAME, null, DATABASE_VERSION);

   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       final String SQL_CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY autoincrement, judul TEXT NOT NULL, deskripsi TEXT NOT NULL)";
       sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
       onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("judul", cursor.getString(1));
                map.put("deskripsi", cursor.getString(2));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;

        }

        public void insert(String judul, String deskripsi){
            SQLiteDatabase database = this.getWritableDatabase();
            String QUERY = "INSERT INTO users (judul,deskripsi) VALUES('" + judul + "', '" + deskripsi + "')";
            database.execSQL(QUERY);
        }

        public void update(int id, String judul, String deskripsi){
            SQLiteDatabase database = this.getWritableDatabase();
            String QUERY = "UPDATE users SET judul = '"+judul+"', deskripsi = '"+deskripsi+"' WHERE id = "+id;
            database.execSQL(QUERY);

        }

        public void delete(int id){
             SQLiteDatabase database = this.getWritableDatabase();
             String QUERY ="DELETE FROM users WHERE id = "+id;
             database.execSQL(QUERY);

    }

}
