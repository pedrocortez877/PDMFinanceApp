package com.example.pdmfinanceapppedrocortez.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Registers implements Parcelable {

    private int id;
    private String description;
    private Double price;

    public Registers(int id, String description, Double price){
        this.id = id;
        this.description = description;
        this.price = price;
    }

    protected Registers(Parcel in){
        id = in.readInt();
        description = in.readString();
        if(in.readByte() == 0){
            price = null;
        }else {
            price = in.readDouble();
        }
    }

    public static final Creator<Registers> CREATOR = new Creator<Registers>() {
        @Override
        public Registers createFromParcel(Parcel in) {
            return new Registers(in);
        }

        @Override
        public Registers[] newArray(int size) {
            return new Registers[size];
        }
    };

    public int getId(){ return id; }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(description);
        if(price == null){
            parcel.writeByte((byte) 0);
        }else{
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
    }
}
