package com.example.codelabsroomwithview;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("delete from word_table")
    void deleteAll();

    @Query("select * from word_table order by word ASC")
    LiveData<List<Word>> getAllWord();
}
