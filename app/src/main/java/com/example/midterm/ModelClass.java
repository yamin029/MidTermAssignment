package com.example.midterm;

import android.graphics.Bitmap;

public class ModelClass {
    private Bitmap mImage;
    private String mFirstName;
    private String mLastName;
    private String mDOB;
    private String mAddress;
    private String mEducation;
    private String mSkills;
    private String mExperience;

    public ModelClass(Bitmap mImage, String mFirstName, String mLastName, String mDOB, String mAddress, String mEducation, String mSkills, String mExperience) {
        this.mImage = mImage;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mDOB = mDOB;
        this.mAddress = mAddress;
        this.mEducation = mEducation;
        this.mSkills = mSkills;
        this.mExperience = mExperience;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmDOB() {
        return mDOB;
    }

    public void setmDOB(String mDOB) {
        this.mDOB = mDOB;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEducation() {
        return mEducation;
    }

    public void setmEducation(String mEducation) {
        this.mEducation = mEducation;
    }

    public String getmSkills() {
        return mSkills;
    }

    public void setmSkills(String mSkills) {
        this.mSkills = mSkills;
    }

    public String getmExperience() {
        return mExperience;
    }

    public void setmExperience(String mExperience) {
        this.mExperience = mExperience;
    }
}
