package com.roverdream.quiz.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

}
