package com.example.anupama.viewmodelarchitecture.Database.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName ="feed_table")
public class FeedEntity {


    public FeedEntity(){

    }

    @PrimaryKey(autoGenerate = true)
    int primarykey;

    @ColumnInfo(name ="momentId")
    private String momentId ;

    @ColumnInfo(name ="momentUrl")
    private String momentUrl ;

    @ColumnInfo(name ="momentDescription")
    private String momentDescription ;

    @ColumnInfo(name ="momentLocation")
    private String momentLocation ;

    @ColumnInfo(name ="momentSportId")
    private String momentSportId ;

    @ColumnInfo(name ="username")
    private String username ;

    @ColumnInfo(name ="userfullname")
    private String userfullname ;

    @ColumnInfo(name ="userId")
    private String userId ;

    @ColumnInfo(name ="userProfileImageUrl")
    private String userProfileImageUrl ;

    @ColumnInfo(name ="isMomentLiked")
    private boolean isMomentLiked ;

    @ColumnInfo(name ="comment_count")
    private String comment_count ;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getMomentUrl() {
        return momentUrl;
    }

    public void setMomentUrl(String momentUrl) {
        this.momentUrl = momentUrl;
    }

    public String getMomentDescription() {
        return momentDescription;
    }

    public void setMomentDescription(String momentDescription) {
        this.momentDescription = momentDescription;
    }

    public String getMomentLocation() {
        return momentLocation;
    }

    public void setMomentLocation(String momentLocation) {
        this.momentLocation = momentLocation;
    }

    public String getMomentSportId() {
        return momentSportId;
    }

    public void setMomentSportId(String momentSportId) {
        this.momentSportId = momentSportId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public boolean isMomentLiked() {
        return isMomentLiked;
    }

    public void setMomentLiked(boolean momentLiked) {
        isMomentLiked = momentLiked;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }
}
