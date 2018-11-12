package com.example.anupama.viewmodelarchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;
import com.example.anupama.viewmodelarchitecture.Repository.FeedRepository;

import java.util.List;

public class FeedViewModel extends AndroidViewModel {

    private FeedRepository mRepository;
    private LiveData<List<FeedEntity>> mAllFeeds;

    public FeedViewModel(Application application) {
        super(application);
        mRepository = new FeedRepository(application);
        mAllFeeds = mRepository.getAllFeeds();
    }

    /*public FeedViewModel (Application application) {
        super(application);
        mRepository = new FeedRepository(application);
        mAllFeeds = mRepository.getAllFeeds();
    }
*/
    public LiveData<List<FeedEntity>> getAllFeeds() { return mAllFeeds; }

    public void insert(FeedEntity FeedEntity) {
        mRepository.insert(FeedEntity);
    }

}
