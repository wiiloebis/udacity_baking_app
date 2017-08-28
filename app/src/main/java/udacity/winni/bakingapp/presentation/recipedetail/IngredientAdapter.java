package udacity.winni.bakingapp.presentation.recipedetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.IngredientVM;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<IngredientVM> ingredientVMs;

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient)
        TextView tvIngredient;

        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        @BindView(R.id.tv_measure)
        TextView tvMeasure;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final IngredientVM ingredientVM) {
            tvIngredient.setText(ingredientVM.getIngredient());
            tvQuantity.setText(""+ingredientVM.getQuantity());
            tvMeasure.setText(ingredientVM.getMeasure());
        }
    }

    public IngredientAdapter(List<IngredientVM> ingredients) {
        this.ingredientVMs = ingredients;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_recipe_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IngredientViewHolder) holder).bindData(ingredientVMs.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return ingredientVMs.size();
    }

    public void addData(List<IngredientVM> ingredients) {
        ingredientVMs.addAll(ingredients);
        notifyDataSetChanged();
    }

    public void clearData() {
        ingredientVMs.clear();
        notifyDataSetChanged();
    }

    public void resetData(List<IngredientVM> ingredients) {
        ingredientVMs.clear();
        ingredientVMs.addAll(ingredients);
        notifyDataSetChanged();
    }

    public List<IngredientVM> getData() {
        return ingredientVMs;
    }
}
