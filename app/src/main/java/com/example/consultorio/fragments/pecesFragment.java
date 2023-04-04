package com.example.consultorio.fragments;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultorio.activities.Login;
import com.example.consultorio.activities.buscarCita;
import com.example.consultorio.adapters.MypecesRecyclerViewAdapter;
import com.example.consultorio.models.Cita;
import com.example.myapplication.R;
//import com.example.recyclerview.model.Peces;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.example.recyclerview.retrofit_data.RetrofitClient;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@Link OnListFragmentInteractionListener}
 * interface.
 */
public class pecesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static com.example.consultorio.interfaces.ClientesSB clientesSB;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    RecyclerView recyclerView;
    List<Cita> pecesList  = new ArrayList<>();

    //Context ctx;
    MypecesRecyclerViewAdapter adapters;

    //private RecyclerView recyclerView;
    /*private RecyclerView.LayoutManager layoutManager;
    private List<Cita> cita;
    ProgressBar progressBar;
    private MypecesRecyclerViewAdapter adapter;
    private pecesFragment fragment;

    String[] item;
    SearchView svBuscar;*/



    //private RetrofitApiService retrofitApiService;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public pecesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static pecesFragment newInstance(int columnCount) {
        pecesFragment fragment = new pecesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        /*svBuscar = (SearchView) svBuscar.findViewById(R.id.svBuscar);
        progressBar = progressBar.findViewById(R.id.progressBar);
        recyclerView = recyclerView.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);*/

        //fetchUser("sonrisasBrillantes","");
        //getPeces2("");
    }


    public void getPeces2(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.7:8080/sonrisasBrillantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //iniciarControles();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapters = new MypecesRecyclerViewAdapter(getActivity(),pecesList,mListener);
        recyclerView.setAdapter(adapters);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        com.example.consultorio.interfaces.ClientesSB clientesSB =   retrofit.create(com.example.consultorio.interfaces.ClientesSB.class);

        Call< List<Cita>> lista= clientesSB.getAllCita();
        lista.enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                if(response.isSuccessful()){
                    //progressBar.setVisibility(View.GONE);
                    pecesList = response.body();
                    adapters = new MypecesRecyclerViewAdapter(getActivity(),pecesList,mListener);
                    recyclerView.setAdapter(adapters);
                    adapters.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);
                //Toast.makeText(getContext(),"ERROR on :" +toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_peces_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //Lista de elementos
            /*pecesList = new ArrayList<>();
            pecesList.add(new Peces("Sumatrano", "Tetra", 25.50f, 4.5f, "https://i.ytimg.com/vi/x9NyQJu9bck/maxresdefault.jpg"));
            pecesList.add(new Peces("Gota de Sangre", "Tetra", 15.50f, 3f, "https://aquariumfish.net/images_01/blood_parrot_130418a4_w0640.jpg"));
            pecesList.add(new Peces("Angel", "Escalar", 40.00f, 5f, "https://hablemosdepeces.com/wp-content/uploads/2017/06/Pez-%C3%A1ngel-reina.jpg"));
            pecesList.add(new Peces("Guppy Leopardo", "Guppy", 15.00f, 4f, "https://3.bp.blogspot.com/-acsA3kbtrXI/T1vopN7c0JI/AAAAAAAAALY/VfQsl4sKJRs/s1600/guppy1.jpg"));

            */

            recyclerView.setAdapter(new MypecesRecyclerViewAdapter(getActivity(), pecesList, mListener));

        }
        getPeces2();
        return view;
    }


    /*@Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener){
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }*/

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        menu.findItem(R.id.search).setVisible(true);
        super.onCreateOptionsMenu(menu,inflater);
    }*/




    public interface OnListFragmentInteractionListener{
        void onListFragmentInteraction(Cita item);
    }


}