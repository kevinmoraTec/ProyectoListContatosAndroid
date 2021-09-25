package com.example.listcontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listcontacts.database.Contact;
import com.example.listcontacts.database.ContactLab;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaveContact extends AppCompatActivity {

    private Button BtnCancelar, BtnGuardar;
    private EditText txtNombre, txtNumero, txtCiudad, txtDescripcion, txtFoto;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ContactLab contactLab;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contact);

        BtnCancelar = (Button) findViewById(R.id.btnCancel);
        BtnGuardar = (Button) findViewById(R.id.btnSave);

        txtNombre = (EditText) findViewById(R.id.editName);
        txtNumero = (EditText) findViewById(R.id.editNumber);
        txtCiudad = (EditText) findViewById(R.id.editCity);
        txtDescripcion = (EditText) findViewById(R.id.editDescription);
        txtFoto = (EditText) findViewById(R.id.editPhoto);

//        inicializarFirebase();
        contactLab = new ContactLab(this);


        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveContact.this, MainActivity.class );
                startActivity(intent);
            }
        });

        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //Con firebase
//                String nombre = txtNombre.getText().toString();
//                String numero = txtNumero.getText().toString();
//                String description = txtDescripcion.getText().toString();
//                String foto = txtFoto.getText().toString();
//                String ciudad = txtCiudad.getText().toString();
//
//                if (nombre.equals("")||numero.equals("")||description.equals("")||foto.equals("")||ciudad.equals("")) {
//                    validacion();
//                } else{
//                    Contact contact = new Contact();
//                    contact.setId(UUID.randomUUID().toString());
//                    contact.setNombre(nombre.trim());
//                    contact.setNumero(numero.trim());
//                    contact.setCiudad(ciudad.trim());
//                    contact.setDescripcion(description.trim());
//                    contact.setFoto(foto.trim());
//                    databaseReference.child("Contact").child(contact.getId()).setValue(contact);
//
//                    Toast toast = Toast.makeText(SaveContact.this, "Registro Agregado", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                    toast.show();
//                    Intent intent1 = new Intent(SaveContact.this, MainActivity.class);
//                    startActivity(intent1);
//                    limpiarDatos();
//                }
                guardarBd();
                validacion();

            }
        });
    }

//    private void inicializarFirebase(){
//        FirebaseApp.initializeApp(this);
//        firebaseDatabase = firebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//    }

    private void limpiarDatos(){
        txtNombre.setText("");
        txtNumero.setText("");
        txtDescripcion.setText("");
        txtFoto.setText("");
        txtCiudad.setText("");
    }

    private void validacion(){
        String nombre = txtNombre.getText().toString();
        String numero = txtNumero.getText().toString();
        String description = txtDescripcion.getText().toString();
        String foto = txtFoto.getText().toString();
        String ciudad = txtCiudad.getText().toString();

        if (nombre.equals("")){
            txtNombre.setError("Campo requerido");
        }else if (numero.equals("")){
            txtNumero.setError("Campo requerido");
        }else if (ciudad.equals("")){
            txtCiudad.setError("Campo requerido");
        }else if (description.equals("")){
            txtDescripcion.setError("Campo requerido");
        }else if (foto.equals("")){
            txtFoto.setError("Campo requerido");
        }
    }

    private void guardarBd(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Aviso");
        dialogo1.setMessage("¿Desea agregar este contacto?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                contact = new Contact();
                contact.setNombre(txtNombre.getText().toString().trim());
                contact.setNumero(txtNumero.getText().toString().trim());
                contact.setCiudad(txtCiudad.getText().toString().trim());
                contact.setDescripcion(txtDescripcion.getText().toString().trim());
                contact.setFoto(txtFoto.getText().toString().trim());
                contactLab.addContacto(contact);
                Toast toast = Toast.makeText(SaveContact.this, "Registro Agregado", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                limpiarDatos();
                Intent  intent = new Intent(SaveContact.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();

    }
}