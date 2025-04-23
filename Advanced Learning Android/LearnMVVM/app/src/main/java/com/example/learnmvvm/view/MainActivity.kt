package com.example.learnmvvm.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.learnmvvm.R
import com.example.learnmvvm.databinding.ActivityMainBinding
import com.example.learnmvvm.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var calculatorViewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        binding.calcuateButton.setOnClickListener {
            // toIntOrNull: converts the string obtained in toString to an integer
            // if the conversion succeeds then we return the integer value
            // otherwise it returns null
            // "?:" the Elvis operator: takes the result of toIntOrNull then if toIntOrNull returns Null
            // then its default is set to zero 0
            val num1 = binding.editTextNum1.text.toString().toIntOrNull() ?: 0
            val num2 = binding.editTextNum2.text.toString().toIntOrNull() ?: 0

            val result = calculatorViewModel.calculateSum(num1, num2)
            binding.resultTextView.text = "${result.sum}"

        }
    }
}