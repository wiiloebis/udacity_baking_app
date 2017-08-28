package udacity.winni.bakingapp.presentation.recipedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.StepVM;
import udacity.winni.bakingapp.presentation.recipegallery.RecipeMenuAdapter;

/**
 * Created by winniseptiani on 7/8/17.
 */

public class RecipeMenuFragment extends Fragment implements RecipeMenuAdapter
    .OnItemClickedListener {

    public static final String RECIPE_STEP_KEY = "RECIPE_STEP_KEY";

    private RecipeDetailActivity recipeDetailActivity;

    private RecipeMenuAdapter recipeAdapter;

    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.rv_recipe_menu)
    RecyclerView rvRecipeMenu;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipeDetailActivity = (RecipeDetailActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_menu, container, false);
        ButterKnife.bind(this, view);
        setMenuAdapter();
        return view;
    }

    private void setMenuAdapter() {
        recipeAdapter = new RecipeMenuAdapter(new ArrayList<>(), this);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        rvRecipeMenu.setAdapter(recipeAdapter);
        rvRecipeMenu.setLayoutManager(linearLayoutManager);
    }

    public void displayRecipeMenu(List<StepVM> stepVMs) {
        recipeAdapter.addData(stepVMs);
    }

    @Override
    public void onRecipeStepClicked(StepVM step, int position) {
        recipeAdapter.setSelectedPosition(position);
        recipeDetailActivity.onRecipeStepClicked(step);
    }

    @Override
    public void onRecipeIngredient() {
        recipeDetailActivity.onRecipeIngredient();
    }
}
