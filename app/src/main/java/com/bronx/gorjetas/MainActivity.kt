package com.bronx.gorjetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var amount : EditText
    private lateinit var CustomPct : SeekBar
    private lateinit var tip15pctTxt : TextView
    private lateinit var tipCustompctTxt : TextView
    private lateinit var total15pctTxt : TextView
    private lateinit var totalCustompctTxt : TextView
    private lateinit var  txtCustomPct : TextView
    private var CustomValue : Int = 0
    private var value : String = ""
    private var tip15 : Double = 0.00
    private var tipCustom : Double = 0.00
    private var totalTip15 : Double = 0.00
    private var totalTipCustom : Double = 0.00



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amount = findViewById(R.id.AmountEditText)
        CustomPct = findViewById(R.id.seekBarCustompct)
        tip15pctTxt = findViewById(R.id.tip15pctTxt)
        tipCustompctTxt = findViewById(R.id.tipCustompctTxt)
        total15pctTxt = findViewById(R.id.total15pctTxt)
        totalCustompctTxt = findViewById(R.id.totalCustompctTxt)
        txtCustomPct = findViewById(R.id.txtCustomPct)

        var amountValue = amount.text.toString()
        amount.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                var valueAux = p0.toString()
                value = valueAux
                if(p0.toString() == ""){
                    tip15pctTxt.text = "$0.00"
                    tipCustompctTxt.text = "$0.00"
                    total15pctTxt.text = "$0.00"
                    totalCustompctTxt.text = "$0.00"
                } else {
                    if(value == "" ){
                        tip15pctTxt.text = "$0.00"
                        tipCustompctTxt.text = "$0.00"
                        total15pctTxt.text = "$0.00"
                        totalCustompctTxt.text = "$0.00"
                    } else{
                        valores(value)
                    }

                }

            }

        })

        CustomPct.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                txtCustomPct.text = "$p1%"
                CustomValue = p1
                valores(value)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    fun valores(valueAux: String){
        Log.d("Value", value.toString())
        if(valueAux != ""){
            var value = valueAux.toDouble()
            this.tip15 = String.format("%.2f", 0.15*value).toDouble()
            tip15pctTxt.text = "$$tip15"


            if(CustomValue == 0){
                this.tipCustom = (this.CustomValue.toDouble()/100)*value
                this.totalTipCustom = tipCustom
                tipCustompctTxt.text = "$$tipCustom"
                totalCustompctTxt.text = "$$totalTipCustom"
            } else {
                this.tipCustom = String.format("%.2f", (this.CustomValue.toDouble()/100)*value).toDouble()
                tipCustompctTxt.text = "$$tipCustom"
                this.totalTipCustom = String.format("%.2f", tipCustom + value).toDouble()
                totalCustompctTxt.text = "$$totalTipCustom"
            }

            this.totalTip15 = String.format("%.2f", tip15 + value).toDouble()
            total15pctTxt.text = "$$totalTip15"

        }

    }
}
