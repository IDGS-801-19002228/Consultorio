package com.example.consultorio.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.consultorio.fragments.AcercaDe;
import com.example.consultorio.fragments.Citas;
import com.example.consultorio.fragments.Inicio;
import com.example.consultorio.interfaces.ClientesSB;
import com.example.consultorio.interfaces.comunicacionFragment;
import com.example.consultorio.models.Cita;
import com.example.consultorio.models.Cliente;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;

public class barraNavegacionInferior extends AppCompatActivity implements ClientesSB, comunicacionFragment {

    Inicio inicio = new Inicio();
    Citas agendaCita = new Citas();
    AcercaDe acercaDe = new AcercaDe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barra_inferior);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(inicio);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.inicio:
                    loadFragment(inicio);
                    return true;
                case R.id.agendaCita:
                    loadFragment(agendaCita);
                    return true;
                case R.id.buscarCita:
                    loadFragment(acercaDe);
                    return true;
            }
            return false;
        }
    };


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    @Override
    public Call<List<Cliente>> getAllClieentes() {
        return null;
    }

    @Override
    public Call<List<Cita>> getAllCita() {
        return null;
    }

    @Override
    public Call<Cliente> getClientes(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, int edad, String genero, String correo, String contrasenia) {
        return null;
    }

    @Override
    public Call<Cita> saveCita(int idCliente_fk, String especialidad, String tratamiento, String alergias, int numeroContacto, String fechaCita, int horario) {
        return null;
    }

   // @Override
    public Call<Cita> saveCita(String especialidad, String tratamiento, String alergias, int numeroContacto, String fechaCita, int horario, Cliente cliente) {
        return null;
    }

    @Override
    public void agendaCita() {
        //Toast.makeText(getApplicationContext(),"Iniciar actividad",Toast.LENGTH_SHORT).show();
        Intent intentCita = new Intent(this, agregarCita.class);
        startActivity(intentCita);
    }

    @Override
    public void buscaCita() {
        Intent intentBuscarCita = new Intent(this, buscarCita.class);
        startActivity(intentBuscarCita);
    }

    @Override
    public void modificaCita() {
        Intent intentModificarCita = new Intent(this, modificarCita.class);
        startActivity(intentModificarCita);
    }

    @Override
    public void cancelaCita() {
        Intent intentCancelarCita = new Intent(this, cancelarCita.class);
        startActivity(intentCancelarCita);
    }

    //Metodo para mostrar y ocultar items
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //Metodo para asignar la funcionalidad a los items
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.itemLogOut){
            //Toast.makeText(this,"Cerrando Sesion",Toast.LENGTH_SHORT).show();
            Intent intentLogOut = new Intent(this, Login.class);
            startActivity(intentLogOut);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}