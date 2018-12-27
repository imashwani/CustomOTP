package com.example.ashwani.customotp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {Contact.class},version =1)
public abstract  class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
