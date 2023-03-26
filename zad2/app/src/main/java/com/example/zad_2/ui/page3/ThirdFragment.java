package com.example.zad_2.ui.page3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad_2.MainActivity;
import com.example.zad_2.R;
import com.example.zad_2.databinding.FragmentThirdBinding;



public class ThirdFragment extends Fragment {

    Spinner spinner;
    String[] sexArray ={"Kobieta", "Mężczyzna"};

    boolean isMale = false;
    private FragmentThirdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ThirdViewModel viewModel = new ViewModelProvider(this).get(ThirdViewModel.class);

        spinner = (Spinner)binding.sexSpinner;
        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item, sexArray);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                isMale = parent.getSelectedItem().toString() == "Mężczyzna" ? true : false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}