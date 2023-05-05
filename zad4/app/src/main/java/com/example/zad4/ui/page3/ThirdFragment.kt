package com.example.zad4.ui.page3

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zad4.databinding.FragmentFirstBinding
import com.example.zad4.databinding.FragmentThirdBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    var spinner: Spinner? = null
    var sexArray = arrayOf<String?>("Kobieta", "Mężczyzna")
    var isMale = false
    private var weight = 0.0 // weight entered by the user
    private var height = 0.0 // height entered by the user
    private var age = 0 // height entered by the user
    private var weightTextView // shows formatted weight
            : TextView? = null
    private var heightTextView // shows formatted weight
            : TextView? = null
    private var ageTextView //shows formatted age
            : TextView? = null
    private var resultTextView // shows calculated BMI
            : TextView? = null
    private var calculateButton: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        val root: View = binding.getRoot()
        // get references to programmatically manipulated TextViews
        weightTextView = binding.weightTextView
        heightTextView = binding.heightTextView
        ageTextView = binding.ageTextView
        resultTextView = binding.resultTextView
        calculateButton = binding.btn
        calculateButton!!.setOnClickListener(calculateOnClick)
        val weightEditText: EditText = binding.weightEditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText: EditText = binding.heightEditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        val ageEditText: EditText = binding.ageEditText
        ageEditText.addTextChangedListener(ageEditTextWatcher)
        spinner = binding.sexSpinner
        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(root.context, R.layout.simple_spinner_dropdown_item, sexArray)
        spinner!!.adapter = adapter
        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, i: Int, l: Long) {
                isMale = if (parent.selectedItem.toString() === "Mężczyzna") true else false
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        return root
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                weight = s.toString().toDouble()
                weightTextView!!.text = numberFormat.format(weight)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                weightTextView!!.text = ""
                weight = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = s.toString().toDouble()
                heightTextView!!.text = numberFormat.format(height)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                heightTextView!!.text = ""
                height = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                age = s.toString().toInt()
                ageTextView!!.text = numberFormat.format(age.toLong())
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                ageTextView!!.text = ""
                age = 0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
    private val calculateOnClick = View.OnClickListener {
        var result = 0.0
        result = if (isMale) {
            66.5 + 13.75 * weight + 5.003 * height - 6.775 * age
        } else {
            655.1 + 9.563 * weight + 1.85 * height - 4.676 * age
        }
        val df = DecimalFormat("#.#")
        val result_trimmed = df.format(result).toDouble()
        resultTextView!!.text = java.lang.Double.toString(result_trimmed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}