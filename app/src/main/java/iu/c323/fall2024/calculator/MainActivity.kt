package iu.c323.fall2024.calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private var currentTextView = "0"
    private var operation: String? = null
    private var firstNumber: String? = null
    private var secondNumber: String? = null

    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var negative: Button
    private lateinit var clear: Button
    private lateinit var percent: Button
    private lateinit var divide: Button
    private lateinit var multiply: Button
    private lateinit var minus: Button
    private lateinit var plus: Button
    private lateinit var equals: Button
    private lateinit var decimal: Button

//Initializes app and creates click listeners for each button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        displayTextView = findViewById(R.id.input)
        displayTextView.text = currentTextView
        one = findViewById(R.id.one_button)
        two = findViewById(R.id.two_button)
        three = findViewById(R.id.three_button)
        four = findViewById(R.id.four_button)
        five = findViewById(R.id.five_button)
        six = findViewById(R.id.six_button)
        seven = findViewById(R.id.seven_button)
        eight = findViewById(R.id.eight_button)
        nine = findViewById(R.id.nine_button)
        zero = findViewById(R.id.zero_button)
        negative = findViewById(R.id.negate_button)
        clear = findViewById(R.id.c_button)
        percent = findViewById(R.id.percent_button)
        divide = findViewById(R.id.division_button)
        multiply = findViewById(R.id.multiply_button)
        minus = findViewById(R.id.minus_button)
        plus = findViewById(R.id.plus_button)
        equals = findViewById(R.id.equal_button)
        decimal = findViewById(R.id.decimal_button)

        val buttons = listOf(
            one,
            two,
            three,
            four,
            five,
            six,
            seven,
            eight,
            nine,
            zero,
            negative,
            clear,
            percent,
            divide,
            multiply,
            minus,
            plus,
            decimal,
            equals
        )

        buttons.forEach { button ->
            button.setOnClickListener { view ->
                onButtonClick(view)
            }


        }
    }

    //Handles button click event

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        //Edge case
        if(currentTextView == "Error"){
            currentTextView = "0"
            firstNumber = null
            secondNumber = null
            operation = null
        }

        if (button.id == R.id.c_button) {
            currentTextView = "0"
            firstNumber = null
            secondNumber = null
            operation = null
            displayTextView.text = currentTextView
        } else if (button.id == R.id.division_button || button.id == R.id.multiply_button || button.id == R.id.minus_button || button.id == R.id.plus_button) {
            //Case for basic operation
            if (currentTextView.isNotEmpty() && firstNumber == null) {
                firstNumber = currentTextView
                operation = buttonText
                displayTextView.text = currentTextView
                currentTextView = ""
            //Case for when the user has already clicked an operation (a long calculation)
            } else if (currentTextView.isNotEmpty() && operation != null) {
                secondNumber = currentTextView
                currentTextView = performOperation(
                    firstNumber!!.toDouble(),
                    secondNumber!!.toDouble(),
                    operation!!
                )
                operation = buttonText
                displayTextView.text = currentTextView
                firstNumber = currentTextView
                secondNumber = null
                displayTextView.text = currentTextView
                currentTextView = ""
            }
        } else if (button.id == R.id.equal_button) {
            if (firstNumber != null && operation != null) {
                secondNumber = currentTextView
                currentTextView = performOperation(
                    firstNumber!!.toDouble(),
                    secondNumber!!.toDouble(),
                    operation!!
                )
                firstNumber = null
                secondNumber = null
                operation = null
            }
            displayTextView.text = currentTextView
        } else if (button.id == R.id.decimal_button) {
            if (!currentTextView.contains(".")) {
                currentTextView += "."
            }
            displayTextView.text = currentTextView
        } else if (button.id == R.id.negate_button) {
            if (currentTextView.isNotEmpty()) {
                currentTextView = if (currentTextView.startsWith("-")) {
                    currentTextView.substring(1)
                } else {
                    "-$currentTextView"
                }
            }
            displayTextView.text = currentTextView
        } else if (button.id == R.id.percent_button) {
            if (currentTextView.isNotEmpty()) {
                currentTextView = (currentTextView.toDouble() / 100).toString()
            }
            displayTextView.text = currentTextView
        } else {
            if (currentTextView == "0") {
                currentTextView = buttonText
            } else {
                currentTextView += buttonText
            }
            displayTextView.text = currentTextView
        }
    }

// Calculates operation

    private fun performOperation(num1: Double, num2: Double, op: String): String {
        //Edge case
        if(op=="/" && num2 == 0.0){
            return "Error"
        }
        return when (op) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "x" -> (num1 * num2).toString()
            "/" -> (num1 / num2).toString()
            else -> ""
        }
    }

}