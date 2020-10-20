package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onEqual(view: View){
        if(lastNumeric){
            try {
                fun removeAfterZero(result: String){
                    var value = result

                    if (result.contains(".0")) {
                        value = result.substring(0, result.length - 2)
                    }
                }
                var calValue = calculator_textView.text.toString()
                var prefix =""
                if(calValue.startsWith("-")){
                    prefix="-"
                    calValue = calValue.substring(1)
                    val numSplit = calValue.split("-")
                    val num1 = numSplit[0]
                    val num2 = numSplit[1]
                    calculator_textView.text=(-(num1.toDouble()+num2.toDouble())).toString()
                }
                if(calValue.contains("-")){
                    val numSplit = calValue.split("-")
                    val num1 = numSplit[0]
                    val num2 = numSplit[1]
                    calculator_textView.text = (num1.toDouble()-num2.toDouble()).toString()
                }
                if (calValue.contains("/")) {
                    // Will split the inputValue using Division operator
                    val splitedValue = calValue.split("/")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }


                    calculator_textView.text = removeAfterZero(((one.toDouble())/(two.toDouble())).toString()).toString()
                }
                if (calValue.contains("+")) {
                    // Will split the inputValue using Division operator
                    val splitedValue = calValue.split("+")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }


                    calculator_textView.text = ((one.toDouble())+(two.toDouble())).toString()
                }
                if (calValue.contains("X")) {
                    // Will split the inputValue using Division operator
                    val splitedValue = calValue.split("X")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }


                    calculator_textView.text = ((one.toDouble())*(two.toDouble())).toString()
                }
            }catch (e: ArithmeticException){
                //Arithmetic exceptions like DivideByZero Exception are caught with this block
            }
        }
    }

     fun onDigit(view: View){
        calculator_textView.append((view as Button).text)
         lastNumeric=true
    }
    fun onClear(view:View){
        calculator_textView.text=""
        lastNumeric=false
        lastDot=false
    }
    fun onDecimalPt(view: View){
        if(!lastDot && lastNumeric){
            calculator_textView.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(calculator_textView.text.toString())){
            calculator_textView.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }

    }
    fun removeAfterZero(result:String){
        if(result.contains(".0")){
            result.substring(0,result.length-2)
        }
    }
    private fun isOperatorAdded(value: String):Boolean{
        return if (!value.startsWith("-")) {
            false
        } else {
            value.contains("/")||value.contains("-")||value.contains("*")||value.contains("+")
            //Operator-check is being done in this part
        }
    }
}
