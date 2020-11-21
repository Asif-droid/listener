package com.example.listener;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class data_handeller extends SQLiteOpenHelper {

    private static final String data_basename="Contacts";
    private static final String table_name="contact_table";
    private static final String id="id";
    private static final String name="name";
    private  static final String phn="phone";
    private static final String mail="E_mail";
    public data_handeller(@Nullable Context context) {
        super(context, data_basename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="CREATE TABLE "+ table_name+"( "+id+" INTEGER PRIMARY KEY , "+name+" TEXT , "
                +phn+" TEXT , "+mail+" TEXT )";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    public void addcontact(contacts person){
        ContentValues values=new ContentValues();
        values.put(name,person.getName());
        values.put(phn,person.getPhn());
        values.put(mail,person.getMail_add());
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(table_name,null,values);
        db.close();
    }
    public contacts find(String q_name){
        String query="Select * From "+table_name+" Where "+name+" = \""+q_name+"\""; //"where "+name+" = \""+q_name+"\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        contacts rslt_contact=new contacts();
       if(cursor.moveToFirst()){
           cursor.moveToFirst();
           rslt_contact.setId(Integer.parseInt(cursor.getString(0)));
           rslt_contact.setName(cursor.getString(1));
           rslt_contact.setPhn(cursor.getString(2));
           rslt_contact.setMail_add(cursor.getString(3));
           cursor.close();
       }
       else{
           rslt_contact=null;
       }
       db.close();
        return rslt_contact;


    }


}
