package com.example.kuis;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {

    String nama;
    int bestScore, score;

    protected Player(Parcel in) {
        nama = in.readString();
        bestScore = in.readInt();
        score = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(String nama, int bestScore, int score) {
        this.nama=nama;
        this.bestScore=bestScore;
        this.score=score;
    }

    public Player() {
        setNama(nama);
        setBestScore(bestScore);
        setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String getNama() {
        return nama;
    }

    public int getBestScore() {
        return bestScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeInt(bestScore);
        parcel.writeInt(score);
    }
}
