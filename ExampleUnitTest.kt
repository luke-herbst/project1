package iu.c323.fall2024.calculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    private lateinit var calculator: MainActivity
    private lateinit var mockButton: Button
    private lateinit var mockDisplayTextView: TextView


    @Before
    fun setUp() {

        calculator = MainActivity()

        mockButton = mock(Button::class.java)


        mockDisplayTextView = mock(TextView::class.java)


        calculator.displayTextView = mockDisplayTextView
    }

    @Test
    fun testAddition() {
        val result = calculator.performOperation(2.0, 3.0, "+")
        assertEquals("5.0", result)
    }


    @Test
    fun testSubtraction() {
        val result = calculator.performOperation(3.0, 2.0, "-")
        assertEquals("1.0", result)

    }

    fun testDivision{
        val result = calculator.performOperation(4.0,2.0."/")
        assertEquals("2.0", result)
    }

    fun testMultiplication{
        val result = calculator.performOperation(4.0,2.0."*")
        assertEquals("8.0", result)
    }

    fun testDivision2{
        val result = calculator.performOperation(4.0,0.0."/")
        assertEquals("Error", result)
    }

    fun testElse{
        val result = calculator.performOperation(2.0,3.0."b")
        assertEquals("", result)
    }


    fun testOnButtonClick_AdditionOperation() {
        // Set up a scenario where the button clicked is the "+" button
        `when`(mockButton.id).thenReturn(R.id.plus_button)
        `when`(mockButton.text).thenReturn("+")

        // Set up initial values for the calculator
        calculator.currentTextView = "5"
        calculator.firstNumber = "5"
        calculator.operation = null

        // Simulate the button click
        calculator.onButtonClick(mockButton)

        // Verify the expected result after the button click
        assertEquals("", calculator.currentTextView) // Current text cleared for next number
        assertEquals("5", calculator.firstNumber) // First number should remain "5"
        assertEquals("+", calculator.operation) // Operation should be set to "+"
    }


    fun testOnButtonClick_SubtractionOperation() {

        `when`(mockButton.id).thenReturn(R.id.plus_button)
        `when`(mockButton.text).thenReturn("-")


        calculator.currentTextView = "5"
        calculator.firstNumber = "5"
        calculator.operation = null


        calculator.onButtonClick(mockButton)


        assertEquals("", calculator.currentTextView)
        assertEquals("5", calculator.firstNumber)
        assertEquals("-", calculator.operation)
    }

    fun testOnButtonClick_MultiplicationOperation() {

        `when`(mockButton.id).thenReturn(R.id.multiply_button)
        `when`(mockButton.text).thenReturn("*")


        calculator.currentTextView = "5"
        calculator.firstNumber = "5"
        calculator.operation = null


        calculator.onButtonClick(mockButton)


        assertEquals("", calculator.currentTextView)
        assertEquals("5", calculator.firstNumber)
        assertEquals("*", calculator.operation)
    }

    fun testOnButtonClick_DivisionOperation() {

        `when`(mockButton.id).thenReturn(R.id.division_button)
        `when`(mockButton.text).thenReturn("/")


        calculator.currentTextView = "5"
        calculator.firstNumber = "5"
        calculator.operation = null


        calculator.onButtonClick(mockButton)


        assertEquals("", calculator.currentTextView)
        assertEquals("5", calculator.firstNumber)
        assertEquals("/", calculator.operation)
    }
}