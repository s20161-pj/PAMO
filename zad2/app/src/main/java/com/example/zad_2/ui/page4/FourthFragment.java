package com.example.zad_2.ui.page4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad_2.databinding.FragmentFourthBinding;


public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FourthViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FourthViewModel.class);

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFourth;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}