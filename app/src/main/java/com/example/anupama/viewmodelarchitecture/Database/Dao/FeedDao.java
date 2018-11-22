package com.example.anupama.viewmodelarchitecture.Database.Dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;

import java.util.List;

@Dao
public interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FeedEntity feedEntity);

    @Query("DELETE FROM feed_table")
    void deleteAll();

    @Query("SELECT * from feed_table")
    LiveData<List<FeedEntity>> getAllFeeds();

    @Query("SELECT * from feed_table where momentId = :momentId")
    LiveData<FeedEntity> getMomentInfo(String momentId);


}
