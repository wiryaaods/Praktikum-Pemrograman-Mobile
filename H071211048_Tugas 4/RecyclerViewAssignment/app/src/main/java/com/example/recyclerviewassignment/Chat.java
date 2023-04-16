package com.example.recyclerviewassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Chat implements Parcelable {

    String nama,pesan,time, noTelp, status, date;
    int foto;

    public Chat (String nama, String pesan, String time, int foto, String noTelp,
                 String status, String date){
        this.nama=nama;
        this.pesan=pesan;
        this.time=time;
        this.foto=foto;
        this.noTelp = noTelp;
        this.status = status;
        this.date = date;
    }

    protected Chat(Parcel in) {
        nama = in.readString();
        pesan = in.readString();
        time = in.readString();
        foto = in.readInt();
        noTelp = in.readString();
        status = in.readString();
        date = in.readString();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };


    public String getNama() {
        return nama;
    }

    public String getPesan() {
        return pesan;
    }

    public int getFoto() {
        return foto;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(pesan);
        parcel.writeString(time);
        parcel.writeInt(foto);
        parcel.writeString(noTelp);
        parcel.writeString(status);
        parcel.writeString(date);
    }
}
