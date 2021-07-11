package com.example.pdmfinanceapppedrocortez.ui.newdrive;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pdmfinanceapppedrocortez.R;
import com.example.pdmfinanceapppedrocortez.data.DaoRegistersSingleton;
import com.example.pdmfinanceapppedrocortez.databinding.FragmentNewdriveBinding;
import com.example.pdmfinanceapppedrocortez.model.Registers;

public class NewDriveFragment extends Fragment {

    private NewDriveViewModel newDriveViewModel;
    private FragmentNewdriveBinding binding;
    private TextView etxtDescription;
    private TextView etxtValue;
    private Spinner spnDebitOrCredit;
    private String type;
    private Button btnSend;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newDriveViewModel =
                new ViewModelProvider(this).get(NewDriveViewModel.class);

        binding = FragmentNewdriveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        etxtDescription = root.findViewById(R.id.etxtDescription);
        etxtValue = root.findViewById(R.id.etxtValue);
        spnDebitOrCredit = root.findViewById(R.id.spnDebitOrCred);
        btnSend = root.findViewById(R.id.btnSend);
        spnDebitOrCredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = spnDebitOrCredit.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nenhum item selecionado");
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave(view);
            }
        });

        return root;
    }

    public void onClickSave(View view){
        String descriptionSave = this.etxtDescription.getText().toString();
        Double valueSave = Double.parseDouble(this.etxtValue.getText().toString());

        Registers register = new Registers();
        register.setDescription(descriptionSave);
        register.setPrice(valueSave);
        register.setType(type);

        DaoRegistersSingleton.getInstance().addRegister(register);

        etxtDescription.setText("");
        etxtValue.setText("");
        spnDebitOrCredit.setSelection(0);
        Toast toast = Toast.makeText(getContext(), "Transação efetuada", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}