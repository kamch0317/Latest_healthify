package com.example.healthify

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthify.databinding.ActivityCalculatorBinding
import com.example.healthify.*
import kotlinx.android.synthetic.main.activity_calculator.*
import java.text.NumberFormat
import kotlin.math.pow

class Calculator : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculate()
        }

        binding.timerButton.setOnClickListener {
            timer()
        }

        binding.reminderButton.setOnClickListener {
            reminder()
        }


//        binding.backButton.setOnClickListener {
//            home()
//        }

        bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homefragment -> {
                    home()

                }

                R.id.tools -> {
                    tools()


                }

                R.id.workout -> {
                    workout()

                }

                R.id.foodplan -> {
                    foodplan()

                }

                R.id.userfragment -> {
                    usersetting()

                }


            }
            true
        }
    }

    private fun calculate() {
        val weight = binding.weightInput.text
        val height = binding.heightInput.text
        val age = binding.ageInput.text
        val id = binding.radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = binding.root.findViewById(id)
        val gender = radioButton.text.toString()

        val weightValue = weight.toString().toDoubleOrNull()
        val heightValue = height.toString().toDoubleOrNull()
        val ageValue = age.toString().toIntOrNull()

        binding.noSatisfiedQuote.setBackgroundResource(R.drawable.round_border)
        binding.moderateQuote.setBackgroundResource(R.drawable.round_border)
        binding.satisfiedQuote.setBackgroundResource(R.drawable.round_border)

        binding.noSatisfiedQuote.setTextColor(Color.parseColor("#000000"))
        binding.moderateQuote.setTextColor(Color.parseColor("#000000"))
        binding.satisfiedQuote.setTextColor(Color.parseColor("#000000"))

        if (weightValue == null || heightValue == null || ageValue == null) {
            Toast.makeText(
                this,
                "Please enter weight, height and age to calculate Fat Percentage & BMI!",
                Toast.LENGTH_SHORT
            ).show()
            binding.resultFinal.setTextColor(Color.parseColor("#917D7D"))
            binding.resultFinal.text = getString(R.string.final_result, "Your Results are")
            val ori = "Your Fat Percentage level is"
            binding.fpSentence.text = getString(R.string.FP_sentence, "$ori")
            return
        }

        val squareHeight: Double = heightValue.pow(2)

        val bmi: Double = weightValue / squareHeight
        val roundedBmi = String.format("%.2f", bmi).toDouble()
        val formatBmi = NumberFormat.getNumberInstance().format(roundedBmi)

        if (gender == "Male") {
            //fat percentage formula for male: (1.20 x BMI) + (0.23 x Age) - 16.2
            val fatPercentage: Double = (roundedBmi.times(1.2)) + (ageValue.times(0.23)) - 16.2
            val roundedFatPercentage = String.format("%.2f", fatPercentage).toDouble()
            val formatFatPercentage = NumberFormat.getNumberInstance().format(roundedFatPercentage)
            binding.fpBmiResult.setTextColor(Color.parseColor("#000000"))
            binding.fpBmiResult.text = getString(R.string.final_result, "$formatFatPercentage% & $formatBmi kg/m²")

            when{
                ageValue <= 40 ->{
                    fatPercentageLevel(fatPercentage,8,19,25)
                }

                ageValue in 41..60 ->{
                    fatPercentageLevel(fatPercentage,11,22,27)
                }

                ageValue > 60 ->{
                    fatPercentageLevel(fatPercentage,13,25,30)
                }
            }

        } else if(gender == "Female"){
            //fat percentage formula: (1.20 x BMI) + (0.23 x Age) - 5.4
            val fatPercentage: Double = (roundedBmi.times(1.2)) + (ageValue.times(0.23)) - 5.4
            val roundedFatPercentage = String.format("%.2f", fatPercentage).toDouble()
            val formatFatPercentage = NumberFormat.getNumberInstance().format(roundedFatPercentage)
            binding.fpBmiResult.setTextColor(Color.parseColor("#000000"))
            binding.fpBmiResult.text = getString(R.string.final_result, "$formatFatPercentage% & $formatBmi kg/m²")

            when{
                ageValue <= 40 ->{
                    fatPercentageLevel(fatPercentage,21,33,39)
                }

                ageValue in 41..59 ->{
                    fatPercentageLevel(fatPercentage,23,25,40)
                }

                ageValue > 60 ->{
                    fatPercentageLevel(fatPercentage,24,36,42)
                }
            }
        }


    }

    private fun fatPercentageLevel(fatPercentage: Double,underFat: Int, overweight: Int, obese:Int){
        when{
            fatPercentage < underFat ->{
                binding.moderateQuote.setBackgroundColor(Color.parseColor("#6804EC"))
                binding.moderateQuote.setTextColor(Color.parseColor("#FFFFFF"))
                val underFatString = "You are Underfat!"
                binding.fpSentence.text = getString(R.string.FP_sentence, "$underFatString")
                binding.fpSentence.setTextColor(Color.parseColor("#1331f0"))
            }

            fatPercentage > underFat && fatPercentage < overweight ->{
                binding.satisfiedQuote.setBackgroundColor(Color.parseColor("#6804EC"))
                binding.satisfiedQuote.setTextColor(Color.parseColor("#FFFFFF"))
                val healthyString = "You are Healthy!"
                binding.fpSentence.text = getString(R.string.FP_sentence, "$healthyString")
                binding.fpSentence.setTextColor(Color.parseColor("#20c920"))
            }

            fatPercentage > overweight && fatPercentage < obese ->{
                binding.moderateQuote.setBackgroundColor(Color.parseColor("#6804EC"))
                binding.moderateQuote.setTextColor(Color.parseColor("#FFFFFF"))
                val overweight = "You are OverWeight!"
                binding.fpSentence.text = getString(R.string.FP_sentence, "$overweight")
                binding.fpSentence.setTextColor(Color.parseColor("#1331f0"))
            }

            fatPercentage > obese ->{
                binding.noSatisfiedQuote.setBackgroundColor(Color.parseColor("#6804EC"))
                binding.noSatisfiedQuote.setTextColor(Color.parseColor("#FFFFFF"))
                val overweight = "You are Obese!"
                binding.fpSentence.text = getString(R.string.FP_sentence, "$overweight")
                binding.fpSentence.setTextColor(Color.parseColor("#f01831"))
            }
        }
    }

    private fun timer(){
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        intent.putExtra("username", name);
        val intent = Intent(context, Timers::class.java)
        startActivity(intent)
    }

    private fun reminder(){
        val name = intent.getStringExtra("username")
        //val context = binding.bottomNav.context
        intent.putExtra("userName", name);
        val intent = Intent(this, Reminder::class.java)
        startActivity(intent)
    }

    private fun home() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Home::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

    private fun tools() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Calculator::class.java)
        intent.putExtra("username", name);
        startActivity(intent)
    }

    private fun workout() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, WorkoutMain::class.java)
        intent.putExtra("username", name);
        startActivity(intent)
    }

    private fun foodplan() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, SelectedPage::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

    private fun usersetting() {
        val name = intent.getStringExtra("username")
//        val context = binding.bottomNav.context
        val intent = Intent(this, Usersetting::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

}
