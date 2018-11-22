package com.example.anupama.viewmodelarchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.anupama.viewmodelarchitecture.Database.AppDatabase;
import com.example.anupama.viewmodelarchitecture.Database.Dao.FeedDao;
import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;

import java.util.List;

public class FeedRepository {

    private FeedDao mFeedDao;
    private LiveData<List<FeedEntity>> mAllFeeds;
    private LiveData<FeedEntity> feedEntityLiveData ;
    private Application application;

    public FeedRepository(Application application) {
        this.application =application;
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mFeedDao = db.feedDao();
        mAllFeeds = mFeedDao.getAllFeeds();
    }

    public LiveData<List<FeedEntity>> getAllFeeds() {
        return mAllFeeds;
    }

    public void insert (FeedEntity feedEntity) {
        new insertAsyncTask(mFeedDao).execute(feedEntity);
    }

    private static class insertAsyncTask extends AsyncTask<FeedEntity, Void, Void> {

        private FeedDao mAsyncTaskDao;

        insertAsyncTask(FeedDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FeedEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll(){
        new DeleteAll(mFeedDao).execute();
    }

    private static class DeleteAll extends AsyncTask<Void,Void,Void>{

        private FeedDao deleteDao ;

        public DeleteAll(FeedDao mFeedDao) {
            deleteDao = mFeedDao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            deleteDao.deleteAll();
            return null;
        }
    }


    public LiveData<FeedEntity> getParticularMomentInfo(String momentId){
         return mFeedDao.getMomentInfo(momentId);
    }




}
