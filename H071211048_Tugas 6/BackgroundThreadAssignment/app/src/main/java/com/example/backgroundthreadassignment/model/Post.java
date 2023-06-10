package com.example.backgroundthreadassignment.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable{
        private String userName, fullName, caption, profile, upload;

        public Post(String userName, String fullName, String caption, String profile, String upload) {
                this.userName = userName;
                this.fullName = fullName;
                this.caption = caption;
                this.profile = profile;
                this.upload = upload;
        }

        protected Post(Parcel in) {
                userName = in.readString();
                fullName = in.readString();
                caption = in.readString();
                profile = in.readString();
                upload = in.readString();
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public String getCaption() {
                return caption;
        }

        public void setCaption(String caption) {
                this.caption = caption;
        }

        public String getProfile() {
                return profile;
        }

        public void setProfile(String profile) {
                this.profile = profile;
        }

        public String getUpload() {
                return upload;
        }

        public void setUpload(String upload) {
                this.upload = upload;
        }

        public static final Creator<Post> CREATOR = new Creator<Post>() {
                @Override
                public Post createFromParcel(Parcel in) {
                        return new Post(in);
                }

                @Override
                public Post[] newArray(int size) {
                        return new Post[size];
                }
        };

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
                parcel.writeString(userName);
                parcel.writeString(fullName);
                parcel.writeString(caption);
                parcel.writeString(profile);
                parcel.writeString(upload);
        }
}