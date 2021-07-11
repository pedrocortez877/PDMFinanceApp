package com.example.pdmfinanceapppedrocortez.ui.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pdmfinanceapppedrocortez.R;
import com.example.pdmfinanceapppedrocortez.data.DaoRegistersSingleton;
import com.example.pdmfinanceapppedrocortez.databinding.FragmentListBinding;
import com.example.pdmfinanceapppedrocortez.list_builders.RegisterListBuilder;
import com.example.pdmfinanceapppedrocortez.model.Registers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;
    private FragmentListBinding binding;
    private TextView etxtTotalPrice;
    private FloatingActionButton flabGenerateFile;
    AlertDialog alertDialog;

    public ListFragment(){
        super(R.layout.fragment_list);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        etxtTotalPrice = root.findViewById(R.id.valueTotalRegister);
        flabGenerateFile = root.findViewById(R.id.floatingActionButton);

        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Relatório Financeiro:");
        alertDialog.setMessage("Você deseja emitir um relatório financeiro?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SIM",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DaoRegistersSingleton.getInstance().formatAndExportRegisters(getContext());
                        Toast toast = Toast.makeText(getContext(), "Relatório financeiro foi emitido.", Toast.LENGTH_SHORT);
                        toast.show();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NÃO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        etxtTotalPrice.setText(verifyRegisterType());
        flabGenerateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        if (!DaoRegistersSingleton.getInstance().getRegisters().isEmpty()) {
            flabGenerateFile.setVisibility(View.VISIBLE);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Registers> registers = DaoRegistersSingleton.getInstance().getRegisters();
        new RegisterListBuilder(getActivity(), R.id.rvListRegisters, view)
                .load(registers, getActivity());
    }

    private String verifyRegisterType() {
        NumberFormat df = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        Double totalValue = DaoRegistersSingleton.getInstance().getRegisters().stream().map(
                register -> register.getType().equals("Credit") ? register.getPrice() : register.getPrice() * -1D
        ).reduce(0D, Double::sum);
        return df.format(totalValue);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}