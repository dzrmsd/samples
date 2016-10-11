package com.dzrmsd.programreactive.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper helper;
    private static final String database = "data.db";
    private static int version = 1;

    private DBHelper(Context context) {
        super(context, database, null, version);
    }

    public static synchronized DBHelper newInstance(Context context) {
        if (helper == null) helper = new DBHelper(context.getApplicationContext());
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(simpleTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String simpleTable = "create table tasks(_id integer not null primary key, title varchar, content text);";
}
