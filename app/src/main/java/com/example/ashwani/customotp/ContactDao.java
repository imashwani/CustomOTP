package com.example.ashwani.customotp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    public List<Contact> getList();

    @Insert
    void insert(Contact contact);
}
