import android.widget.Button
import android.widget.TextView
import iu.c323.fall2024.calculator.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainActivityUnitTest {


    // DOES NOT WORK ALL TESTS UNDER ANDROID TEST
    @Mock
    private lateinit var mockButton: Button

    @Mock
    private lateinit var mockDisplayTextView: TextView


    private lateinit var calculator: MainActivity

    @Before
    fun setUp() {
        // Initialize the calculator with mocked views
        calculator = MainActivity().apply {
            displayTextView = mockDisplayTextView
        }
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

    @Test
    fun testDivision() {
        val result = calculator.performOperation(4.0, 2.0, "/")
        assertEquals("2.0", result)
    }

    @Test
    fun testMultiplication() {
        val result = calculator.performOperation(4.0, 2.0, "*")
        assertEquals("8.0", result)
    }

    @Test
    fun testDivisionByZero() {
        val result = calculator.performOperation(4.0, 0.0, "/")
        assertEquals("Error", result)
    }

    @Test
    fun testUnknownOperation() {
        val result = calculator.performOperation(2.0, 3.0, "unknown")
        assertEquals("", result)
    }

    // Add more tests for onButtonClick if necessary
}