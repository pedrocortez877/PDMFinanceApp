package com.example.pdmfinanceapppedrocortez.list;

import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdmfinanceapppedrocortez.R;
import com.example.pdmfinanceapppedrocortez.model.Registers;

import java.util.ArrayList;

public class RegisterDataAdapter extends RecyclerView.Adapter<RegisterDataViewHolder> {
    private ArrayList<Registers> registers;
    private SparseBooleanArray toggleInfo;

    public RegisterDataAdapter(ArrayList<Registers> registers, Activity activity){
        this.registers = registers;
        this.toggleInfo = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RegisterDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.register_item_view, parent, false);
        return new RegisterDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterDataViewHolder holder, int position) {
        holder.bind(this.registers.get(position));
    }

    @Override
    public int getItemCount() {
        return this.registers.size();
    }

    //----------- Métodos auxiliares

    public void setOpenViewCacheFor(int id) {
        this.toggleInfo.put(id, true);
    }

    public void unsetOpenViewCacheFor(int id) {
        this.toggleInfo.delete(id);
    }

    public boolean getOpenViewCacheFor(int id) {
        return this.toggleInfo.get(id, false);
    }


}
