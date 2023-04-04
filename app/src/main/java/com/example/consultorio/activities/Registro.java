package com.example.consultorio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consultorio.interfaces.ClientesSB;
import com.example.consultorio.models.Cliente;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity {
EditText txtNombre;
EditText txtapellidoPaterno;
EditText txtapellidoMaterno;
EditText txtfechaNacimiento;
EditText txtEdad;
EditText txtGenero;
//RadioButton rbtnMasculino;
//RadioButton rbtnFemenino;
EditText txtCorreo;
EditText txtContrasenia;
Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.txtEspecialidad_sv);
        txtapellidoPaterno = findViewById(R.id.txtapellidoPaterno);
        txtapellidoMaterno = findViewById(R.id.txtapellidoMaterno);
        txtfechaNacimiento = findViewById(R.id.txtfechaNacimiento);
        txtEdad = findViewById(R.id.txtEdad);
        txtGenero = findViewById(R.id.txtGenero);
        //rbtnMasculino = findViewById(R.id.rbtnMasculino);
        //rbtnFemenino = findViewById(R.id.rbtnFemenino);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasenia = findViewById(R.id.txtContrasenia);


    }

    public void Principal(View view){
        Intent intentPrincipal = new Intent(this, Login.class);
        startActivity(intentPrincipal);
    }

    public void insertarRegisto(View view){

       String nombre = String.valueOf(txtNombre.getText());
       String apellidoPaterno = String.valueOf(txtapellidoPaterno.getText());
       String apellidoMaterno = String.valueOf(txtapellidoMaterno.getText());
       String fechaNacimiento = String.valueOf(txtfechaNacimiento.getText());
       String edad =String.valueOf(txtEdad.getText());
       String  genero = String.valueOf(txtGenero.getText());
       String correo = String.valueOf(txtCorreo.getText());
       String contrasenia = String.valueOf(txtContrasenia.getText());

       if(correo.isEmpty() && contrasenia.isEmpty() && nombre.isEmpty() && apellidoPaterno.isEmpty() && apellidoMaterno.isEmpty()
       && fechaNacimiento.isEmpty() && genero.isEmpty() && !TextUtils.isEmpty(edad)){

           txtNombre.setError("Introduce un valor valido");
           txtapellidoPaterno.setError("Introduce un valor valido");
           txtapellidoMaterno.setError("Introduce un valor valido");
           txtfechaNacimiento.setError("Introduce un valor valido");
           txtEdad.setError("Introduce un valor valido");
           txtGenero.setError("Introduce un valor valido");
           txtCorreo.setError("Introduce un valor valido");
           txtContrasenia.setError("Introduce un valor valido");

       }else if(correo.isEmpty()){
           txtCorreo.setError("Introduce un valor valido");
       }else if(contrasenia.isEmpty()){
           txtContrasenia.setError("Introduce un valor valido");
       }else if(nombre.isEmpty()){
           txtNombre.setError("Introduce un valor valido");
       }else if(apellidoPaterno.isEmpty()){
           txtapellidoPaterno.setError("Introduce un valor valido");
       }else if(apellidoMaterno.isEmpty()){
           txtapellidoMaterno.setError("Introduce un valor valido");
       }else if(edad.isEmpty()){
           txtEdad.setError("Introduce un valor valido");
       } else if(genero.isEmpty()){
           txtGenero.setError("Introduce un valor valido");
       }else{
           Intent intentRegistro = new Intent(this, Login.class);
           intentRegistro.putExtra("correo", correo);
           startActivity(intentRegistro);
       }

       Cliente cliente= new Cliente();

       cliente.setNombre(nombre);
       cliente.setApellidoPaterno(apellidoPaterno);
       cliente.setApellidoMaterno(apellidoMaterno);
       cliente.setFechaNacimiento(fechaNacimiento);
       cliente.setEdad( Integer.parseInt(edad));
       cliente.setGenero(genero);
       cliente.setCorreo(correo);
       cliente.setContrasenia(contrasenia);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:8080/sonrisasBrillantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClientesSB csb = retrofit.create(ClientesSB.class) ;
        Call<Cliente> call= csb.getClientes(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(),
        cliente.getFechaNacimiento(), cliente.getEdad(), cliente.getGenero(), cliente.getCorreo(), cliente.getContrasenia());
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Toast.makeText(Registro.this, "Cliente registrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(Registro.this, "ERROR"+t, Toast.LENGTH_SHORT).show();
System.out.println("ERROR"+t);
            }
        });

    }

}