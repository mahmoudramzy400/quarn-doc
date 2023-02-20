package com.example.qurandoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private static final String ARABIC_AYATS_TABLE ="ayats_arabic";
    private static final String UTHMANI_AYATS_COLUMN ="text_uthmani_smart";
    private static final String AYAT_INDEX_COLUMN = "ayat_index";



    private TextView ayaTextView;
    private SQLiteDatabase database;
    private  String[] uthmanQuarnAyats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //ayaTextView = findViewById(R.id.text_aya);

        readUthmaniAyats();

        DatabaseHelper databaseHelper =  new DatabaseHelper(this);
        database = databaseHelper.openDatabase();
        Log.d(TAG, "onCreate: dataBase: "+ database.toString());

        query(database);
        updateUthmaniColumn();
    }

    private void readUthmaniAyats(){
        String str = "" ;
        try {

            InputStream inputStream = getAssets().open("HafsSmart_08.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            str = new String(buffer,"utf-8");


            str =str.trim();
            str = str.replaceAll("\n" ,"");
            str = str.replaceAll("\n\n" ,"");

          // uthmanQuarnAyats =  str.split("[٠-٩]+");
            uthmanQuarnAyats =  str.split("[\uE95A-\uE962]+");

            Log.d(TAG, "readFile: array size :"+ uthmanQuarnAyats.length);

            for (int i = 0 ; i <300 ;i++){
                Log.d(TAG, "readFile: line :" +uthmanQuarnAyats[i]);
            }


        } catch (IOException e) {
            Log.e(TAG, "readFile: error:"+ e
            );
            throw new RuntimeException(e);
        }
    }
    private void query(SQLiteDatabase sqLiteDatabase){
       Cursor cursor =
               sqLiteDatabase.query(ARABIC_AYATS_TABLE,
                       null,
                      null ,
                       null,
                       null,
                       null,
                       null) ;

       String[] columnsName = cursor.getColumnNames();
       for (String column : columnsName){
           Log.d(TAG, "query: cursor: "+ column);
       }

    }
    private void updateUthmaniColumn(){


        for (int i =0 ;i<  uthmanQuarnAyats.length ; i++) {
            String aya = uthmanQuarnAyats[i];

            ContentValues contentValues = new ContentValues();
            contentValues.put(UTHMANI_AYATS_COLUMN,aya);
            database.update(ARABIC_AYATS_TABLE,contentValues,AYAT_INDEX_COLUMN + " = ?",new String[]{i+1+""});

        }

    }



}