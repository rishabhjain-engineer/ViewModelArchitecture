package com.example.anupama.viewmodelarchitecture.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anupama.viewmodelarchitecture.Database.Dao.FeedDao;
import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;


@Database(entities = {FeedEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE ;
    public abstract FeedDao feedDao();

    public static AppDatabase getAppDatabase(final Context context){

        if(INSTANCE == null) {

            synchronized (AppDatabase.class) {

                if(INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() ,
                            AppDatabase.class ,
                            "app_database")
                            .build() ;

                }
            }

        }
        return INSTANCE;
    }


}
