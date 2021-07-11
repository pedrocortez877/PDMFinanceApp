package com.example.pdmfinanceapppedrocortez.list_builders;

import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdmfinanceapppedrocortez.list.RegisterDataAdapter;
import com.example.pdmfinanceapppedrocortez.model.Registers;

import java.util.ArrayList;

public class RegisterListBuilder {
    private RecyclerView rvRegisterList;
    private LinearLayoutManager layoutManager;
    private RegisterDataAdapter adapter;

    public RegisterListBuilder(Activity activity, @IdRes int rvRegisters, View view) {
        this.rvRegisterList = view.findViewById(rvRegisters);
        this.layoutManager = new LinearLayoutManager(activity);
        this.rvRegisterList.setLayoutManager(this.layoutManager);
        this.adapter = null;
    }

    public RegisterListBuilder load(ArrayList<Registers> registers, Activity activity) {
        this.adapter = new RegisterDataAdapter(registers, activity);
        this.rvRegisterList.setAdapter(this.adapter);
        return this;
    }


}
