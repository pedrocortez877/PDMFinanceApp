package com.example.pdmfinanceapppedrocortez.list;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdmfinanceapppedrocortez.R;
import com.example.pdmfinanceapppedrocortez.model.Registers;

public class RegisterDataViewHolder extends RecyclerView.ViewHolder{
    private final TextView txtRegisterDescription;
    private final TextView txtRegisterPrice;
    private final View viewRegisterType;

    public RegisterDataViewHolder(@NonNull View itemView) {
        super(itemView);
        this.txtRegisterDescription = itemView.findViewById(R.id.labelDescRegister);
        this.txtRegisterPrice = itemView.findViewById(R.id.registerPriceLabel);
        this.viewRegisterType = itemView.findViewById(R.id.typeRegisterLabel);
    }

    public void bind(Registers register) {
        this.txtRegisterDescription.setText(register.getDescription());
        this.txtRegisterPrice.setText(register.formatPrice());
        int colorRegister = register.getType().equals("Credit") ? R.color.green : R.color.red;
        this.viewRegisterType.setBackgroundResource(colorRegister);
    }

}
