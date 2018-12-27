package com.example.ashwani.customotp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    @ColumnInfo
    String nameAndPhone;

    @ColumnInfo
    String otp;

    @ColumnInfo
    String time;

    public Contact(String nameAndPhone, String otp, String time) {
        this.nameAndPhone = nameAndPhone;
        this.otp = otp;
        this.time = time;
    }
}
