package com.example.zad4.ui.page2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentSecondBinding
import com.example.zad4.databinding.FragmentThirdBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class SecondFragment : Fragment() {
    private var weight = 0.0 // weight entered by the user
    private var height = 0.0 // height entered by the user
    private var weightTextView // shows formatted weight
            : TextView? = null
    private var heightTextView // shows formatted weight
            : TextView? = null
    private var resultTextView // shows calculated BMI
            : TextView? = null
    private var calculateButton: Button? = null
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val dashboardViewModel = ViewModelProvider(this).get(
            SecondViewModel::class.java
        )
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.getRoot()

        // get references to programmatically manipulated TextViews
        weightTextView = binding.weightTextView
        heightTextView = binding.heightTextView
        resultTextView = binding.resultTextView
        calculateButton = binding.btn
        calculateButton!!.setOnClickListener(calculateOnClick)
        val weightEditText: EditText = binding.weightEditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText: EditText = binding.heightEditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        return root
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try {
                weight = s.toString().toDouble()
                weightTextView!!.text = numberFormat.format(weight)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                weightTextView!!.text = ""
                weight = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    // listener object for the EditText's text-changed events
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try {
                height = s.toString().toDouble()
                heightTextView!!.text = numberFormat.format(height)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                heightTextView!!.text = ""
                height = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }
    private val calculateOnClick = View.OnClickListener {
        val heightM = height / 100
        val BMI = weight / (heightM * heightM)
        val df = DecimalFormat("#.#")
        val BMI_trimmed = df.format(BMI).toDouble()
        resultTextView!!.text = java.lang.Double.toString(BMI_trimmed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // currency and percent formatter objects
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}