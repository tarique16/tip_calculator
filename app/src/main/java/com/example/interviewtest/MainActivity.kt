package com.example.interviewtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding.tvCalculate.setOnClickListener(this)
        binding.tvReset.setOnClickListener(this)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                tvCalculate.id -> {
                    if (etEnterAmount.text.isEmpty() || etEnterSplitAmount.text.isEmpty() || etEnterTipAmount.text.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Missing feilds", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        if (etEnterAmount.text.toString() == "0.0" || etEnterSplitAmount.text.toString() == "0" || etEnterTipAmount.text.toString() == "0") {
                            Toast.makeText(
                                this@MainActivity,
                                "Cannot calculate on 0 value",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else {
                            tvTotal.text = calculate(
                                if (etEnterAmount.text.isNotEmpty()) etEnterAmount.text.toString()
                                    .toDouble() else 0.0,
                                if (etEnterSplitAmount.text.isNotEmpty()) etEnterSplitAmount.text.toString()
                                    .toDouble() else 0.0,
                                if (etEnterTipAmount.text.isNotEmpty()) etEnterTipAmount.text.toString()
                                    .toDouble() else 0.0
                            )
                        }
                    }
                }

                tvReset.id -> {
                    etEnterAmount.setText("0.0")
                    etEnterSplitAmount.setText("0")
                    etEnterTipAmount.setText("0")
                    tvTotal.text = "\u20b9 0.0"
                    tvTotalTip.text = "\u20b9 0.0"
                }
            }
        }
    }

    private fun calculate(amount: Double, split: Double, tip: Double): String {
        val total: String
        val tipAmount = (amount.times(tip)).div(100).toString()
        binding.tvTotalTip.text = "\u20b9 $tipAmount"
        val totalAmountTip = amount.plus(tipAmount.toDouble())
        total = "₹ ${totalAmountTip.div(split.toInt())}"
        return total
    }

}