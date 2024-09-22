package iu.c323.fall2024.calculator

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.After
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>



    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)

    }

    @After
    fun tearDown() {
        scenario.close()

    }


    //Unit Tests

    @Test
    fun clearResetsDisplay() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.c_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("0")))
    }

    @Test
    fun additionOperation() {
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("5.0")))
    }
    @Test
    fun subtractionOperation() {
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1.0")))
    }

    @Test
    fun multiplicationOperation() {
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.multiply_button)).perform(click())
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("6.0")))
    }

    @Test
    fun divisionOperation() {
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.division_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1.0")))
    }

    @Test
    fun negateButton() {
        onView(withId(R.id.five_button)).perform(click())
        onView(withId(R.id.negate_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.input)).check(matches(withText("-5")))
    }

    @Test
    fun decimalButton() {
        onView(withId(R.id.decimal_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.input)).check(matches(withText("0.")))
    }


    @Test
    fun percentButton() {
        onView(withId(R.id.five_button)).perform(click())
        onView(withId(R.id.percent_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.input)).check(matches(withText("0.05")))
    }


    @Test
    fun cosButton(){
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.cos_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1.0")))
    }

    @Test
    fun lnButton(){
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.ln_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("0.0")))
    }





    //Instrumented Tests

    @Test
    fun showsDisplayOnLaunch() {
        onView(withId(R.id.input)).check(matches(withText("0")))

    }

    @Test
    fun buttonClickUpdatesDisplay() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1")))
    }

    @Test
    fun divisionByZero() {
        onView(withId(R.id.six_button)).perform(click())
        onView(withId(R.id.division_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("Error")))
    }

    @Test
    fun multipleOperations(){
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("3.0")))
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("6.0")))
    }

    @Test
    fun rotationRetainsState() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("2")))
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.input)).check(matches(withText("2")))
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("3.0")))
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(withId(R.id.input)).check(matches(withText("3.0")))
    }


    @Test
    fun rotationRetainsOperationState(){
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("2.0")))
    }

    @Test
    fun errorHandling(){
        onView(withId(R.id.six_button)).perform(click())
        onView(withId(R.id.division_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("Error")))
        onView(withId(R.id.six_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("6")))
    }

    @Test
    fun negativeLogHandling(){
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.negate_button)).perform(click())
        onView(withId(R.id.log_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("NaN")))
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1")))
    }

    @Test
    fun cosPerformedBeforeOperation(){
        scenario.onActivity { activity ->
            activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.cos_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("1.0")))
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("2.0")))
    }

    @Test
    fun negativeHandling(){
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.negate_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("-1")))
        onView(withId(R.id.equal_button)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("0.0")))
    }







}
