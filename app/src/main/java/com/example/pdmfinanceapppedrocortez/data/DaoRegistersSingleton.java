package com.example.pdmfinanceapppedrocortez.data;

import com.example.pdmfinanceapppedrocortez.model.Registers;

import java.util.ArrayList;

public class DaoRegistersSingleton {
    private static DaoRegistersSingleton INSTANCE;

    private ArrayList<Registers> registers;

    private DaoRegistersSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DaoRegistersSingleton();
        }
        return INSTANCE;
    }

    public ArrayList<Registers> getRegisters() {
        return registers;
    }

    public void addRegister(Registers registers){this.registers.add(registers);}
}
