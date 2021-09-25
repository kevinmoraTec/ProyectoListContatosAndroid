package com.example.listcontacts.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;

import java.util.List;

public class ContactLab {

    @SuppressLint("StaticFieldLeak")
    private static ContactLab sContactoLab;

    private ContactDAO mContactoDao;

    public ContactLab(Context context) {

        Context appContext = context.getApplicationContext();
        ContactDataBase dataBase = Room.databaseBuilder(appContext, ContactDataBase.class, "contactosDB")
                .allowMainThreadQueries().build();

        mContactoDao = dataBase.getContactDao();

    }

    public static ContactLab get(Context context){

        if (sContactoLab==null){
            sContactoLab=new ContactLab(context);
        }
        return sContactoLab;
    }

    //Leer
    public List<Contact> getContacts(){
        return mContactoDao.getContacts();
    }

    //String
    public Contact getContact(String n){
        return mContactoDao.getContact(n);
    }

    //Agregar
    public void addContacto(Contact c){
        mContactoDao.addContacto(c);
    }
    public void updatePersona(Contact c) {
        mContactoDao.updateContacto(c);
    }

    public void deletePersona(Contact c) {
        mContactoDao.deleteContacto(c);
    }
    public void deleteAllPersona() {
        mContactoDao.deleteAllContacto();
    }
}
