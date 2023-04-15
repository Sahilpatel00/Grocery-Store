package com.sahil.labs.grocerystore.util.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class GroceryDatabase extends SQLiteOpenHelper {
    public static final String Database="GroceryDatabase";
    public static final String TableName="GroceryDatabaseTable";
    public static final String COL1="ID";
    public static final String COL2="USERNAME";
    public static final String COL3="PASSWORD";
    public static final String COL4="USER_DETAIL";
    public static final String COL5="USER_CART";

    public GroceryDatabase(Context context) {
        super(context,Database ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate="CREATE TABLE "+TableName+"("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL2+" TEXT,"+COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT)";
        sqLiteDatabase.execSQL(sqlCreate);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName );
        onCreate(sqLiteDatabase);
    }

    public boolean Insert(String USERNAME,String PASSWORD,String USER_DETAIL,String USER_CART){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,USERNAME);
        contentValues.put(COL3,PASSWORD);
        contentValues.put(COL4,USER_DETAIL);
        contentValues.put(COL5,USER_CART);
        long result=db.insert(TableName,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor Select(String uname){
        SQLiteDatabase db=this.getWritableDatabase();
        String sqlSelect="SELECT * FROM "+TableName+" WHERE "+COL2+"='"+uname+"'";
        Cursor result=db.rawQuery(sqlSelect,null);
        return result;
    }
    public boolean Update(String Uname,String Cart){
        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE "+TableName+" SET "+COL5+" = '"+Cart+"'"+" WHERE "+COL2+" = '"+ Uname+"'";
        Log.i("mytag",strSQL);
        db.execSQL(strSQL);
        return true;
    }

}
