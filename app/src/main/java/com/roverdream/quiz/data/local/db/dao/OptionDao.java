package com.roverdream.quiz.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.roverdream.quiz.data.model.db.Option;

import java.util.List;

@Dao
public interface OptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Option option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Option> options);

    @Query("SELECT * FROM options")
    List<Option> loadAll();

    @Query("SELECT * FROM options WHERE question_id = :questionId")
    List<Option> loadAllByQuestionId(Long questionId);
}
