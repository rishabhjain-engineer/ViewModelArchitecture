package com.example.anupama.viewmodelarchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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

}
