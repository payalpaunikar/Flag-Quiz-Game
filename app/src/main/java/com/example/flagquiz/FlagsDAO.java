package com.example.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO {

    //here we pull data from database ;

    //her we make array for ten quenstion answer;
    public ArrayList<FlagsModel> getRandomTenquestion(FlagsDatabase fd)
    {

        ArrayList<FlagsModel> modelArrayList=new ArrayList<>();

        //we create object from sqlitedatabaseclass query;

        SQLiteDatabase liteDatabase=fd.getWritableDatabase();

        //object from cursor to read data by line by line

        Cursor cursor=liteDatabase.rawQuery("SELECT * FROM flagquizgametable ORDER BY RANDOM() LIMIT 10",null);
               //rawQuery(SELECT * FROM DATABASENAME)  is used to get data from database
              //but we need random 10 data so we write here ORDER BY RANDOM () LIMIT WHAT LIMIT WE WANT WRITE IN INTEGER method here ;


        //here we pass column information in this variable;
         int flagIdIndex = cursor.getColumnIndex("flag_id");
         int flagNameIndex = cursor.getColumnIndex("flag_name");
         int flagImageIndex = cursor.getColumnIndex("flag_image");

         //cursor.moveToNext() is method used for that cursor object read data start form first data in database and read untill the last
         while (cursor.moveToNext())
         {
             //now we transfer read data to an object we will creat from model(structure)
             FlagsModel model=new FlagsModel(cursor.getInt(flagIdIndex),
                     cursor.getString(flagNameIndex)
                     ,cursor.getString(flagImageIndex));

             //transfer data to our arrayList
             modelArrayList.add(model);
         }
         return  modelArrayList;

    }


    //In the method we writw code for wrong answer;
    //In this method flag_id store right answer;
    public ArrayList<FlagsModel> getRandomThreeOptins(FlagsDatabase fd,int flag_id)
    {

        ArrayList<FlagsModel> modelArrayList=new ArrayList<>();

        //we create object from sqlitedatabaseclass query;

        SQLiteDatabase liteDatabase=fd.getWritableDatabase();

        //object from cursor to read data by line by line

        Cursor cursor=liteDatabase.rawQuery("SELECT * FROM flagquizgametable WHERE flag_id !="+flag_id+" ORDER BY RANDOM() LIMIT 3",null);
        //rawQuery(SELECT * FROM DATABASENAME)  is used to get data from database
        //but we need random 3 data so we write here ORDER BY RANDOM () LIMIT WHAT LIMIT WE WANT WRITE IN INTEGER method here ;
       //using WHERE flag_id we restricated that it will not be repeated;


        //here we pass column information in this variable;
        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        //cursor.moveToNext() is method used for that cursor object read data start form first data in database and read untill the last
        while (cursor.moveToNext())
        {
            //now we transfer read data to an object we will creat from model(structure)
            FlagsModel model=new FlagsModel(cursor.getInt(flagIdIndex),
                    cursor.getString(flagNameIndex)
                    ,cursor.getString(flagImageIndex));

            //transfer data to our arrayList
            modelArrayList.add(model);
        }
        return  modelArrayList;

    }
}
