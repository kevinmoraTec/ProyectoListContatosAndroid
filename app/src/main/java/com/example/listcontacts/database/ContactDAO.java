package com.example.listcontacts.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contactos")
    List<Contact> getContacts();

    @Query("SELECT * FROM contactos WHERE id LIKE:uuid")
    Contact getContact(String uuid);

    @Insert
    void addContacto(Contact c);

    @Delete
    void deleteContacto(Contact c);

    @Update
    void updateContacto(Contact c);

    @Query("DELETE FROM contactos")
    void deleteAllContacto();

}
