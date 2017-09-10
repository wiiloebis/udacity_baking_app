package udacity.winni.bakingapp.presentation.recipegallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.ApplicationComponent;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipedetail.RecipeDetailActivity;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeGalleryActivity extends AppCompatActivity implements RecipeGalleryContract.View,
    RecipeAdapter.OnItemClickedListener {

    private static final String RECIPES = "RECIPES";

    private RecipeGalleryPresenter recipeGalleryPresenter;

    private RecipeAdapter recipeAdapter;

    @BindView(R.id.rv_recipe)
    RecyclerView rvRecipe;

    @BindView(R.id.layout_failed)
    TextView tvFailMessage;

    GridLayoutManager gridLayoutManager;

    private ProgressDialog progressDialog;

    private ActionBar actionBar;

    private List<RecipeVM> recipes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_gallery);
        ButterKnife.bind(this);
        recipeGalleryPresenter = new RecipeGalleryPresenter(this,
            ApplicationComponent.provideGetRecipes(),
            ApplicationComponent.provideAddFavoriteRecipes(),
            new RecipeMapper());
        setRecipeAdapter();
        handleDataFromState(savedInstanceState);
        setScreenActionBar();
    }

    private void handleDataFromState(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(RECIPES)) {
            recipeGalleryPresenter.getRecipes();
        } else {
            recipes = savedInstanceState.getParcelableArrayList(RECIPES);
            recipeAdapter.resetData(recipes);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(RECIPES, new ArrayList<>(recipeAdapter.getData()));
        super.onSaveInstanceState(outState);
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

    private void setScreenActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void setRecipeAdapter() {
        recipeAdapter = new RecipeAdapter(new ArrayList<>(), this);
        boolean isPhone = getResources().getBoolean(R.bool.is_phone);
        int columnNumber;

        if (isPhone) {
            columnNumber = 1;
        } else {
            columnNumber = 3;
        }

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), columnNumber);
        rvRecipe.setAdapter(recipeAdapter);
        rvRecipe.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onGetRecipeSuccess(List<RecipeVM> recipes) {
        this.recipes = recipes;
        rvRecipe.setVisibility(View.VISIBLE);
        tvFailMessage.setVisibility(View.GONE);
        recipeAdapter.addData(this.recipes);
    }

    @Override
    public void onGetRecipeFailed() {
        rvRecipe.setVisibility(View.GONE);
        tvFailMessage.setVisibility(View.VISIBLE);
        tvFailMessage.setText(getString(R.string.fetch_recipes_failed));
    }

    @Override
    public void onAddFavoriteRecipeSuccess() {

    }

    @Override
    public void onAddFavoriteRecipeFailed() {

    }

    @Override
    public void showLoadingBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.show();
    }

    @Override
    public void hideLoadingBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClicked(RecipeVM recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.RECIPES, recipe);
        startActivity(intent);
    }

    @Override
    public void onItemLongClicked(RecipeVM recipeVM) {
        recipeGalleryPresenter.addAsFavoriteRecipe(recipeVM);
    }
}
