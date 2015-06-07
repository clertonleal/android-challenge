package clertonleal.com.simpleflickr.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import clertonleal.com.simpleflickr.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void refresh_shots() {
        expandMenu();
        onView(ViewMatchers.withText("Atualizar")).perform(click());
    }

    private void expandMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    }

    @Test
    public void open_shot_detail() {
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(android.R.id.list))
                .perform(RecyclerViewActions.scrollToPosition(9));
        onView(withId(android.R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));
    }

    @Test
    public void open_zoom_image() {
        open_shot_detail();
        onView(withId(R.id.image_photo)).perform(click());
    }

}
