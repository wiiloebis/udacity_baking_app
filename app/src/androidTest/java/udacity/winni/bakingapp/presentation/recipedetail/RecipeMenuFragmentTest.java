package udacity.winni.bakingapp.presentation.recipedetail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.StepVM;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by winniseptiani on 30/8/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeMenuFragmentTest {

    @Mock
    StepVM stepVM;

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mActivityTestRule = new ActivityTestRule<>(
        RecipeDetailActivity.class);

    @Before
    public void init(){
        mActivityTestRule.getActivity()
            .getSupportFragmentManager().findFragmentById(R.id.list_recipe_menu);
    }

    @Test
    public void clickRecipeRecyclerViewItemOpensARecipeDialog() {
        if(stepVM != null) {
            onView(withId(R.id.rv_recipe_menu))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

            onView(withText("Recipe Ingredients")).check(matches(isDisplayed()));
        }
    }

    @Test
    public void displayRecipeIngredientsMenuWhenClickRecyclerViewItem() {
        if(stepVM != null) {
        onView(ViewMatchers.withId(R.id.rv_recipe_menu))
            .perform(RecyclerViewActions.scrollToPosition(0));

            String firstRowText =
                mActivityTestRule.getActivity().getResources()
                    .getString(R.string.recipe_ingredients);
            onView(withText(firstRowText)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void displayNoContentMessageWhenClickRecyclerViewItem() {
        if(stepVM == null) {
            String noContent =
                mActivityTestRule.getActivity().getResources()
                    .getString(R.string.no_content);
            onView(withText(noContent)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void displayStepInstructionWhenInstructionItemClicked() {
        if(stepVM != null) {
            onView(ViewMatchers.withId(R.id.rv_recipe_menu))
                .perform(RecyclerViewActions.scrollToPosition(1));

            onView(withText(stepVM.getDescription())).check(matches(isDisplayed()));
        }
    }
}