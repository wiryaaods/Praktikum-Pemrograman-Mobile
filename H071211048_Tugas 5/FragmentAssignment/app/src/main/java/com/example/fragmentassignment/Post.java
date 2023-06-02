package com.example.fragmentassignment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class Post implements Parcelable {

    String caption;
    Uri post;

    public Post (String caption, Uri post){
        this.caption = caption;
        this.post = post;
    }
    protected Post(Parcel in) {
        caption = in.readString();
        post = in.readParcelable(Uri.class.getClassLoader());
    }

    public String getCaption() {
        return caption;
    }
    public Uri getPost() {
        return post;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setPost(Uri post) {
        this.post = post;
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
        parcel.writeString(caption);
        parcel.writeParcelable(post, i);
    }
}
