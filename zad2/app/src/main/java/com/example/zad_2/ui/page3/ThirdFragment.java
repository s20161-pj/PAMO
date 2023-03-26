package com.example.zad_2.ui.page3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.zad_2.R;
import com.example.zad_2.databinding.FragmentThirdBinding;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ThirdFragment extends Fragment {

    Spinner spinner;
    String[] sexArray = {"Kobieta", "Mężczyzna"};

    boolean isMale = false;

    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private double weight = 0; // weight entered by the user

    private double height = 0; // height entered by the user

    private int age = 0; // height entered by the user

    private TextView weightTextView; // shows formatted weight
    private TextView heightTextView; // shows formatted weight
    private TextView ageTextView; //shows formatted age
    private TextView resultTextView; // shows calculated BMI

    private Button calculateButton;
    private FragmentThirdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ThirdViewModel viewModel = new ViewModelProvider(this).get(ThirdViewModel.class);

        // get references to programmatically manipulated TextViews
        weightTextView = binding.weightTextView;
        heightTextView = binding.heightTextView;
        ageTextView = binding.ageTextView;
        resultTextView = binding.resultTextView;
        calculateButton = binding.btn;
        calculateButton.setOnClickListener(calculateOnClick);

        EditText weightEditText = binding.weightEditText;
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText heightEditText = binding.heightEditText;
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText ageEditText = binding.ageEditText;
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        spinner = (Spinner) binding.sexSpinner;
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

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };


        private final TextWatcher ageEditTextWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    age = Integer.parseInt(s.toString());
                    ageTextView.setText(numberFormat.format(age));
                } catch (NumberFormatException e) { // if s is empty or non-numeric
                    ageTextView.setText("");
                    age = 0;
                }
            }
        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private final View.OnClickListener calculateOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double result = 0.0;
            if (isMale) {
                result = 66.5+(13.75*(weight) + (5.003*(height))-(6.775*(age)));
            } else {
                result = 655.1+(9.563*(weight) + (1.85*(height))-(4.676*(age)));
            }
            DecimalFormat df = new DecimalFormat("#.#");
            double result_trimmed = Double.parseDouble(df.format(result));

            resultTextView.setText(Double.toString(result_trimmed));
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}