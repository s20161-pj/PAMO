package com.example.zad4.ui.page4

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentFourthBinding
import com.example.zad4.databinding.FragmentThirdBinding

class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    private var resultTextView: TextView? = null
    private var button: Button? = null
    var spinner: Spinner? = null
    var recipeArray = arrayOf<String?>("Chicken pasta bake", "Vegetarian lasagne")
    var recipeDescriptionArray = arrayOf(
        """
    STEP 1
    Heat 2 tbsp of the oil in a pan over a medium heat and fry the onion gently for 10-12 mins. Add the garlic and chilli flakes and cook for 1 min. Tip in the tomatoes and sugar and season to taste. 
    
    STEP 2
    Heat 1 tbsp of oil in a non-stick frying pan. Season the chicken and fry for 5-7 mins or until the chicken is cooked through.
    """.trimIndent(),
        """
              STEP 1
              To make the tomato sauce, heat the olive oil in a saucepan. Add the onions, garlic and carrot. Cook for 5-7 mins over a medium heat until softened. 
              
              STEP 2
              To make the white sauce, melt the butter in a saucepan, stir in the plain flour, then cook for 2 mins. Slowly whisk in the milk, then bring to the boil, stirring. Turn down the heat, then cook until the sauce starts to thicken and coats the back of a wooden spoon.
              """.trimIndent()
    )
    var recipe = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val notificationsViewModel = ViewModelProvider(this).get(
            FourthViewModel::class.java
        )
        _binding = FragmentFourthBinding.inflate(inflater, container, false)

        val root: View = binding!!.root
        spinner = binding!!.spinner
        resultTextView = binding!!.resultTextView
        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(root.context, R.layout.simple_spinner_dropdown_item, recipeArray)
        spinner!!.adapter = adapter
        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, i: Int, l: Long) {
                recipe = recipeDescriptionArray[i]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        button = binding!!.btn
        button!!.setOnClickListener(buttonOnClick)
        val textView = binding!!.textFourth
        notificationsViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
            textView.text = text
        }
        return root
    }

    private val buttonOnClick = View.OnClickListener { resultTextView!!.text = recipe }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}