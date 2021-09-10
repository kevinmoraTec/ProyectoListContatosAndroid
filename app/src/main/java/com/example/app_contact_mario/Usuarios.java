package com.example.app_contact_mario;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuarios {
    @PrimaryKey(autoGenerate = true)
    int id; //definimos un id ncremental
    @ColumnInfo(name="nombreUser")
    String nombreUser;
    @ColumnInfo(name="apellidoUsers")
    String apellidoUser;
    @ColumnInfo(name="telefono")
    String telefonoUser;


    public Usuarios(String nombreUser, String apellidoUser, String telefonoUser) {
        this.nombreUser = nombreUser;
        this.apellidoUser = apellidoUser;
        this.telefonoUser = telefonoUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public void setApellidoUser(String apellidoUser) {
        this.apellidoUser = apellidoUser;
    }

    public String getTelefonoUser() {
        return telefonoUser;
    }

    public void setTelefonoUser(String telefonoUser) {
        this.telefonoUser = telefonoUser;
    }
}
