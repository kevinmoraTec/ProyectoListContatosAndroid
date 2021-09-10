    package com.example.app_contact_mario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    EditText etMsj,etCel;
    Button btnEnviar,btnWats,btnContac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMsj=findViewById(R.id.editTextTextPersonName);
        etCel=findViewById(R.id.editTextTextPersonName2);
        btnEnviar=findViewById(R.id.button);
        btnWats=findViewById(R.id.buttonW);
        btnContac=findViewById(R.id.buttoncontact);
    //Damos permisos para poder enivar msj

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1 );
        }

        //Damos la accion de click al boton
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager =  SmsManager.getDefault();
                smsManager.sendTextMessage(etCel.getText().toString(),null,etMsj.getText().toString(),null,null);
                Toast.makeText(MainActivity.this,"Msj Eviado Correctamente ",Toast.LENGTH_LONG).show();
            }
        });

        btnWats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCel.getText().toString().isEmpty()){
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, etMsj.getText().toString());
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String uri ="whatsapp://send?phone="+etCel.getText().toString()+"&text="+etMsj.getText().toString();
                sendIntent.setData(Uri.parse(uri));
                startActivity(sendIntent);
            }
        });

        btnContac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Contact.class);
                startActivity(i);
            }
        });


    }
}