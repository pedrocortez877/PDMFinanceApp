package com.example.pdmfinanceapppedrocortez.ui.newdrive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pdmfinanceapppedrocortez.databinding.FragmentNewdriveBinding;

public class NewDriveFragment extends Fragment {

    private NewDriveViewModel notificationsViewModel;
    private FragmentNewdriveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NewDriveViewModel.class);

        binding = FragmentNewdriveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}