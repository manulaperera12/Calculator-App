package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var decPoint = false
    private var startedMinus = false
    private var numberTyped = false
    private var programmeStarted = false
    private var isPressedEqual = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDigit(view: View) {

        if (isPressedEqual){
            inputText.text = ""
            isPressedEqual = false
        }

        inputText.append((view as Button).text)
        numberTyped = true
    }

    fun onClear(view: View){
        inputText.text = ""
    }


    fun decimalPoint(view: View){
        if (!decPoint){
            inputText.append((view as Button).text)
            decPoint = true
        }
    }


    private fun isOperatorAdd(): Boolean {
        var inputTextTyped = inputText.text.toString()
        var minuses = inputTextTyped.split("-")

        startedMinus = minuses.size < 3

        return if (inputTextTyped.startsWith("-") && startedMinus){
            false
        }else{
            (inputTextTyped.contains("-") || inputTextTyped.contains("+") ||
                    inputTextTyped.contains("*") || inputTextTyped.contains("/"))
        }
    }

    fun onOperator(view: View){
        if (!isOperatorAdd() && numberTyped || !programmeStarted){
            inputText.append((view as Button).text)

            isPressedEqual = false
            decPoint = false
            numberTyped = false
            programmeStarted = true
        }
    }

    fun onEqual(view: View) {

        isPressedEqual = true

        if (numberTyped){
            var inputTextView = inputText.text.toString()

            var prefix = ""

            try {
                if (inputTextView.startsWith("-")){
                    prefix = "-"
                    inputTextView = inputTextView.substring(1)
                }

                if (inputTextView.contains("-")){
                    val splitValues = inputTextView.split("-")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    inputText.text = (one.toDouble() - two.toDouble()).toString()
                }

                if (inputTextView.contains("+")){
                    val splitValues = inputTextView.split("+")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    inputText.text = (one.toDouble() + two.toDouble()).toString()
                }

                if (inputTextView.contains("*")){
                    val splitValues = inputTextView.split("*")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    inputText.text = (one.toDouble() * two.toDouble()).toString()
                }

                if (inputTextView.contains("/")){
                    val splitValues = inputTextView.split("/")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    inputText.text = (one.toDouble() / two.toDouble()).toString()
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}