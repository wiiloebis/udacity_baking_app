package udacity.winni.bakingapp.presentation.recipeingredients;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.IngredientVM;
import udacity.winni.bakingapp.presentation.recipedetail.IngredientAdapter;

/**
 * Created by winniseptiani on 11/8/17.
 */

public class RecipeIngredientDialog extends DialogFragment {

    private static final String RECIPE_INGREDIENTS = "RECIPE_INGREDIENTS";

    @BindView(R.id.rv_recipe_ingredient)
    RecyclerView rvRecipeIngredient;

    private IngredientAdapter recipeIngredientAdapter;

    private LinearLayoutManager linearLayoutManager;

    public static RecipeIngredientDialog newInstance(List<IngredientVM> ingredients) {
        RecipeIngredientDialog recipeIngredientDialog = new RecipeIngredientDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(RECIPE_INGREDIENTS, new ArrayList<>(ingredients));
        recipeIngredientDialog.setArguments(bundle);
        return recipeIngredientDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_recipe_ingredients, container, false);
        ButterKnife.bind(this, rootView);
        setMenuAdapter();
        attachDataToView();
        return rootView;
    }

    private void setMenuAdapter() {
        recipeIngredientAdapter = new IngredientAdapter(new ArrayList<>());
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        rvRecipeIngredient.setAdapter(recipeIngredientAdapter);
        rvRecipeIngredient.setLayoutManager(linearLayoutManager);
    }

    private void attachDataToView() {
        Bundle bundle = getArguments();
        List<IngredientVM> ingredients = bundle.getParcelableArrayList(RECIPE_INGREDIENTS);
        recipeIngredientAdapter.addData(ingredients);
    }
}
