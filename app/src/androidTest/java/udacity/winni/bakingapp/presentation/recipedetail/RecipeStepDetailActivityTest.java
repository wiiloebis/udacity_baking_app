package udacity.winni.bakingapp.presentation.recipedetail;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import android.support.test.rule.ActivityTestRule;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.StepVM;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by winniseptiani on 1/9/17.
 */
public class RecipeStepDetailActivityTest {

    @Mock
    StepVM stepVM;

    @Rule
    public ActivityTestRule<RecipeStepDetailActivity> mActivityTestRule = new ActivityTestRule<>(
        RecipeStepDetailActivity.class);

    @Test
    public void displayStepInstructionWhenInstructionItemClicked() {
        if (stepVM != null)
            onView(withText(stepVM.getDescription())).check(matches(isDisplayed()));
    }

    @Test
    public void displayStepInstructionView() {
        onView(withId(R.id.textview_instruction)).check(matches(isDisplayed()));
    }
}