package com.example.consultorio.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.consultorio.interfaces.ClientesSB;
import com.example.consultorio.interfaces.comunicacionFragment;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Citas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Citas extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CardView crdvAgregar;
    CardView crdvModificar;
    CardView crdvBuscar;
    CardView crdvCancelar;
    comunicacionFragment interfaceFragment;
    View vista;
    Activity actividad;

    public Citas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Citas.
     */
    // TODO: Rename and change types and number of parameters
    public static Citas newInstance(String param1, String param2) {
        Citas fragment = new Citas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_citas);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void setContentView(int fragment_citas) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_citas, container, false);
        crdvAgregar = vista.findViewById(R.id.crdvAgregar);
        crdvModificar = vista.findViewById(R.id.crdvModificar);
        crdvBuscar = vista.findViewById(R.id.crdvBuscar);
        crdvCancelar = vista.findViewById(R.id.crdvCancelar);

        crdvAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceFragment.agendaCita();
            }
        });

        crdvModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceFragment.modificaCita();
            }
        });

        crdvBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceFragment.buscaCita();
            }
        });

        crdvCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceFragment.cancelaCita();
            }
        });

        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad = (Activity) context;
            interfaceFragment = (comunicacionFragment) actividad;
        }
    }
}