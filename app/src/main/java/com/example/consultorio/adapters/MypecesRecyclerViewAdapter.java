package com.example.consultorio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.consultorio.fragments.pecesFragment;
import com.example.consultorio.models.Cita;
import com.example.myapplication.R;
//import com.example.recyclerview.model.Peces;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MypecesRecyclerViewAdapter extends RecyclerView.Adapter<MypecesRecyclerViewAdapter.ViewHolder> implements Filterable {

    private final List<Cita> mValues;
    //private List<Cita> originalItems;
    private List<Cita> backup;


    private final pecesFragment.OnListFragmentInteractionListener mListener;
    Context ctx;

    public MypecesRecyclerViewAdapter(Context context, List<Cita> items, pecesFragment.OnListFragmentInteractionListener listener) {
        ctx = context;
        mValues = items;
        mListener = listener;
        backup = new ArrayList<>(items);
        //backup.addAll(items);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_peces, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Toma los datos de los elementos de nuestra estructura de datos 1,2,3....n
        holder.mItem = mValues.get(position);

        holder.txtEspecialidad_sv.setText(holder.mItem.getEspecialidad());
        holder.txtTratamiento_sv.setText(holder.mItem.getTratamiento());
        holder.txtAlergias_sv.setText(String.valueOf(holder.mItem.getAlergias()));
        holder.txtnumeroContacto_sv.setText(String.valueOf(holder.mItem.getNumeroContacto()));
        holder.txtfechaCita_sv.setText(String.valueOf(holder.mItem.getFechaCita()));
        holder.txtHorario_sv.setText(String.valueOf(holder.mItem.getHorario()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener){
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /*public void filter(String strSearch){
        if (strSearch.length()==0){
            mValues.clear();
            mValues.addAll(originalItems);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Cita> collect = mValues.stream()
                        .filter(i -> i.getFechaCita().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                mValues.clear();
                mValues.addAll(collect);
            }else{
                mValues.clear();
                for (Cita i : originalItems){
                    if (i.getFechaCita().toLowerCase().contains(strSearch)){
                        mValues.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<Cita> filtereddata=new ArrayList<>();
            if (keyword.toString().isEmpty()){
                filtereddata.addAll(backup);
            }else{
                for (Cita obj : backup){
                    if (obj.getFechaCita().toString().toLowerCase().contains(keyword.toString().toLowerCase())){
                        filtereddata.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtereddata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mValues.clear();
            mValues.addAll((List<Cita>)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtEspecialidad_sv;
        public final TextView txtTratamiento_sv;
        public final TextView txtAlergias_sv;
        public final TextView txtnumeroContacto_sv;
        public final TextView txtfechaCita_sv;
        public final TextView txtHorario_sv;
        public Cita mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtEspecialidad_sv = view.findViewById(R.id.txtEspecialidad_sv);
            txtTratamiento_sv = view.findViewById(R.id.txtTratamiento_sv);
            txtAlergias_sv = view.findViewById(R.id.txtAlergias_sv);
            txtnumeroContacto_sv = view.findViewById(R.id.txtnumeroContacto_sv);
            txtfechaCita_sv = view.findViewById(R.id.txtfechaCita_sv);
            txtHorario_sv = view.findViewById(R.id.txtHorario_sv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtEspecialidad_sv.getText() + "'";
        }
    }
}