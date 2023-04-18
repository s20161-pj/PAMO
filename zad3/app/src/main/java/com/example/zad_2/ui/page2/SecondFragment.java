package com.example.zad_2.ui.page2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad_2.databinding.FragmentSecondBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class SecondFragment extends Fragment {
    // currency and percent formatter objects
    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private double weight = 0; // weight entered by the user
    private double height = 0; // height entered by the user

    private TextView weightTextView; // shows formatted weight
    private TextView heightTextView; // shows formatted weight

    private TextView resultTextView; // shows calculated BMI

    private Button calculateButton;

    private FragmentSecondBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SecondViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SecondViewModel.class);

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // get references to programmatically manipulated TextViews
        weightTextView = binding.weightTextView;
        heightTextView = binding.heightTextView;
        resultTextView = binding.resultTextView;
        calculateButton = binding.btn;
        calculateButton.setOnClickListener(calculateOnClick);

        EditText weightEditText = binding.weightEditText;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText heightEditText = binding.heightEditText;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        return root;
    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                weight = Double.parseDouble(s.toString());
                weightTextView.setText(numberFormat.format(weight));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                weightTextView.setText("");
                weight = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    // listener object for the EditText's text-changed events
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                height = Double.parseDouble(s.toString());
                heightTextView.setText(numberFormat.format(height));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                heightTextView.setText("");
                height = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    private final View.OnClickListener calculateOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double heightM = height / 100;
            double BMI = (weight) / (heightM * heightM);
            DecimalFormat df = new DecimalFormat("#.#");
            double BMI_trimmed = Double.parseDouble(df.format(BMI));

            resultTextView.setText(Double.toString(BMI_trimmed));
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}