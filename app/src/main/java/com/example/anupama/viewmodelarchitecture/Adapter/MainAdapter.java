package com.example.anupama.viewmodelarchitecture.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anupama.viewmodelarchitecture.Database.Entity.FeedEntity;
import com.example.anupama.viewmodelarchitecture.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<FeedEntity> mFeedList ;

    public MainAdapter(List<FeedEntity> mFeedList){
        this.mFeedList = mFeedList ;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row,viewGroup,false);
        MainViewHolder mainViewHolder = new MainViewHolder(view);
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        mainViewHolder.textView1.setText(mFeedList.get(i).getMomentId());
        //mainViewHolder.textView2.setText(mFeedList.get(i).getUserfullname());
        mainViewHolder.textView3.setText(mFeedList.get(i).getUsername());
        //mainViewHolder.textView4.setText(mFeedList.get(i).getMomentUrl());

    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView textView1, textView2, textView3, textView4 ;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            textView3 = itemView.findViewById(R.id.text3);
            textView4 = itemView.findViewById(R.id.text4);

        }
    }
}
