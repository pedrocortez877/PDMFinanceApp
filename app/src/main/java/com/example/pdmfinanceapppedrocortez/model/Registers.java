package com.example.pdmfinanceapppedrocortez.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Registers implements Parcelable {

    private String description;
    private Double price;
    private String type;

    public Registers(String description, Double price, String type){
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Registers(){

    }

    protected Registers(Parcel in){
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

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toCsvString() {
        Double registerValue = this.type.equals("Credit") ? this.price : this.price * -1;
        String csvString = this.description.concat(";").concat(registerValue.toString()).concat("\n");
        return csvString;
    }

    public String formatPrice() {
        String value = "";
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        if (this.type.equals("Debit")) {
            value = value.concat("-");
        }
        value = value.concat(formatter.format(new BigDecimal(this.price)));
        return value;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        if(price == null){
            parcel.writeByte((byte) 0);
        }else{
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
    }
}
