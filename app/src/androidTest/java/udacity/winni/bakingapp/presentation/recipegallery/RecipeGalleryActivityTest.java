package udacity.winni.bakingapp.presentation.recipegallery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import udacity.winni.bakingapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by winniseptiani on 30/8/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeGalleryActivityTest {

    @Rule
    public ActivityTestRule<RecipeGalleryActivity> mActivityTestRule = new ActivityTestRule<>(
        RecipeGalleryActivity.class);

    @Test
    public void clickRecyclerViewItemOpensGalleryMenuActivity() {
        onView(withId(R.id.rv_recipe))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
    }

    @Test
    public void displayBakingActionBarTitle() {
        onView(withText("Baking Time")).check(matches(isDisplayed()));
    }

    @Test
    public void displayCheeseCakeWhenScrollToPosition3() {
        onView(ViewMatchers.withId(R.id.rv_recipe))
            .perform(RecyclerViewActions.scrollToPosition(3));

        onView(withText("Cheesecake")).check(matches(isDisplayed()));
    }

}