package com.example.anupama.viewmodelarchitecture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anupama.viewmodelarchitecture.Adapter.MainAdapter;
import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;
import com.example.anupama.viewmodelarchitecture.Network.ApiRequest;
import com.example.anupama.viewmodelarchitecture.Network.ApiServices;
import com.example.anupama.viewmodelarchitecture.Network.RetrofitInstance;
import com.example.anupama.viewmodelarchitecture.ViewModel.FeedViewModel;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements ApiRequest.NetworkResponse {

    private RecyclerView mFeedRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainAdapter mMainAdapter;
    private List<FeedEntity> mFeedList = new ArrayList<>();
    private final String AUTH_TOKEN = "kbvNISE2swVMxWj29EnZhg";
    public FeedViewModel mFeedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFeedRecyclerView = findViewById(R.id.feedlist);
        mLayoutManager = new LinearLayoutManager(this);
        mFeedRecyclerView.setLayoutManager(mLayoutManager);
        mFeedRecyclerView.setHasFixedSize(true);

        mMainAdapter = new MainAdapter(mFeedList);
        mFeedRecyclerView.setAdapter(mMainAdapter);

        mFeedViewModel = ViewModelProviders.of(MainActivity.this).get(FeedViewModel.class) ;

        getFeedApiResult();
        getNotificationApiResult();


        mFeedViewModel.getAllFeeds().observe(this, new Observer<List<FeedEntity>>() {
            @Override
            public void onChanged(@Nullable List<FeedEntity> feedEntities) {
                Log.e("Rishabh","feedEntites size: "+feedEntities.size());
                mFeedList.addAll(feedEntities);
                mMainAdapter.notifyDataSetChanged();
            }
        });
    }


    private void getFeedApiResult() {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<JsonElement> call = apiServices.getMoments("1", "20", "", AUTH_TOKEN);
        ApiRequest.getApiRequestInstance().hitAPIRequest(MainActivity.this, call, "getFeedApiResult");
    }


    private void getNotificationApiResult() {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<JsonElement> call = apiServices.getNotifications(AUTH_TOKEN);
        ApiRequest.getApiRequestInstance().hitAPIRequest(MainActivity.this, call, "getNotificationApiResult");

    }


    private void parseFeedResult(JSONObject jsonObject) {

        try {
            String moments = jsonObject.optString("moments");
            JSONArray jsonArray = new JSONArray(moments);
            if(jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject innerJsonObject = jsonArray.optJSONObject(i);
                    String momentId = innerJsonObject.optString("id");
                    String description = innerJsonObject.optString("description");
                    String location = innerJsonObject.optString("location");
                    String sportId = innerJsonObject.optString("sport_id");
                    String commentCount = innerJsonObject.optString("comment_count");
                    String likeCount = innerJsonObject.optString("like_count");
                    String viewCount = innerJsonObject.optString("view_count");
                    String userInfo = innerJsonObject.optString("user_info");
                    JSONObject userInfoObject = new JSONObject(userInfo);
                    String username = userInfoObject.optString("username");
                    String momentVideo = innerJsonObject.optString("video");
                    String momentImage = innerJsonObject.optString("image");

                    FeedEntity feedEntity = new FeedEntity();
                    feedEntity.setMomentId(momentId);
                    feedEntity.setMomentDescription(description);
                    feedEntity.setMomentLocation(location);
                    feedEntity.setMomentSportId(sportId);
                    feedEntity.setComment_count(commentCount);
                    feedEntity.setUsername(username);
                    mFeedViewModel.insert(feedEntity);
                }



            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Rishabh","exception: "+e.toString());
        }

    }


    private void parseNotificationResult(JSONObject jsonObject) {

    }

    @Override
    public void onNetworkSuccess(JSONObject jsonObject, String tag) {

        Log.e("Rishabh","NetworkSuccess, tag: "+tag);

        switch (tag) {

            case "getFeedApiResult":
                parseFeedResult(jsonObject);
                break;

            case "getNotificationApiResult":
                parseNotificationResult(jsonObject);
                break;

            default:
                break;

        }

    }

    @Override
    public void onNetworkError(String error) {
        Log.e("Rishabh","NetworkError: "+error);
    }
}
