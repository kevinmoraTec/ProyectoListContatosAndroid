package com.example.listcontacts.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDataBase extends RoomDatabase {

    public abstract ContactDAO getContactDao();
}
