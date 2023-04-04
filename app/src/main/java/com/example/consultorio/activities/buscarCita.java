package com.example.consultorio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.consultorio.adapters.MypecesRecyclerViewAdapter;
import com.example.consultorio.fragments.pecesFragment;
import com.example.consultorio.models.Cita;
import com.example.myapplication.R;

public class buscarCita extends AppCompatActivity implements pecesFragment.OnListFragmentInteractionListener{

    /*private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Cita> cita;
    private MypecesRecyclerViewAdapter adapter;
    private pecesFragment fragment;
    private ProgressBar progressBar;
    String[] item;
    SearchView svBuscar;*/
    private MypecesRecyclerViewAdapter adapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cita);

        search = (SearchView) findViewById(R.id.search);
        /*svBuscar = (SearchView) findViewById(R.id.svBuscar);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchUser("sonrisasBrillantes","");*/
    }

    /*private void fetchUser(String type, String key){

    }*/

    //Metodo para mostrar y ocultar items
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search);
        //menu.findItem(R.id.search);
        SearchView search =  (SearchView) item.getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //Metodo para asignar la funcionalidad a los items
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.search){
        }
        return super.onOptionsItemSelected(item);
    }

    public void volverHome(View view){
        Intent intentRegresarHome = new Intent(this, barraNavegacionInferior.class);
        startActivity(intentRegresarHome);
    }

    @Override
    public void onListFragmentInteraction(Cita item) {

    }

}