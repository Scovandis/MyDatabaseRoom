package com.example.mydatabaseroom.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mydatabaseroom.dao.TaskDao;
import com.example.mydatabaseroom.entity.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}
