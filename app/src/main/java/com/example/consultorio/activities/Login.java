package com.example.consultorio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.consultorio.interfaces.ClientesSB;
import com.example.consultorio.models.Cliente;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {


    TextView txtRegistro;
    EditText txtCorreo_fk;
    EditText txtContrasenia_fk;
    TextView txtCopyright;
    Button btnRegistrar;
    List<Cliente>listaCliente = new ArrayList<>();
    boolean validateU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRegistrar = findViewById(R.id.btnIngresar);
        txtRegistro = findViewById(R.id.txtRegistro);
        txtCorreo_fk = findViewById(R.id.txtCorreo_fk);
        txtContrasenia_fk = findViewById(R.id.txtContrasenia_fk);
        txtCopyright = findViewById(R.id.txtCopyright);

    }
    public void registro(View view){
        String usuario= txtCorreo_fk.getText().toString();
        String contrasenia= txtContrasenia_fk.getText().toString();
        boolean va;

        if(usuario.isEmpty() && contrasenia.isEmpty()){
            txtCorreo_fk.setError("Ingresa un valor valido");
            txtContrasenia_fk.setError("Ingresa un valor valido");
        }else if(usuario.isEmpty()){
            txtCorreo_fk.setError("Ingresa un valor valido");
        }else if(contrasenia.isEmpty()){
            txtContrasenia_fk.setError("Ingresa un valor valido");
        }else{
            obtenerClientes();
            va = findClientes(usuario,contrasenia);
            if (!va){
                Toast.makeText(getApplicationContext(),"Datos Incorrectos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Datos Correctos", Toast.LENGTH_SHORT).show();
                Intent intentLogin = new Intent(this, splash_activity.class);
                startActivity(intentLogin);
            }

        }

    }
    public void login(View view){
        Intent intentLogin = new Intent(this, Registro.class);
        startActivity(intentLogin);
    }

    public void obtenerClientes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/sonrisasBrillantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientesSB csb = retrofit.create(ClientesSB.class) ;
        Call<List<Cliente>> listaCli = csb.getAllClieentes();
        listaCli.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(response.code() != 200){
                    Toast.makeText(getApplicationContext(), Integer.toString(response.code()), Toast.LENGTH_SHORT).show();
                } else{
                    for (Cliente clientes : response.body()){
                        listaCliente.add(clientes);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

            }
        });
    }

    public boolean findClientes(String usuario, String contrasenia){
        boolean value = false;

        for (Cliente cli: listaCliente){
            if (!cli.getCorreo().equals(usuario) && !cli.getContrasenia().equals(contrasenia)){
                continue;
            } else{
                return  true;
            }
        }
        return value;
    }
}