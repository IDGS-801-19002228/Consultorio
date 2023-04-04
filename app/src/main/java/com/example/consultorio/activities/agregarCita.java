package com.example.consultorio.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.consultorio.interfaces.ClientesSB;
import com.example.consultorio.models.Cita;
import com.example.myapplication.R;

public class agregarCita extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    EditText id_Cliente_fk;
    Spinner spnEspecialidad;
    Spinner spnTratamiento;
    RadioButton rbtnSi;
    RadioButton rbtnNo;
    EditText txtAlergias;
    RadioGroup rbtnAlergias;
    EditText txtnumeroContacto;
    EditText txtfechaCita;
    EditText txtHorario;
    Cita cita = new Cita();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cita);


        //-------------------------------------------------------------------------------------------------------------------------------------------------//
        spinner1 = (Spinner) findViewById(R.id.spnTratamiento);
        spinner2 = (Spinner) findViewById(R.id.spnEspecialidad);

        String[] tratamientos = {"Reconstruccion dental", "Obsturacion", "Endodoncias", "Correccion dental", "Extracciones", "Sustitucion de dientes"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tratamientos);
        spinner1.setAdapter(adapter1);

        String[] especialidades = {"Odontologia preventiva", "Odontologia Conservadora", "Endodoncia", "Odontopediatria", "Ortodoncia", "Cirugia bucal",
                "Implatologia", "Protesis", "Periodoncia", "Estetica dental"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, especialidades);
        spinner2.setAdapter(adapter2);


        id_Cliente_fk = (EditText) findViewById(R.id.id_Cliente_fk);
        rbtnSi = (RadioButton) findViewById(R.id.rbtnSi);
        rbtnNo = (RadioButton) findViewById(R.id.rbtnNo);
        spnEspecialidad = findViewById(R.id.spnEspecialidad);
        spnTratamiento = findViewById(R.id.spnTratamiento);
        txtAlergias = findViewById(R.id.txtAlergias);
        txtnumeroContacto = findViewById(R.id.txtnumeroContacto);
        txtfechaCita = findViewById(R.id.txtfechaCita);
        txtHorario = findViewById(R.id.txtHorario);


        rbtnAlergias = (RadioGroup) findViewById(R.id.rbtnAlergias);
        rbtnSi = (RadioButton) findViewById(R.id.rbtnSi);
        rbtnNo = (RadioButton) findViewById(R.id.rbtnNo);
        txtAlergias = (EditText) findViewById(R.id.txtAlergias);

        rbtnAlergias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbtnSi.isChecked()) {
                    txtAlergias.setVisibility(View.VISIBLE);
                } else {
                    txtAlergias.setVisibility(View.GONE);

                    txtAlergias.setText("N/A");
                }
            }
        });

    }


    public void volverHome(View view) {
        Intent intentRegresarHome = new Intent(this, barraNavegacionInferior.class);
        startActivity(intentRegresarHome);
    }


    public void agregarCita(View view) {

        cita.setIdCliente_fk(Integer.parseInt(id_Cliente_fk.getText().toString()));
        cita.setEspecialidad(spinner2.getSelectedItem().toString());
        cita.setTratamiento(spinner1.getSelectedItem().toString());

        cita.setAlergias(txtAlergias.getText().toString());
        cita.setNumeroContacto(Integer.parseInt(txtnumeroContacto.getText().toString()));
        cita.setFechaCita(txtfechaCita.getText().toString());
        cita.setHorario(Integer.parseInt(txtHorario.getText().toString()));
        // Toast.makeText(this, cita.toString(), Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:8080/sonrisasBrillantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClientesSB csb = retrofit.create(ClientesSB.class);
        Call<Cita> call = csb.saveCita(cita.getIdCliente_fk(), cita.getEspecialidad(), cita.getTratamiento(),
                cita.getAlergias(), cita.getNumeroContacto(), cita.getFechaCita(), cita.getHorario());

        call.enqueue(new Callback<Cita>() {
            @Override
            public void onResponse(Call<Cita> call, Response<Cita> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error : " + response.code());


                } else {
                    //Toast.makeText(this,"Se registro  exitosamente", Toast.LENGTH_LONG).show();
                    Toast.makeText(agregarCita.this, "Cita registrada", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<Cita> call, Throwable t) {
                //Toast.makeText(agregarCita.this, "ERROR"+t, Toast.LENGTH_SHORT).show();
                System.out.println("ERROR" + t);

            }
        });


    }
}