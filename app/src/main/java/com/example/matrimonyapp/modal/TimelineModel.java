package com.example.matrimonyapp.modal;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.example.matrimonyapp.R;

public class TimelineModel {

    String userId;
    String userName;
    String userQualification;
    String userAge;
    String userBio;
    String userBirthday;
    String userMobileNo;
    String userOccupation;
    String userEmail;
    //Uri profilePic;
    String profilePic;
    Context context;

    public TimelineModel()
    {}

    public TimelineModel(Context context) {
        //this.context = context;
        this.userId = "";
        this.userName = "";
        this.userAge = "";
        this.userQualification = "";
        this.userBio = "";
        //this.profilePic = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+context.getResources().getResourcePackageName(R.drawable.flower2)
        //        +"/"+context.getResources().getResourceTypeName(R.drawable.flower2)
        //        +"/"+context.getResources().getResourceEntryName(R.drawable.flower2));
    }

    public TimelineModel(String userId, String userName, String userAge, String userQualification, String userBio, String profilePic, Context context) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userQualification = userQualification;
        this.userBio = userBio;
        this.profilePic = profilePic;
        this.context = context;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserQualification() {
        return userQualification;
    }

    public void setUserQualification(String userQualification) {
        this.userQualification = userQualification;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
