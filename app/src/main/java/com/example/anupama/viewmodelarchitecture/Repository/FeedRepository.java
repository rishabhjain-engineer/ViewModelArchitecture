package com.example.anupama.viewmodelarchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.anupama.viewmodelarchitecture.Database.AppDatabase;
import com.example.anupama.viewmodelarchitecture.Database.Dao.FeedDao;
import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;

import java.util.List;

public class FeedRepository {

    private FeedDao mFeedDao;
    private LiveData<List<FeedEntity>> mAllFeeds;

    public FeedRepository(Application application) {
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


    public void getDataEntryCount(String momentId){
        new DataExist(mFeedDao).execute(momentId);
    }

    private static class DataExist extends AsyncTask<String,Void,Void>{

        private FeedDao dataDao ;

        public DataExist(FeedDao mFeedDao) {
            dataDao = mFeedDao ;
        }


        @Override
        protected Void doInBackground(String... strings) {

            Log.e("Rishabh","data: "+strings[0]);
            String a = dataDao.getParticularMomentId(strings[0]);
            Log.e("Rishabh","a: "+a);
            return null;
        }
    }


}
