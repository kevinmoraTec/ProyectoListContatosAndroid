package com.example.app_contact_mario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Contact extends AppCompatActivity {

    EditText etNombre,etApellido,etnumeroTelefono,etconsulta;
    Button btnGuardar,btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etUsuario);
        etnumeroTelefono=findViewById(R.id.etPassword);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnMostrar=findViewById(R.id.btnMostrar);
        etconsulta=findViewById(R.id.etConsulta);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
                Usuarios usuarios = new Usuarios(etNombre.getText().toString(),etApellido.getText().toString(),etnumeroTelefono.getText().toString());

                db.usuariosDao().insert(usuarios);
            }
        });


        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
                List<Usuarios> lista =db.usuariosDao().getAll();
                String  valores = "";

                for(int i = 0; i<lista.size();i++){

                    valores+=""+lista.get(i).getId()+": "+lista.get(i).nombreUser+" usuario: "+lista.get(i).apellidoUser+" Numero: "+lista.get(i).telefonoUser+"\n";

                }
                etconsulta.setText(valores);

            }
        });
    }
}