package br.igorsantos.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.igorsantos.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercent = when(selectedId){
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }
        var tip = tipPercent * cost

        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}