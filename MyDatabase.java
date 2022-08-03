package com.example.contactdayari;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, "student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table contactbook (id integer primary key autoincrement, name TEXT, contact TEXT)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(String nm, String contac) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String qry = "insert into contactbook values(NULL,'" + nm + "','" + contac + "')";
        sqLiteDatabase.execSQL(qry);
    }

    public Cursor showData() {
        SQLiteDatabase db = getReadableDatabase();
        String qry = "select * from contactbook";
        Cursor cursor = db.rawQuery(qry, null);

        return cursor;
    }

    public void deleteData(int pos) {
        SQLiteDatabase db = getWritableDatabase();

        String qry = "delete from contactbook where id = '" + pos + "'";
        db.execSQL(qry);
    }

    public void updateData(int pos, String newname, String newcontact) {
        SQLiteDatabase db = getWritableDatabase();

        String qry = "update contactbook set name = '" + newname + "',contact='" + newcontact + "' where id = '" + pos + "'";
        db.execSQL(qry);
    }
}
