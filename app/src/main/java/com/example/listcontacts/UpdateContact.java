package com.example.listcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.listcontacts.database.Contact;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateContact extends AppCompatActivity {

    private Button BtnCancelar, BtnActualizar;
    private EditText txtNombre, txtNumero, txtCiudad, txtDescripcion, txtFoto;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        BtnCancelar = (Button) findViewById(R.id.btnCancelUpdate);
        BtnActualizar = (Button) findViewById(R.id.btnSaveUpdate);

        txtNombre = (EditText) findViewById(R.id.editNameUpdate);
        txtNumero = (EditText) findViewById(R.id.editNumberUpdate);
        txtCiudad = (EditText) findViewById(R.id.editCityUpdate);
        txtDescripcion = (EditText) findViewById(R.id.editDescriptionUpdate);
        txtFoto = (EditText) findViewById(R.id.editPhotoUpdate);

        inicializarFirebase();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre = intent.getStringExtra("nombre");
        String city = intent.getStringExtra("ciudad");
        String description = intent.getStringExtra("descripcion");
        String img = intent.getStringExtra("foto");
        String number = intent.getStringExtra("numero");
        System.out.println("Imagen: " + img);
        txtNombre.setText(nombre);
        txtCiudad.setText(city);
        txtDescripcion.setText(description);
        txtFoto.setText(img);
        txtNumero.setText(number);

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateContact.this, MainActivity.class );
                startActivity(intent);
            }
        });

//        BtnActualizar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nombre = txtNombre.getText().toString();
//                String numero = txtNumero.getText().toString();
//                String description = txtDescripcion.getText().toString();
//                String foto = txtFoto.getText().toString();
//                String ciudad = txtCiudad.getText().toString();
//
//                Toast toast = Toast.makeText(UpdateContact.this, "Click en el botón actualizar: ", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                toast.show();
//
//                if (nombre.equals("")||numero.equals("")||description.equals("")||foto.equals("")||ciudad.equals("")) {
//                    validacion();
//                } else {
//                    Contact contact = new Contact();
//                    contact.setId(id);
//                    contact.setNombre(nombre.trim());
//                    contact.setNumero(numero.trim());
//                    contact.setCiudad(ciudad.trim());
//                    contact.setDescripcion(description.trim());
//                    contact.setFoto(foto.trim());
//                    System.out.println(contact);
//                    databaseReference.child("Contact").child(contact.getId()).setValue(contact);
//                    Intent intent1 = new Intent(UpdateContact.this, MainActivity.class);
//                    startActivity(intent1);
//                    limpiarDatos();
//                }
//            }
//        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

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

}