package com.example.consultorio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.R;

public class modificarCita extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;

    RadioGroup rbtnAlergias;
    RadioButton rbtnSi;
    RadioButton rbtnNo;
    EditText txtAlergias;
    RadioGroup rbtgGenero;
    RadioButton rbtnMujer;
    RadioButton rbtnHombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cita);
        spinner1 = (Spinner)findViewById(R.id.spnTratamiento);
        spinner2 = (Spinner)findViewById(R.id.spnEspecialidad);

        String [] tratamientos = {"Reconstruccion dental","Obsturacion","Endodoncias","Correccion dental","Extracciones","Sustitucion de dientes"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tratamientos);
        spinner1.setAdapter(adapter1);

        String [] especialidades = {"Odontologia preventiva","Odontologia Conservadora","Endodoncia","Odontopediatria","Ortodoncia","Cirugia bucal",
                "Implatologia","Protesis","Periodoncia","Estetica dental"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, especialidades);
        spinner2.setAdapter(adapter2);

        rbtnAlergias = (RadioGroup) findViewById(R.id.rbtnAlergias);
        rbtnSi = (RadioButton) findViewById(R.id.rbtnSi);
        rbtnNo = (RadioButton) findViewById(R.id.rbtnNo);
        txtAlergias = (EditText) findViewById(R.id.txtAlergias);

        rbtnAlergias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbtnSi.isChecked()){
                    txtAlergias.setVisibility(View.VISIBLE);
                }else{
                    txtAlergias.setVisibility(View.GONE);
                }
            }
        });

        rbtgGenero = (RadioGroup) findViewById(R.id.rbtnGnearo_fk);
        rbtnMujer = (RadioButton) findViewById(R.id.rbtnFemenino_fk);
        rbtnHombre = (RadioButton) findViewById(R.id.rbtnMasculino_fk);

        rbtgGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbtnHombre.isChecked()){
                    rbtnHombre.setChecked(false);
                }
                if (rbtnMujer.isChecked()){
                    rbtnMujer.setChecked(false);
                }
            }
        });

    }

    public void volverHome(View view){
        Intent intentRegresarHome = new Intent(this, barraNavegacionInferior.class);
        startActivity(intentRegresarHome);
    }

}