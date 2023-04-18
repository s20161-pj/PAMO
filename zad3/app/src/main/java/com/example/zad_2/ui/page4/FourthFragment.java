package com.example.zad_2.ui.page4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad_2.R;
import com.example.zad_2.databinding.FragmentFourthBinding;
public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;
    private TextView resultTextView;
    private Button button;
    Spinner spinner;
    String[] recipeArray = {"Chicken pasta bake", "Vegetarian lasagne"};
    String[] recipeDescriptionArray = {"STEP 1\n" +
            "Heat 2 tbsp of the oil in a pan over a medium heat and fry the onion gently for 10-12 mins. Add the garlic and chilli flakes and cook for 1 min. Tip in the tomatoes and sugar and season to taste. \n" +
            "\n" +
            "STEP 2\n" +
            "Heat 1 tbsp of oil in a non-stick frying pan. Season the chicken and fry for 5-7 mins or until the chicken is cooked through.",

            "STEP 1\n" +
                    "To make the tomato sauce, heat the olive oil in a saucepan. Add the onions, garlic and carrot. Cook for 5-7 mins over a medium heat until softened. \n" +
                    "\n" +
                    "STEP 2\n" +
                    "To make the white sauce, melt the butter in a saucepan, stir in the plain flour, then cook for 2 mins. Slowly whisk in the milk, then bring to the boil, stirring. Turn down the heat, then cook until the sauce starts to thicken and coats the back of a wooden spoon."};
    String recipe = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FourthViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FourthViewModel.class);
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinner = (Spinner) binding.spinner;
        resultTextView = binding.resultTextView;

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item, recipeArray);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                recipe = recipeDescriptionArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button = binding.btn;
        button.setOnClickListener(buttonOnClick);

        final TextView textView = binding.textFourth;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private final View.OnClickListener buttonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resultTextView.setText(recipe);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}