package com.example.mydatabaseroom.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient mInstance;

    //our appdatabase objct
    private AppDatabase appDatabase;

    private DatabaseClient(Context context){
        this.context = context;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (mInstance == null){
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
