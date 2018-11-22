package com.example.anupama.viewmodelarchitecture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;
import com.example.anupama.viewmodelarchitecture.Repository.FeedRepository;
import com.example.anupama.viewmodelarchitecture.ViewModel.FeedViewModel;

public class DetailActivity extends AppCompatActivity {

    private String mReceivedMomentId ;
    public FeedViewModel mFeedViewModel ;
    private FeedEntity mFeedEntity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getIntent() != null){

            mReceivedMomentId = getIntent().getStringExtra("momentId");
            Log.e("Rishabh","mReceivedMomentId: "+mReceivedMomentId);
        }


        mFeedViewModel = ViewModelProviders.of(DetailActivity.this).get(FeedViewModel.class);
        mFeedViewModel.getParticularMomentInfo(mReceivedMomentId).observe(this, new Observer<FeedEntity>() {
            @Override
            public void onChanged(@Nullable FeedEntity feedEntity) {
                mFeedEntity = feedEntity ;
                Log.e("Rishabh","mFeedEntity after query: "+mFeedEntity.getMomentId());
                Log.e("Rishabh","mFeedEntity after query: "+mFeedEntity.getComment_count());
            }
        });


    }


}
