package com.roverdream.quiz.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.roverdream.quiz.data.local.db.dao.OptionDao;
import com.roverdream.quiz.data.local.db.dao.QuestionDao;
import com.roverdream.quiz.data.local.db.dao.UserDao;
import com.roverdream.quiz.data.model.db.Option;
import com.roverdream.quiz.data.model.db.Question;
import com.roverdream.quiz.data.model.db.User;

@Database(entities = {User.class, Question.class, Option.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OptionDao optionDao();

    public abstract QuestionDao questionDao();

    public abstract UserDao userDao();
}
