package com.example.pdmfinanceapppedrocortez.data;

import android.content.Context;

import com.example.pdmfinanceapppedrocortez.model.Registers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DaoRegistersSingleton {
    private static DaoRegistersSingleton INSTANCE;

    private ArrayList<Registers> registers;

    public static DaoRegistersSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DaoRegistersSingleton();
        }
        return INSTANCE;
    }

    public ArrayList<Registers> getRegisters() {
        if(this.registers == null){
            this.registers = new ArrayList<>();
        }
        return this.registers;
    }

    public void addRegister(Registers registers){
        if(this.registers == null){
            this.registers = new ArrayList<>();
        }
        this.registers.add(registers);
    }

    public void formatAndExportRegisters(Context context) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String nameFile = formatDate.format(LocalDateTime.now());
        File file = new File(context.getFilesDir(), nameFile);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String fileString = this.registers.stream().map(register -> register.toCsvString()).reduce("", String::concat);
            writer.write(fileString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
