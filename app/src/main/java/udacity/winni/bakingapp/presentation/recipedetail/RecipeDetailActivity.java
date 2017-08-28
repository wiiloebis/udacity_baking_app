package udacity.winni.bakingapp.presentation.recipedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.List;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.model.StepVM;
import udacity.winni.bakingapp.presentation.recipeingredients.RecipeIngredientDialog;

/**
 * Created by winniseptiani on 7/8/17.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPES = "RECIPES";

    private ActionBar actionBar;

    private RecipeVM recipeVM;

    private RecipeMenuFragment recipeMenuFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        displayRecipeMenu();
        setScreenActionBar();
    }

    private void displayRecipeMenu() {
        recipeVM = getIntent().getParcelableExtra(RECIPES);
        List<StepVM> steps = recipeVM.getSteps();
        RecipeMenuFragment recipeMenuFragment = (RecipeMenuFragment)
            getSupportFragmentManager()
                .findFragmentById(R.id.list_recipe_menu);
        recipeMenuFragment.displayRecipeMenu(steps);
    }

    private void setScreenActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(recipeVM.getName());
    }

    public void onRecipeStepClicked(StepVM step) {
        RecipeMenuDetailFragment recipeMenuDetailFragment = (RecipeMenuDetailFragment)
            getSupportFragmentManager()
                .findFragmentById(R.id.details_recipe_menu);
        if (recipeMenuDetailFragment == null) {
            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(RecipeStepDetailActivity.RECIPE_NAME, recipeVM.getName());
            intent.putExtra(RecipeStepDetailActivity.RECIPE_STEP, step);
            startActivity(intent);
        } else {
            recipeMenuDetailFragment.setVideoUrl(step.getVideoURL());
            recipeMenuDetailFragment.displayInstruction(step);
            recipeMenuDetailFragment.restartPlayer();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRecipeIngredient() {
        RecipeIngredientDialog recipeIngredientDialog = RecipeIngredientDialog
            .newInstance(recipeVM.getIngredients());
        recipeIngredientDialog.show(getSupportFragmentManager(), "recipe_ingredient_dialog");
    }
}
